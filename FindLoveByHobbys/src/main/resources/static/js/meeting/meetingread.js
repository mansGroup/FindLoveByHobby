/**
 * 
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 const inviteForm = document.querySelector('form#inviteForm');
	 const inviteBtn = document.querySelector('button#inviteBtn');
	 const colid = document.querySelector('input#colid');
	 
	 inviteBtn.addEventListener('click',(e)=>{
		 
		 
		 e.preventDefault();
		 
		 let invite = inviteBtn.getAttribute('data-id');
		 let id = colid.value;
		 
		 inviteForm.action = `/meeting/invite?party=${invite}&id=${id}`;
		 inviteForm.method = "get";
		 inviteForm.submit();
		 
	 })
	 
 })