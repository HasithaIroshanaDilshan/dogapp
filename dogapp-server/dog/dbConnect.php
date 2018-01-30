<?php

$servername="localhost";
$username="root";
$password="";



if (!($link=mysqli_connect($servername,$username,$password))) {
    echo 'Could not connect to mysql';
    exit;
}

$query = "use dogapp";
$setdb=mysqli_query($link,$query);


$query="select count(*) from dog";
$arrlength=mysqli_query($link,$query);
$count=mysqli_fetch_row($arrlength);


$query="SELECT * FROM dog WHERE id=(SELECT MAX(id) FROM dog)";
$arrlength=mysqli_query($link,$query);
$lastupdate=mysqli_fetch_row($arrlength);

list($date , $time) = explode("-", $lastupdate[6]);  
    
?>    