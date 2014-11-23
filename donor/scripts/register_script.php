<?php
include_once '../../rest/include/DbHandler.php';

$firstname=$_POST["firstname"];
$lastname=$_POST["lastname"];
$username=$_POST["username"];
$password=$_POST["password"];
$confirm_password=$_POST["confirm_password"];
$phone=$_POST["phone"];
$station=$_POST["institution"];

//
$station=1;


$handler=new DbHandler();
$result=$handler->createAdmin($username, $password, $firstname, $lastname, $phone, $station);
if($result==1){
    $msg="User successfuly created. Please Login.";
    header("Location: ../../donorjj/index.php?msgreg=".$msg);

}
else if($result==0){
    $msg="Failed to create user. Please try again.";
    header("Location: ../../donorjj/index.php?msgreg=".$msg);

}
else if($result==-1){
    $msg="Username exists. Please try different username.";
    header("Location: ../../donorjj/index.php?msgreg=".$msg);

}


?>