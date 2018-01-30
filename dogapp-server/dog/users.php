<html>
    <header>
        <style>
    body{
      background: papayawhip;
    }
    </style>    
   </header> 
    
    
    
    
    
<?php include('header.html');
require_once('dbConnect.php');


$query="select count(*) from user";
$arrlength=mysqli_query($link,$query);
$usercount=mysqli_fetch_row($arrlength);


echo "Dog count = ".$count[0] ."<br>";//dog count given by dbConnet 
echo "Total users = ".$usercount[0];

$query="select max(points) from user";
$arrlength=mysqli_query($link,$query);
$max=mysqli_fetch_row($arrlength);

$query="select * from user where points ='$max[0]'";
$arrlength=mysqli_query($link,$query);
$maxuser=mysqli_fetch_row($arrlength);

echo "<br><br> Best contributer is $maxuser[1] <br> ($max[0] dogs added)";
?>

<br>
<br>
<form action="usearch.php">
<input type="text" name="searchName" placeholder="Search user.." size="20" required/>
<button type="submit">Go</button>
</form>










</html>