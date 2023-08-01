/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	
	const inputUploadFiles = document.querySelector('input#uploadFiles');
	
	inputUploadFiles.addEventListener('input', uploadImage);
	
	function uploadImage(event) {
		console.log('들어옴')
		
		const formData = new FormData();
		
		const inputFile = $("input[type='file']");
		
		const files = inputFile[0].files;
		
		for (var i = 0; i < files.length; i++) {
            console.log(files[i]);
            formData.append("uploadFiles", files[i]);
        }
        
        ajax({
            url: '/api/upload/upload',
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            dataType:'json',
            success: function(result){
                console.log(result);
                //나중에 화면 처리
            },
            error: function(jqXHR, textStatus, errorThrown){
                console.log(textStatus);
            }

        });
    
		
	}
	
});