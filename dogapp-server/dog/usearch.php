<?php //  
$name = $_GET["searchName"]; 
//echo $name
?>


<?php 
//include('header.html');
require_once('dbConnect.php');
include('users.php');    
$query="select * from user where uname ='$name' or email='$name'";
$check=mysqli_query($link,$query);
//$user=mysqli_fetch_row($arrlength);



if (!($user=mysqli_fetch_row($check))) {
   echo "your seach '$name' does not match with names" ;

}else{   
    
    session_start();  
    $_SESSION["user"] = $user;
    $_SESSION["name"] = $name;
    header('Location:user.php');    
        
        
    exit();
     
       
       
       
       
       
       
       
       
       
       
       
       
       
       
}      