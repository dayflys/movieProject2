<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%--<link rel="stylesheet" href="/resources/static/myCss.css" />--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
<style>	
	.icon-white{
  		color: white;
	}
	.bg-color{
		background-color:#D0D0D0;
		border:none;
	}
	.color{
		color:#D0D0D0;
	}
	.bg{
		background-color:#0f1b29;
	}
	.img{
		border-radius: 12px;
		box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;
		display: block;
		  margin-left: auto;
		  margin-right: auto;
		  width: 50%;
		
	}
	.content{
		color:blue;
		font-weghit: bold;
		text-shadow: 2px 2px 2px gray; 
	}
	.com_date{
		font-size : 2px;
		color : gray;
	}
</style>
</head>
<script>

	console.log("${sessionScope.movielist[movieId].movieNM}")
</script>
<body   class="bg color " style=" width: 100vw; height: 100vh;">

	<jsp:include page="header.jsp"></jsp:include>
	
		<div class="container bg-white mx-auto mt-3" style="width:80vw; height:auto; color:black; border-radius:30px; padding-bottom:15px;">
			<br>
			<h1 class = "text-center" style = "text-shadow: 2px 2px 2px gray; ">"${sessionScope.movielist[movieId].movieNM}"</h1>
			<hr style="color:black;">
			<img class="mt-3 img " src="${sessionScope.movielist[movieId].imgUrl.replaceAll("mit110", "mit500")}"style="width:400px;height:600px;">
			<form action = "/dib/insert" method = "post">
				<button class="btn text-white col-2" type="submit" id="save" style="float:right;
				border:0.1px solid black;background-color:#0c3869;" <c:if test="${sessionScope.user == null}"> disabled </c:if>>영화 찜하기</button>
				<input type="hidden" name="moviename" value="${sessionScope.movielist[movieId].movieNM}">
				<input type="hidden" name="imgurl" value="${sessionScope.movielist[movieId].imgUrl}">
				<input type="hidden" name="nickname" value="${sessionScope.user.nickname}">
			</form>

			<hr style="color:black;">
			<p><span class = "content">부제 :</span> "${sessionScope.movielist[movieId].subtitle}"  </p>
			<p><span class = "content">박스 오피스 순위 :</span> "${sessionScope.movielist[movieId].rank}"위 </p>
			<p><span class = "content">관객 평점:</span> "${sessionScope.movielist[movieId].userRating}" 점</p>
			<p><span class = "content">개봉 년도 :</span> "${sessionScope.movielist[movieId].pubDate}" </p>
			<p><span class = "content">영화 감독 :</span> "${sessionScope.movielist[movieId].director}" </p>
			<p><span class = "content">영화 배우 :</span> "${sessionScope.movielist[movieId].actor}"</p>




			<hr style="color:black;">
			<form action = "/comment/insert" method = "post">
				<div class="input-group mb-3 mx-auto floating-right row">
					<input type="hidden" name="moviename" value="${sessionScope.movielist[movieId].movieNM}">
					<input type="hidden" name="nickname" value="${sessionScope.user.nickname}">
					<input type="hidden" name="prehour" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" >
					<input type="hidden" name="id" value="${movieId}">
					<%--<input type="hidden" name="id" value="<%= id%>">--%>
					<c:if test="${sessionScope.user != null}">
					<input type="text" name="content" class="form-control col-10" placeholder="댓글을 작성해주세요" aria-describedby="button-addon2">
					<button class="btn col-2" type="submit" id="button-addon2" style="border:0.1px solid black;background-color:#D0D0D0;" <%--<%if (nick.equals("")){ %>disabled <%} %>--%> >게시</button>
					</c:if>
					<c:if test="${sessionScope.user == null}">
					<input type="text" name="content" class="form-control col-10" placeholder="댓글을 작성하려면 로그인 해주세요" aria-describedby="button-addon2">
						<button class="btn col-2" type="submit" id="button-addon2" style="border:0.1px solid black;background-color:#D0D0D0;" disabled>게시</button>
					</c:if>
				</div>
			</form>
			<hr style="color:black;">
			<table class="table table-striped table-sm table caption-top" style = "font-weight :bold ;">
				<caption>Comment</caption>
				<thead class = "table-dark">
					<tr>
						<th class="col-2">닉네임</th><th class="col-4">댓글 내용</th>
						<th class="col-2">좋아요</th><th class="col-1">수정</th><th class="col-1">삭제</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="vo" items = "${list}"  begin="0" end="${list.size()}" >
					<tr>
						<td class="col-2">${vo.nickname}
						<p class = "com_date">${vo.prehour}</p></td>
						<td class="col-4">${vo.content}</td>
						<td class="col-2">
						
							<div style="display:flex; flex-direction:row;">
								<p class="mx-3">${vo.like}</p>

								<c:if test="${sessionScope.user != null}">
								<form action="/comment/like" method="post">
									<input type="image" class="mx-1 border rounded"  style="width:20px; height:20px;" src="/resources/static/plus-lg.svg">
									<input type="hidden" name="like"  value="${vo.like+1}" >
									<input type = "hidden" name = "cnt" value = "${vo.cnt}">
									<input type = "hidden" name = "moviename" value="${vo.moviename}">
									<input type="hidden" name="id" value="${movieId}">
								</form>
								<form action="/comment/like" method="post">
									<input type="image"  class="mx-1 border rounded" style="width:20px; height:20px;" src="/resources/static/dash-square.svg">
									<input type="hidden" name="like"  value="${vo.like-1}" >
									<input type = "hidden" name = "cnt" value = "${vo.cnt}">
									<input type = "hidden" name = "moviename" value="${vo.moviename}">
									<input type="hidden" name="id" value="${movieId}">
								</form>

								</c:if>
							</div>
							
						</td>

						<td class="col-1">
							<c:if test="${vo.nickname == sessionScope.user.nickname}">
							<button type="button"
									class="btn btn-sm btn-outline-primary"
									data-bs-toggle="modal"
									data-bs-target="#comment-edit-modal"
									data-bs-action="update"
									data-bs-cnt="${vo.cnt}"
									data-bs-movie="${vo.moviename}"
									data-bs-id="${movieId}">수정
							</button>
							</c:if>
						</td>

						<td class="col-1">
						<c:if test="${vo.nickname == sessionScope.user.nickname}">
							<form action="/comment/delete" method="post">
								<input type="image" name="delete" src="/resources/static/delete.png" style="width:20px; height:20px;">
								<input type = "hidden" name = "cnt" value = "${vo.cnt}">
								<input type="hidden" name="moviename" value="${vo.moviename}" >
								<input type="hidden" name="id" value="${movieId}">
							</form>
						</c:if>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>

	<!-- Modal -->
