/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {

	// 취소 버튼 객체 찾기
	const usualImagePic1CancelBtn = document.querySelector('button#usualImagePic1CancelBtn');
	const usualImagePic2CancelBtn = document.querySelector('button#usualImagePic2CancelBtn');
	const usualImagePic3CancelBtn = document.querySelector('button#usualImagePic3CancelBtn');
	const hobbyImagePic1CancelBtn = document.querySelector('button#hobbyImagePic1CancelBtn');
	const hobbyImagePic2CancelBtn = document.querySelector('button#hobbyImagePic2CancelBtn');
	const hobbyImagePic3CancelBtn = document.querySelector('button#hobbyImagePic3CancelBtn');

	// 유저 아이디 객체 찾기
	const inputUserId = document.querySelector('input#userId');
	
	// <img> 태그들 찾기
	const modifyUsualImagePic1 = document.querySelector('img#modifyUsualImagePic1');
	const modifyUsualImagePic2 = document.querySelector('img#modifyUsualImagePic2');
	const modifyUsualImagePic3 = document.querySelector('img#modifyUsualImagePic3');
	const modifyHobbyImagePic1 = document.querySelector('img#modifyHobbyImagePic1');
	const modifyHobbyImagePic2 = document.querySelector('img#modifyHobbyImagePic2');
	const modifyHobbyImagePic3 = document.querySelector('img#modifyHobbyImagePic3');
	
	// 파일 input 객체 찾기
	const imageInput1 = document.getElementById('uploadFilesUsualImage1');
	const imageInput2 = document.getElementById('uploadFilesUsualImage2');
	const imageInput3 = document.getElementById('uploadFilesUsualImage3');
	const hobbyImageInput1 = document.getElementById('hobbyUploadFilesUsualImage1');
	const hobbyImageInput2 = document.getElementById('hobbyUploadFilesUsualImage2');
	const hobbyImageInput3 = document.getElementById('hobbyUploadFilesUsualImage3');
	
	// 유저의 저장되어 있는 이미지 input 찾기
	const inputUsualPic1 = document.querySelector('input#usualPic1');
	const inputUsualPic2 = document.querySelector('input#usualPic2');
	const inputUsualPic3 = document.querySelector('input#usualPic3');
	const inputHobbyPic1 = document.querySelector('input#hobbyPic1');
	const inputHobbyPic2 = document.querySelector('input#hobbyPic2');
	const inputHobbyPic3 = document.querySelector('input#hobbyPic3');

	const usualImagePic1Cancel = () => {

		const userId = inputUserId.value;

		const url = `/api/upload/usualPic1/delete/${userId}`;

		let result = confirm('삭제 하시겠습니까?');

		if (result) {
			axios.post(url)
				.then((responce) => {
					console.log('responce >>> ', responce);
					
					modifyUsualImagePic1.src = '/images/Adding_a_Person_Image.png';
				}).catch((errer) => console.log(errer));
		}

	};
	
	const usualImagePic2Cancel = () => {

		const userId = inputUserId.value;

		const url = `/api/upload/usualPic2/delete/${userId}`;

		let result = confirm('삭제 하시겠습니까?');

		if (result) {
			axios.post(url)
				.then((responce) => {
					console.log('responce >>> ', responce);
					
					modifyUsualImagePic2.src = '/images/Adding_a_Person_Image.png';
				}).catch((errer) => console.log(errer));
		}

	};
	
	const usualImagePic3Cancel = () => {

		const userId = inputUserId.value;

		const url = `/api/upload/usualPic3/delete/${userId}`;

		let result = confirm('삭제 하시겠습니까?');

		if (result) {
			axios.post(url)
				.then((responce) => {
					console.log('responce >>> ', responce);
					
					modifyUsualImagePic3.src = '/images/Adding_a_Person_Image.png';
				}).catch((errer) => console.log(errer));
		}

	};
	
	const hobbyImagePic1Cancel = () => {

		const userId = inputUserId.value;

		const url = `/api/upload/hobbyPic1/delete/${userId}`;

		let result = confirm('삭제 하시겠습니까?');

		if (result) {
			axios.post(url)
				.then((responce) => {
					console.log('responce >>> ', responce);
					
					modifyHobbyImagePic1.src = '/images/Adding_a_Person_Image.png';
				}).catch((errer) => console.log(errer));
		}

	};
	
	const hobbyImagePic2Cancel = () => {

		const userId = inputUserId.value;

		const url = `/api/upload/hobbyPic2/delete/${userId}`;

		let result = confirm('삭제 하시겠습니까?');

		if (result) {
			axios.post(url)
				.then((responce) => {
					console.log('responce >>> ', responce);
					
					modifyHobbyImagePic2.src = '/images/Adding_a_Person_Image.png';
				}).catch((errer) => console.log(errer));
		}

	};
	
	const hobbyImagePic3Cancel = () => {

		const userId = inputUserId.value;

		const url = `/api/upload/hobbyPic3/delete/${userId}`;

		let result = confirm('삭제 하시겠습니까?');

		if (result) {
			axios.post(url)
				.then((responce) => {
					console.log('responce >>> ', responce);
					
					modifyHobbyImagePic3.src = '/images/Adding_a_Person_Image.png';
				}).catch((errer) => console.log(errer));
		}

	};
	
	usualImagePic1CancelBtn.addEventListener('click', usualImagePic1Cancel);
	usualImagePic2CancelBtn.addEventListener('click', usualImagePic2Cancel);
	usualImagePic3CancelBtn.addEventListener('click', usualImagePic3Cancel);
	hobbyImagePic1CancelBtn.addEventListener('click', hobbyImagePic1Cancel);
	hobbyImagePic2CancelBtn.addEventListener('click', hobbyImagePic2Cancel);
	hobbyImagePic3CancelBtn.addEventListener('click', hobbyImagePic3Cancel);
	
	imageInput1.addEventListener('change', function() {
		const usualPic1 = inputUsualPic1.value;
		
		const file = imageInput1.files[0];
		
		if (file !== undefined) {
		
		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				modifyUsualImagePic1.src = reader.result;
				modifyUsualImagePic1.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			modifyUsualImagePic1.src = `${usualPic1}`;
			modifyUsualImagePic1.style.display = 'none';
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
		
		}
		
	});
	
	imageInput2.addEventListener('change', function() {
		const usualPic2 = inputUsualPic2.value;
		
		const file = imageInput2.files[0];
		
		if (file !== undefined) {
		
		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				modifyUsualImagePic2.src = reader.result;
				modifyUsualImagePic2.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			modifyUsualImagePic2.src = `${usualPic2}`;
			modifyUsualImagePic2.style.display = 'none';
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
		
		}
		
	});
	
	imageInput3.addEventListener('change', function() {
		const usualPic3 = inputUsualPic3.value;
		
		const file = imageInput3.files[0];
		
		if (file !== undefined) {
		
		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				modifyUsualImagePic3.src = reader.result;
				modifyUsualImagePic3.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			modifyUsualImagePic3.src = `${usualPic3}`;
			modifyUsualImagePic3.style.display = 'none';
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
		
		}
		
	});
	
	hobbyImageInput1.addEventListener('change', function() {
		const hobbyPic1 = inputHobbyPic1.value;
		
		const file = hobbyImageInput1.files[0];
		
		if (file !== undefined) {
		
		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				modifyHobbyImagePic1.src = reader.result;
				modifyHobbyImagePic1.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			modifyHobbyImagePic1.src = `${hobbyPic1}`;
			modifyHobbyImagePic1.style.display = 'none';
		}
		
		console.info('들어옴')

		const id = inputUserId.value;

		const formData = new FormData();

		// 추가적인 파일 데이터를 FormData 객체에 추가합니다.
		const fileInput = document.querySelector('input#hobbyUploadFilesUsualImage1');
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
		
		}
		
	});
	
	hobbyImageInput2.addEventListener('change', function() {
		const hobbyPic2 = inputHobbyPic2.value;
		
		const file = hobbyImageInput2.files[0];
		
		if (file !== undefined) {
		
		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				modifyHobbyImagePic2.src = reader.result;
				modifyHobbyImagePic2.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			modifyHobbyImagePic2.src = `${hobbyPic2}`;
			modifyHobbyImagePic2.style.display = 'none';
		}
		
		console.info('들어옴')

		const id = inputUserId.value;

		const formData = new FormData();

		// 추가적인 파일 데이터를 FormData 객체에 추가합니다.
		const fileInput = document.querySelector('input#hobbyUploadFilesUsualImage2');
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
		
		}
		
	});
	
	hobbyImageInput3.addEventListener('change', function() {
		const hobbyPic3 = inputHobbyPic3.value;
		
		const file = hobbyImageInput3.files[0];
		
		if (file !== undefined) {
		
		if (file) {
			const reader = new FileReader();

			reader.addEventListener('load', function() {
				modifyHobbyImagePic3.src = reader.result;
				modifyHobbyImagePic3.style.display = 'block';
			});

			reader.readAsDataURL(file);
		} else {
			modifyHobbyImagePic3.src = `${hobbyPic3}`;
			modifyHobbyImagePic3.style.display = 'none';
		}
		
		console.info('들어옴')

		const id = inputUserId.value;

		const formData = new FormData();

		// 추가적인 파일 데이터를 FormData 객체에 추가합니다.
		const fileInput = document.querySelector('input#hobbyUploadFilesUsualImage3');
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
		
		}
		
	});

});