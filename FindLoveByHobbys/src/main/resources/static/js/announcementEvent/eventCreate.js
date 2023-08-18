/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {


	const files = document.getElementById('files');
	const image = document.getElementById('image');
	const btnCreate = document.querySelector('button#btnCreate');
	const title = document.querySelector('#title');
	const content = document.querySelector('#content');
	const divide = document.querySelector('#divide');


	// 버튼을 기본 이미지로 바꿔준다.
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

	});
	const imageUpload = (id) => {
		console.info('들어옴')

		const formData = new FormData();

		// 추가적인 파일 데이터를 FormData 객체에 추가합니다.
		const fileInput = document.querySelector('input#files');
		
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


	};


	btnCreate.addEventListener('click', (e) => {
		e.preventDefault();

		let titleVal = title.value;
		let contentVal = content.value;
		const divideVal = divide.value;

		if (titleVal === '') {
			alert('제목을 입력해주세요');
			return;
		}

		if (contentVal === '') {
			alert('내용을 입력해주세요');
			return;
		}

		if (titleVal.length > 50) {
			alert('제목은 최대 100자 까지 가능합니다.');
			return;
		}

		if (contentVal.length > 500) {
			alert('내용은 최대 500자 까지 가능합니다.');
			return;
		}

		if (image === `/images/Adding_a_Person_Image.png`	) {
			alert('반드시 사진을 등록해주세요.');
			return;
		}
		
		const formData = new FormData();

		formData.append('title', titleVal);
		formData.append('author', '관리자');
		formData.append('content', contentVal);
		formData.append('divide', divideVal);

		const check = confirm('등록할까요?');
		if (check) {
			const xhr = new XMLHttpRequest();
			const url = '/api/eventUpload/eventCreate'; // 서버의 URL
			xhr.open("POST", url, true);

			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE) {
					if (xhr.status === 200) {
						const responseText = xhr.responseText;
						console.log('responseText >>>> ', responseText);

						imageUpload(responseText);


						// 버튼을 누르면 밑의 주소로 이동한다.
						window.location.href = '/announcementEvent/eventMain';
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