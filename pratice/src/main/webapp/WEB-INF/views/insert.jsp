<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>kang homework</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
    	.insert_box{
    		display: grid;
    		grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
    		grid-gap: 10px;
    	}
    	.buttonbox{
    		width:100px;
    		display:grid;
    		grid-template-columns: auto auto;
    		grid-gap: 10px;
    	}
    </style>
</head>
<body>
    <div class="container">
        <form action="/datainsert" method="POST" onsubmit="return join()">
        	<div  class="insert_box">
	        	<div class="form-group">
	            	<label for="num">Number</label>
		        	<input type="text" name="num" autocomplete="off" id="num"  required="required" class="form-control">
		        </div>
		        <div class="form-group">
	                <label for="userid">Userid</label>
		            <input type="text" name="userid" autocomplete="off" id="userid"  required="required" class="form-control">
		        </div>
		        <div class="form-group">
	               	<label for="stdnum">Stdnum</label>
		            <input type="text" name="stdnum" autocomplete="off" id="stdnum"  required="required" class="form-control">
		        </div>
		        <div class="form-group">
	                <label for="mynum">Mynum</label>
		            <input type="text" name="mynum" autocomplete="off" id="mynum"  required="required" class="form-control">
		        </div>
		        <div class="form-group">
	                <label for="phone">Phone</label>
		            <input type="text" name="phone" autocomplete="off" id="phone" required="required" class="form-control">
				</div>
				<div class="form-group">
	                <label for="name">Name</label>
		            <input type="text" name="name" autocomplete="off" required="required" class="form-control">
		        </div>
		        <div class="form-group">
	                <label for="password">Password</label>
		            <input type="text" name="password" autocomplete="off" required="required" class="form-control">
		        </div>
		        <div class="form-group">
	                <label for="addr">Address</label>
		            <input type="text" name="addr" autocomplete="off" required="required" class="form-control">
		        </div>
		        <div class="form-group">
	                <label for="title">Title　 </label>
		            <input type="text" name="title" id="title" autocomplete="off" required="required" class="form-control">
		        </div>
		        <div class="form-group">
	                <label for="content">Content</label>
		            <input type="text" name="content" autocomplete="off"  required="required" class="form-control">
		        </div>
		        <div class="form-group">
	                <label for="country">Country</label>
		            <input type="text" name="country" autocomplete="off" required="required" class="form-control">
		        </div>
		        <div class="form-group">
	               	<label for="email">Email</label>
		            <input type="text" name="email" autocomplete="off"  required="required" class="form-control">
		        </div>    
		        <div class="form-group">
	              	<label for="gender">Gender</label>
		            <input type="text" name="gender" autocomplete="off"  required="required" class="form-control">
		        </div>
	       	</div>
	        <div class="buttonbox">
	        	<div><input type="button" value="check" id="checkid"></div>
	        	<div><input type="submit" value="insert"></div>
	        </div>
        </form>  
        
    </div>
    <script>
    	var check = 1;
		// 프라이머리 키 중복 체크
    	$("#checkid").click(function(){
        		var num_element 			= 		document.querySelector("#num");
        		var userid_element 		= 		document.querySelector("#userid");
        		var stdnum_element 		= 		document.querySelector("#stdnum");
        		var mynum_element 		= 		document.querySelector("#mynum");
        		var phone_element 		= 		document.querySelector("#phone");
        		
                var num 		= 		num_element.value;
                var userid 		= 		userid_element.value;
                var stdnum 	= 		stdnum_element.value;
                var mynum 	= 		mynum_element.value;
                var phone 		= 		phone_element.value;
		
                $.ajax({
                    
                    url:'/checkid',
                    headers: { 
                        "Content-Type": "application/json",                    
                    },
                    type: 'POST',
                    data: JSON.stringify({
                        'num' : num,
                        'userid' : userid,
                        'stdnum' : stdnum,
                        'mynum' : mynum,
                        'phone' : phone
                    }),
                    success: function (obj) {
                        if(1 == obj){
                            alert("使用可能"); 
                            check=0;                
                        }else{
                            alert("重複");
                          	check=1;
                        }
                    },
                    error: function () {               
                        alert("error");
                       
                    }
                })  
                
      
        })
    	
        function join(){
			if(check == 1){
				alert("重複チェックしてください");
				return false;
			}
        }
    </script>
</body>
</html>