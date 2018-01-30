<?php
require "conn.php";
$id = $_POST["id"];
$user_name = $_POST["username"];

$mysql_qry = "update user set logged = 0 where id= '$id' and uname= '$user_name';";

	if($conn->query($mysql_qry)===TRUE){
		echo "ok";
	}else{
		echo "error";
	}

?>