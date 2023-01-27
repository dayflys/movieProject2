<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-26
  Time: 오후 6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />

</head>
<body>
<h1>Hello 회원 정보</h1>
<div class = "container" >

    <a href="http://localhost:8088/log">메인페이지로</a>
    <%--찜 목록 나열--%>
    <c:forEach var="dib" items = "${diblist}" begin="0" end="${diblist.size()}" >
        <img src = "${dib.imgUrl}" alt = ""/>
        <%--삭제 버튼--%>
        <form action="/dib/delete" method="post">
            <button type = "submit">삭제</button>
            <input type="hidden" name = "cnt" value="${dib.cnt}">
            <input type="hidden" name = "nickname" value = ${sessionScope.user.nickname}>
        </form>
    </c:forEach>


</div>

</body>
</html>
