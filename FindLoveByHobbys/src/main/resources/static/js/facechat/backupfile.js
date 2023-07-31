/**
 * 
 */

 	// 시그널링 서버로부터 메시지를 수신하는 이벤트 핸들러
	wss.onmessage = (event) => {
		const message = JSON.parse(event.data);
		if (message.type === 'offer') {
			// 상대방으로부터 Offer SDP를 받은 경우
			handleOffer(message);
		} else if (message.type === 'answer') {
			// 상대방으로부터 Answer SDP를 받은 경우
			handleAnswer(message);
		} else if (message.type === 'candidate') {
			// 상대방으로부터 ICE candidate를 받은 경우
			handleCandidate(message);
		}
	};

	// WebRTC 연결 시작
	function startConnection() {
		const configuration = { iceServers: [{ urls: 'stun:stun.l.google.com:19302' }] };
		peerConnection = new RTCPeerConnection(configuration);

		// 로컬 비디오 스트림을 가져와서 원격 피어로 전송
		navigator.mediaDevices.getUserMedia({ video: true, audio: true })
			.then((stream) => {
				localStream = stream;
				localVideo.srcObject = stream;
				stream.getTracks().forEach((track) => peerConnection.addTrack(track, stream));
			})
			.catch((error) => {
				console.error('비디오 스트림 가져오기 실패:', error);
			});

		// 원격 피어로부터 스트림을 받을 때의 이벤트 핸들러
		peerConnection.ontrack = (event) => {
			remoteVideo.srcObject = event.streams[0];
		};

		// ICE candidate를 생성하여 상대방에게 전송
		peerConnection.onicecandidate = (event) => {
			if (event.candidate) {
				const message = JSON.stringify({ type: 'candidate', candidate: event.candidate });
				wss.send(message);
			}
		};
	}

	// Offer SDP를 생성하여 시그널링 서버에 전송
	function sendOffer() {
		peerConnection.createOffer()
			.then((offer) => {
				return peerConnection.setLocalDescription(offer);
			})
			.then(() => {
				const message = JSON.stringify({ type: 'offer', offer: peerConnection.localDescription });
				wss.send(message);
			})
			.catch((error) => {
				console.error('Offer SDP 생성 실패:', error);
			});
	}

	// Answer SDP를 생성하여 시그널링 서버에 전송
	function sendAnswer() {
		peerConnection.createAnswer()
			.then((answer) => {
				return peerConnection.setLocalDescription(answer);
			})
			.then(() => {
				const message = JSON.stringify({ type: 'answer', answer: peerConnection.localDescription });
				wss.send(message);
			})
			.catch((error) => {
				console.error('Answer SDP 생성 실패:', error);
			});
	}

	// 시그널링 서버로부터 받은 Offer SDP 처리
	function handleOffer(message) {
		const offerSDP = new RTCSessionDescription(message.offer);
		peerConnection.setRemoteDescription(offerSDP)
			.then(() => {
				sendAnswer();
			})
			.catch((error) => {
				console.error('Offer SDP 처리 실패:', error);
			});
	}

	// 시그널링 서버로부터 받은 Answer SDP 처리
	function handleAnswer(message) {
		const answerSDP = new RTCSessionDescription(message.answer);
		peerConnection.setRemoteDescription(answerSDP)
			.catch((error) => {
				console.error('Answer SDP 처리 실패:', error);
			});
	}

	// 시그널링 서버로부터 받은 ICE candidate 처리
	function handleCandidate(message) {
		const candidate = new RTCIceCandidate(message.candidate);
		peerConnection.addIceCandidate(candidate)
			.catch((error) => {
				console.error('ICE candidate 처리 실패:', error);
			});
	}

	// 연결 시작
	startConnection();