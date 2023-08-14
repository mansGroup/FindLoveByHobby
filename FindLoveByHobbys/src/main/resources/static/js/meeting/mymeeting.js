/**
 * 
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 let imagelist = document.querySelectorAll('img.grayimg');
	 const statusvalue = document.querySelector('input#statusvalue');
	 
	 
	 if(statusvalue.value == 1){
		 
		 for(let x of imagelist){
			 
			 x.style.filter = 'grayscale(100%)';
			 
		 }
		 
	 }
	 
	 
	 
	 
 })