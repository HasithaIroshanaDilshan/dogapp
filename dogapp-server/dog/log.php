<?php

require_once('dbConnect.php');
 
//echo 'welcome   '.$_POST["name"]."<br>".$_POST["pwd"];
$name =$_POST["name"];
$pwd = $_POST["pwd"];



$query="select username from admin where username='$name' and password ='$pwd'";
$check=mysqli_query($link,$query);
if (!($name1=mysqli_fetch_row($check))) {
   echo "<br>"."error" ;
   $query="select username from admin where username='$name'";
   $check=mysqli_query($link,$query);
   if (!($name2=mysqli_fetch_row($check))) { //check wether username exit in the database
       echo "<br>"."user does not exist";     //if username doesn't extit redirect to anothor login page
               //rgister
       header('Location:templog.html');
       exit;
   }else{
        echo "<br>"."incorrect password" ; // user exits and password didn't match show message
   }
    }else{
        
        session_start();  
        $_SESSION["username"] = $name;
        header('Location:welcome.php');
        exit;
    }

?>

