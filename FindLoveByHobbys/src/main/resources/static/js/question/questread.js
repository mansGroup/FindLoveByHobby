/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {

	let options = document.querySelector('input#options');
	let check = options.value;
	let status = document.querySelector('input#status');
	let statustext = document.querySelector('span#statustext');
	
	if (check == 1) {

		options.value = '서비스 문의';

	} else if (check == 2) {

		options.value = '불편 사항';

	} else if (check == 3) {

		options.value = '건의 사항';

	} else {

		options.value = '오류 접수';

	}
	
	switch(status.value){
		
		case 0:
			statustext.innerHTML = '확인 전';
			break;
		case 1:
			statustext.innerHTML = '처리 중';
			break;
		case 2:
			statustext.innerHTML = '처리 완료';
			break;
		
	}


	

})