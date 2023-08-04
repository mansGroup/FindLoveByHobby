document.addEventListener('DOMContentLoaded', () => {
	const btnSexy = document.querySelector('#btnSexy');
	const btnBeautiful = document.querySelector('#btnBeautiful');
	const btnCuteWoman = document.querySelector('#btnCuteWoman');
	const btnCuteMan = document.querySelector('#btnCuteMan');
	const btnWonderful = document.querySelector('#btnWonderful');
	const btnHandsome = document.querySelector('#btnHandsome');

	const memberSexy = document.querySelector('#memberSexy');
	const spanRecommendCnt = document.querySelector('#spanRecommendCnt'); // 추가: span 요소를 가져옴
	
	const assessmentId = document.querySelector('input#assessmentId');
	const inputUserId = document.querySelector('input#uesrId');
	// 어떤 호감도 인지 찾기 (assessmentName)
	
	// 컨트롤러에서 assessmentId(프로필 사람), inputUuerId(로그인한 사람) 를 user-assessment 추가
	// assessmentId 사람의 assessment 테이블에 Id와 assessmentName를 넘겨서 assessmentName를 + 1
	
	// 페이지 자동 갱신을 위해
	const makeRecommendElements = (data) => {
		spanRecommendCnt.innerHTML = '';
		let htmlStr = '';  
		htmlStr += `호감도 ${data.assessment}<span>`;
		spanRecommendCnt.innerHTML = htmlStr;
		
	};

	// btnSexy를 눌렀을 때 이벤트처리
	btnSexy.addEventListener('click', () => { 
		let mSexy = memberSexy.value;
		mSexy = parseInt(mSexy) += 1;

		axios.post(`/api/matchingDetail/assessment/${assessmentId}`, 
			{ mSexy: mSexy }) 
			
			.then((response) => {
				console.log(response.data);
				// 자동으로 갱신할 수 있는 코드를 호출함
				makeRecommendElements(response.data); 
			})
			.catch((error) => console.log(error));
	});
});
