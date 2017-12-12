<?php
$host="127.0.0.1";
$user="root";
$password="1234567";
$con=mysql_connect($host,$user,$password);
if($con) {
    echo 'Connected to MySQL';
} else {
    echo 'MySQL Server is not connected';
}
?>