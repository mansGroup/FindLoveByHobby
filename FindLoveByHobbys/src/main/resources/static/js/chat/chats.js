document.addEventListener("DOMContentLoaded", ()=>{

    const username = 'Dahan';
    const btnSend = document.querySelector('button#button-send');
    const msgArea = document.querySelector('div#msgArea');

    // $("#disconn").on("click", (e) => {
    //     disconnect();
    // })
    btnSend.addEventListener('click', ()=>{
        send();
    });

    let websocket = new WebSocket("ws://localhost:8090/ws/chat");
    //let websocket = new WebSocket("ws://192.168.219.103:8080/ws/chat");

    websocket.onmessage = onMessage;
    websocket.onopen = onOpen;
    websocket.onclose = onClose;

    /*function disconnect(){

        websocket.send(username + "님이 퇴장하셨습니다.");
        websocket.close();
    }*/
    function send(){

        let msg = document.getElementById("msg");

        console.log(username + ":" + msg.value);
        websocket.send(username + ":" + msg.value);
        msg.value = '';
    }
    //채팅창에서 나갔을 때
    function onClose(evt) {
        let str = username + ": 님이 방을 나가셨습니다.";
        websocket.send(str);
    }
    //채팅창에 들어왔을 때
    function onOpen(evt) {
        let str = username + ": 님이 입장하셨습니다.";
        websocket.send(str);
    }

    function onMessage(msg) {
        var data = msg.data;
        var sessionId = null;
        //데이터를 보낸 사람
        var message = null;
        var arr = data.split(":");

        for(var i=0; i<arr.length; i++){
            console.log('arr[' + i + ']: ' + arr[i]);
        }

        var cur_session = username;

        //현재 세션에 로그인 한 사람
        console.log("cur_session : " + cur_session);
        sessionId = arr[0];
        message = arr[1];

        console.log("sessionID : " + sessionId);
        console.log("cur_session : " + cur_session);

        //로그인 한 클라이언트와 타 클라이언트를 분류하기 위함
        if(sessionId == cur_session){
            var str = "<div class='col-6'>";
            str += "<div class='alert alert-secondary'>";
            str += "<b>" + sessionId + " : " + message + "</b>";
            str += "</div></div>";
            msgArea.innerHTML += str;
        }
        else{
            var str = "<div class='col-6'>";
            str += "<div class='alert alert-warning'>";
            str += "<b>" + sessionId + " : " + message + "</b>";
            str += "</div></div>";
            msgArea.innerHTML += str;
        }
    }
})