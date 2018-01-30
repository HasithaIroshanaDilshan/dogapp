<html>
    
    
<?php

require_once('dbConnect.php');
  

session_start();
$user=$_SESSION["user"] ;
$name=$_SESSION["name"] ;

$query="select count(user) from dog where user = \"".$user[0]."\"";
$arrlength=mysqli_query($link,$query);
$count1=mysqli_fetch_row($arrlength);  
    
    
$query="SELECT latitude FROM dog where user=\"".$user[0]."\"";
$lat=mysqli_query($link,$query);

$query="SELECT longitude FROM dog where user = \"".$user[0]."\"";
$lon=mysqli_query($link,$query);

$latitude=array();
$longitude=array();

for($x = 0; $x < $count1[0]; $x++) {
    $row=mysqli_fetch_row($lat);
    array_push($latitude,$row[0]);
    
    $row=mysqli_fetch_row($lon);
    array_push($longitude,$row[0]);
}

?>
    
    
<h2>Dogs uploaded by <?php echo $user[1]." -- ".$user[2]  ?> </h2>
<h3> <?php echo $count1[0]." dogs" ?></h3>


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
           //marker1.push(marker); 
            
    }
    

}       
</script>   

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAurM6wxlyeuMtNiFPZ6k642FmpsX7vS04&callback=myMap"></script>


</html>

