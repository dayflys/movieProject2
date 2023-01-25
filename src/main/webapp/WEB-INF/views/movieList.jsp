<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.movieedu.model.vo.MemberVO,com.example.movieedu.model.vo.MovieVO, java.util.List, java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/static/myCss.css" />
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

.grad {
  	background: radial-gradient(#D91883, #0f1b29);
}
.gradyellow {
  background: radial-gradient(#FAC919, #0f1b29);
  
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
<%
	String title = request.getParameter("title");

%>		
		<% request.setCharacterEncoding("utf-8"); %>	
	<div class="container mt-3 " style="max-width:85vw; height:5vh;margin-bottom: 15px;">
		<h1 class = "subtitle " style=""> 일일 박스 오피스 랭킹</h1>
	</div>
  <!-- style = "background-color:#265ff0;   border-radius:30px; box-shadow : 1px 3px 10px 0px #D91883" grad-->			
	<div class ="container " >
		<div class="container-fluid d-flex flex-row"  style="color:#265ff0; overflow:scroll; max-width:100vw; height:80vh;">
			<c:forEach var="i" begin="0" end="9" >

				<form method = "get" action ="/comment/select">

					<input type="image" class="mt-3 mx-3 img" src="${movielist.get(i).getImgUrl().replaceAll("mit110","mit500")}" style=" max-width: 20vw;height:60vh" >
					<input type="hidden" name ="moviename" value="${movielist.get(i).getMovieNM()}">
					<input type="hidden" name="id" value="${i}" >

				</form>
			
			</c:forEach>
			
		</div>
	</div>
	
</div>


</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</html>