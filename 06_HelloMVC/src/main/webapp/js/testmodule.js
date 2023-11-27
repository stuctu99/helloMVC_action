/**
 * 
 */

const fn_membervalidate=()=>{
		//반환값이 false면 submit실행하지않음
		//true면 정상실행
		const userId=$("#userId_").val();
		if(userId.length<4){
			alert("아이디는 4글자 이상입력하세요 ");
			return false;
		}
		const reg = /^(?=.*[A-Za-z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{5,15}$/;
		const password=$("#password_").val();
		if(!reg.test(password)){
			alert("비밀번호는 영문자, 특수기호,숫자를 반드시 포함해야합니다.");
			$("#password_").val("");
			$("#password_2").val("");
			$("#password_").focus();
			return false;
		}
		
			
	}