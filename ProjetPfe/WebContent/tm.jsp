<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <title>Google Maps JavaScript API Example: Map Simple</title>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAD9vTLPPWJQl2gto-4dQf6dRNePNYJYc8"
            type="text/javascript"></script>
    <script type="text/javascript">
    var marker;
    var infowindow;

    function initialize() {
      var latlng = new google.maps.LatLng(37.4419, -122.1419);
      var options = {
        zoom: 13,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
      }
      var map = new google.maps.Map(document.getElementById("map"), options);
      var html = "<table>" +
                 "<tr><td>Name:</td> <td><input type='text' id='name'/> </td> </tr>" +
                 "<tr><td>Address:</td> <td><input type='text' id='address'/></td> </tr>" +
                 "<tr><td>Type:</td> <td><select id='type'>" +
                 "<option value='bar'>bar</option>" +
                 "<option value='restaurant'>restaurant</option>" +
                 "</select> </td></tr>" +
                 "<tr><td></td><td><input type='button' value='Save and Close' onclick='saveData()'/></td></tr>";
    infowindow = new google.maps.InfoWindow({
     content: html
    });

    google.maps.event.addListener(map, "click", function(event) {
        marker = new google.maps.Marker({
          position: event.latLng,
          map: map
        });
        google.maps.event.addListener(marker, "click", function() {
          infowindow.open(map, marker);
        });
    });
    }

    function saveData() {
      var name = escape(document.getElementById("name").value);
      var address = escape(document.getElementById("address").value);
      var type = document.getElementById("type").value;
      var latlng = marker.getPosition();

      var url ="http://localhost/curisLoc/Login.php?name="+name+
      "&address="+address+
      "&type="+type+
      "&lat="+latlng.lat()+
      "&lng="+latlng.lng();
      downloadUrl(url, function(data, responseCode) {
        if (responseCode == 200 && data.length >= 1) {
          infowindow.close();
          document.getElementById("message").innerHTML = "Location added.";
        }
      });
    }

    function downloadUrl(url, callback) {
      var request = window.ActiveXObject ?
          new ActiveXObject('Microsoft.XMLHTTP') :
          new XMLHttpRequest;

      request.onreadystatechange = function() {
        if (request.readyState == 4) {
          request.onreadystatechange = doNothing;
          callback(request.responseText, request.status);
        }
      };

      request.open('GET', url, true);
      request.send(null);
    }

    function doNothing() {}
    </script>
  </head>

 <body style="margin:0px; padding:0px;" onload="initialize()">

    <div id="map" style="width: 500px; height: 300px"></div>
    <div id="message"></div>

  </body>
</html>