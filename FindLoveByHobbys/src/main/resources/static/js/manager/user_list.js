/**
 * 
 */


document.addEventListener('DOMContentLoaded', () => {

	// 버튼으로 구현할 객체 찾기
	const spanColNumber = document.querySelector('span#spanColName');
	const thColName = document.querySelector('th#colName');
	const thColNickname = document.querySelector('th#colNickname');
	const thColGender = document.querySelector('th#colGender');
	const thColRole = document.querySelector('th#colRole');

	// 업, 다운 이미지 태그들 찾기
	const imgColNumber = document.querySelector('img#colNumberImg');
	const imgColName = document.querySelector('img#colNameImg');
	const imgColNickname = document.querySelector('img#colNicknameImg');
	const imgColGender = document.querySelector('img#colGenderImg');
	const imgColRole = document.querySelector('img#colRoleImg');

	// 현재 정렬 상태 찾기
	const sortingInput = document.querySelector('input#sortingInput');

	spanColNumber.addEventListener('click', () => {
		console.log('들어옴');

		const sortingValue = sortingInput.value;
		const imgColNumberValue = imgColName.src;
		console.log('imgColNumberValue >>> ', imgColNumberValue);

		let sorting = '';

		if (sortingValue === 'basic') { // 없을때는 최대로
			console.log('1번 조건문');
			sorting = 'nameUp';
		} else if (sortingValue === 'nameUp') { // 최대에서는 아래로
			console.log('2번 조건문');
			sorting = 'nameDown';
		} else if (sortingValue === 'nameDown') { // 아래에서는 기본으로
			console.log('3번 조건문');
			sorting = 'basic';
		}

		console.log('sorting >>> ', sorting);

		const url = `/api/manager/user/list/${sorting}`;

		axios.post(url)
			.then((responce) => {
				console.log('responce >>> ', responce);
				
				makeUserListElements(responce.data);
			})
			.catch((error) => console.log(error));


	});

	const makeUserListElements = (members) => {
		
		// 댓글 개수를 배열(data)의 원소 개수로 업데이트
		document.querySelector('span#replyCount').innerText = data.length;

		// 댓글 목록을 삽입할 div 요소를 찾음.
		const tableBody = document.querySelector('tbody#tableBody');

		// div 안에 작성된 기존 내용은 삭제.
		tableBody.innerHtml = '';

		// div 안에 삽입할 HTML 코드 초기화.
		let htmlStr = '';
/*
		htmlStr += `
                    <tr th:each="member, idx : ${members}" th:if="${idx.index >= 5 * (pageCount - 1) and idx.index < 5 * pageCount}" data - bs - toggle="offcanvas" class="bodyTr" th: data - bs - target="'#offcanvasScrolling' + ${ idx.index }" >
						<td th:text = "${idx.index + 1}" ></td>
						<td th:text="${member.name}"></td>
						<td th:text="${member.nickname}"></td>
						<td><span th:text="${member.sex}"></span></td>
						<td>
							<span th:if="${member.role == role[0]}">심사 완료</span> 
							<span th:if="${member.role == role[1]}" style="color: #FF5500;">심사 대기중</span> 
							<span th:if="${member.role == role[2]}" style="color: red;">휴면 계정</span> 
							<span th:if="${member.role == role[3]}" style="color: blue;">관리자</span>
						</td>
					</tr>
		`;
        
        tableBody.innerHtml = htmlStr; */
     };


});