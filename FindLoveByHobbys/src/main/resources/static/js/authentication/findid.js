document.addEventListener('DOMContentLoaded', () => {
    const userid = document.querySelector('input#userid').value;
    const email = document.querySelector('input#email').value;
    const btnSendAuthenticationNumber = document.querySelector('span#btnSendAuthenticationNumber');
    const authenticationNumber = document.querySelector('input#authenticationNumber');
    const btnCheckKey = document.querySelector('span#btnCheckKey');
    const findIdForm = document.querySelector('form#findIdForm');
    let code = '';

    btnSendAuthenticationNumber.addEventListener('click', () => {
        const url = '/authentication/findid/' + userid + '/' + email;

        axios.get(url)
            .then((response) => {
                if (response.data == '이메일을 확인해 주세요.') {
                    alert('이메일을 확인해 주세요.');
                } else {
                    alert('인증번호가 발송되었습니다.');
                    code = response.data();
                }
            }).catch((error) => console.log(error));
    });

    btnCheckKey.addEventListener('click', () => {
        if (code != authenticationNumber.value) {
            alert('인증번호가 틀렸습니다.');
        } else {
            findIdForm.
        }
    });
});