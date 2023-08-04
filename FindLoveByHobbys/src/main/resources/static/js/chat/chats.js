document.addEventListener("DOMContentLoaded", ()=>{
    const myNickname = document.querySelector('div#myNickname').getAttribute('value');
    const otherNickname = document.querySelector('div#otherNickname').getAttribute('value');
    const btnSend = document.querySelector('button#button-send');
    const msgArea = document.querySelector('ul#msgArea');
    const roomId = document.querySelector('div#roomId').getAttribute('value');
    console.log('방번호'+roomId);

    // 보내기 버튼 이벤트 리스너
    btnSend.addEventListener('click', ()=>{
        send();
    });

    // 웹소켓 생성
    let websocket = new WebSocket('wss://' + location.host + `/ws/chat?roomId=${roomId}`);

    websocket.onmessage = onMessage;
    websocket.onopen = onOpen;
    websocket.onclose = onClose;

    /*function disconnect(){

        websocket.send(myNickname + "님이 퇴장하셨습니다.");
        websocket.close();
    }*/
    function send(){
        let msg = document.getElementById("msg");
        console.log(myNickname + ":" + msg.value);
        const curDate = new Date();

        const Data = {
            "message" :  msg.value,
            "createdTime" : curDate,
            "nickname" : myNickname
        }

        const jsonData = JSON.stringify(Data);
        websocket.send(jsonData);
        msg.value = '';
    }

    //채팅창에서 나갔을 때
    function onClose(evt) {
        let str = myNickname + ": 님이 방을 나가셨습니다.";
        websocket.send(str);
    }

    //채팅창에 들어왔을 때
    function onOpen(evt) {
        console.log(roomId+'로 접속함');
    }

    function onMessage(msg) {
        console.log('onMessage() 호출됨');

        // 데이터를 담음 데이터에는 send()에서 담은 nickname과 메세지, 보낸시간이 들어있음
        let data = JSON.parse(msg.data);
        let sessionId = data.nickname;
        let message = data.message;
        let time = formatDateTime(data.createdTime);

        // myNickname
        let cur_session = myNickname;



        //로그인 한 클라이언트와 타 클라이언트를 분류하기 위함
        if(sessionId == cur_session){
            let str = "<li className=\"clearfix\" style='margin-bottom: -15px; height: 5px; font-size: 12px;'>";
            str += '<div className=\"message-data\" style="margin-bottom: -15px; height: 5px; font-size: 12px; text-align: right;">';
            str += `<span className=\"message-data-time\">${time}</span> &nbsp; &nbsp;`;
            str += `<span className=\"message-data-name\">${myNickname}</span>`;
            str += '</div>';
            str += '<div className=\"message other-message float-right\" style="color: white;\n' +
                '    padding: 8px 20px;\n' +
                '    line-height: 18px;\n' +
                '    font-size: 15px;\n' +
                '    border-radius: 7px;\n' +
                '    margin-bottom: 3px;\n' +
                '    margin-top: 30px;\n' +
                '    width: 57%; left: 306px; \n'  +

                '    position: relative;' +
                '    background: #86BB71">';
            str += `${message}`;
            str += '</div>';
            str += '</li>';
            msgArea.innerHTML += str;

            const reqUrl = `/api/chatting/${roomId}/${myNickname}/${message}`

            axios
                .get(reqUrl)
                .then((response) => {
                    console.log('response data = ' + response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
        else{
            let str = "<li className=\"clearfix\" style='margin-bottom: -15px; height: 5px; font-size: 12px;'>";
            str += '<div className=\"message-data\" style="margin-bottom: -15px; height: 5px; font-size: 12px;">';
            str += `<span className=\"message-data-name\">${myNickname}</span>`;
            str += `<span className=\"message-data-time\" style="padding-left: 6px;">${time}</span> &nbsp; &nbsp;`;
            str += '</div>';
            str += '<div className=\"message other-message float-right\" style="color: white;\n' +
                '    padding: 8px 20px;\n' +
                '    line-height: 18px;\n' +
                '    font-size: 15px;\n' +
                '    border-radius: 7px;\n' +
                '    margin-bottom: 3px;\n' +
                '    margin-top: 30px;\n' +
                '    width: 57%;\n' +
                '    position: relative;    background: #94C2ED;">';
            str += `${message}`;
            str += '</div>';
            str += '</li>';
            msgArea.innerHTML += str;
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
})