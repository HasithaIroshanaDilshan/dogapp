<?php
require "conn.php";
$user_name = $_POST["username"];
$user_pass = $_POST["password"];

$mysql_qry = "select * from user where uname = '$user_name' and password ='$user_pass';";

$result = mysqli_query($conn,$mysql_qry);
if(mysqli_num_rows($result) > 0){
	$id = 0;
	$verified=0;
	$points = 0;
	while($row = $result->fetch_assoc()){
		$id = $row["id"];
		$verified = $row["verified"];
		$points = $row["points"];
	}
	if($verified == 1){
		$mysql_qry = "update user set logged = 1 where id= '$id' and uname= '$user_name';";
		if($conn->query($mysql_qry)===TRUE){
			echo "login success,$id,$points";
		}else{
			echo "error";
		}
	}else{
		echo "not verified,$id";
	}
	
}else{
	echo "login not success";
}

?>