<?php
$servername = "127.0.0.1";
$username = "root";
$password = "kgks";
$db = "ishop_new";

// Create connection
$conn = mysqli_connect($servername, $username, $password,$db);

// Check connection

$name=$_POST["name"];
$password=$_POST["password"];



$statement=mysqli_prepare($conn,"SELECT email FROM customer WHERE name=? AND password=?");

mysqli_stmt_bind_param($statement,"ss",$name,$password);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$name,$password);
$response=array();
$response["success"]=false;

while (mysqli_stmt_fetch($statement)) {
$response["success"]=true;
$response["email"]=$email;
}


echo json_encode($response);

?>
