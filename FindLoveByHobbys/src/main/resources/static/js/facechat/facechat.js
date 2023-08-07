/**
 * 화상 채팅에 필요한 js
 */

document.addEventListener('DOMContentLoaded', () => {

	// 신고 여부
	let reportForm = document.querySelector('form#reportForm');
	let report = document.querySelector('input#report');
	let btnReport = document.querySelector('button#btnReport');
	let reports = document.querySelector('input#reports');

	// 화면 공유/음성 송출을 해제하는 버튼
	const btnScreen = document.querySelector('button#btnScreen');
	// 화면이 송출 중인지 여부에 대해 표시(false=쉬는중 / true=송출중)
	let isScreenSharing = true;


	// 오퍼 연결을 전달하는 버튼
	let btnOffer = document.querySelector('button#btnOffer');

	// 방번호
	let roomId = document.querySelector('input#roomId').value;
	btnOffer.addEventListener('click', createOffer);
	let connectioncheck = false;

	// MyFace = 내 카메라, peerFace = 상대 카메라, myStream = 내 카메라에서 나오는 영상 스트림
	let myFace = document.getElementById("myFace");
	let peerFace = document.querySelector('video#peerFace');
	let myStream;
	let audios = document.querySelector('input#audios');
	// wss 주소로 웹소켓을 연결(채팅에는 ws로도 가능하나, RTC는 반드시 Security 연결 필요)
	let wss = 'wss://' + location.host + `/facechat?roomid=${roomId}`;


	let conn = new WebSocket(wss);

	// RTC 객체 생성 후 해당 객체에 Stun 서버 주소 전달.
	let configuration = {
		"iceServers": [{
			"url": "stun:stun.l.google.com:19302"
		}]
	}
	let myPeerConnection = new RTCPeerConnection(configuration);

	// mediaDevices 를 통해 영상, 음성 기기들을 찾고, 거기서 기기를 가져옴.
	async function getMedia() {
		try {
			myStream = await navigator.mediaDevices.getUserMedia({
				audio: true,
				video: true
			});

			// 불러온 카메라를 HTML에 연결함. 이때부터 카메라에 담기는 화면이 웹에 표시됨.
			myFace.srcObject = myStream;
		} catch (e) {
			console.log("Media Link Error");
		}
	}

	// 오퍼 생성 시 해당 함수가 호출됨.
	async function createOffer(e) {
		e.preventDefault();
		if (connectioncheck == true) {

			alert('이미 연결된 상대가 있습니다.');
			return;

		}
		console.log("Offer Send");

		// 아까 작성한 카메라 연결 함수 호출.
		await getMedia();

		// RTC Connection에 내 화면에서 송출되는 영상, 소리를 담음.
		await myStream.getTracks().forEach((track) => myPeerConnection.addTrack(track, myStream));

		// RTC 객체 생성 -> RTC 커넥션에 트랙(영상,소리) 담기 -> Offer 전달(SDP 메시지 전달)
		let offer = await myPeerConnection.createOffer();
		console.log("오퍼 전달")
		// 이제 send 로 오퍼 전달.

		// TODO 여기 await 지움.
		send({
			event: "offer",
			data: offer
		})
		console.log("Offer send Success")
		await myPeerConnection.setLocalDescription(offer);
	}




	// 상대가 나를 연결할 수 있는 방법들을 만들어 전달.
	myPeerConnection.onicecandidate = function(event) {
		console.log("candidate send");
		send({
			event: "candidate",
			data: event.candidate
		})
	}
	// 연결이 되서 피어의 스트림이 내 RTC객체에 등록되면 자동 호출.
	myPeerConnection.addEventListener("addstream", handleAddStream);
	function handleAddStream(data) {
		console.log("Streaming Data Receive");

		// 연결된 피어의 스트림을 웹의 상대화면 부분에 표시
		peerFace.srcObject = data.stream;

		// 녹음 시작
		audiorecording();


		// 성공적으로 Facechatroom이 만들어졌다고 판단되어 방을 만드는 api 호출.

	}

	// 소켓 연결 시 실행할 함수
	conn.onopen = function() {
		console.log("웹소켓 연결 완료.");
	};

	// 소켓에서 메시지 수신 시 실행할 함수
	conn.onmessage = async function(msg) {
		let content = JSON.parse(msg.data);

		if (content.event == 'report') {

			console.log('report');
			reports.value = 1;
			return;
		}
		connectioncheck = true;
		if (content.event == "offer") {
			console.log("Offer Receive");

			// Offer가 오면 Offer를 RemoteDescription에 등록함.
			let offer = content.data;
			await myPeerConnection.setRemoteDescription(offer);
			// 수신한 쪽에도 내 카메라에서 송출되면 화면 연결.
			await getMedia();
			await myStream.getTracks().forEach((track) => myPeerConnection.addTrack(track, myStream));
			// answer 생성 후 LocalDescription에 전달.
			let answer = await myPeerConnection.createAnswer();
			await myPeerConnection.setLocalDescription(answer);
			console.log("Answer Send");
			send({
				event: "answer",
				data: answer
			})
		} else if (content.event == "answer") {

			// answer를 수신한 경우, remoteDescription에 전달.
			console.log("Answer Receive");
			answer = content.data;
			await myPeerConnection.setRemoteDescription(answer);
		} else if (content.event == "candidate") {
			console.log("candidate Receive");
			// RemoteDescription에 연결된 상대와 나의 연결방식을 결정.
			await myPeerConnection.addIceCandidate(content.data);
		}
	}

	// 웹소켓 서버로 메시지 전달하는 함수
	function send(message) {
		conn.send(JSON.stringify(message));
	}












	// 화면 공유 중지 버튼 클릭 이벤트 처리 ( 7월 31일 밤 추가 TODO )
	btnScreen.addEventListener('click', async (e) => {

		e.preventDefault();

		// 스트리밍 자체가 시작되기 전인 경우
		if (!myStream) return;

		// 스트리밍 되는 게 없음
		if (myStream.getVideoTracks().length === 0) return;

		if (!isScreenSharing) {
			console.log("Video show");
			btnScreen.innerHTML = '화면 숨김';
			myStream.getVideoTracks()[0].enabled = true; // 원래 화면 송출 상태로 변경
			myStream.getAudioTracks()[0].enabled = true; // 음성 송출 원래 상태로 변경
			myFace.srcObject = myStream;
		} else {
			console.log("Video hide");
			btnScreen.innerHTML = '화면 표시';
			myStream.getVideoTracks()[0].enabled = false; // 화면 송출 중지
			myStream.getAudioTracks()[0].enabled = false; // 음성 송출 중지
			myFace.srcObject = null;
		}

		isScreenSharing = !isScreenSharing;








	});


	let audioContext;
	let mediaStreamSource;
	let recorder;
	let audioChunks = [];
	let recordedBlob;
	let fileuri = '';
	async function audiorecording() {

		try {
			const stream = await navigator.mediaDevices.getUserMedia({ audio: true });
			audioContext = new (window.AudioContext || window.webkitAudioContext)();
			mediaStreamSource = audioContext.createMediaStreamSource(stream);

			// 스트림을 녹음할 노드 생성
			recorder = new MediaRecorder(stream);
			console.log(recorder);

			recorder.ondataavailable = (event) => {
				if (event.data.size > 0) {
					audioChunks.push(event.data);
					console.log("데이터추가");
				}
			};

			recorder.onstop = async () => {
				console.log("stop 동작");
				console.log(audioChunks);
				// 녹음이 완료된 후에 녹음된 음성 데이터를 처리하거나 서버에 업로드할 수 있습니다.
				recordedBlob = new Blob(audioChunks, { type: 'audio/wav' });
				console.log(recordedBlob);
				// 여기에서 녹음된 음성 데이터를 처리하거나 서버에 업로드할 수 있습니다.

				console.log(recordedBlob);
				const formData = new FormData();
				formData.append('audioFile', recordedBlob);

				try {
					let reqUrl = `/faceapi/report/${roomId}`;
					let response = await axios.post(reqUrl, formData, {
						headers: {
							'Content-Type': 'multipart/form-data'
						}
						
					});
					console.log(response);
					console.log(response.data);
					let result = response.data;
					fileuri = response.data;
					audios.value = result;
					alert('오디오 저장 성공!');
					

					report.value = 1;





					reportForm.method = 'post';
					reportForm.action = `/facechat/report`;
					reportForm.submit();

				} catch (error) {

					console.log(error);

				}
			};

			recorder.start();
		} catch (err) {
			console.error('마이크 접근 권한을 얻지 못했습니다.', err);
		}
	}

	btnReport.addEventListener('click', async (e) => {

		e.preventDefault();

		if (connectioncheck == false) {

			alert('아직 연결된 상대가 없어 신고할 수 없습니다.');
			return;

		}

		let answerRes = confirm('정말로 신고하시겠습니까? 신고하시면 곧바로 통화가 종료됩니다.');

		if (!answerRes) {

			return;

		}

		// recordedBlob 레코드 파일.
		// 녹음 중단
		console.log(recorder);
		recorder.stop();

		send({
			event: 'report',
			data: 'report'

		})




	})

	setInterval(() => {

		if (reports.value == 1) {

			reportForm.method = 'get';
			reportForm.action = '/facechat/report';
			reportForm.submit();
			alert('상대 유저로부터 신고를 당하셔서 통화가 강제로 종료됩니다.');

		}

	}, 3000);


})

