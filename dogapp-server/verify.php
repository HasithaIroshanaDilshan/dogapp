<?php
require "conn.php";
$id = $_POST["id"];
$user_verification_code = $_POST["verification_code"];

$check_qry = "select verification_code from user where id = '$id';";

$result = mysqli_query($conn,$check_qry);
if(mysqli_num_rows($result) > 0){
	while($row = $result->fetch_assoc()){
		$code = $row["verification_code"];
		if($code == $user_verification_code){
			$verify_qry = "update user set verified = 1 where id= '$id';";
			if(mysqli_query($conn,$verify_qry)===TRUE){
				echo 'correct';
			}else{
				echo 'wrong';
			}
		}else{
			echo 'wrong';
		}
	}
}else{
	echo $check_qry;
}



?>