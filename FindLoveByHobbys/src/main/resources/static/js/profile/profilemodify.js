/**
 * 	    const title = document.querySelector('input#title').value;
	    const content = document.querySelector('textarea#content').value;
	    if (title === '' || content === '') {
	        alert('제목과 내용은 반드시 입력하세요.');
	        return;
	    }
	    
	    
	    	
		const btnDelete = document.querySelector('#btnDelete');
		btnDelete.addEventListener('click', (e) => {
			const check = confirm('프로필을 삭제하시겠습니까?');
				if(!check) {
					return;
				}		
					profileModifyForm.action = '/profile/delete';
					profileModifyForm.method = 'post';
					profileModifyForm.sumit();	
		});
 */

document.addEventListener('DOMContentLoaded', () => {

	// <form> 요소를 찾음.
	const profileModifyForm = document.querySelector('#profileModifyForm');
	
	const btnUpdate = document.querySelector('#btnUpdate');
	btnUpdate.addEventListener('click', (e) => {
		alert("프로필을 수정하셨습니다");
	
	    const check = confirm('변경 내용을 저장할까요?');
	    if (check) {
	        profileModifyForm.action = '/profile/update';
	        profileModifyForm.method = 'post';
	        profileModifyForm.submit();
	    }
	});

	
});