/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
        
       
        
        const btnDelete = document.getElementById("btnDelete");
		const btnEdit = document.querySelector('#btnEdit');
		const replyid = document.querySelector('input#replyid');
	   
    	btnDelete.addEventListener('click', () => {
       
       const check = confirm('삭제할까요?')
        
        if(check) {
		
        modifyForm.method = 'post';
        modifyForm.submit();
        }
          
    });
    
    btnEdit.addEventListener('click',(e)=>{
		
		e.preventDefault();
		
		modifyForm.method = 'post';
		modifyForm.action = `/questionrep/update?id=${replyid.value}`;
		modifyForm.submit();
		
	})

   
      });
    
    /*
    editBtn.addEventListener('click', (e) => {
		e.preventDefault();
        // 제목과 내용이 입력되어 있는지 체크. 
        const replyContent = document.querySelector('replyContent').value; // textarea에 입력된 값.
        if(replyContent === '') {
            alert("내용은 반드시 입력");
            return; // 함수 종료
        }
        
        const check = confirm('변경 내용을 저장할까요?');
        if(check) {
            
        
        modifyForm.action = './update'; // 폼 요청 주소
        modifyForm.method = 'post'; // 폼 요청 방식
        modifyForm.submit();
        }
        
    });
	
	
	
       /* if (replyContent !== null) { // replycontent 값이 null이 아니면
            submitBtn.style.display = "none";  // 답변 등록 버튼 숨김
            editBtn.style.display = "block";   // 수정 버튼 보임
            deleteBtn.style.display = "block"; // 삭제 버튼 보임
            saveEditBtn.style.display = "none";
            cancelEditBtn.style.display = "none";
            textarea.readOnly = true; 
        } else {
            submitBtn.style.display = "block"; 
            editBtn.style.display = "none";    
            deleteBtn.style.display = "none";  
            saveEditBtn.style.display = "none"; 
            cancelEditBtn.style.display = "none"; 
            textarea.readOnly = false;
        }
        
        function enableEditMode() { // 수정 버튼을 눌렀을 때
            editBtn.style.display = "none";         // "수정" 버튼 숨김
            deleteBtn.style.display = "none";       // "삭제" 버튼 숨김
            saveEditBtn.style.display = "block";    // "수정 완료" 버튼 보임
            cancelEditBtn.style.display = "block";  // "수정 취소" 버튼 보임
            textarea.readOnly = false;              // textarea 수정 가능 상태로 설정
        }
        
         function disableEditMode() { // 수정 완료, 취소 버튼을 눌렀을 때
            editBtn.style.display = "block";        // "수정" 버튼 보임
            deleteBtn.style.display = "block";      // "삭제" 버튼 보임
            saveEditBtn.style.display = "none";     // "수정 완료" 버튼 숨김
            cancelEditBtn.style.display = "none";   // "수정 취소" 버튼 숨김
            textarea.readOnly = true;               // textarea 읽기 전용으로 설정
        }

            cancelEditBtn.addEventListener("click", function() {
                disableEditMode();
            });
			
			editBtn.addEventListener("click", function() {
                enableEditMode();
            });
			
            saveEditBtn.addEventListener("click", function() {
                // 여기에 수정을 저장하는 로직 추가
                disableEditMode();  // 수정 완료 후 다시 읽기 전용 모드로 변경
            });
            */
  