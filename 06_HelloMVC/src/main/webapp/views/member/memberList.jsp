<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.dto.Member,java.util.List,java.util.Arrays" %>    
<%
	List<Member> members=(List<Member>)request.getAttribute("members");
%>    

<%@ include file="/views/common/header.jsp" %>
<!-- <!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
   section#memberList-container {text-align:center;}
   
   section#memberList-container table#tbl-member {width:100%; border:1px solid gray; border-collapse:collapse;}
   section#memberList-container table#tbl-member th, table#tbl-member td {border:1px solid gray; padding:10px; }
       div#search-container {margin:0 0 10px 0; padding:3px; 
    background-color: rgba(0, 188, 212, 0.3);}
    div#search-userId{display:inline-block;}
    div#search-userName{display:none;}
    div#search-gender{display:none;}
    div#numPerpage-container{float:right;}
    form#numperPageFrm{display:inline;}
</style>
   
   <section id="memberList-container">
       <h2 class="text-info">회원관리</h2>
        <div id="search-container">
        	<span>검색타입 : </span>
        	<select id="searchType">
        		<option value="userId" >아이디</option>
        		<option value="userName" >회원이름</option>
        		<option value="gender" >성별</option>
        	</select>
				       	
        	<div id="search-userId">
        		<form action="<%=request.getContextPath()%>/admin/searchMember">
        			<input type="hidden" name="searchType" value="userId" >
       				<input type="text" name="searchKeyword" size="25" 
       				placeholder="검색할 아이디를 입력하세요" >
       				<button type="submit" class="btn btn-outline-danger">검색</button>
        		</form>
        	</div>
	        	
        	
        	<div id="search-userName">
        		<form action="<%=request.getContextPath()%>/admin/searchMember">
        			<input type="hidden" name="searchType" value="userName">
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 이름을 입력하세요">
        			<button type="submit" class="btn btn-outline-danger">검색</button>
        		</form>
        	</div>
        	<div id="search-gender">
        		<form action="<%=request.getContextPath()%>/admin/searchMember">
        			<input type="hidden" name="searchType" value="gender">
        			<label><input type="radio" name="searchKeyword" value="M" >남</label>
        			<label><input type="radio" name="searchKeyword" value="F" >여</label>
        			<button type="submit" class="btn btn-outline-danger">검색</button>
        		</form>
        	</div>
        </div>
        <div id="numPerpage-container">
        	페이지당 회원수 : 
        	<form id="numPerFrm" action="">
        		<select name="numPerpage" id="numPerpage">
        			<option value="10">10</option>
        			<option value="5" >5</option>
        			<option value="3" >3</option>
        		</select>
        	</form>
        </div>
       <table class="table table-striped">
           <thead>
               <tr>
                   <th>아이디</th>
				    <th>이름</th>
				    <th>성별</th>
				    <th>나이</th>
				    <th>이메일</th>
				    <th>전화번호</th>
				    <th>주소</th>
				    <th>취미</th>
				    <th>가입날짜</th>
       		   </tr>
    	</thead>
    	<tbody>
			<%if(!members.isEmpty()){ 
				for(Member m:members){%>
				<tr>
					<td><%=m.getUserId() %></td>
					<td><%=m.getUserName() %></td>
					<td><%=m.getGender() %></td>
					<td><%=m.getAge() %></td>
					<td><%=m.getEmail() %></td>
					<td><%=m.getPhone() %></td>
					<td><%=m.getAddress() %></td>
					<td>
						<%if(m.getHobby()!=null){ %>
							<%=Arrays.toString(m.getHobby()) %>
						<%} %>
						<%-- <%if(m.getHobby()!=null){%>
							<ul>
							<%for(String h : m.getHobby()){%>
								<li><%=h %></li>
							<%} %>
							</ul>
						<%} %> --%>
					</td>
					<td><%=m.getEnrollDate() %></td>
				</tr>
			<%	}
			}%>
    	</tbody>
       </table>
       <div id="pageBar" class="container">
       		<%=request.getAttribute("pageBar") %>
       </div>
   </section>
   <script>
   	$("#searchType").change(e=>{
   		const type=$(e.target).val();
   		console.log(type);
   		$("#search-container>div").hide();
   		$("#search-"+type).css("display","inline-block");
   	});
   
   </script>
   
<%@ include file="/views/common/footer.jsp" %>   