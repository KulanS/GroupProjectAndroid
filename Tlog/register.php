<?php

$servername = "127.0.0.1";
$username = "root";
$password = "kgks";
$db = "ishop_new";

// Create connection
$conn = mysqli_connect($servername, $username, $password,$db);


$name=$_POST["name"];
$email=$_POST["email"];
$password=$_POST["password"];


$statement=mysqli_prepare($conn,"INSERT INTO customer(name,email,password) VALUES('$name','$email','$password');");

//mysqli_stmt_bind_param($statement,"siss",$username,$email,$password);

mysqli_stmt_execute($statement);
$response=array();
$response["success"]=true;

echo json_encode($response);

?>
