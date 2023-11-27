<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<style>
	.success{
		color:green;
	}
	.fail{
		color:red;
	}
</style>
<section id=enroll-container>
        <h2>회원 가입 정보 입력</h2>
        <form action="<%=request.getContextPath() %>/member/enrollMemberEnd.do" method="post" onsubmit="return fn_membervalidate();" >
        <table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" placeholder="4글자이상" name="userId" id="userId_" >
					<!-- <input type="button" value="중복확인"
					 onclick="fn_idduplicate();"> -->
					 <span id="idresult"></span>
					<script>
						$("#userId_").keyup(e=>{
							const userId=e.target.value;
							if(userId.length>=4){
								$.get("<%=request.getContextPath()%>/ajax/idduplicate.do?userId="+userId,
										data=>{
											console.log(data);
											let msg="",cl="";
											$("#idresult").removeClass("success fail");
											if(data=='true'){
												msg="사용할 수 있는 아이디입니다.";
												cl="success";
											}else{
												msg="사용할 수 없는 아이디입니다.";
												cl="fail";
											}
											$("#idresult").text(msg).addClass(cl);
											/* $("#idresult").text(msg).toggleClass("success fail"); */
										});
							}else{
								$("#idresult").html("");
							}
						});
					</script> 
				</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="password" id="password_" ><br>
				</td>
			</tr>
			<tr>
				<th>패스워드확인</th>
				<td>	
					<input type="password" id="password_2" ><br>
				</td>
			</tr>  
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="userName" id="userName" ><br>
				</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>	
				<input type="number" name="age" id="age" required><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M">
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동"><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산"><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임"><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" >
		<input type="reset" value="취소">
        </form>
    </section>
<script>
	const fn_idduplicate=()=>{
		const userId=$("#userId_").val().trim();
		if(userId.length>=4){
			const child=open("<%=request.getContextPath()%>/member/idDuplicate.do?userId="+userId,
				"_blank","width=300,height=200");
			
		}else{
			alert("아이디는 4글자이상 입력하세요");
			$("#userId_").focus();
		}
	}

</script>    
<script src="<%=request.getContextPath()%>/js/testmodule.js"></script>

<%@ include file="/views/common/footer.jsp"%>