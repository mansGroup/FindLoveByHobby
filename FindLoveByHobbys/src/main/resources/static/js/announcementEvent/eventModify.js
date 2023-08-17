/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {

	const btnUpdate = document.querySelector('button#btnUpdate');
	const btnDelete = document.querySelector('button#btnDelete');

	const title = document.querySelector('input#title');
	const content = document.querySelector('#content');
	const modifyForm = document.querySelector('#modifyForm');

	const files = document.getElementById('filesModify');
	
	const image = document.getElementById('imageModify');
	
	const id = document.querySelector('#id').value;
	
	const inputBasicUserPicture = document.querySelector('input#basicUserPicture');

	btnDelete.addEventListener('click', () => {

		const check = confirm('정말 삭제할까요?');
		if (check) {
			modifyForm.action = '/announcementEvent/eventDelete';
			modifyForm.method = 'post';
			modifyForm.submit();

		}
	});
	
	files.addEventListener('change', function() {
		const basicUserPicture = inputBasicUserPicture.value;
		
		const file = files.files[0];
		
		if (file.name === undefined) {
			image.src = `${basicUserPicture}`;
			image.style.display = 'none';
		}
		
		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				image.src = reader.result;
				image.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			image.src = `${basicUserPicture}`;
			image.style.display = 'none';
		}
		
		console.info('들어옴')

		const formData = new FormData();

		// 추가적인 파일 데이터를 FormData 객체에 추가합니다.
		const fileInput = document.querySelector('input#filesModify');
		const uploadFile = fileInput.files[0]; // 첫 번째 파일만 선택합니다.
		formData.append("uploadFile", uploadFile);

		// Ajax 요청
		const xhr = new XMLHttpRequest();
		const url = `/api/eventUpload/modifyImage/${id}`; // 서버의 URL
		xhr.open("POST", url, true);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					const responseText = xhr.responseText;
					// 요청 성공 시 처리 로직
					console.log('responseText >>>> ', responseText);
				} else {
					console.error("Request failed");
				}
			}
		};

		// FormData 객체를 전송
		xhr.send(formData);
		

	});


	btnUpdate.addEventListener('click', () => {

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

		const check = confirm('수정할까요');
		if (check) {
			modifyForm.action = '/announcementEvent/eventUpdate';
			modifyForm.method = 'post';
			modifyForm.submit();
		}

	});

});