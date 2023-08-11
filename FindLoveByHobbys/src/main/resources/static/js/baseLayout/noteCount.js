document.addEventListener('DOMContentLoaded', () => {
    const id = document.querySelector('span#securityId').getAttribute('data-securityId');
    const noteNumber = document.querySelector('p.noteNumber');

    setInterval(function () {
        console.log('note인터벌 실행중');
        const url = `/api/note/count/${id}`;
        axios.get(url)
            .then((response) => {
                console.log(response.data);
                if (response.data != 0) {
                    noteNumber.innerHTML = ''+response.data;
                }
            }).catch((error) => console.log(error));
    }, 3000);
});