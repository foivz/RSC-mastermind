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
    

public function getAllDonationsByUser($iduser){
    
    //get donations time, station
    $result=$this->con->query("SELECT station.name AS name, donation.time AS time FROM donation JOIN station ON donation.station_idstation=station.idstation WHERE donation.user_iduser='".$iduser."';");

            return $result;
    }
}

 
$handle=new DbHandler();

 $result = $handle->getAllDonationsByUser(1);
 
            $response["error"] = false;
            $response["donations"] = array();

          
            while ($donation = $result->fetch_assoc()) {
                $tmp = array();
                $tmp["name"] = $donation["name"];
                echo $tmp["name"];
                $tmp["time"] = $donaiton["time"];
                 echo $tmp["time"];
                }
                
                
?>
