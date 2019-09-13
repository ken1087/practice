<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kang homework</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
	body{
		
		text-align: center;
	}
	.container1{
		display:inline-block;
		width:90%;
	}
	.downbutton{
		text-align:left;
	}
</style>
</head>
<body>
	<div class="container1">
		<table id="download_table" class="table">
            <thead>
                <tr>
                	<!--Primary　Keyの項目5個  -->
                    <th>Number</th>
                    <th>UserId</th>
                    <th>StdNum</th>
                    <th>MyNum</th>
                    <th>Phone</th>
					<th>Name</th><!--項目10個追加表示  -->
					<th>Password</th>
					<th>Address</th>
					<th>Email</th>
					<th>Title</th>
					<th>Content</th>
                    <th>Country</th>
                    <th>Gender</th>
                    <th>CreateDate</th>
                    <th>UpdateDate</th>
                </tr>    
            </thead>
            <tbody>
            	<c:forEach var="map" items="${user}">	<!-- 		Modelに格納したデータ（users） 		-->			
            		<tr>
            			
            			<c:forEach var = "item" items="${map }">
            				<c:set var="pk" value="${item.key}"/>
		            		<c:forEach items="${fn:split(pk,'|')}" var="items">
		            			<!--Primary　Keyの項目5個  -->
		                   		<td>${items}</td>
		            		</c:forEach>
            				<td>${item.value[0]}</td>
	                   		<td>${item.value[1]}</td>
	                   		<td>${item.value[2]}</td>
	                   		<td>${item.value[3]}</td>
	                   		<td>${item.value[4]}</td>
	                   		<td>${item.value[5]}</td>
	                   		<td>${item.value[6]}</td>
	                   		<td>${item.value[7]}</td>
	                   		<td>${item.value[8]}</td>
	                   		<td>${item.value[9]}</td>
            			</c:forEach>
                   		
                   		<!--項目10個  -->
                   		
                	</tr>
            	</c:forEach>
            </tbody>
        </table>
        <div class="downbutton">
            <input type="button" id="download" value="TEXT DownLoad">
        </div>
        
	</div>
<script> //CSV FILE Download
    function download_csv(csv, filename) {
        var csvFile;
        var downloadLink;

        // CSV FILE
        csvFile = new Blob([csv], {type: "text/csv"});	//データ保存するBlob関数に配列とTypeを入力

        // Download link
        downloadLink 						= document.createElement("a");	//	a domを作成

        // File name
        downloadLink.download 		= filename;	//filenameを付ける

        // We have to create a link to the file
        downloadLink.href 				= window.URL.createObjectURL(csvFile);	//作成したa domにlinkを付ける

        // Make sure that the link is not displayed
        downloadLink.style.display 	= "none";	// 作成したa linkを見えないようにする

        // Lanzamos
        downloadLink.click();	//自動にClickする
    }

    function export_table_to_csv(html, filename) {
    	var csv = [];	//配列宣言
    	var rows = document.querySelectorAll("#download_table tr");	//Tableのtrを全部探す
    	
        for (var i = 0; i < rows.length; i++) {	//trの数でfor文を利用する
    		var row = [], cols = rows[i].querySelectorAll("td, th");	//cols = tr[i]の中にあるtd or thを全部探す
    		
            for (var j = 0; j < cols.length; j++) 
                row.push(cols[j].innerText);	//row配列にcolsのTextを挿入
            
    		csv.push(row.join(","));	//csv配列に一つにした値を渡す
    	}

        // Download CSV
        download_csv(csv.join("\n"), filename);	//download_csvの関数にcsvとファイル名を渡す
    }

    document.querySelector("#download").addEventListener("click", function () {	//　downloadと書いてあるIdをClickすると
        var html = document.querySelector("#download_table").outerHTML;	//download_tableというClassの値を探す
    	export_table_to_csv(html, "table.csv");	//	tableと保存するファイル名を関数export_table_to_csvに渡す
    });
</script>
</body>
</html>