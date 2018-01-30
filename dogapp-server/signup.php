<?php
require "conn.php";
$user_name = $_POST["username"];
$email = $_POST["email"];
$user_pass = $_POST["password"];

$check_qry = "select id from user where uname = '$user_name' and password ='$user_pass';";

$result = mysqli_query($conn,$check_qry);
if(mysqli_num_rows($result) > 0){
	echo "user_already";
}else{
	$verification_code=rand(1111,9999);
	$mysql_qry = "insert into user (uname,email,password,logged,verified,verification_code) values ('$user_name','$email','$user_pass',0,0,$verification_code);";

	if(mysqli_query($conn,$mysql_qry)===TRUE){
		echo "signup successful,". mysqli_insert_id($conn);
	}else{
		echo "signup not successful";
	}
}



?>