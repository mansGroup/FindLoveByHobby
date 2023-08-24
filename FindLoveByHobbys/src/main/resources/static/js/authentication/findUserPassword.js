document.addEventListener('DOMContentLoaded', () => {
    const userId = document.querySelector('input#userId');
    const email = document.querySelector('input#email');
    const btnSendAuthenticationNumber = document.querySelector('button#btnSendAuthenticationNumber');
    const authenticationNumber = document.querySelector('input#authenticationNumber');
    const btnCheckKey = document.querySelector('button#btnCheckKey');
    let code = '';
    let userPassword = '';

    btnSendAuthenticationNumber.addEventListener('click', () => {
        const userIdValue = userId.value;
        const emailValue = email.value;
        console.log(userIdValue + '=========' + emailValue);
        let responseid = '';

        axios.get('/authentication/userid/' + userIdValue)
            .then((response) => { responseid = response.data })
            .catch((error) => console.log(error));
        console.log(responseid+'][][][][][');
        if (responseid == 'empty') {
            alert('아이디를 확인해주세요');
            return;
        }
        let checkEmail = '';
        axios.get('/authentication/findpassword/' + userIdValue + '/' + emailValue)
            .then((response) => {
                const data = response.data;
                if (data.userPassword == 'null') {
                    alert('이메일을 확인해주세요.');
                    checkEmail = data.userPassword;
                } else {
                    if (checkEmail == 'null') {
                        return;
                    }
                    alert('인증번호가 발송되었습니다.');
                    code = data.code;
                    userPassword = data.userPassword;
                }
            }).catch((error) => console.log(error));
    });

    btnCheckKey.addEventListener('click', () => {
        if (authenticationNumber.value != null) {
            if (code != authenticationNumber.value) {
                alert('인증번호가 일치하지 않습니다.');
            } else {
                alert('임시 비밀번호는 ' + userPassword + ' 입니다.\n mypage -> 회원정보수정에서 비밀번호를 바꿔주세요~');
            }
        }
    });
});