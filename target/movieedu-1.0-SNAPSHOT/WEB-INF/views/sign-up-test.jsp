<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>회원가입 구현 테스트</title>
		<link rel="stylesheet" href="myCss.css" />
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
	</head>
	
	<body style="background-color:#0f1b29;">
	<% request.setCharacterEncoding("utf-8"); %>	
	
		<div class="container py-4" style="background-color:#0f1b29;">
            <div class="row align-items-center justify-content-between">
                <a class="navbar-brand h1 text-center">
                    <span class=" h1" style="color:#D0D0D0;">회원가입</span>                 
                </a>
            </div>
            
            <form action = "register" method = "post" id="signupForm" name="signupForm">
            	<div class="form-floating mt-4">
					 <input type="text" class="form-control" id="nickname" name="nickname" placeholder="holdid" required>
					 <label for="id">닉네임</label>
						 <div class="valid-feedback">사용가능</div>
						 <div class="invalid-feedback">4자리 이상 입력하세요.</div>
				</div>	
                <div class="form-floating mt-4">
					 <input type="text" class="form-control" id="id1" name="id" placeholder="holdid" required>
					 <label for="id">아이디</label>
						 <div class="valid-feedback">사용가능</div>
						 <div class="invalid-feedback">4자리 이상 입력하세요.</div>
				</div>		
							
				<div class="form-floating mt-4">
					<input type="password" class="form-control" id="pwd1" name="pwd" placeholder="holdpwd" required>
					<label for="pwd">비밀번호</label>
						<div class="valid-feedback">사용가능</div>
						<div class="invalid-feedback">4자리 이상 입력하세요.</div>
				</div>
				
				<div class="form-floating mt-4">
					<input type="password" class="form-control" id="repwd" name="repwd" placeholder="holdrepwd" required>
					<label for="repwd">비밀번호 재확인</label>
						<div class="valid-feedback">일치합니다.</div>
						<div class="invalid-feedback">일치하지 않습니다.</div>
				</div>
				
                <div class="form-floating mt-4">
                    <input type="text" class="form-control" id="name" name="name" placeholder="holdname" required>
                    <label for="name">이름</label>
                </div>
                
			    <div class="form-group">
			      <label for="select" class="form-label mt-4">성별</label>
				      <select class="form-select" id="select" name="gender">
				        <option value="남자" selected>남자</option>
				        <option value="여자" >여자</option>
				      </select>
			    </div>
			               
                <div class="form-group">
               		<label for="email1" class="form-label mt-4">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="이메일" required>
						<div class="invalid-feedback">유효하지 않은 이메일 입니다.</div>
                </div>
                
				<div class="d-grid mt-4 gap-2">
                    <button class="btn btn-primary btn-lg" type="submit">가입하기</button>
                </div>
                
            </form>
        </div>
        
        <script> 
        
	        function checkValid(self, isValid) {	
	    		if(isValid){
	    			self.classList.remove("is-invalid");
	    			self.classList.add("is-valid");
	    			
	    		} else{        			
	    			self.classList.remove("is-valid");
	    			self.classList.add("is-invalid");
	    		}
	    	}
        
	        document.querySelector("#nickname").addEventListener("input", function(){
        		let inputId=this.value;
        		isValid = inputId.length >= 4 ? true : false;   
      		
        		checkValid(this, isValid);
        	});
	        
        	document.querySelector("#id1").addEventListener("input", function(){
        		let inputId=this.value;
        		isValid = inputId.length >= 4 ? true : false;   
      		
        		checkValid(this, isValid);
        	});
        	
        	document.querySelector("#pwd1").addEventListener("input", function(){
        		let inputPwd=this.value;
        		isValid = inputPwd.length >= 4 ? true : false;   
      		
        		checkValid(this, isValid);
        	});
        	
        	document.querySelector('#repwd').addEventListener("input", function(){
        		let inputRepwd=this.value;
        		let pwd = document.querySelector("#pwd1").value;       		
        		isValid = inputRepwd == pwd ? true : false;
        		
        		checkValid(this, isValid);
        	});
        	
        	document.querySelector("#name").addEventListener("input", function(){
        		let inputName=this.value;
        		isValid = inputName.length > 1 ? true : false;   
      		
        		checkValid(this, isValid);
        	});
        	
        	document.querySelector("#email").addEventListener("input", function(){
        		let inputEmail=this.value;
        		let reg_email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        		
        		checkValid(this, reg_email.test(inputEmail));    		
        	});

        </script>     
	</body>
</html>