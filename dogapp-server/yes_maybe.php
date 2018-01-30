<?php
require "conn.php";

	$type = $_POST["type"];
	$id = $_POST["id"];
	$id_saved = $_POST["id_saved"];
	if($type=='yes'){
		$insert_qry = "UPDATE dog SET duplicate = $id WHERE id = $id_saved;";
		if($conn->query($insert_qry)===TRUE){
			echo "ok";
		}else{
			echo "error";
		}
	}else if($type=='maybe'){
		$insert_qry = "UPDATE dog SET maybe = $id WHERE id = $id_saved;";
		if($conn->query($insert_qry)===TRUE){
			echo "ok";
		}else{
			echo "error";
		}
	}
?>