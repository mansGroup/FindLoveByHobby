document.addEventListener('DOMContentLoaded', () => {
    const mySex = document.querySelector('div#mySex').getAttribute('value');
    const myId = document.querySelector('div#myId').getAttribute('value');
    const chatCountss = document.querySelectorAll('div.chatCount');
    for (const count of chatCountss) {
        count.getAttribute('va')
    }

    setInterval(function () {
        const url = "/chatCount/countList/" + myId + "/" + mySex;

        axios.get(url)
            .then((response) => {
                for (const count of response.data) {
                    console.log(count);
                    for (const htmlCount of chatCounts) {
                        if (count.roomId == htmlCount.roomId) {
                        }
                    }
                }
            }).catch((error) => console.log(error));
    }, 2000);
});