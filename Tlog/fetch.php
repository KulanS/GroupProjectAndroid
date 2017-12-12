<?php


$servername = "127.0.0.1";
$username = "root";
$password = "1234567";
$db = "groupproject";

// Create connection
$conn = new mysqli($servername, $username, $password, $db);


 
 
//creating an array for storing the data 
$test = array(); 
 
//this is our sql query 
$sql = "SELECT  name FROM test";
 
//creating an statment with the query
$stmt = $conn->prepare($sql);
 
//executing that statment
$stmt->execute();
 
//binding results for that statment 
$stmt->bind_result($name);
 
//looping through all the records
while($stmt->fetch()){
 
 //pushing fetched data in an array 
 $temp = [
 'name'=>$name
 ];
 
 //pushing the array inside the hero array 
 array_push($test, $temp);
}
 
//displaying the data in json format 
echo json_encode($test);
?>