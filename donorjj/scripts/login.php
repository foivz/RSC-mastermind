<?php

require_once '../../rest/include/DbHandler.php';
//User login procedures

$username=$_POST["username"];
$password=$_POST["password"];

$handler=new DbHandler();

$result=$handler->loginAdmin($username, $password);


//-1 username not exist, 0 db error, 1 login ok
if($result==-1){
   
    $msg="Wrong username or password";
    header("Location: ../index.php?message=".urlencode($msg));
}
else if($result==0){
    
    $msg="Database error";
   header("Location: ../index.php?message=".urlencode($msg));
    
}else if($result==1){
    
    //get user data
    $role=$handler->getAdminRole($username);
  
        if (!session_id()) {
        session_start();
        $_SESSION['username']=$username;
        $_SESSION['role']=$role;
      
        header("Location: ../../donor/index.php");
    }
    
    
    }

    



?>