/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	
	const inputUserId = document.querySelector('input#userId');

	
	const files = document.getElementById('files');
	
	const image = document.getElementById('image');
	
	files.addEventListener('change', function() {
		const file = files.files[0];

		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				image.src = reader.result;
				image.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			image.src = '/images/Adding_a_Person_Image.png';
			image.style.display = 'none';
		}
		
		console.info('들어옴')

		const id = inputUserId.value;

		const formData = new FormData();

		// 추가적인 파일 데이터를 FormData 객체에 추가합니다.
		const fileInput = document.querySelector('input#uploadFilesUsualImage1');
		const uploadFile = fileInput.files[0]; // 첫 번째 파일만 선택합니다.
		formData.append("uploadFile", uploadFile);

		// Ajax 요청
		const xhr = new XMLHttpRequest();
		const url = `/api/eventUpload/usualImage/${id}`; // 서버의 URL
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
});