<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Food Order</title>
</head>
<body>
<form action="/TakePhoto/CustomerOrder" method="post"  style="border:1px solid #ccc">
 


<input type="checkbox" name="LV" value="LV"> Lemon Vegetable
   <input type="text" name="qty1" value="0"><br>
  <input type="checkbox" name="OS" value="OS" > Orange Salad
  <input type="text" name="qty2" value="0"><br>
<input id="test" type="submit"
			value="Order"  style="width: 200px; height: 30px;" />
			
	<div id="demo"></div>
	<div id="payment1" style=display:none;> Please proceed to <a href="payment.jsp">payment </a></div>
	</form>
	<script type="text/javascript">
	
	function send() {
		//var imageData = canvas.toDataURL();
		var x = document.getElementById("payment1");
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("Post", "CustomerOrder", true);
		//xmlhttp.send(imageData);
		xmlhttp.onreadystatechange=function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("demo").innerHTML=xmlhttp.responseText;
				x.style.display="block";
			}
		}
		//window.location.replace("payment.jsp");
	};

	</script>
</body>
</html>