<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.dto.Member,java.util.List,java.util.Arrays" %>    
<%@ include file="/views/common/header.jsp"%>
<%
	Member m=(Member)request.getAttribute("member");
	List<String> hobby=Arrays.asList(m.getHobby());
%>    
<section id=enroll-container>
		<h2>회원 정보 수정</h2>
		<form id="memberFrm" method="post" >
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" id="userId_" 
						value="<%=m.getUserId()%>" readonly>
					</td>
				</tr>
<!-- 				<tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="password" id="password_">
					</td>
				</tr>
				<tr>
					<th>패스워드확인</th>
					<td>	
						<input type="password" id="password_2"><br>
					</td>
				</tr>  --> 
				<tr>
					<th>이름</th>
					<td>	
					<input type="text"  name="userName" id="userName" 
					value="<%=m.getUserName()%>" required><br>
					</td>
				</tr>
				<tr>
					<th>나이</th>
					<td>	
					<input type="number" name="age" id="age"
					value="<%=m.getAge()%>"><br>
					</td>
				</tr> 
				<tr>
					<th>이메일</th>
					<td>	
						<input type="email" placeholder="abc@xyz.com"
						 value="<%=m.getEmail() %>" name="email" id="email"><br>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>	
						<input type="tel" placeholder="(-없이)01012345678" 
						value="<%=m.getPhone() %>" name="phone" id="phone" maxlength="11"><br>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>	
						<input type="text" placeholder="" 
						 value="<%=m.getAddress() %>" name="address" id="address" ><br>
					</td>
				</tr>
				<tr>
					<th>성별 </th>
					<td>
							<input type="radio" name="gender" id="gender0" value="M" 
							<%=m.getGender().equals("M")?"checked":"" %>>
							<label for="gender0">남</label>
							<input type="radio" name="gender" id="gender1" value="F"
							<%=m.getGender().equals("F")?"checked":"" %>>
							<label for="gender1">여</label>
					</td>
				</tr>
				<tr>
					<th>취미 </th>
					<td>
						<input type="checkbox" name="hobby" id="hobby0" value="운동" 
						<%=hobby.contains("운동")?"checked":"" %>><label for="hobby0">운동</label>
						<input type="checkbox" name="hobby" id="hobby1" value="등산" 
						<%=hobby.contains("등산")?"checked":"" %>><label for="hobby1">등산</label>
						<input type="checkbox" name="hobby" id="hobby2" value="독서" 
						<%=hobby.contains("독서")?"checked":"" %>><label for="hobby2">독서</label><br />
						<input type="checkbox" name="hobby" id="hobby3" value="게임" 
						<%=hobby.contains("게임")?"checked":"" %>><label for="hobby3">게임</label>
						<input type="checkbox" name="hobby" id="hobby4" value="여행" 
						<%=hobby.contains("여행")?"checked":"" %>><label for="hobby4">여행</label><br />
					</td>
				</tr>
			</table>
			<input type="button" value="정보수정"/>
			<input type="button" value="비밀번호수정"
			 onclick="fn_changepassword();"/>
			<!-- 비밀번호 수정 창이 open되고 거기에서 비밀번호 수정시키기! -->
			<input type="button" value="탈퇴"/>
		</form>
	</section>
	<script>
		const fn_changepassword=()=>{
			open("<%=request.getContextPath()%>/member/updatePassword.do?userId=<%=m.getUserId()%>",
					"_blank","width=400,height=210");
		}
	
	</script>
<%@ include file="/views/common/footer.jsp"%>