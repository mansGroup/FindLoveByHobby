document.addEventListener('DOMContentLoaded', () => {
	
	// 버튼들
	const btnSexy = document.querySelector('button#btnSexy');
	const btnBeautiful = document.querySelector('#btnBeautiful');
	const btnCuteWoman = document.querySelector('#btnCuteWoman');
	const btnCuteMan = document.querySelector('#btnCuteMan');
	const btnWonderful = document.querySelector('#btnWonderful');
	const btnHandsome = document.querySelector('#btnHandsome');
	
	// sexy 텍스트
	const spanMemberSexy = document.querySelector('span#memberSexy');
	const spanMemberSexyCount = document.querySelector('span#memberSexyCount');
	
	// 로그인한 사용자 아이디
	const getterId = document.querySelector('input#getterId').value;
	
	// 사용자 프로필 아이디
	const senderId = document.querySelector('input#senderId').value;

	const makeAssessmentElements = (data) => {
		const sexyCount = spanMemberSexyCount.querySelector('#sexyCount');
		sexyCount.innerHTML = `${data}`;
	};

	btnSexy.addEventListener('click', () => {
		const assessmentName = spanMemberSexy.innerHTML;
		
		console.log('assessmentName >>>> ', assessmentName);
		console.log('들어옴');
		
		const checkUrl = `/api/matchingDetail/assessment/chack/${senderId}/${getterId}`;

		axios.post(checkUrl)
			.then((responce) => {
				console.log('responce 중복 체크 >>>> ', responce);
				if (responce.data == 0) {
					axios.post(`/api/matchingDetail/assessment/${assessmentName}/${senderId}/${getterId}`)
						.then((response) => {
							console.log(response.data);
							makeAssessmentElements(response.data);
						})
						.catch((error) => console.log(error));

				} else {
					alert('이미 호감도를 표시했습니다.')
				}

			})
			.catch((errer) => console.log(errer));

	});

	// ... 나머지 버튼들에 대한 이벤트 처리도 동일하게 구현 ...
});
