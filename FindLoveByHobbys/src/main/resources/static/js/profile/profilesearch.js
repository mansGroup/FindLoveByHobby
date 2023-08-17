/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	console.log('profilesearch.js 로드됨');

	// 이벤트 적용할 버튼
	member1LikeBtn = document.querySelector('button#member1LikeBtn');
	member2LikeBtn = document.querySelector('button#member2LikeBtn');
	member3LikeBtn = document.querySelector('button#member3LikeBtn');
	member4LikeBtn = document.querySelector('button#member4LikeBtn');

	// 로그인한 아이디
	const inputUserId = document.querySelector('input#loginUserId');

	// 프로필 유저 아이디
	inputMember1UserId = document.querySelector('input#member1Id');
	inputMember2UserId = document.querySelector('input#member2Id');
	inputMember3UserId = document.querySelector('input#member3Id');
	inputMember4UserId = document.querySelector('input#member4Id');

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

	const member3LikeEvent = () => {
		console.log('button2')

		// 로그인 유저 아이디
		const userId = inputUserId.value;

		// 프로필 유저 아이디
		const member3UserId = inputMember3UserId.value;

		const url = `/api/matching/member3/likesend/${userId}/${member3UserId}`;

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

	const member4LikeEvent = () => {
		console.log('button2')

		// 로그인 유저 아이디
		const userId = inputUserId.value;

		// 프로필 유저 아이디
		const member4UserId = inputMember4UserId.value;

		const url = `/api/matching/member4/likesend/${userId}/${member4UserId}`;

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
	member3LikeBtn.addEventListener('click', member3LikeEvent);
	member4LikeBtn.addEventListener('click', member4LikeEvent);

});