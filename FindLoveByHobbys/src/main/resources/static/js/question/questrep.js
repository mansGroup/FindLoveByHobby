/**
 * 
 */
document.addEventListener("DOMContentLoaded", function() {
        let replyContent = document.getElementById("replyContent").value;
        let submitBtn = document.getElementById("submitBtn");
        let editBtn = document.getElementById("editBtn");
        let deleteBtn = document.getElementById("deleteBtn");
        let saveEditBtn = document.getElementById("saveEditBtn");
        let cancelEditBtn = document.getElementById("cancelEditBtn");
		let textarea = document.getElementById("repmessage");
	
        if (replyContent !== null) { // replycontent 값이 null이 아니면
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
        /*
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
    });