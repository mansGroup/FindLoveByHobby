document.addEventListener('DOMContentLoaded', () => {
    const id = document.querySelector('span#securityId').innerText;
    const noteNumber = document.querySelector('p.noteNumber');

    setInterval(function () {
        console.log('note인터벌 실행중 id = {}', id);
        const url = `/api/note/count/${id}`;
        axios.get(url)
            .then((response) => {
                console.log(response.data);
                if (response.data != 0) {
                    noteNumber.innerHTML = ''+response.data;
                }
            }).catch((error) => console.log(error));
    }, 10000);
});