document.addEventListener('DOMContentLoaded', () => {
    const username = document.querySelector('input#username');
    const email = document.querySelector('input#email');
    const btnSendAuthenticationNumber = document.querySelector('button#btnSendAuthenticationNumber');
    const authenticationNumber = document.querySelector('input#authenticationNumber');
    const btnCheckKey = document.querySelector('button#btnCheckKey');
    let code = '';
    let userid = '';

    btnSendAuthenticationNumber.addEventListener('click', (e) => {
        e.preventDefault();
        const usernameValue = username.value;
        const emailValue = email.value;
        console.log(username + '=========' + email);
        const url = '/authentication/findid/' + usernameValue + '/' + emailValue;
        axios.get(url)
            .then((response) => {
                const data = response.data;
                if (data.userid == '$9)유저아이디가 없습니다!#') {
                    alert('이름을 확인해주세요');
                    return;
                }else if (data.userid == 'null') {
                    alert('이메일이 일치하지 않습니다.');
                    return;
                } else {
                    alert('인증번호가 발송되었습니다.');
                    code = data.code;
                    userid = data.userid;
                }
            }).catch((error) => console.log(error));
    });

    btnCheckKey.addEventListener('click', () => {
        if (authenticationNumber.value != null) {
            if (code != authenticationNumber.value) {
                alert('인증번호가 일치하지 않습니다.');
                return;
            } else {
                alert('아이디는 ' + userid + ' 입니다.');
            }
        }
    });
});