<div class="modal fade" id="comment-edit-modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">댓글 수정</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<!-- 댓글 작성 폼 -->
				<form>
					<!-- 댓글 본문 입력 -->
					<div class="mb-3">
						<label class="form-label">댓글 내용</label>
						<textarea class="form-control form-control-sm" rows="3" id="edit-comment-body"></textarea>
					</div>

					<!-- 히든 인풋 -->
					<input type="hidden" id="edit-comment-id" value = "${movieId}">
					<input type="hidden" id="edit-comment-prehour" value = "<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/>">
					<input type="hidden" id="edit-comment-cnt" value = "1">
					<input type="hidden" id="edit-comment-movie" value = "${sessionScope.movielist[movieId].movieNM}">

					<!-- 전송 버튼 -->
					<button type="button" class="btn btn-outline-primary btn-sm" id="comment-update-btn">수정 완료</button>
				</form>
			</div>
		</div>
	</div>
</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script>

//수정 완료 버튼
const commentUpdateBtn = document.querySelector("#comment-update-btn");

{
    // 모달 요소 선택
    const commentEditModal = document.querySelector("#comment-edit-modal");

    // 모달 이벤트 감지
    commentEditModal.addEventListener("show.bs.modal", function(event) {
        // 트리거 버튼 선택
        const triggerBtn = event.relatedTarget;
        // 데이터 가져오기
        const cnt= triggerBtn.getAttribute("data-bs-cnt");
        // 데이터 반영
        document.querySelector("#edit-comment-cnt").value = cnt;

    });

{

}	// 수정 완료 버튼 누를 시!
	commentUpdateBtn.addEventListener("click",function() {
	    // 수정 객체 댓글을 생성
	    const comment = {
			id: document.querySelector("#edit-comment-id").value,
	        prehour: document.querySelector("#edit-comment-prehour").value,
	        content: document.querySelector("#edit-comment-body").value,
	        cnt: document.querySelector("#edit-comment-cnt").value,
	        moviename: document.querySelector("#edit-comment-movie").value,
	    };
	
	    console.log(comment);
	
	    // 수정 REST API 호출 - fetch()
	    fetch("/comment/update", {
	        method: "POST",          
	        headers: {
	            'Content-Type': 'application/json',
	          },
	        body: JSON.stringify(comment), // 수정된 댓글 객체를 JSON 으로 전달
	    }).then(response => {
	        // http 응답 코드에 따른 메세지 출력
	            const msg = (response.ok) ? "댓글 수정이 완료 되었습니다." : "댓글 수정 실패...!";
	            window.alert(msg);
	        // 현재 페이지를 새로 고침
	        window.location.reload();
	    });
	});

}
	
</script>
</html>