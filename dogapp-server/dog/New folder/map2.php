<html>
<h1>Dog Population Map</h1>
</html>

<?php

$servername="localhost";
$username="root";
$password="";



if (!($link=mysqli_connect("localhost","root",""))) {
    echo 'Could not connect to mysql';
    exit;
}

$query = "use dogapp";
$setdb=mysqli_query($link,$query);


$query="select count(*) from dog";
$arrlength=mysqli_query($link,$query);

$length=mysqli_fetch_row($arrlength);

$query="SELECT id FROM dog";
$list=mysqli_query($link,$query);
$stack=array();
for($x = 0; $x < $length[0]; $x++) {
    $row=mysqli_fetch_row($list);
    array_push($stack,$row[0]);
 
}


?>
<html>
    <h2>Dog count =     <?php   echo $length[0];    ?>  </h2>

</html>





<html>    
<body>
<div id="map" style="width:100%;height:80%"></div>

<script>
function myMap() {
var mapOptions = {
    center: new google.maps.LatLng(7.2549661,80.5947912),
    zoom: 9,
    mapTypeId: google.maps.MapTypeId.roadmap
}
var map = new google.maps.Map(document.getElementById("map"), mapOptions);

var latitude = <?php echo '["' . implode('", "', $row) . '"]' ?>;
document.write(latitude[0]+'\xa0'+'\t');


        var features = [
          {
            position: new google.maps.LatLng(7.2549661,80.5947912),
            type: 'info'
          }, {
            position: new google.maps.LatLng(8.2549661,80.5947912),
            type: 'info'
          }, {
            position: new google.maps.LatLng(9.2549661,80.5947912),
            type: 'info'
          }, {
            position: new google.maps.LatLng(9.2549661,80.1947912),
            type: 'info'
          }, {
            position: new google.maps.LatLng(7.2549661,80.0947912),
            type: 'info'
          }, {
            position: new google.maps.LatLng(8.2549661,80.9947912),
            type: 'info'
          }, {
            position: new google.maps.LatLng(7.2549661,81.5947912),
            type: 'info'
          }
        ];
        
        
        // Create markers.
        var image ='dot.png';
        features.forEach(function(feature) {
          var marker = new google.maps.Marker({
            position: feature.position,
            icon: image, //icons[feature.type].icon,
            map: map
          });
        });
      }
    </script>
    
    

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAurM6wxlyeuMtNiFPZ6k642FmpsX7vS04&callback=myMap"></script>


</body>

</html>
</html>
