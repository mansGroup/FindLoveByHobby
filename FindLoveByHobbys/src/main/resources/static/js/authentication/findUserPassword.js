document.addEventListener('DOMContentLoaded', () => {
    const userId = document.querySelector('input#userId');
    const email = document.querySelector('input#email');
    const btnSendAuthenticationNumber = document.querySelector('span#btnSendAuthenticationNumber');
    const authenticationNumber = document.querySelector('input#authenticationNumber');
    const btnCheckKey = document.querySelector('span#btnCheckKey');
    let code = '';
    let userPassword = '';

    btnSendAuthenticationNumber.addEventListener('click', () => {
        const userIdValue = userId.value;
        const emailValue = email.value;
        console.log(userId + '=========' + email);
        const url = '/authentication/findpassword/' + userIdValue + '/' + emailValue;

        axios.get(url)
            .then((response) => {
                const data = response.data;
                if (response.data.userPassword == 'null') {
                    alert('입력정보를 확인해주세요.');
                } else {
                    alert('인증번호가 발송되었습니다.');
                    code = data.code;
                    userPassword = data.userPassword;
                }
            }).catch((error) => console.log(error));
    });

    btnCheckKey.addEventListener('click', () => {
        if (code != authenticationNumber.value) {
            alert('인증번호가 일치하지 않습니다.');
        } else {
            alert('임시 비밀번호는 ' + userPassword + ' 입니다.');
        }
    });
});