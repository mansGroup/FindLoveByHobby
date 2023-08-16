/**
 * 
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 const inviteForm = document.querySelector('form#inviteForm');
	 const inviteBtn = document.querySelector('button#inviteBtn');
	 const colid = document.querySelector('input#colid');
	 const inviteduser = document.querySelectorAll('div.inviteduser');
	 const no1img = document.querySelector('div#no1img');
	 const no2img = document.querySelector('div#no2img');
	 const no3img = document.querySelector('div#no3img');
	 const imgs3 = document.querySelector('button#imgs3');
	 const imgs2 = document.querySelector('button#imgs2');
	 const imgs1 = document.querySelector('button#imgs1');
	 
	 
	 
	 let num = 1;
	 const imgchange = (e) => {
		 e.preventDefault();
		 console.log("실행은 되는건가");
		 
		 
		 if(num==1){
			 
			 no2img.style.display='inline-block';
			 no1img.style.display='none';
			 console.log("1번 이미지 교체");
			 num=2;
		 } else if(num==2){
			 
			 no3img.style.display='inline-block';
			 no2img.style.display='none';
			 console.log("2번 이미지 교체");
			 num=3;
		 } else {
			 
			 no1img.style.display='inline-block';
			 no3img.style.display='none';
			 console.log("3번 이미지 교체");
			 num=1;
		 }
		 
	 }
	 
	 imgs1.addEventListener('click',imgchange);
	 imgs2.addEventListener('click',imgchange);
	 imgs3.addEventListener('click',imgchange);
	 
	 const whoisLeader = async ()=>{
		 let reqUrl = `/api/meeting/checkleader?id=${colid.value}`;
		 let response = await axios.get(reqUrl);
		 
		 for(let x of inviteduser){
			 
			 let dataid = x.getAttribute('data-id');
			 console.log(dataid);
			 
			 if(response.data == dataid){
				 console.log("실제 실행되는 중");
				 x.style.borderWidth = '3px';
				 x.style.borderColor = 'green';
			 }
			 
			 
		 }
		 
	 };
	 
	 whoisLeader();
	 
	 inviteBtn.addEventListener('click',(e)=>{
		 
		 
		 e.preventDefault();
		 
		 let invite = inviteBtn.getAttribute('data-id');
		 let id = colid.value;
		 
		 inviteForm.action = `/meeting/invite/${invite}?id=${id}`;
		 inviteForm.method = "get";
		 inviteForm.submit();
		 
	 })
	 
	 
	 
 })