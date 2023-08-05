/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {

	let datalist = document.querySelector('tbody#datalist');
	let checknum = document.querySelector('input#checknum');
	let btnchanges = document.querySelectorAll('button.btnChange');
	let reports = document.querySelectorAll('td.reports');




	for (let z of reports) {

		if (z.innerHTML == 1) {

			z.innerHTML = '처리 전';

		} else {

			z.innerHTML = '처리 완료';

		}

	}

	const refreshlist = (btnchange) => {

		for (let x of btnchange) {

			x.addEventListener('click', async () => {
				datalist.style.opacity = "0";
				datalist.style.transition = "opacity 0.5s ease-in-out";
				let checknumvalue = checknum.value;
				let reqUrl = `/api/admin/refresh`;
				let data = {

					'check': checknumvalue,
					'updatenum': x.getAttribute('id'),
					'status': x.getAttribute('sts')
				}
				try {
					const response = await axios.post(reqUrl, data);
					let list = response.data;
					console.log(list);
					makelist(list);





				} catch (error) {

					console.log(error);

				}
			})

		}

	}

	const makelist = (list) => {

		let html = '';
		datalist.innerHTML = html;
		for (let y of list) {

			let crtime = y.createdTime;
			let date = new Date(crtime);
			let datestr = date.toLocaleString();
			let chatfile = y.chatfile;
			if (y.chatfile == '' || y.chatfile == null) {

				chatfile = '';

			}

			let reports = '';
			if (y.report == 1) {

				reports = '처리 전';

			} else {

				reports = '처리 완료';

			}

			if (y.report == 1) {
				html += `
						<tr>
							<td>${y.id}</td>
							<td>${y.reporter}</td>
							<td>${y.respondent}</td>
							<td>${datestr}</td>
							<td>${reports}</td>
							<td><audio id="audioPlayer"
							controls>
							<source src="data:audio/wav;base64,${y.audio}" type="audio/wav">
						</audio></td>
							<td><button class="btnChange" sts="${y.report}" id="${y.id}">처리</button></td>
						</tr>
						
						`
			} else {

				html += `
						<tr>
							<td>${y.id}</td>
							<td>${y.reporter}</td>
							<td>${y.respondent}</td>
							<td>${datestr}</td>
							<td>${reports}</td>
							<td><audio id="audioPlayer"
							controls>
							<source src="data:audio/wav;base64,${y.audio}" type="audio/wav">
						</audio></td>
							<td><button class="btnChange" sts="${y.report}" id="${y.id}">처리 취소</button></td>
						</tr>
						
						`

			}
		}
		datalist.innerHTML += html;

		setTimeout(() => {

			datalist.style.opacity = "1";
			datalist.style.transition = "opacity 0.5s ease-in-out";
		}, 1000)


		btnchanges = document.querySelectorAll('button.btnChange');

		refreshlist(btnchanges);
	}

	refreshlist(btnchanges);

})