/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	
	// 유저 아이디 찾기
	const inputUserId = document.querySelector('input#userId');
	
	// 파일 이미지 객체
	const imageInput1 = document.getElementById('uploadFilesUsualImage1');
	const imageInput2 = document.getElementById('uploadFilesUsualImage2');
	const imageInput3 = document.getElementById('uploadFilesUsualImage3');
	const hobbyImageInput1 = document.getElementById('hobbyUploadFilesUsualImage1');
	const hobbyImageInput2 = document.getElementById('hobbyUploadFilesUsualImage2');
	const hobbyImageInput3 = document.getElementById('hobbyUploadFilesUsualImage3');
	
	// 이미지 보여주는 img태그
	const previewImage1 = document.getElementById('defultImage1');
	const previewImage2 = document.getElementById('defultImage2');
	const previewImage3 = document.getElementById('defultImage3');
	const previewImage4 = document.getElementById('hobbyDefultImage1');
	const previewImage5 = document.getElementById('hobbyDefultImage2');
	const previewImage6 = document.getElementById('hobbyDefultImage3');

	imageInput1.addEventListener('change', function() {
		const file = imageInput1.files[0];

		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				previewImage1.src = reader.result;
				previewImage1.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			previewImage1.src = '#';
			previewImage1.style.display = 'none';
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
		const url = `/api/upload/usualImage1/${id}`; // 서버의 URL
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
	
	imageInput2.addEventListener('change', function() {
		const file = imageInput2.files[0];

		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				previewImage2.src = reader.result;
				previewImage2.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			previewImage2.src = '#';
			previewImage2.style.display = 'none';
		}
		
		console.info('들어옴')

		const id = inputUserId.value;

		const formData = new FormData();

		// 추가적인 파일 데이터를 FormData 객체에 추가합니다.
		const fileInput = document.querySelector('input#uploadFilesUsualImage2');
		const uploadFile = fileInput.files[0]; // 첫 번째 파일만 선택합니다.
		formData.append("uploadFile", uploadFile);

		// Ajax 요청
		const xhr = new XMLHttpRequest();
		const url = `/api/upload/usualImage2/${id}`; // 서버의 URL
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
	
	imageInput3.addEventListener('change', function() {
		const file = imageInput3.files[0];

		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				previewImage3.src = reader.result;
				previewImage3.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			previewImage3.src = '#';
			previewImage3.style.display = 'none';
		}
		
		console.info('들어옴')

		const id = inputUserId.value;

		const formData = new FormData();

		// 추가적인 파일 데이터를 FormData 객체에 추가합니다.
		const fileInput = document.querySelector('input#uploadFilesUsualImage3');
		const uploadFile = fileInput.files[0]; // 첫 번째 파일만 선택합니다.
		formData.append("uploadFile", uploadFile);

		// Ajax 요청
		const xhr = new XMLHttpRequest();
		const url = `/api/upload/usualImage3/${id}`; // 서버의 URL
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
	
	hobbyImageInput1.addEventListener('change', function() {
		const file = hobbyImageInput1.files[0];

		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				previewImage4.src = reader.result;
				previewImage4.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			previewImage4.src = '#';
			previewImage4.style.display = 'none';
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
		const url = `/api/upload/hobbyusualimage1/${id}`; // 서버의 URL
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
	
	hobbyImageInput2.addEventListener('change', function() {
		const file = hobbyImageInput2.files[0];

		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				previewImage5.src = reader.result;
				previewImage5.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			previewImage5.src = '#';
			previewImage5.style.display = 'none';
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
		const url = `/api/upload/hobbyusualimage2/${id}`; // 서버의 URL
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
	
	hobbyImageInput3.addEventListener('change', function() {
		const file = hobbyImageInput3.files[0];

		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				previewImage6.src = reader.result;
				previewImage6.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			previewImage6.src = '#';
			previewImage6.style.display = 'none';
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
		const url = `/api/upload/hobbyusualimage3/${id}`; // 서버의 URL
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