/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {

	const inputUsualImage1 = document.querySelector('input#uploadFilesUsualImage');


	const uploadImage = (e) => {
		console.info('들어옴')
		
		const id = document.querySelector('input#userId');
		
		const formData = new FormData();

		// 추가적인 파일 데이터를 FormData 객체에 추가합니다.
		const fileInput = document.querySelector('input#uploadFilesUsualImage');
		const uploadFile = fileInput.files[0]; // 첫 번째 파일만 선택합니다.
		formData.append("uploadFile", uploadFile);
		
		// Ajax 요청
		const xhr = new XMLHttpRequest();
		const url = `/api/upload/usualImage1/${id}`; // 서버의 URL
		xhr.open("POST", url, true);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					// 요청 성공 시 처리 로직
					const response = JSON.parse(xhr.responseText);
					console.log(response);
				} else {
					console.error("Request failed");
				}
			}
		};

		// FormData 객체를 전송
		xhr.send(formData);

	};

	inputUsualImage1.addEventListener('input', uploadImage);

});