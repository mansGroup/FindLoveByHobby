document.addEventListener('DOMContentLoaded', () => {

	// 버튼들
	const btnSexy = document.querySelector('button#btnSexy');
	const btnBeautiful = document.querySelector('button#btnBeautiful');
	const btnCute = document.querySelector('button#btnCute');
	const btnWonderful = document.querySelector('button#btnWonderful');
	const btnHandsome = document.querySelector('button#btnHandsome');

	// sexy 텍스트
	const spanMemberSexy = document.querySelector('span#memberSexy');
	const spanMemberSexyCount = document.querySelector('span#memberSexyCount');

	// beautiful 텍스트
	const spanMemberBeautiful = document.querySelector('span#memberBeautiful');
	const spanMemberBeautifulCount = document.querySelector('span#memberBeautifulCount');

	// Cute 텍스트
	const spanMemberCute = document.querySelector('span#memberCute');
	const spanMemberCuteCount = document.querySelector('span#memberCuteCount');

	// wonderful 텍스트
	const spanMemberWonderful = document.querySelector('span#memberWonderful');
	const spanMemberWonderfulCount = document.querySelector('span#memberWonderfulCount');

	// handsome 텍스트
	const spanMemberHandsome = document.querySelector('span#memberHandsome');
	const spanMemberHandsomeCount = document.querySelector('span#memberHandsomeCount');

	// 로그인한 사용자 아이디
	const getterId = document.querySelector('input#getterId').value;

	// 사용자 프로필 아이디
	const senderId = document.querySelector('input#senderId').value;
	
	
	// 호감도버튼의 span을 새로운 값으로 변경시켜줌
	const makeAssessmentElements = (data) => {
		const sexyCount = spanMemberSexyCount.querySelector('#sexyCount');
		sexyCount.innerHTML = `${data}`;
		
		const beautifulCount = spanMemberBeautifulCount.querySelector('#beautifulCount');
		beautifulCount.innerHTML = `${data}`;
		
		const cuteCount = spanMemberCuteCount.querySelector('#cuteCount');
		cuteCount.innerHTML = `${data}`;
		
		const wonderfulCount = spanMemberWonderfulCount.querySelector('#wonderfulCount');
		wonderfulCount.innerHTML = `${data}`;
		
		const handsomeCount = spanMemberHandsomeCount.querySelector('#handsomeCount');
		handsomeCount.innerHTML = `${data}`;
	
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
					alert('호감 표시는 한번밖에 할 수 없습니다.')
				}

			})
			.catch((errer) => console.log(errer));

	});

	btnBeautiful.addEventListener('click', () => {
		const assessmentName = spanMemberBeautiful.innerHTML;

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
					alert('호감 표시는 한번밖에 할 수 없습니다.')
				}

			})
			.catch((errer) => console.log(errer));

	});
	
	btnCute.addEventListener('click', () => {
		const assessmentName = spanMemberCute.innerHTML;

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
					alert('호감 표시는 한번밖에 할 수 없습니다.')
				}

			})
			.catch((errer) => console.log(errer));
		
	});
	
	btnWonderful.addEventListener('click', () => {
		const assessmentName = spanMemberWonderful.innerHTML;

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
					alert('호감 표시는 한번밖에 할 수 없습니다.')
				}

			})
			.catch((errer) => console.log(errer));
		
	});
	
	btnHandsome.addEventListener('click', () => {
		const assessmentName = spanMemberHandsome.innerHTML;

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
					alert('호감 표시는 한번밖에 할 수 없습니다.')
				}

			})
			.catch((errer) => console.log(errer));
	});
});
