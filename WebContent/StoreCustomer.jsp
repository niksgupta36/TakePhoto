<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
/* Center the loader */
#loader {
  position: absolute;
  left: 50%;
  top: 50%;
  z-index: 1;
  width: 150px;
  height: 150px;
  margin: -75px 0 0 -75px;
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite;
  animation: spin 2s linear infinite;
}

@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

/* Add animation to "page content" */
.animate-bottom {
  position: relative;
  -webkit-animation-name: animatebottom;
  -webkit-animation-duration: 1s;
  animation-name: animatebottom;
  animation-duration: 1s
}

@-webkit-keyframes animatebottom {
  from { bottom:-100px; opacity:0 } 
  to { bottom:0px; opacity:1 }
}

@keyframes animatebottom { 
  from{ bottom:-100px; opacity:0 } 
  to{ bottom:0; opacity:1 }
}

#myDiv {
  display: none;
  text-align: center;
}
</style>
<title>Welcome Customer!</title>
</head>
<body onload="myFunction()" style="margin:0;">
	<div id="live">
		<video id="videoID" autoplay style="border: 1px solid black;"></video>
	</div>
	<div id="static">
		<canvas id="canvasID" style="border: 1px solid black;"></canvas>
	</div>
	<tr>
       <td>Upload your photo </td>
       <td>
           <input type="file" name="file" value="file">
       </td>
   </tr> 
	<div>
		<input type="button" value="Take photo" onclick="capture()"
			style="width: 200px; height: 30px;" /> 
			</div>
			<div>
		<input type="button"
			value="Send" onclick="  send();" style="width: 200px; height: 30px;" />
		
	</div>
	<div id="loading"></div>
	<div id="demo"></div>
	<script type="text/javascript">
	var myVar;

	function myFunction() {
	    myVar = setTimeout(showPage, 3000);
	}
	function showPage() {
		  document.getElementById("loader").style.display = "none";
		  document.getElementById("myDiv").style.display = "block";
		}
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
			document.getElementById("loading").innerHTML="Loading..";
			var imageData = canvas.toDataURL();
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.open("POST", "/TakePhoto/StoreCustomer", true);
			xmlhttp.send(imageData);
			
			xmlhttp.onreadystatechange=function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					document.getElementById("demo").innerHTML=xmlhttp.responseText;
				}
			}
			//window.location.replace("thankyou.jsp");
		};
	</script>

</body>
</html>

