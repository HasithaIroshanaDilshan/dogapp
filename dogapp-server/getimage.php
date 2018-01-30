<?php
require "conn.php";

	$image_id = $_POST["image_id"];

	$image=file_get_contents('dog_images/img'.$image_id.'.png');
	$encoded = base64_encode($image);
	echo $encoded;
?>