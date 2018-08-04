<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Customer!</title>
<link rel="stylesheet" href="styles1.css">

</head>
<body>
	<div>
		<video id="videoID" autoplay style="border: 7px solid black; border-radius: 100px;"></video>
	</div>
	<div>
		<canvas id="canvasID"  style="border: 7px solid black; border-radius: 100px;  "></canvas>
	</div>
	
	<div >
		<input class="button" type="button" value="Take photo" onclick="capture()"
			style="width: 200px; height: 40px; " /> 
		<input  class="button" type="button"
			value="Confirm" onclick=" send();" style="width: 200px; height: 40px; " />
	</div><br>
	
	<div id="demo"></div><br>
	
	<div>
	<button onclick="location.href = 'http://localhost:8080/TakePhoto/Store.jsp';"  style=" width:100px; height:30px; ">
	HOME PAGE
	</button>
	</div>
	<script type="text/javascript">
		var video = document.getElementById('videoID');
		var canvas = document.getElementById('canvasID');
		var context = canvas.getContext('2d');
		
		window.URL = window.URL || window.webkitURL;
		navigator.getUserMedia = navigator.getUserMedia
				|| navigator.webkitGetUserMedia || navigator.mozGetUserMedia
				|| navigator.msGetUserMedia;

		navigator.getUserMedia({
			video : true
		}, function(stream) {
			video.src = window.URL.createObjectURL(stream);
		}, function(e) {
			console.log('An error happened:', e);
		});

		function capture() {
			
			context.drawImage(video, 0, 0, canvas.width, canvas.height);
			
			
		};
	
		function send() {
			document.getElementById("demo").innerHTML="Loading..";
			document.getElementById("videoID").style.f
			var imageData = canvas.toDataURL();
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.open("POST", "/TakePhoto/LStoreCustomer", true);
			xmlhttp.send(imageData);
			xmlhttp.onreadystatechange=function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					document.getElementById("demo").innerHTML=xmlhttp.responseText;
				}
			}
	//	load();
			//window.location.replace("thankyou.jsp");
		};
	</script>

</body>
</html>

