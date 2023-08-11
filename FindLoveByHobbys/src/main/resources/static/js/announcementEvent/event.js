/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {

	const btnUpdate = document.querySelector('button#btnUpdate');
	const btnDelete = document.querySelector('button#btnDelete');

	const title = document.querySelector('input#title');
	const content = document.querySelector('#content');
	const modifyForm = document.querySelector('#modifyForm');

	let titleVal = title.value;
	let contentVal = content.value;

	if (titleVal === '') {
		alert('제목을 입력해주세요');
		return;
	}

	if (contentVal === '') {
		alert('내용을 입력해주세요');
		return;
	}

	btnDelete.addEventListener('click', () => {

		const check = confirm('정말 삭제할까요?');
		if (check) {
			modifyForm.action = '/announcementEvent/eventDelete';
			modifyForm.method = 'post';
			modifyForm.submit();

		}
	});

	btnUpdate.addEventListener('click', () => {
		
		const check = confirm('수정할까요');
		if (check) {
			modifyForm.action = '/announcementEvent/eventUpdate';
			modifyForm.method = 'post';
			modifyForm.submit();
		}

	});

});