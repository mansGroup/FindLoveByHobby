/**
 * matchingList 페이지에서 좋아요 이벤트 구성
 */

document.addEventListener('DOMContentLoaded', () => {
	
	// 좋아요 버튼
	const likeButton = document.querySelector('g#likeButton'); // html에서 269줄 버튼
	
	// 로그인한 사용자 아이디
	const inputUserId = document.querySelector('input#userId'); // html에서 초반에 있음.
	
	// 프로필 사용자 아이디
	const inputMemberId = document.querySelector('input#memberId');
	
	likeButton.addEventListener('click', (e) => {
		console.log('클릭');
		
		const userId = inputUserId.value;
		const memberId = inputMemberId.value;
		
		// 메세지 보내기
		const result = confirm('좋아요를 보냅니다.')
		
		// URL
		const reqUrl = `/api/matching/likesend/${userId}/${memberId}`;
		
		// 요청 보내기
		axios.post(reqUrl)
			.then((response) => { // 성공 응답
				
				
			})
			. catch((errer) => console.log(error)); // 실패 응답
		
	});
	
});