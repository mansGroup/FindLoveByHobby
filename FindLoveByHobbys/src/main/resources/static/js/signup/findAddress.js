document.addEventListener('DOMContentLoaded', () => {
    const btnFindAddress = document.querySelector('button#btnFindAddress');


    btnFindAddress.addEventListener('click', () => sample4_execDaumPostcode());
    function sample4_execDaumPostcode() {
        new daum.Postcode({
                oncomplete : function(data) {
                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.querySelector("input#jibunAddress").value = data.jibunAddress;
                }
            }).open();
    }
});
