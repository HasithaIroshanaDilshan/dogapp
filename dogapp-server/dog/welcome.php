<html>
<?php 

session_start();
    $user=$_SESSION["username"] ;
    if(is_null($user)){
        header('Location:dog.html'); 
    }


require_once('dbConnect.php'); // this will give total dog count also

$query="select count(*) from user";
$arrlength=mysqli_query($link,$query);
$usercount=mysqli_fetch_row($arrlength);



$query="select count(size) from dog where size = \"Puppy\"";
$arrlength=mysqli_query($link,$query);
$puppycount=mysqli_fetch_row($arrlength);


$query="select count(size) from dog where size = \"Grown\"";
$arrlength=mysqli_query($link,$query);
$growncount=mysqli_fetch_row($arrlength);


$query="select count(type) from dog where type = \"Pet\"";
$arrlength=mysqli_query($link,$query);
$petcount=mysqli_fetch_row($arrlength);


$query="select count(type) from dog where type = \"Stray\"";
$arrlength=mysqli_query($link,$query);
$straycount=mysqli_fetch_row($arrlength);




$query="select count(maybe) from dog where maybe>0";
$arrlength=mysqli_query($link,$query);
$maybe=mysqli_fetch_row($arrlength);


$query="select count(maybe) from dog where maybe>0";
$arrlength=mysqli_query($link,$query);
$maybe=mysqli_fetch_row($arrlength);


$query="select count(maybe) from dog where duplicate>0";
$arrlength=mysqli_query($link,$query);
$dup=mysqli_fetch_row($arrlength);



include('header.html');
?> 
<head> 
<style>
table {
    border-collapse: collapse;
    background:cyan;
    padding: 25px;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}

 body{
      background: papayawhip;
    }
    
    
    
    
    #ABC {
      background-color:chartreuse;
      position: absolute;
      padding-left: 15px;
      width: 50%;
    }
    
    
    
</style>







</head>    
    
    
    
    
    
    

<body>
    
 <h2>   
 <?php    
    echo "<br>"."Hi " .$user . "<br>"."<br>";
    //session_destroy();
    echo "Dog count upper bound is = $count[0]  <br>";//dog count given by dbConnet 
    $lowerbound =$count[0]-$maybe[0];
    echo "Dog count lower bound is = $lowerbound  <br>";
?> 
  <form action="map.php" method="post">
  <input type="submit" value="view dog population map" name="viewMap" />
  </form>   
</h2>    
     
<h3>
    <?php echo "Total users = ".$usercount[0];  ?> 
    
    <form action="users.php" method="post">
    <input type="submit" value="view users" name="viewUsers" />
    </form>
</h3>    
    
 <table>
     
  <tr>
    <th>Total Dog count</th>
    <th> <?php echo $count[0];?></th>
  </tr>
  <tr>
      <th>Stray dogs</th>
      <th><?php echo $straycount[0];?></th>
  </tr>
  <tr>
      <th>Pets</th>
      <th><?php echo $petcount[0];?></th>
  </tr>
  <tr>
    <th>Grown Dogs</th>
     <th><?php echo $growncount[0];?></th>
  </tr>
  <tr>
    <th>Puppies</th>
    <th><?php echo $puppycount[0];?></th>
  </tr>
  
 </table>
 <br> 
<section id="ABC">
 <br>   
last dog added on  <?php echo     " ". $date . " at ". $time; ?> 






<?php 
$latitude = $lastupdate[1]; 
$longitude= $lastupdate[2]
?> 








<p id="location"></p>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
    
    
var latitude = "<?php echo $latitude ?>"
var longitude = "<?php echo $longitude ?>"


var link ="https://maps.googleapis.com/maps/api/geocode/json?latlng="+latitude+"," +longitude+"&key=AIzaSyAurM6wxlyeuMtNiFPZ6k642FmpsX7vS04";
        $.get(link, function(data, status){
 
            str = JSON.stringify(data);
            console.log(str); 
            var res = str.split("\"");
            var a = res.indexOf("formatted_address");           
            document.getElementById("location").innerHTML ="Near " + res[a+2]; //+"\n"+link
        });
</script>
<a class ="hyperlink" href="logout.php">Log Out</a>   

</section>

<br>    
   
</body>





</html>
