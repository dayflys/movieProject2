<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>info</title>
        <link rel="stylesheet" href="/resources/myCss2.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
<style>
    .subtitle {
        height: 50px;
        top: 50%;
        -webkit-background-clip: text;
        filter: drop-shadow(2px 2px black);
    }

</style>
    </head>
    <body style="background-color: #0f1b29;">

    <jsp:include page="header.jsp"/>
    <div class="container mx-lg-5" >
    <section class="section about-section gray-bg mt-5" id="about" style="border-radius: 50px">
        <div class="container" >
            <div class="align-items-center d-flex flex-row px-lg-5">
                <div class="col-lg-6 mx-1" >
                    <div class="about-avatar" style="shadow: black">
                        <img src="/resources/profile.png" style="border-radius: 50px; width: 25vw; height: 40vh">
                    </div>
                </div>
                <div class="col-lg-6" style="margin-left: 10px;">
                    <div class="about-text go-to">
                        <div class="row about-list">
                            <div class="col-md-6">
                                <div class="media">
                                    <label>닉네임</label>
                                    <h5>${sessionScope.get("user").getNickname()}</h5>
                                </div>
                                <div class="media">
                                    <label>E-mail</label>
                                    <h5>${sessionScope.get("user").getEmail()}</h5>
                                </div>
                                <div class="media">
                                    <label>가입날짜</label>
                                    <h5>${sessionScope.get("user").getCurdate()}</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <hr class="mt-3 mb-3" style="color: black;">
            <div class="container mb-4 mx-3" style="max-width:85vw; height:5vh;margin-bottom: 15px;">
                <h1 class = "subtitle " > 찜 목록 </h1>
            </div>
            <div class="counter mt-3 mx-auto " style="width: 80vw;">
                <div class="container-fluid d-flex flex-row" style="overflow: scroll;max-width: 90vw; height: 70vh;">
                <c:forEach var="dib" items = "${diblist}" begin="0" end="${diblist.size()}" >
                    <div class="container" >
                    <img src = "${dib.imgUrl}" alt = "" style="border-radius: 30px;max-width: 20vw;height:60vh;"/>
                    <%--삭제 버튼--%>
                        <div class="container-fluid">
                            <form action="/dib/delete" method="post">
                                <button class="btn-lg btn btn-dark mt-3 mx-auto" type = "submit">삭제</button>
                                <input type="hidden" name = "cnt" value="${dib.cnt}">
                                <input type="hidden" name = "nickname" value = ${sessionScope.user.nickname}>
                            </form>
                        </div>
                    </div>
                </c:forEach>
                </div>
            </div>
        </div>
    </section>
    </div>
    <jsp:include page="footer.jsp" />
    </body>
</html>
