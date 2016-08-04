<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="http://avocatpoket.com/notiny.js"></script>

<script>

 var audio = new Audio('http://avocatpoket.com/mp3.mp3');

    function post()
    {
 
      audio.play();
    }


var NomVariable = 2;

$(document).ready( function() {
 
setInterval(function(){
		$("#screen").load('http://localhost:8080/ProjetPfe/test.jsf')
    }, 20);
  
 
});
</script>


<link href="http://avocatpoket.com/notiny.css" rel="stylesheet" />

<div id="screen">


</div>


</body>
</html>