<?php

class DbConnection {
 
    private $con;
 
    function __construct() {        
    }
 
    function connectDB() {
        include_once dirname(__FILE__) . './Config.php';
 
        //Connect to DB
        $this->con = new mysqli(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME);
 
        //Error connecting DB
        if (mysqli_connect_errno()) {
            echo "Error connecting MYSQL: " . mysqli_connect_error();
        }
 
        // return connection
        return $this->con;
    }
 
}

?>