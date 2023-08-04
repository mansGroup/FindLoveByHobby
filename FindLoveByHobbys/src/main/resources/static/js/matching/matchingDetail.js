document.addEventListener('DOMContentLoaded', () => {
	const btnSexy = document.querySelector('#btnSexy');
	const btnBeautiful = document.querySelector('#btnBeautiful');
	const btnCuteWoman = document.querySelector('#btnCuteWoman');
	const btnCuteMan = document.querySelector('#btnCuteMan');
	const btnWonderful = document.querySelector('#btnWonderful');
	const btnHandsome = document.querySelector('#btnHandsome');

	const memberSexy = document.querySelector('#memberSexy');
	const spanRecommendCnt = document.querySelector('#spanRecommendCnt'); // 추가: span 요소를 가져옴
	
	const memberId = document.querySelector('input#memberId');
	
	const makeRecommendElements = (data) => {
		spanRecommendCnt.innerHTML = '';
		let htmlStr = '';
		htmlStr += `호감도 ${data.assessment}<span>`;
		spanRecommendCnt.innerHTML = htmlStr;
	};

	btnSexy.addEventListener('click', () => { 
		let mSexy = memberSexy.value;
		mSexy = parseInt(mSexy) += 1;

		axios.post(`/api/matchingDetail/assessment/${memberid}`, 
			{ mSexy: mSexy }) 
			
			.then((response) => {
				console.log(response.data);
				makeRecommendElements(response.data); 
			})
			.catch((error) => console.log(error));
	});
});
