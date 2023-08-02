/**
 * 임시 파일.
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 let btnOpen = document.querySelector('button#roomIn');
	 let roomId = document.querySelector('input#roomId');
	 let forms = document.querySelector('form#facechat');
	 
	 btnOpen.addEventListener('click',()=>{
		 
		 let roomNum = roomId.value;
		 
		 if(roomNum == ''){
			 
			 alert('방 번호 입력해주세요');
			 return;
			 
		 }
		 
		 forms.method = 'post';
		 forms.action = `/facechat/room`;
		 forms.submit();
		 
	 })
	 
 })