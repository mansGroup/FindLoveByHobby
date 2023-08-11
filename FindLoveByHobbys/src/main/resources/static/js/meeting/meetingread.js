/**
 * 
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 const inviteForm = document.querySelector('form#inviteForm');
	 const inviteBtn = document.querySelector('button#inviteBtn');
	 const colid = document.querySelector('input#colid');
	 
	 inviteBtn.addEventListener('click',(e)=>{
		 
		 e.preventDefault();
		 
		 inviteForm.action = "/meeting/invite";
		 inviteForm.method = "get";
		 inviteForm.submit();
		 
	 })
	 
 })