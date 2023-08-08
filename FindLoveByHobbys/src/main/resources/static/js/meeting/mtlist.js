/**
 * 
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 
	 const optionselect = document.querySelector('select#optionselect');
	 
	 optionselect.addEventListener('change',()=>{
		 
		 let selection = optionselect.value;
		 const optionvalue = document.querySelector('select#optionvalue');
		 let reqUrl = `/`
		 
		 if(selection == 0){
			 
			 optionvalue.innerHTML = `
			 
			 
			 `
			 
		 } else if(selection == 1){
			 
			 
		 } else {
			 
			 
		 }
		 
		 
	 })
	 
 })