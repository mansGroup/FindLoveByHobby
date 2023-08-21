/**
 * 회원가입 중복체크
 */
document.addEventListener('DOMContentLoaded', () => {

	const idcfButton = document.querySelector('#idcf');
	const cfmessageSpan = document.querySelector('#cfmessage');
	const inputPhone = document.querySelector('input#phone');
	const inputBirthdate = document.querySelector('input#birthdate');
	const signupbtn = document.querySelector('#signupbtn');
	const submitmessage = document.querySelector('#submitmessage');
	const phoneErrorMessage = document.getElementById('phoneErrorMessage');
	const birthdateErrorMessage = document.getElementById('birthdateErrorMessage');

	idcfButton.addEventListener('click', async function(e) {
		e.preventDefault();
		console.log(e.target);
		const userid = document.querySelector('#userid').value;

		const reqUrl = `/api/member/${userid}`;
		console.log(reqUrl);
		try {
			let response = await axios.get(reqUrl);
			let answer = response.data;
			console.log('answer=' + answer);
			if (answer == false) {

				cfmessageSpan.textContent = '사용 가능한 아이디입니다.';
				idcheck = 1;
			} else {

				cfmessageSpan.textContent = '이미 사용 중인 아이디입니다.';

			}
		} catch (error) {

			console.log('에러 발생');
		}

	});

	const passwordMessage = document.querySelector('#passwordMessage');
	const passwordCheck = document.querySelector('#passwordCheck');
	const password = document.querySelector('#password')


	passwordCheck.onkeyup = () => {
		try {
			if (password.value === passwordCheck.value) {
				passwordMessage.textContent = '비밀번호가 일치합니다.'

			}
			else {
				passwordMessage.textContent = '비밀번호가 불일치합니다.'
			}
		} catch (error) {
			console.log(error);
		}
	};


	const nickcfmessage = document.querySelector('#nickcfmessage');
	const nickcfButton = document.querySelector('#nickcf');

	nickcfButton.addEventListener('click', async function(e) {
		e.preventDefault();

		const nickname = document.querySelector('#nickname').value;

		const reqUrl = `/api/member/${nickname}`;
		console.log(reqUrl);
		try {
			let response = await axios.post(reqUrl);
			let answer = response.data;
			console.log('answer=' + answer);
			if (answer == false) {

				nickcfmessage.textContent = '사용 가능한 닉네임입니다.';
				nickcheck = 1;
			} else {

				nickcfmessage.textContent = '이미 사용 중인 닉네임입니다.';

			}
		} catch (error) {

			console.log('에러 발생');
		}

	});


	inputPhone.addEventListener('change', () => {
		const regex = /^\d{3}\d{4}\d{4}$/;
		const isValid = regex.test(inputPhone.value);

		if (!isValid) {
			phoneErrorMessage.textContent = '유효한 핸드폰 번호를 입력하세요.';
			inputPhone.style.borderColor = 'red';
		} else {
			phoneErrorMessage.textContent = '';
			inputPhone.style.borderColor = '';
		}
	});

	inputBirthdate.addEventListener('change', () => {
		const regex = /^\d{8}$/;
		const isValid = regex.test(inputBirthdate.value);

		if (!isValid) {
			birthdateErrorMessage.textContent = '유효한 생년월일을 입력하세요.';
			inputBirthdate.style.borderColor = 'red';
		} else {
			birthdateErrorMessage.textContent = '';
			inputBirthdate.style.borderColor = '';
		}
	});



});
