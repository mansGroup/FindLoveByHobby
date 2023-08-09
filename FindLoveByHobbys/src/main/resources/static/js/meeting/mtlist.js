/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {


	const optionselect = document.querySelector('select#optionselect');
	const hobbyId = document.querySelector('input#hobbyId');
	const locationId = document.querySelector('input#locationId');
	const ageId = document.querySelector('input#ageId');
	const btnsearch = document.querySelector('button#btnsearch');

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
					for (let x of data) {

						const optionElement = document.createElement('option');
						optionElement.setAttribute('selname', x.ageName);
						optionElement.value = x.ageId;
						optionElement.textContent = x.ageName;
						optionvalue.appendChild(optionElement);
					}
					console.log("연령");
					break;


			}

			let eventmaking = (event) => {
				if(optionvalue.value == '-1'){
					
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
						hobbyId = optionvalue.value;
						break;
					case "2":
						console.log(selectionRe);
						let age = document.querySelector('input#age');
						age.value = selname;
						ageId = optionvalue.value;
						break;
				}


			}

			optionvalue.addEventListener('change', eventmaking);

		} catch (error) {

			console.log(error);

		}


	})
	
	btnsearch.addEventListener('click',(e)=>{
		
		e.preventDefault();
		
		let reqUrl = '/api/meeting/search';
		
		
	})

})