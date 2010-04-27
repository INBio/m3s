<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
   
 
<script type="text/javascript"> 
var lat=${gpsLatitudeKey};
var lon=${gpsLongitudeKey};

  function initialize(lat,lon) {
    var myLatlng = new google.maps.LatLng(lat, lon);
    var myOptions = {
      zoom: 8,
      center: myLatlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

    var marker = new google.maps.Marker({
        position: myLatlng, 
        map: map
    });   
  }
</script> 

<div id="mapContainer" style="margin:0px; padding:0px;"> 
  <div id="map_canvas" style="width:600px; height:400px"></div> 
</div> 

<script type="text/javascript">
<!--
initialize(lat,lon);
//-->
</script> 
