/**
 * 화상 채팅에 필요한 js
 */

document.addEventListener('DOMContentLoaded', () => {

	let btnOffer = document.querySelector('button#btnOffer');
	let roomId = document.querySelector('input#roomId').value;
	btnOffer.addEventListener('click', createOffer);
	// 미디어와 관련된 변수를 선언해요 myFace는 element를 받고 myStream엔 영상 내용을 담을거에요
	let myFace = document.getElementById("myFace");
	let peerFace = document.querySelector('video#peerFace');
	let myStream;
	// 일단 소켓 연결을 해봐요
	let wss = 'wss://' + location.host + `/facechat?roomid=${roomId}`;


	let conn = new WebSocket(wss);
	// 먼저 RTC객체를 만들어요 구글 stun 서버로 부터 나의 정보를 가져올게요
	let configuration = {
		"iceServers": [{
			"url": "stun:stun.l.google.com:19302"
		}]
	}
	let myPeerConnection = new RTCPeerConnection(configuration);

	//미디어 내용을 받기 시작하는 함수에요
	async function getMedia() {
		try {
			myStream = await navigator.mediaDevices.getUserMedia({
				audio: true,
				video: true
			});
			myFace.srcObject = myStream;
		} catch (e) {
			console.log("미디어를 가져오는 중 에러가 발생했어요");
		}
	}

	// '오퍼를 생성해요'라는 버튼을 눌렀을 때 이 메서드가 실행되요
	async function createOffer() {
		console.log("오퍼보내기");

		//일단 카메라를 킬게요 키면서 myStream에도 미디어 정보를 담아와요
		await getMedia();

		// getMedia에서 가져온 audio, video 트랙을 myPeerConnection에 등록해요
		await myStream.getTracks().forEach((track) => myPeerConnection.addTrack(track, myStream));

		// RTC객체도 만들었고 나의 미디어도 RTC객체에 담았으니 오퍼를 생성해볼게요
		let offer = await myPeerConnection.createOffer();
		console.log("오퍼 전달")
		// 이제 send함수를 통해 소켓으로 나의 offer를 전송해 볼게요
		await send({
			event: "offer",
			data: offer
		})
		console.log("오퍼 전송 완료")
		await myPeerConnection.setLocalDescription(offer);
	}




	// 내가 나의 캔디데이트(너가 나를 연결하는 방법들의 후보)를 등록하면(즉 로컬디스크립션을 설정하면)
	// 트리거 되는 메서드에요
	myPeerConnection.onicecandidate = function(event) {
		console.log("캔디데이트 전송");
		send({
			event: "candidate",
			data: event.candidate
		})
	}
	// 연결이 되서 피어의 스트림이 내 RTC객체에 등록되면 시작되는 메서드에요
	myPeerConnection.addEventListener("addstream", handleAddStream);
	function handleAddStream(data) {
		console.log("스트리밍 데이터 수신");
		
		peerFace.srcObject = data.stream;
	}

	// 소켓이 연결되었을 때 실행할 콜백함수에요
	conn.onopen = function() {
		console.log("웹소켓 연결 완료.");
	};

	// 소켓에서 메세지를 받아왔을 때 실행할 콜백함수에요
	conn.onmessage = async function(msg) {
		let content = JSON.parse(msg.data);
		if (content.event == "offer") {
			console.log("오퍼 수신");
			// 오퍼가 오면 가장먼저 그 오퍼를 리모트 디스크립션으로 등록해줘요
			let offer = content.data;
			await myPeerConnection.setRemoteDescription(offer);
			// 받는 쪽에서도 자신의 미디어를 켜줘요
			await getMedia();
			await myStream.getTracks().forEach((track) => myPeerConnection.addTrack(track, myStream));
			// 이제 앤서를 보내요
			let answer = await myPeerConnection.createAnswer();
			await myPeerConnection.setLocalDescription(answer);
			console.log("앤서를 보낼게요");
			send({
				event: "answer",
				data: answer
			})
		} else if (content.event == "answer") {
			console.log("앤서가 왔어요");
			answer = content.data;
			await myPeerConnection.setRemoteDescription(answer);
		} else if (content.event == "candidate") {
			console.log("캔디데이트가 왔어요");
			// 이 메서드를 통해 리모트 디스크립션에 설정되어있는 피어와의 연결방식을 결정해요
			await myPeerConnection.addIceCandidate(content.data);
		}
	}

	// 앞으로 소켓으로 메세지를 보낼 땐 이 함수를 쓸 생각이에요
	function send(message) {
		conn.send(JSON.stringify(message));
	}

	


})

