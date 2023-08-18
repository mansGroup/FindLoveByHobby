document.addEventListener('DOMContentLoaded', () => {
    const id = document.querySelector('input#id');
    const password = document.querySelector('input#userPassword');
    const email = document.querySelector('input#email');
    const phone = document.querySelector('input#phone');
    const jibunAddress = document.querySelector('input#jibunAddress');
    const detailAddress = document.querySelector('input#detailAddress');
    const btnModifyInfo = document.querySelector('button#btnModifyInfo');

    btnModifyInfo.addEventListener('click', (e) => {
        e.preventDefault();
        if (!confirm('회원정보를 수정하시겠습니까?')) {
            return
        }
        console.log(id.value);
        axios.post('/api/member/info/update',
            {
                id: id.value,
                password: password.value,
                email: email.value,
                phone: phone.value,
                address: jibunAddress.value + ' ' + detailAddress.value
        })
            .then((response) => {
                const data = response.data;
                id.value = data.id;
                email.value = data.email;
                phone.value = data.phone;
                jibunAddress.value = data.address;
                
                window.XMLHttpRequest('/mypage/room');
            })
            .catch((error) => console.log(error));
    });
});