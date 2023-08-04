/**
 * 
 */

 function count(obj){
	 var chkBox = document.getElementsByName("check");
	 var chkCnt = 0;
	 
	 for(var i = 0; i < chkBox.length; i++) {
		 if(chkBox[i].checked) {
			 chkCnt++;
		 }
	 }
	 
	 if(chkCnt > 3) {
		 alert("최대 3개까지 선택할 수 있습니다.");
		 obj.checked = false;
		 return false;
	 }
	 
 }