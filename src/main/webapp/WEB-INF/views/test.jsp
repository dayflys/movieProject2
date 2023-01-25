
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>


<body>
<c:if test="${ !empty list }" >
    <hr>
    <h2>select comment</h2>
    <table>
        <c:forEach var="vo"  items="${list}">
            <tr>
                <td class="${vo.cnt}">${ vo.moviename }</td>
                <td class="${vo.cnt}">${ vo.content }</td>

            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${ !empty msg }" >
    <script>
        alert('${ msg }');
    </script>
</c:if>
</body>
</html>
