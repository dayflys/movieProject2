<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.memberVO,model.vo.MovieVO, java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/myCss.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
<style>
.img{
          border-radius: 12px;
          transition: transform 0.2s ease-in-out;
          box-shadow: #0c3869 10px 10px 20px;
        }
.img:hover{
          transform: scale(1.05) translateY(-8px);
}
#scroll::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
}

.subtitle {
		height: 50px; 
	    top: 50%;
	    margin-top: -25px; /* Half this element's height */
     	font-weight : bold;
		background: linear-gradient(#869fd9 0%, white 100%);
		/*background-clip: text;*/
		-webkit-background-clip: text;
		color: transparent;
		filter: drop-shadow(2px 2px black);  
}

.subtitleyellow {
		height: 50px;
	    top: 50%;
	    margin-top: -25px; /* Half this element's height */
     	font-weight : bold;
		background: linear-gradient(#869fd9, white 100%);
		/*background-clip: text;*/
		-webkit-background-clip: text;
		color: transparent;
		filter: drop-shadow(2px 2px black);  
}

.toprank {
	font-weight : bold;
	background: linear-gradient(#265ff0 0%, white 100%);
	/*background-clip: text;*/
	-webkit-background-clip: text;
	color: transparent;
	filter: drop-shadow(2px 2px black);
}

.lowrank {
	background: linear-gradient(#265ff0 0%, white 100%);
	/*background-clip: text;*/
	-webkit-background-clip: text;
	color: transparent;
	filter: drop-shadow(2px 2px black);
}

.movietitle {
	  font-family: 'Exo 2', sans-serif;
	  font-weight: 100;
	  background: -webkit-linear-gradient(-86deg, #265ff0 5%, white 91%);
	  -webkit-background-clip: text;
	  -webkit-text-fill-color: transparent;
	  filter: drop-shadow(2px 2px black);
}

</style>
</head>
<body>
<div class="p-2 mt-3" >
		<% request.setCharacterEncoding("utf-8"); %>

	<div class="container mb-lg-5">
		<div class="d-flex flex-lg-row gap-3 mx-lg-auto">
			<form action="/movie/country" method="get">
				<input type="hidden" name="num" value="1">
				<input type="hidden" name="subject" value="국외 영화">
				<button class="btn btn-lg btn-dark " type="submit" >국외 영화</button>
			</form>
			<form action="/movie/country" method="get">
				<input type="hidden" name="num" value="0">
				<input type="hidden" name="subject" value="국내 영화">
				<button class="btn btn-lg btn-dark" type="submit" >국내 영화</button>
			</form>
			<form action="/movie/ganre" method="get">
				<input type="hidden" name="num" value="0">
				<input type="hidden" name="subject" value="상업 장르 영화">
				<button class="btn btn-lg btn-dark" type="submit" >상업 장르 영화</button>
			</form>
			<form action="/movie/ganre" method="get">
				<input type="hidden" name="num" value="1">
				<input type="hidden" name="subject" value="다양성 장르 영화">
				<button class="btn btn-lg btn-dark" type="submit" >다양성 장르 영화</button>
			</form>
			<form action="/movie/delete" method="get">
				<button class="btn btn-lg btn-dark" type="submit" ><img src="/resources/trash3.svg"/></button>
			</form>
			</div>
		</div>

<c:if test="${!empty subject}">
	<div class="container mb-3 mt-3" style="max-width:85vw; height:5vh;margin-bottom: 15px;">
		<h1 class = "subtitle " > 일별 ${subject[0]}</h1>
	</div>
		<div class ="container" >
			<div class="container-fluid d-flex flex-row" style="color:#265ff0; overflow:scroll; max-width:100vw; height:80vh;">
				<c:forEach var="i" begin="0" end="9" >
					<form action="/comment/select" method="get" style=" color: #869fd9;">
						<input type="image" class="mt-3 mx-3 img" id="movieImg" src="${select[i].getImgUrl().replaceAll("mit110","mit500")}" style=" max-width: 20vw;height:60vh" >
						<input type="hidden" name="id" value="${i}" >
						<input type="hidden" name="curMovie" value="select" >
						<input type="hidden" name="moviename" value="${select[i].getMovieNM()}" >
						<br>
						<c:if test="${select[i].getRank() eq 1}">
							<h1 class = "mt-3 text-center toprank">&nbsp;TOP ${select[i].getRank() }</h1>
						</c:if>
						<c:if test="${select[i].getRank() eq 2}">
							<h2 class = "mt-3 text-center toprank">&nbsp;TOP ${select[i].getRank() }</h2>
						</c:if>
						<c:if test="${select[i].getRank() eq 3}">
							<h3 class = "mt-3 text-center toprank">&nbsp;TOP ${select[i].getRank() }</h3>
						</c:if>
						<c:if test="${select[i].getRank() > 3}">
							<h4 class = "mt-3 text-center lowrank">&nbsp;TOP ${select[i].getRank() }</h4>
						</c:if>

						<h5 class = "text-center mb-3 movietitle">&nbsp;${select[i].getMovieNM() }</h5>
					</form>

				</c:forEach>
			</div>
		</div>
</c:if>


	<div class="container mt-5 " style="max-width:85vw; height:5vh;margin-bottom: 15px;">
		<h1 class = "subtitle " style=""> 일일 박스 오피스 랭킹</h1>
	</div>
	<div class ="container " >
		<div class="container-fluid d-flex flex-row"  style="color:#265ff0; overflow:scroll; max-width:100vw; height:80vh;">
			<c:forEach var="i" begin="0" end="9" >
		
				<form action="/comment/select" method="get" style=" color: #869fd9;">

					<input type="image" class="mt-3 mx-3 img" id="movieImg" src="${daily[i].getImgUrl().replaceAll("mit110","mit500")}" style=" max-width: 20vw;height:60vh" >

					<input type="hidden" name="id" value="${i}" >
					<input type="hidden" name="curMovie" value="daily" >
					<input type="hidden" name="moviename" value="${daily[i].getMovieNM()}" >
					<br>
					<c:if test="${daily[i].getRank() eq 1}">
					<h1 class = "mt-3 text-center toprank">&nbsp;TOP ${daily[i].getRank() }</h1>
					</c:if>
					<c:if test="${daily[i].getRank() eq 2}">
					<h2 class = "mt-3 text-center toprank">&nbsp;TOP ${daily[i].getRank() }</h2>
					</c:if>
					<c:if test="${daily[i].getRank() eq 3}">
					<h3 class = "mt-3 text-center toprank">&nbsp;TOP ${daily[i].getRank() }</h3>
					</c:if>
					<c:if test="${daily[i].getRank() > 3}">
					<h4 class = "mt-3 text-center lowrank">&nbsp;TOP ${daily[i].getRank() }</h4>
					</c:if>

					<h5 class = "text-center mb-3 movietitle">&nbsp;${daily[i].getMovieNM() }</h5>
				</form>
			
			</c:forEach>
			
		</div>
	</div>


	<div class="container mt-3" style="max-width:85vw; height:5vh;">
		<h1 class = "subtitleyellow"> 주간 박스 오피스 랭킹</h1>
	</div>
	<div class ="container " >
		<div class="container-fluid d-flex flex-row"  id="scroll" style="color:#265ff0; overflow:scroll; max-width:100vw; height:80vh;">
			<c:forEach var="i" begin="0" end="9" >
				<form action="/comment/select" method="get" style=" color: #869fd9;">
					<input type="image" class="mt-3 mx-3 img" src="${weekly[i].getImgUrl().replaceAll("mit110","mit500")}" style=" max-width: 20vw;height:60vh" >
					
					<input type="hidden" name="id" value="${i}" >
					<input type="hidden" name="curMovie" value="weekly" >
					<input type="hidden" name="moviename" value="${weekly[i].getMovieNM()}" >
					<br>
					<c:if test="${weekly[i].getRank() eq 1}">
					<h1 class = "mt-3 text-center toprank">&nbsp;TOP ${weekly[i].getRank() }</h1>
					</c:if>
					<c:if test="${weekly[i].getRank() eq 2}">
					<h2 class = "mt-3 text-center toprank">&nbsp;TOP ${weekly[i].getRank() }</h2>
					</c:if>
					<c:if test="${weekly[i].getRank() eq 3}">
					<h3 class = "mt-3 text-center toprank">&nbsp;TOP ${weekly[i].getRank() }</h3>
					</c:if>
					<c:if test="${weekly[i].getRank() > 3}">
					<h4 class = "mt-3 text-center lowrank">&nbsp;TOP ${weekly[i].getRank() }</h4>
					</c:if>
					
					<h5 class = "text-center mb-3 movietitle">&nbsp;${weekly[i].getMovieNM() }</h5>
				</form>
			
			</c:forEach>
		</div>
	</div>
	
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</html>