<?php
require "conn.php";
$id = $_POST["id"];
$user_name = $_POST["username"];

$check_qry = "select logged,points from user where uname = '$user_name' and id ='$id';";

$result = mysqli_query($conn,$check_qry);
if(mysqli_num_rows($result) > 0){
	$logged = '';
	$points = 0;
	while($row = $result->fetch_assoc()){
		$logged = $row["logged"];
		$points = $row["points"];
	}
	if($logged=='1'){
		$logged = 'logged,'.$points;
	}else{
		$logged = 'not_logged,0';
	}
	echo $logged;
}else{
	echo "not_logged,0";
}

?>