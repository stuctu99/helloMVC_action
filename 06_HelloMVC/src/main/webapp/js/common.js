function makeMemberTable(members){
	const $table=$("<table>");
	const $header=$("<tr>");
	$header.html("<th>아이디</th><th>이름</th>"
	+"<th>나이</th><th>성별</th><th>이메일</th>"
	+"<th>전화번호</th><th>취미</th><th>주소</th>"
	+"<th>등록일</th>");
	$table.append($header);
	members.forEach(e=>{
		const $tr=$("<tr>");
		const $userId=$("<td>").text(e['userId']);
		const $userName=$("<td>").text(e['userName']);
		const $age=$("<td>").text(e['age']);
		const $gender=$("<td>").text(e['gender']);
		const $email=$("<td>").text(e['email']);
		const $phone=$("<td>").text(e['phone']);
		const $address=$("<td>").text(e['address']);
		const $hobby=$("<td>").text(e['hobby']);
		const $enrollDate=$("<td>").text(e['enrollDate']);
		$tr.append($userId).append($userName)
		.append($age).append($gender).append($email)
		.append($phone).append($address)
		.append($hobby).append($enrollDate);
		$table.append($tr);
	});
	return $table;
}