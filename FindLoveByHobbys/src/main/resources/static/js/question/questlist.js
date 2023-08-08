/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {

	let status = document.querySelectorAll('td.status');

	let options = document.querySelectorAll('td.options');

	const modal = new bootstrap.Modal('div#modals',{backdrop: false});
	const btnModal = document.querySelector('button#btnModal');
	
	// 더보기 페이지 관련
	let num = document.querySelector('input#num');
	let userid = document.querySelector('input#userid');
	const btnAddView = document.querySelector('button#btnaddload')
	const qsbody = document.querySelector('tbody#qsbody');

	btnAddView.addEventListener('click', async () => {
		num.value = parseInt(num.value) + 1;
		
		if(userid.value == ''){
			
			userid.value = '없음';
			
		}
		
		let reqUrl = `/api/quest/list`;
		let data = {
			
			userid : userid.value,
			num : num.value
			
		}

		try {

			let response = await axios.post(reqUrl,data);
			console.log(response.data);
			qsbody.innerHTML = '';
			let html = '';
			for (let list of response.data) {

				let todays = new Date(list.modifiedTime);
				let dates = todays.toLocaleString();

				html += `
				<tr>
					<td>${list.member.id}</td>
					<td class="options" data-id="${list.questionoption}">${list.questionoption}</td>
					<td class="ellipsis"><a
						href="/question/qsread?id=${list.id}">${list.questioncontent}</a></td>
					<td>${dates}</td>
					<td class="status" data-id="${list.status}">${list.status}</td>
				</tr>
				
				`
				

			}
			qsbody.innerHTML = html;
			
			status = document.querySelectorAll('td.status');
			options = document.querySelectorAll('td.options');
			refresh();
		} catch (error) {

			console.log(error);

		}



	})
	
	btnModal.addEventListener('click',()=>{
		
		
		modal.show();
		
	})
	

	function refresh() {

		for (let x of status) {
			let stat = x.getAttribute('data-id');
			
			switch (stat) {

				case "0":
					x.innerHTML = '확인 전';
					break;
				case "1":
					x.innerHTML = '처리 중';
					break;
				case "2":
					x.innerHTML = '처리 완료';
					break;

			}

		}

		for (let x of options) {
			
			let option = x.getAttribute('data-id');
			
			if (option == "1") {

				x.innerHTML = '서비스 문의';

			} else if (option == "2") {

				x.innerHTML = '불편 사항';

			} else if (option == 3) {

				x.innerHTML = '건의 사항';

			} else {

				x.innerHTML = '오류 접수';

			}

		}


	}

	refresh();






})