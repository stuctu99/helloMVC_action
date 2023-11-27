<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<section id="content">
	<h2 align="center" style="margin-top:200px">
		안녕하세요,MVC입니다.
	</h2>
	<h2>아이디검색</h2>
	<input type="search" id="searchId" list="data"><button>검색</button>
	<datalist id="data"></datalist>
	<script>
		$("#searchId").keyup(e=>{
			const value=e.target.value;
			$.ajax({
				url:"<%=request.getContextPath()%>/member/searchId.do",
				data:{"keyword":value},
				success:data=>{
					console.log(data);
					const userIds=data.split(",");
					$("#data").html("");
					userIds.forEach(e=>{
						const $op=$("<option>").attr("value",e).text(e);
						$("#data").append($op);
					});
				}
			})
		});
	</script>
	<button onclick="searchAllMember();">회원조회</button>
	<input type="text" id="key">
	<button onclick="searchName()">이름으로 조회</button>
	<div id="members"></div>
	<button id="member">회원저장</button>
	<script>
		$("#member").click(e=>{
			const member={
					userId:"bsyoo",
					password:"bslove",
					userName:"유병승",
					gender:"M",
					age:19,
					email:"teacherdev09@gmail.com",
					address:"경기도 시흥시",
					hobby:["운동","독서"]
			};
			$.post("<%=request.getContextPath()%>/ajax/insertMember.do",
					{data:JSON.stringify(member)},
					data=>{
						console.log(data);
					});
		})
		function searchName(){
			$.post("<%=request.getContextPath()%>/ajax/searchName.do",
					{"type":"username","key":$("#key").val()},
					data=>{
						$("#members").html(makeMemberTable(data));
					});
		}
		function searchAllMember(){
			$.get("<%=request.getContextPath()%>/ajax/memberAll.do",
					data=>{
						$("#members").html(makeMemberTable(data));
						//console.log(data);
					}
				);
		}
	</script>
	
</section>
<%@ include file="/views/common/footer.jsp" %>		
