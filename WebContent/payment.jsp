<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
$(document).ready(function(){
    $("button").click(function(){
        $.get("Order", function(data, status){
            alert("Your OrderId is: " + data + "\nStatus: " + status);
        });
    });
});
</script>
</head>
<body>

	<h2>Photo taken successfully!!</h2>
	<p>Your OrderId is <button > View Order</button></p>
	
	<div id="data"> 
	</div>
	<p>Please collect your order in 30 minutes</p>
	<h1>Please proceed for payment</h1>
</body>
</html>