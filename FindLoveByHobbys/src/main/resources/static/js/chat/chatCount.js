document.addEventListener('DOMContentLoaded', () => {
    const mySex = document.querySelector('div#mySex').getAttribute('value');
    const myId = document.querySelector('div#myId').getAttribute('value');
    const chatCounts = document.querySelectorAll('div.chatCount');


    setInterval(function () {
        const url = "/chatCount/countList/" + myId + "/" + mySex;

        axios.get(url)
            .then((response) => {
                for (const count of response.data) {
                    console.log(count);
                    for (const htmlCount of chatCounts) {
                        for (const count of response.data) {
                            if (count.roomid == htmlCount.getAttribute('value')) {
                                if (count.femeleChatcount - count.femaleCheckcount != 0
                                    && count.maleChatcount - count.maleCheckcount != 0) {
                                    if (mySex == 1) {
                                        htmlCount.innerHTML = count.femeleChatcount - count.femaleCheckcount;
                                    } else {
                                        htmlCount.innerHTML = count.maleChatcount - count.maleCheckcount;
                                    }
                                }
                            }

                        }
                    }
                }
            }).catch((error) => console.log(error));
    }, 2000);
});