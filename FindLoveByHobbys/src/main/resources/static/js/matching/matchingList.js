/**
 * matchingList 페이지에서 좋아요 이벤트 구성
 */

document.addEventListener('DOMContentLoaded', () => {
	console.log('matchingList.js 로드됨');
	// 이벤트 적용할 버튼
	const member1LikeBtn = document.querySelector('button#member1LikeBtn');
	const member2LikeBtn = document.querySelector('button#member2LikeBtn');
	
	// 로그인한 아이디
	const inputUserId = document.querySelector('input#loginUserId');

	// 프로필 유저 아이디
	const inputMember1UserId = document.querySelector('input#member1Id');
	const inputMember2UserId = document.querySelector('input#member2Id');


	const member1LikeEvent = () => {
		console.log('button1')
		
		// 로그인 유저 아이디
		const userId = inputUserId.value;
		
		// 프로필 유저 아이디
		const member1UserId = inputMember1UserId.value;
		
		const url = `/api/matching/member1/likesend/${userId}/${member1UserId}`;
		
		axios.post(url)
			.then((responce) => {
				console.log(responce);
				
				if (responce.data == 1) {
					alert('호감을 표시했습니다.');
					
					// 알람추가
				} else {
					alert('이미 호감을 표시한 상대입니다.')
				}
				
			}).catch((error) => console.log(error));

	};


	const member2LikeEvent = () => {
		console.log('button2')
		
		// 로그인 유저 아이디
		const userId = inputUserId.value;
		
		// 프로필 유저 아이디
		const member2UserId = inputMember2UserId.value;
		
		const url = `/api/matching/member2/likesend/${userId}/${member2UserId}`;
		
		axios.post(url)
			.then((responce) => {
				console.log(responce);
				
				if (responce.data == 1) {
					alert('호감을 표시했습니다.');
				} else {
					alert('이미 호감을 표시한 상대입니다.')
				}
				
			}).catch((error) => console.log(error));
		
	};
	
	
	member1LikeBtn.addEventListener('click', member1LikeEvent);
	member2LikeBtn.addEventListener('click', member2LikeEvent);
	
});