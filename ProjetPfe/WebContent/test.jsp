<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<%
String connectionURL = "jdbc:mysql://localhost:3306/pfe?user=root;password=";
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pfe","root","");
statement = connection.createStatement();

rs=statement.executeQuery(" SELECT count(nom) from medecin");
int count=0;
while (rs.next()) {
	count = rs.getInt("count(nom)");

}
out.println(""+count+"");
%>

<script>

if(NomVariable!=parseInt(<% out.println(""+count+"");%>)){

post();
 $.notiny({ text: "Message", animate: false });
 Notification.requestPermission(function (permission) {
      
      if (permission === "granted") {
        var notification = new Notification("Message");
      }
    });
}
NomVariable=parseInt(<% out.println(""+count+"");%>);
</script>
</body>
</html>