<?php
$servername = "127.0.0.1";
$username = "root";
$password = "1234567";
$db = "groupproject";

// Create connection
$conn = mysqli_connect($servername, $username, $password,$db);

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
else{
	echo "Connected successfully";

}
$query=mysqli_query($conn,"SELECT * FROM test");

if($query){
	while ($row=mysqli_fetch_array($query)) {
		$flag[]=$row;
		
	}
	print(json_encode($flag));
}
mysqli_close($conn);

?> 