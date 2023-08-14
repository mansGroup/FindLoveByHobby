/**
 * 
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 const inviteForm = document.querySelector('form#inviteForm');
	 const inviteBtn = document.querySelector('button#inviteBtn');
	 const colid = document.querySelector('input#colid');
	 const inviteduser = document.querySelectorAll('div.inviteduser');
	 
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