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
			 
			 alert('숫자만 입력 가능합니다.');
			 return;
			 
		 }
		 
		 forms.method = 'get';
		 forms.action = `/facechat/room?roomId=${roomNum}`;
		 forms.submit();
		 
	 })
	 
 })