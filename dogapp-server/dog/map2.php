<html>
<body>
<?php  
$type = $_GET["type"]; 

?>

<?php
require_once('dbConnect.php');
?>



 <?php 
if($type=="Stray"){
    echo "<h2>Stray dogs population map</h2>";
    
    $query="select count(type) from dog where type = \"Stray\"";
    $arrlength=mysqli_query($link,$query);
    $count1=mysqli_fetch_row($arrlength);
     echo "Stray dog count =  $count1[0]";  
    
    $query="SELECT latitude FROM dog where type =\"Stray\" ";
    $lat=mysqli_query($link,$query);

    $query="SELECT longitude FROM dog where type =\"Stray\" ";
    $lon=mysqli_query($link,$query);
}elseif($type=="Pet"){
    echo "<h2>Pet population map</h2>";
    
    $query="select count(type) from dog where type = \"Pet\"";
    $arrlength=mysqli_query($link,$query);
    $count1=mysqli_fetch_row($arrlength);
    echo "Pet count =  $count1[0]";  
    $query="SELECT latitude FROM dog where type =\"Pet\" ";
    $lat=mysqli_query($link,$query);

    $query="SELECT longitude FROM dog where type =\"Pet\" ";
    $lon=mysqli_query($link,$query);
    
}elseif($type=="Grown"){
    echo "<h2>Grown dogs population map</h2>";
    
    $query="select count(size) from dog where size = \"Grown\"";
    $arrlength=mysqli_query($link,$query);
    $count1=mysqli_fetch_row($arrlength);
    echo "Grown dog count =  $count1[0]"; 
    
    $query="SELECT latitude FROM dog where size =\"Grown\" ";
    $lat=mysqli_query($link,$query);

    $query="SELECT longitude FROM dog where size =\"Grown\" ";
    $lon=mysqli_query($link,$query);
    
}elseif($type=="Puppy"){
    echo "<h2>Puppy population map</h2>";
    
    $query="select count(size) from dog where size = \"Puppy\"";
    $arrlength=mysqli_query($link,$query);
    $count1=mysqli_fetch_row($arrlength);
    echo "Puppy count =  $count1[0]";  
    
    $query="SELECT latitude FROM dog where size =\"Puppy\" ";
    $lat=mysqli_query($link,$query);

    $query="SELECT longitude FROM dog where size =\"Puppy\" ";
    $lon=mysqli_query($link,$query);
}elseif($type=="All"){
    header('Location:map.php');
}

$latitude=array();
$longitude=array();
for($x = 0; $x < $count1[0]; $x++) {
    $row=mysqli_fetch_row($lat);
    array_push($latitude,$row[0]);
    
    $row=mysqli_fetch_row($lon);
    array_push($longitude,$row[0]);
}

?>
    <br>
Total dog count =  <?php   echo $count[0]; ?>   
    
<form action="map2.php" method="get">
  <input type="radio" name="type" value="All" > All
  <input type="radio" name="type" value="Stray"> Stray
  <input type="radio" name="type" value="Pet"> Pet
  <input type="radio" name="type" value="Puppy"> Puppy
  <input type="radio" name="type" value="Grown"> Grown
  <input type="submit" value="View">
</form>


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




</body>
</html>