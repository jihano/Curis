<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/map/css/leaflet.css" type="text/css" />
<link rel="shortcut icon" type="resources/map/image/x-icon" href="/css/images/favicon.ico" />
<link rel="stylesheet" href="resources/map/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="resources/map/css/flexslider.css" type="text/css" media="all" />

	
<script src="resources/map/js/leaflet.js" type="text/javascript"></script>
<script src="resources/map/css/ol.js"></script>
<script src="resources/map/css/ol.js"></script>
<script src="resources/map/js/jquiryy.js"></script>
<script src="resources/map/js/xlsx.js"></script>

        <title>JSP Page</title>
    </head>
<body>

            <div id="mapid" style="width: 100%; height: 100%">
         
 
  <script src="resources/map/js/maroc1.js"></script>
  <script>    var mymap = L.map('mapid').setView([35.7551, -5.8303], 13);

    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoiYmxvb2Rlc3UiLCJhIjoiY2lsenVyYXRxMDBoc3ZrbTVtYXlkem50MSJ9.1mzdPeo3djFjgHVbZzZEPw', {
      maxZoom: 10,
      attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
        '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
        'Imagery © <a href="http://mapbox.com">Mapbox</a>',
      id: 'mapbox.streets'
    }).addTo(mymap);

//35.75708/-5.85747
       L.marker([35.75708, -5.85747]).addTo(mymap)
       .bindPopup("<b>Hello world!</b><br />I am a popup.").openPopup();

    L.circle([35.75708, -5.811], 500, {
      color: 'red',
      fillColor: '#f03',
      fillOpacity: 0.5
    }).addTo(mymap).bindPopup("I am a circle.");

    L.polygon([
      [35.75701, -5.85286],
      [35.75506, -5.85415],
      [35.75341, -5.85131]
    ]).addTo(mymap).bindPopup("I am a polygon.");

    var tab=[[35.75701, -5.85286],[35.75506, -5.85415],[35.75341, -5.85131],[35.75708, -5.811]];
    for (var i = tab.length - 1; i >= 0; i--) {
      L.marker(tab[i]).addTo(mymap);
    }
//-----------------------------------geoJson---------------------------


    

    var tang = new L.geoJson(maroc, {
      // onEachFeature: onEachFeature
      simplifyFactor: 0.5,
      precision: 5,
      onEachFeature: onEachFeature,
      style:style
      //
    }).addTo(mymap);

    function style(feature) {
       if (feature.properties.DENSITE<25) {
         return {weight: 0,opacity: 1,color: '#ffffd9', dashArray: '3',fillOpacity: 0.5,fillColor: '#ffffd9' };
       } else  {if(feature.properties.DENSITE<50){
        return {weight: 0,opacity: 1,color: '#edf8b1', dashArray: '3',fillOpacity: 0.5,fillColor: '#edf8b1' };   
       }
           else{ if(feature.properties.DENSITE<75){
        return {weight: 0,opacity: 1,color: '#c7e9b4', dashArray: '3',fillOpacity: 0.5,fillColor: '#c7e9b4' };   
       }
           else{ if(feature.properties.DENSITE<100){   
         return {weight: 0,opacity: 1,color: '#7fcdbb', dashArray: '3',fillOpacity: 0.5,fillColor: '#7fcdbb' };
       }  else{ { if(feature.properties.DENSITE<125){   
         return {weight: 0,opacity: 1,color: '#41b6c4', dashArray: '3',fillOpacity: 0.5,fillColor: '#41b6c4' };
       }else{ { if(feature.properties.DENSITE<150){   
         return {weight: 0,opacity: 1,color: '#1d91c0', dashArray: '3',fillOpacity: 0.5,fillColor: '#1d91c0' };
       }else{ if(feature.properties.DENSITE<175){   
         return {weight: 0,opacity: 1,color: '#225ea8', dashArray: '3',fillOpacity: 0.5,fillColor: '#225ea8' };
       }else{if(feature.properties.DENSITE<200){   
         return {weight: 0,opacity: 1,color: '#253494', dashArray: '3',fillOpacity: 0.5,fillColor: '#253494' };
       }else{
         return {weight: 0,opacity: 1,color: '#081d58', dashArray: '3',fillOpacity: 0.5,fillColor: '#081d58' };
       }
    }}}}}}}}}}

    function onEachFeature(feature, layer) {
        if (feature.properties) {
            // layer.bindPopup("some crazy HTML");
            layer.bindPopup("PERIMETER = " + feature.properties.PERIMETER + "</br> DENSITE = " + feature.properties.DENSITE);
        }
    }


    // function onEachFeature(feature, layer) {
    //     if (feature.properties) {
    //         // layer.bindPopup("some crazy HTML");
    //         layer.bindPopup("<b>Temperature in " + feature.properties.name + "</b> is " + feature.properties.temp);
    //     }
    // }
    
    //---------------------------------jsonObject--------------------------------------
   

//---------------------------------------------------------------------

    var popup = L.popup();

    function onMapClick(e) {
      popup
        .setLatLng(e.latlng)
        .setContent("You clicked the map at " + e.latlng.toString())
        .openOn(mymap);
    }

    mymap.on('click', onMapClick);


 //L.marker(p).addTo(mymap);

 //for (var i = tab.length - 1; i >= 0; i--) {
  //    L.marker(tab[i]).addTo(mymap);
  //    }


 
</script></div>




</body>
</html>