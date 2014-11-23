<?php

 session_start();
        $username=$_SESSION['username'];
        session_unset();
        session_destroy();
      
        header("Location: ../../donorjj/index.php");

?>