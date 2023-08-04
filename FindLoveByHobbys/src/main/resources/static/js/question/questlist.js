/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {

	let status = document.querySelector('td#status');

	let options = document.querySelector('td#options');

	switch (status.value) {

		case 0:
			status.innerHTML = '확인 전';
			break;
		case 1:
			status.innerHTML = '처리 중';
			break;
		case 2:
			status.innerHTML = '처리 완료';
			break;

	}


	if (check == 1) {

		options.innerHTML = '서비스 문의';

	} else if (check == 2) {

		options.innerHTML = '불편 사항';

	} else if (check == 3) {

		options.innerHTML = '건의 사항';

	} else {

		options.innerHTML = '오류 접수';

	}


})