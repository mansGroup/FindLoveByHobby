/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {


	const optionselect = document.querySelector('select#optionselect');
	const hobbyId = document.querySelector('input#hobbyId');
	const locationId = document.querySelector('input#locationId');
	const ageId = document.querySelector('input#ageId');
	const btnsearch = document.querySelector('button#btnsearch');
	const searchresult = document.querySelector('div#searchresult');
	const btnBack = document.querySelector('button#btnBack');
	const btnFront = document.querySelector('button#btnFront');
	const btnBackPr = document.querySelector('div#btnBackPr');
	const btnFrontPr = document.querySelector('div#btnFrontPr');
	
	let nowcount = 0;
	// 검색 결과 리스트 저장하는 곳.
	let list = '';
	
	optionselect.addEventListener('change', async () => {

		let selection = optionselect.value;
		const optionvalue = document.querySelector('select#optionvalue');
		let reqUrl = `/api/meeting/options/${selection}`;

		optionvalue.innerHTML = '';
		try {

			let response = await axios.get(reqUrl);
			let data = response.data;

			console.log(data);

			const optionelement = document.createElement('option');
			optionelement.setAttribute('selname', '선택안함.');
			optionelement.value = '-1';
			optionelement.textContent = '항목을 선택해주세요.';
			optionvalue.appendChild(optionelement);

			switch (selection) {

				case "0":

					for (let x of data) {

						const optionElement = document.createElement('option');
						optionElement.setAttribute('selname', x.locationname);
						optionElement.value = x.id;
						optionElement.textContent = x.locationname;
						optionvalue.appendChild(optionElement);
					}
					console.log("지역");
					break;

				case "1":
					for (let x of data) {

						const optionElement = document.createElement('option');
						optionElement.setAttribute('selname', x.content);
						optionElement.value = x.hobbyId;
						optionElement.textContent = x.content;
						optionvalue.appendChild(optionElement);

					}
					console.log("취미");
					break;

				case "2":


					let optionElement = document.createElement('option');
					optionElement.setAttribute('selname', '20대');
					optionElement.value = '20';
					optionElement.textContent = '20대';
					optionvalue.appendChild(optionElement);

					optionElement = document.createElement('option');
					optionElement.setAttribute('selname', '30대');
					optionElement.value = '30';
					optionElement.textContent = '30대';
					optionvalue.appendChild(optionElement);

					optionElement = document.createElement('option');
					optionElement.setAttribute('selname', '40대');
					optionElement.value = '40';
					optionElement.textContent = '40대';
					optionvalue.appendChild(optionElement);

					optionElement = document.createElement('option');
					optionElement.setAttribute('selname', '50대');
					optionElement.value = '50';
					optionElement.textContent = '50대';
					optionvalue.appendChild(optionElement);

					console.log("연령");
					break;


			}

			let eventmaking = (event) => {
				if (optionvalue.value == '-1') {

					return;

				}
				let selectionRe = optionselect.value;
				const selectedOption = event.target.options[event.target.selectedIndex];
				const selname = selectedOption.getAttribute('selname');
				switch (selectionRe) {

					case "0":
						console.log(selectionRe);
						let location = document.querySelector('input#location');
						location.value = selname;
						locationId.value = optionvalue.value;
						break;
					case "1":
						console.log(selectionRe);
						let hobby = document.querySelector('input#hobby');
						hobby.value = selname;
						hobbyId.value = optionvalue.value;
						break;
					case "2":
						console.log(selectionRe);
						let age = document.querySelector('input#age');
						age.value = selname;
						ageId.value = optionvalue.value;
						break;
				}


			}

			optionvalue.addEventListener('change', eventmaking);

		} catch (error) {

			console.log(error);

		}


	})

	btnsearch.addEventListener('click', async (e) => {

		e.preventDefault();

		let reqUrl = '/api/meeting/search';
		let data = {

			ageId: ageId.value,
			locationId: locationId.value,
			hobbyId: hobbyId.value

		}

		let response = await axios.post(reqUrl, data);
		list = response.data;
		
		if(list.length != 0){
			
			btnBackPr.classList.remove('d-none');
			btnFrontPr.classList.remove('d-none');
			
		} else {
			
			btnBackPr.classList.add('d-none');
			btnFrontPr.classList.add('d-none');
		}
		
		console.log(list);
		searchResult(list,3,0);
		
	})
	
	function searchResult(list, endcount, startcount) {
		
		let html = '';
		searchresult.innerHTML = '';
		
		
		
		let count = startcount;
		let end = endcount;
		nowcount = count;
		for (let x of list) {
			if(count == end){
				
				return;
				
			}
			count+=1;
			html += `
				<div class="col-lg-4 col-md-6 col-12 d-flex flex-column mb-4 mb-lg-0 mb-md-0">
						<div class="image-hover-thumb">
							<img src="data:image/jpeg;base64,${x.image1}"
								class="img-fluid" alt="이미지 없음">
						</div>

						<div class="section-block">
							<h3 class="my-3">${x.title}</h3>

							<p class="mb-2" >
								${x.location.locationname}</p>

							<p>${x.meetingdate}</p>
						</div>
					</div>

				</div>
			
			`

		}
		searchresult.innerHTML = html;
		
	}
	
	btnBack.addEventListener('click',()=>{
		
		if(nowcount!=0){
			
			nowcount-=3;
			searchResult(list,nowcount+3,nowcount);
			console.log("뒤로 가기");
		} else {
			
			if(list == ''){
				console.log("검색 안한 상태");
				return;
				
			}
			console.log("더 이상 뒤로 갈 수 없음.");
		}
		
	})
	
	btnFront.addEventListener('click',()=>{
		
		if(nowcount+3>list.length-1){
			console.log("더 이상 길이가 안되는 상황");
			return;
			
		} else {
			nowcount+=3;
			searchResult(list,nowcount+3,nowcount);
			console.log("앞으로");
		}
		
	})
	
	

})