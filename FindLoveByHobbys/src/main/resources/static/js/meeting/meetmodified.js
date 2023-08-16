/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {

	const modifyForm = document.querySelector('form#modifiedForm');
	const btnModify = document.querySelector('button#btnModify');
	const title = document.querySelector('input#title');
	const content1 = document.querySelector('input#content1');
	const content2 = document.querySelector('input#content2');
	const content3 = document.querySelector('input#content3');
	const contents = document.querySelector('textarea#contents');
	const meetingtime = document.querySelector('input#meetingtime');
	const hobbyid = document.querySelector('select#hobbyid');
	const locationid = document.querySelector('select#locationid');
	const image1 = document.querySelector('input#image1');
	const image2 = document.querySelector('input#image2');
	const image3 = document.querySelector('input#image3');
	const img1 = document.querySelector('img#img1');
	const img2 = document.querySelector('img#img2');
	const img3 = document.querySelector('img#img3');
	const icon1 = document.querySelector('img#icon1');
	const icon2 = document.querySelector('img#icon2');
	const icon3 = document.querySelector('img#icon3');
	const image1path = document.querySelector('input#image1path');
	const image2path = document.querySelector('input#image2path');
	const image3path = document.querySelector('input#image3path');
	const btnDelete = document.querySelector('button#btnDelete');
	const formDelete = document.querySelector('form#formDelete');
	
	
	
	const locations = document.querySelector('input#locations');
	const hobbys = document.querySelector('input#hobbys');
	
	hobbyid.value = hobbys.value;
	locationid.value = locations.value;
	
	
	function checkValue(){
		
		if(title.value == ''){
			
			return false;
			
		}
		
		if(content1.value == ''){
			
			return false;
			
		}
		
		
		if(content2.value == ''){
			
			return false;
			
		}
		
		if(content3.value == ''){
			
			return false;
			
		}
		
		if(meetingtime.value == ''){
			
			return false;
			
		}
		
		if(contents.value == ''){
			
			return false;
			
		}
		
		if(hobbyid.value == ''){
			
			return false;
			
		}
		
		if(locationid.value == ''){
			
			return false;
			
		}
		
		if(image1path.value == ''){
			
			return false;
			
		}
		
		if(image2path.value == ''){
			
			return false;
			
		}
		
		if(image3path.value == ''){
			
			return false;
			
		}
		
		return true;
		
	}
	
	btnDelete.addEventListener('click',(e)=>{
		
		e.preventDefault();
		
		formDelete.method='post';
		formDelete.action='/meeting/delete';
		formDelete.submit();
		
		
	})
	
	btnModify.addEventListener('click', (e) => {

		e.preventDefault();
		
		if(checkValue()==false){
			
			alert('모든 항목을 입력해주세요.')
			return;
			
		}
		
		
		let check = confirm('정말로 등록하시겠습니까?');
		if(!check){
			
			return;
			
		}
		
		modifyForm.method='post';
		modifyForm.action='/meeting/modify';
		modifyForm.submit();

	})

	function mindate() {

		let now = new Date();
		
		const year = now.getFullYear();
            const month = String(now.getMonth() + 1).padStart(2, '0');
            const day = String(now.getDate()+1).padStart(2, '0');
            const hours = String(now.getHours()).padStart(2, '0');
            const minutes = String(now.getMinutes()).padStart(2, '0');
            return `${year}-${month}-${day}T${hours}:${minutes}`;
		
		

	}
	
	
	
	meetingtime.min = mindate();

	const imgupload = async (img, imgview, icon, imgpath) => {

		console.log(img.value);

		console.log('이미지 업로드 개시');
		const allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
		if (!allowedExtensions.exec(img.value)) {
			alert('이미지 파일만 업로드가 가능합니다.');
			img.value = '';
			return;
		}

		let formData = new FormData();

		formData.append('file', img.files[0]);
		let reqUrl = `/api/meeting/photo`;
		try {
			let response = await axios.post(reqUrl, formData, {
				headers: {
					'Content-Type': 'multipart/form-data'
				}
			});

			let path = response.data;
			let count = 0;
			for (let x of path) {
				console.log(x);
				if (count == 0) {

					imgpath.value = x;
					count += 1;
				} else {

					imgview.src = `data:image/png;base64,${x}`;

				}


			}

			icon.style.opacity = 1;

		} catch (error) {

			console.log(error);

		}
	}

	image1.addEventListener('change', () => imgupload(image1, img1, icon1, image1path));
	image2.addEventListener('change', () => imgupload(image2, img2, icon2, image2path));
	image3.addEventListener('change', () => imgupload(image3, img3, icon3, image3path));

})