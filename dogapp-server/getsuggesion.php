<?php
require "conn.php";
$latitude = $_POST["latitude"];
$longitude = $_POST["longitude"];
$colorCode = $_POST["colorCode"];
$size = $_POST["size"];
$type = $_POST["type"];
$date = $_POST["date"];
$photo = $_POST["photo"];
$user = $_POST["user"];

$latitide_upper = $latitude +(0.5/111.11);
$latitide_lower = $latitude-(0.5/111.11);
$longitude_upper = $longitude+(0.5/111.11*cos($latitude));
$longitude_lower = $longitude-(0.5/111.11*cos($latitude));

$mysql_qry = "SELECT id FROM dog where latitude between $latitide_lower and $latitide_upper AND longitude between $longitude_lower and $longitude_upper AND colorCode = '$colorCode' AND size = '$size' AND type= '$type'";
$result = mysqli_query($conn,$mysql_qry);
$no=0;
$message="";
if(mysqli_num_rows($result) > 0){
	while($row = $result->fetch_assoc()){
		$id = $row["id"];
		$message = $message . $id . ',';
		$no = $no + 1; 
	}
}else{
	echo "0";
}

$insert_qry = "insert into dog (latitude,longitude,colorCode,size,type,date,maybe,duplicate,user) values ('$latitude','$longitude','$colorCode','$size','$type','$date',0,0,$user);";
$saved_id=0;
if($conn->query($insert_qry)===TRUE){
	$saved_id=$conn->insert_id;
	$image= base64_decode($photo);
	file_put_contents('dog_images/img'.$saved_id.'.png', $image);
	$point_query = "UPDATE user SET points = points + 1 WHERE id=$user;";
	$conn->query($point_query);
}

	

if(mysqli_num_rows($result) > 0){
	echo $no . ',' . $saved_id . '-' . $message;
}

?>