document.addEventListener("DOMContentLoaded", () => {
    const myNickname = document.querySelector('div#myNickname').getAttribute('value');
    const otherNickname = document.querySelector('div#otherNickname').getAttribute('value');
    const btnSend = document.querySelector('button#button-send');
    const roomId = document.querySelector('div#roomId').getAttribute('value');
    const btnOut = document.querySelector('div#btnOut');
    const chatOutForm = document.querySelector('form#chatOutForm');
    const btnFaceChat = document.querySelector('span#btnFaceChat');
    const faceChatForm = document.querySelector('form#faceChatForm')
    const maleId = document.querySelector('div#maleID').getAttribute('value');
    const femaleId = document.querySelector('div#femaleID').getAttribute('value');
    const goChat = document.querySelector('form#goChat');
    const myId = document.querySelector('div#myId').getAttribute('value');
    let mySex = '';
    if (maleId == myId) {
        mySex = 1;
    } else {
        mySex = 2;
    }

    console.log('방번호' + roomId);

    // 웹소켓 생성
    let websocket = new WebSocket('wss://' + location.host + `/ws/chat?roomId=${roomId}`);

    websocket.onmessage = onMessage;
    websocket.onopen = onOpen;
    websocket.onclose = onClose;

    btnOut.addEventListener('click', () => {
        if (confirm('채팅방을 나가시겠습니까?')) {
            onClose();
            goChat.submit();
        }
    });

    btnFaceChat.addEventListener('click', () => {
        if (confirm('화상채팅 요청을 보내시겠습니까?')) {
            sendFaceChat();

        }
    });

    function sendFaceChat() {
        const Data = {
            "message": `${myNickname}님에게서 화상채팅 요청이 왔어요!`,
            "textType": "faceChat",
            "nickname": myNickname
        }

        const jsonData = JSON.stringify(Data);
        websocket.send(jsonData);
        msg.value = '';
    }


    // 보내기 버튼 이벤트 리스너
    btnSend.addEventListener('click', () => {
        send();
    });


    function send() {
        let msg = document.getElementById("msg");
        console.log(myNickname + ":" + msg.value);
        const curDate = new Date();

        const Data = {
            "message": msg.value,
            "createdTime": curDate,
            "nickname": myNickname,
            "textType": "message"
        }

        const jsonData = JSON.stringify(Data);
        websocket.send(jsonData);
        msg.value = '';

        // chatCount 올려주기
        const url = "/chatCount/upCount/" + roomId + "/" + maleId + "/" + femaleId + "/" + mySex;

        axios.get(url)
            .catch((error) => console.log(error));
    }

    //채팅창에서 나갔을 때
    function onClose(evt) {
        let str = {
            "message": +`${myNickname}님이 방을 나가셨습니다.`,
            "nickname": myNickname,
            "textType": "bye"
        };

        let jsonData = JSON.stringify(str);

        websocket.send(jsonData);
        websocket.close();

        // chatCount 올려주기
        const url = "/chatCount/upCount/" + roomId + "/" + maleId + "/" + femaleId + "/" + mySex;

        axios.get(url)
            .catch((error) => console.log(error));
    }

    //채팅창에 들어왔을 때
    function onOpen(evt) {
        console.log(roomId + '로 접속함');
    }

    function onMessage(msg) {
        console.log('onMessage() 호출됨');
        let msgArea = document.querySelector('ul#msgArea');

        // 데이터를 담음 데이터에는 send()에서 담은 nickname과 메세지, 보낸시간이 들어있음
        let data = JSON.parse(msg.data);
        let sessionId = data.nickname;
        let cur_session = myNickname;

        switch (data.textType) {
            case 'bye' :
                if (sessionId == cur_session) {
                    bye(data);
                }
                break;
            case 'message':
                if (sessionId == cur_session) {
                    myMessage(data);
                } else {
                    otherMessage(data);
                }
                break;
            case 'faceChat':
                if (sessionId != cur_session) {
                    callFaceChat(data);
                }
                break;
            case 'accept':
				if (sessionId != cur_session) {
                    acceptFaceChat(data);
                } else {
					
					setTimeout(()=>{
						console.log("입장");
						
					},2000);
					
					faceChatForm.method = 'get';
					faceChatForm.submit();
				}
                
                break;
            case 'refuse':
                if (sessionId != cur_session) {
                    refuseMessage(data);
                }
        }

        function refuseMessage(data) {
            alert(data.nickname + '님이 화상채팅을 거절하셨습니다.');
        }

        function acceptFaceChat() {
			
            faceChatForm.submit();
        }

        function callFaceChat(data) {
            if (confirm(data.message)) {
                // 화상채팅 연결 메세지 뿌려주기
                let data = {"textType": "accept"}
                websocket.send(JSON.stringify(data));
            } else {
                // 상대방 거절메세지
                let data = {
                    "textType": "refuse",
                    "nickname": myNickname
                }
                websocket.send(JSON.stringify(data));
            }
        }

        function otherMessage(data) {
            let time = formatDateTime(data.createdTime);
            let str = "<li>";
            str += '<div class="message-data">';
            str += `<span class=message-data-name">${otherNickname}</span>`;
            str += `<span class="message-data-time">${time}</span> &nbsp; &nbsp;`;
            str += '</div>';
            str += '<div class="message other-message float-right">';
            str += `${data.message}`;
            str += '</div>';
            str += '</li>';
            msgArea.innerHTML += str;

            // 실시간으로 채팅을 보고 있음으로 chatCount를 내림
            const url = "/chatcount/downCount/" + roomId + "/" + mySex;

            axios.get(url)
                .catch((error) => console.log(error));
        }

        function myMessage(data) {
            let time = formatDateTime(data.createdTime);
            let str = '<li class="align-right">';
            str += '<div class="message-data">';
            str += `<span class="message-data-time">${time}</span> &nbsp; &nbsp;`;
            str += `<span class="message-data-name">${myNickname}</span>`;
            str += '</div>';
            str += '<div class="message my-message">';
            str += `${data.message}`;
            str += '</div>';
            str += '</li>';
            msgArea.innerHTML += str;

            const reqUrl = `/api/chatting/${roomId}/${myNickname}/${data.message}`;

            axios
                .get(reqUrl)
                .then((response) => {
                    console.log('response data = ' + response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        }

        function bye(data) {
            alert('상대방이 대화방을 나가셨습니다.');
            websocket.close();
            chatOutForm.submit();
        }

    }

    function formatDateTime(dateTimeString) {
        const options = {
            hour: 'numeric',
            minute: 'numeric',
            hour12: true,
            hourCycle: 'h23',
            locale: 'ko-KR',
        }
        const date = new Date(dateTimeString);
        return new Intl.DateTimeFormat('ko-KR', options).format(date);
    }
});