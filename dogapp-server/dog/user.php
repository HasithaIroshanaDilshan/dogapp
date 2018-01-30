<html>
    
    
<?php

require_once('dbConnect.php');

include('users.php');   


session_start();
$user=$_SESSION["user"] ;
$name=$_SESSION["name"] ;

    $query="select count(user) from dog where user = \"".$user[0]."\"";
    $arrlength=mysqli_query($link,$query);
    $count1=mysqli_fetch_row($arrlength);   
    echo "<br> Your search '$name' uploaded $count1[0] dogs";
    echo "<br>Username =$user[1]  email=$user[2]";
 ?> 
<br>
<form action="usermap.php">
<input type="submit" value="View uploaded dogs in map" />
</form>

</html>