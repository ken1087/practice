<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    	.search_box{
    		display: grid;
    		grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
    		grid-gap: 10px;
    	}
    </style>
</head>
<body>
    
    <div class="container">
        <form action="/search" method="GET" autocomplete='off'>
            <div class="search_box">
            	<!---------------------------------------Primary Key 入力欄--------------------------------------->
                	<div class="form-group">
                		<label for="num">Number</label>
                    	<input type="text" name="num" autocomplete="off" value="${num}" id="num" class="form-control">
                	</div>
                	<div class="form-group">
                		<label for="userid">Userid</label>
                		<input type="text" name="userid" autocomplete="off" value="${userid}" id="userid" class="form-control">
                	</div>
                	<div class="form-group">
                		<label for="stdnum">Stdnum</label>
                		<input type="text" name="stdnum" autocomplete="off" value="${stdnum}" id="stdnum" class="form-control">
                	</div>
                	<div class="form-group">
                		<label for="mynum">Mynum</label>
                		<input type="text" name="mynum" autocomplete="off" value="${mynum}" id="mynum" class="form-control">
                	</div>
                	<div class="form-group">
                		<label for="phone">Phone</label>
                		<input type="text" name="phone"  autocomplete="off" value="${phone}" id="phone" class="form-control">
                	</div>
              <div>
                    <input type="submit" value="Search">
        
                <!---------------------------------------データ入力欄--------------------------------------->
            </div>
            </div>
          	<div class="">
                <a href="/insert">date insert</a>	<!-- データ入力するページ移動  -->
            </div> 
        </form>    
        <table class="table">
            <thead>
                <tr>
                	<!--Primary　Keyの項目5個  -->
                    <th>Number</th>
                    <th>UserId</th>
                    <th>StdNum</th>
                    <th>MyNum</th>
                    <th>Phone</th>
                    
					<!--項目2個追加表示  -->
                    <th>Country</th>
                    <th>Gender</th>
                </tr>    
            </thead>
            <tbody>
            	<c:forEach  var="map" items="${users}">	<!-- 		Modelに格納したデータ（users） 		-->			
            		<tr>
                   		<!--Primary　Keyの項目5個  -->
                   		<c:forEach var="item" items="${map}">
                   			<c:set var="pk" value="${item.key}"/>
	                   		<c:forEach items="${fn:split(pk,'|')}" var="items">
								<td><a href="${item.key }">${items}</a></td>
							</c:forEach>
							
								<!--項目2個追加表示  -->
								<td>${item.value[6]}</td>
								<td>${item.value[7]}</td>
						</c:forEach>
                	</tr>
            	</c:forEach>
            </tbody>

        </table>
    </div>
</body>
</html>