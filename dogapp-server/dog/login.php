<html>
<h1>Dog Population Map</h1>
</html>

<?php

$servername="localhost";
$username="root";
$password="";



if (!($link=mysqli_connect($servername,$username,$password))) {
    echo 'Could not connect to mysql';
    exit;
}

$query = "use dogapp";
$setdb=mysqli_query($link,$query);
//$query="show tables";
//$tables=mysqli_query($link,$query);
//while ($row=mysqli_fetch_row($tables)) {
//   echo $row[0]; 
//   echo "<br>" ;
//}


$query="select count(*) from dog";
$arrlength=mysqli_query($link,$query);

$count=mysqli_fetch_row($arrlength);

$query="SELECT latitude FROM dog";
$lat=mysqli_query($link,$query);

$query="SELECT longitude FROM dog";
$lon=mysqli_query($link,$query);

$latitude=array();
$longitude=array();
for($x = 0; $x < $count[0]; $x++) {
    $row=mysqli_fetch_row($lat);
    array_push($latitude,$row[0]);
    
    $row=mysqli_fetch_row($lon);
    array_push($longitude,$row[0]);
}


?>





<html>
    <h2>Dog count =  <?php   echo $count[0]; ?>  </h2>

<div id="map" style="width:100%;height:80%"></div>
      
<script>  
          
function myMap() {
    var mapOptions = {
        center: new google.maps.LatLng(7.2549661,80.5947912),
        zoom: 9,
        mapTypeId: google.maps.MapTypeId.roadmap
    }
    var map = new google.maps.Map(document.getElementById("map"), mapOptions);

    var latitude = <?php echo '["' . implode('", "', $latitude) . '"]' ?>;
    var longitude = <?php echo '["' . implode('", "', $longitude) . '"]' ?>;

    var rows=latitude.length;

    var image ='dot.png';
    var i;
    for(i=0; i<rows;i++){
        var marker = new google.maps.Marker({ 
            position: new google.maps.LatLng(latitude[i],longitude[i]),
            icon: image,
            map: map   
            })       
    }

    }       
</script>   

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAurM6wxlyeuMtNiFPZ6k642FmpsX7vS04&callback=myMap"></script>


</html>

