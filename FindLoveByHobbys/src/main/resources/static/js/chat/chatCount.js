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
                                if (mySex == 1) {
                                    if (Number(count.maleChatcount) - Number(count.maleCheckcount) != 0) {
                                        htmlCount.innerHTML = Number(count.maleChatcount) - Number(count.maleCheckcount);
                                    }
                                } else {
                                    if (Number(count.femaleChatcount) - Number(count.femaleCheckcount) != 0) {
                                        htmlCount.innerHTML = Number(count.femaleChatcount) - Number(count.femaleCheckcount);
                                    }
                                }
                            }
                        }
                    }
                }
            }).catch((error) => console.log(error));
    }, 5000);
});