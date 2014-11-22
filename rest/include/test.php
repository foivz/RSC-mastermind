<?php

/**
 * Class to handle all db operations
 * This class will have CRUD methods for database tables
 *
 * @author Ravi Tamada
 * @link URL Tutorial link
 */
 

class DbHandler {

    private $con;
    
    

    function __construct() {
        require_once dirname(__FILE__) . '/DbConnect.php';
        // opening db connection
        $db = new DbConnect();
        $this->con = $db->connect();
    }
    
    public function getAllStations() {
        $result=$this->con->query("SELECT * FROM station;");
        $num_rows=$result->num_rows;
        if($num_rows==1){
            return $result;
        }
        else return NULL;
    }
}

 
$handle=new DbHandler();

$user = $handle->getAllStations();
 $user=$user->fetch_assoc();
$user=$user['idstation'];
echo $user;
?>
