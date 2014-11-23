<?php

/**
 * Class to handle all db operations
 * This class will have CRUD methods for database tables
 *
 * @author Ravi Tamada
 * @link URL Tutorial link
 */
class DbHandler {

    public $con;

    function __construct() {
        require_once dirname(__FILE__) . '/DbConnect.php';
        // opening db connection
        $db = new DbConnect();
        $this->con = $db->connect();
    }
    
    
    
//_________________________________________________________________________________________//
//_________________________________________________________________________________________//

//Generate random api key
public function generateApiKey() {
        return md5(uniqid(rand(), true));
    }

//check if user exists
    private function isUserExists($email) {
         
        $result = $this->con->query("SELECT iduser from user WHERE email = '".$email."';");
        $num_rows=$result->num_rows;
        
        
        return $num_rows;
    }
    
//check if user exists
    private function isAdminExists($username) {
         
        $result = $this->con->query("SELECT iduser from user WHERE email = '".$username."';");
        $num_rows=$result->num_rows;
        
        
        return $num_rows;
    }

//________________________________________________________________________________________//
//________________________________________________________________________________________//

/* ---------------------- `users` table method ----------------------------------------- */

    
    /*------------------New user registration----------------------------------------*/
                  //createUser($email, $password, $firstname, $lastname, $role, $phone, $blood_type, $piercing, $tattoo, $sickness, $birthyear, $gender, $weight);
    public function createUser($email, $password, $firstname, $lastname, $phone, $blood_type, $piercing, $tattoo, $sickness, $birthyear, $gender, $weight) {
        require_once 'PassHash.php';
        $response = array();

        // First check if user already existed in db
        if ($this->isUserExists($email)==0) {
            // Generating password hash
			
			$pass=new PassHash();
			
            $password_hash = $pass->hash($password);        

            // Generating API key
            $api_key = $this->generateApiKey();

            // insert query
            
            //insert user data
            $result_data=$this->con->query("Insert into user_data (blood_type, piercing, tattoo, sickness) Values ('".$blood_type."','".$piercing."', '".$tattoo."', '".$sickness."')");
            
            
           //get latest user data entry
            //get latest user data entry
            $usrdata=$this->con->query("SELECT MAX(iduser_data) AS data FROM user_data;");
            $usdata=$usrdata->fetch_assoc();
            $data=$usdata['data'];
            
            //insert Users table
            $result_user = $this->con->query("INSERT INTO user(email, pass, phone, active, firstname, lastname, locked, lock_count, score, api_key, birth_year, gender, weight, user_data_iduser_data) 
                                                     values('".$email."','".$password_hash."','".$phone."',1,'".$firstname."','".$lastname."',0,0,0,'".$api_key."','".$birthyear."','".$gender."','".$weight."','".$data."');");
            
            
            // Check for successful insertion
            if ($result_user && $result_data) {
                // User successfully inserted
                return USER_CREATED;
            } else {
                // Failed to create user
                return USER_CREATE_FAILED;
            }
        } else {
            // User with same email already existed in the db
            return USER_EXISTS;
        }

        return $response;
    }
    
    
    
/* ---------------------- `mobile` table method ----------------------------------------- */

    
    /*------------------New admin user registration----------------------------------------*/
                  //createUser($email, $password, $firstname, $lastname, $role, $phone, $blood_type, $piercing, $tattoo, $sickness, $birthyear, $gender, $weight);
    public function createAdmin($username, $password, $firstname, $lastname, $phone, $station) {
        require_once 'PassHash.php';
        $response = array();

        // First check if user already existed in db
        if ($this->isAdminExists($username)==0) {
            // Generating password hash
			
			$pass=new PassHash();
			
            $password_hash = $pass->hash($password);        

            // Generating API key
            $api_key = $this->generateApiKey();
            
            $role=ADMIN_ROLE;
            // insert query
            //insert admin table
            $result_admin = $this->con->query("INSERT INTO admin(username, pass, firstname, lastname, phone, station_idstation, admin_role_idadmin_role, locked, lock_count,api_key) 
                                                     values('".$username."','".$password_hash."','".$firstname."','".$lastname."','".$phone."','".$station."','".$role."',0,0,'".$api_key."');");
            
            
            // Check for successful insertion
            if ($result_admin) {
                // User successfully inserted
                return USER_CREATED;
            } else {
                // Failed to create user
                return USER_CREATE_FAILED;
            }
        } else {
            // User with same email already existed in the db
            return USER_EXISTS;
        }

        return $response;
    }
    
    
    
    

    /*---------------------Login check---------------------------------*/
    
    public function loginMobile($email, $password){
        
        //get password from database to comapare
        
        $result = $this->con->query("SELECT pass FROM user WHERE email= '".$email."'");
        $num_rows=$result->num_rows;
        //User with that pass is not in database
        if($num_rows==0){
            return -1;
        }
        //database error
        else if($num_rows>1 || $num_rows<0){
            return 0;
        }
        else {
            
            $row=$result->fetch_assoc();
            $db_pass=$row['pass'];
            
            $hash_password=md5($password);
            if($db_pass==$hash_password)return 1;
            else return 0;
        }
    
        
    }
    /*____________________Login admin_________________________________*/
    
    public function loginAdmin($username, $password){
        
        //get password from database to comapare
        
        $result = $this->con->query("SELECT pass FROM admin WHERE username= '".$username."'");
        $num_rows=$result->num_rows;
        //User with that pass is not in database
        if($num_rows==0){
            return -1;
        }
        //database error
        else if($num_rows>1 || $num_rows<0){
            return 0;
        }
        else {
            
            $row=$result->fetch_assoc();
            $db_pass=$row['pass'];
            
            $hash_password=md5($password);
            if($db_pass==$hash_password)return 1;
            else return 0;
        }
    
        
    }
    
    
    /*---------------------User table api--------------------------*/
    
    public function getUserByEmail($email){
        
        $result=$this->con->query("SELECT email, phone, active, firstname, lastname, locked, score, api_key, birth_year, gender, weight, blood_type, piercing, tattoo, sickness 
                                    FROM user 
                                    JOIN user_data 
                                    ON user.user_data_iduser_data=user_data.iduser_data 
                                    WHERE email='".$email."';");
                                    
        $num_rows=$result->num_rows;
        if($num_rows==1){
            return $result;
        }
        else return NULL;
    }
    
    /*---------------------Admin table api--------------------------*/
                    
    public function getUserByUsername($username){
        
        $result=$this->con->query("SELECT username, firstname, lastname, phone, api_key, station_idstation, admin_role_idadmin_role, locked, lock_count   
                                    FROM admin 
                                    WHERE username='".$username."';");
                                    
        $num_rows=$result->num_rows;
        if($num_rows==1){
            
            
            return $result;
        }
        else return NULL;
    }
    
  
    
    /*
    //not implemented
    public function getUserById($id){
        $result=$this->con->query("SELECT iduser, email, pass, firstname, lastname, image, api_key, role_idrole FROM user WHERE iduser='".$id."';");
        $num_rows=$result->num_rows;
        if($num_rows==1){
            return $result;
        }
        else return NULL;
        
    }
    
    //not implemented
    public function getUserByApiKey($apikey){
        $result=$this->con->query("SELECT iduser, email, pass, firstname, lastname, image, api_key, role_idrole FROM user WHERE api_key='".$apikey."';");
        $num_rows=$result->num_rows;
        if($num_rows==1){
            return $result;
        }
        else return NULL;
        
    }
    */
    
    public function getUserId($apikey){
        $result=$this->con->query("SELECT iduser FROM user WHERE api_key='".$apikey."';");
        $num_rows=$result->num_rows;
        if($num_rows==1){
            
            $id=$result->fetch_assoc();
            $row=$id['iduser'];
            return $row;
        }
        else return NULL;
        
    }
    
    
    public function getUserIdByEmail($email){
        $result=$this->con->query("SELECT iduser FROM user WHERE email='".$email."';");
        $num_rows=$result->num_rows;
        if($num_rows==1){
            
            $id=$result->fetch_assoc();
            $row=$id['iduser'];
            return $row;
        }
        else return NULL;
        
    }
    
    /*___________________Login lockout checker_________________________________*/
    
    //Get lock status    
    public function getLockStatus($email){
        $result=$this->con->query("SELECT locked FROM user WHERE email='".$email."';");
        $num_rows=$result->num_rows;
        if($num_rows==1){
            
            $lock=$result->fetch_assoc();
            $row=$lock['locked'];
            return $row;
        }
        else return NULL;
        
    }
    
    //Get try counter value
    
    
    public function getLoginTries($email){
        $result=$this->con->query("SELECT lock_count FROM user WHERE email='".$email."';");
        $num_rows=$result->num_rows;
        if($num_rows==1){
            
            $lock=$result->fetch_assoc();
            $row=$lock['lock_count'];
            return $row;
        }
        else return NULL;
        
    }
    
    //set login tries counter +1
    
     public function setLoginTries($email){
        
        $this->con->query("UPDATE user SET lock_count=lock_count+1 WHERE email='".$email."';");
    }
    
    public function setLockUser($email){
        
        $this->con->query("UPDATE user SET locked=1 WHERE email='".$email."';");
        
    }
    /*___________________Admin login tries handling_______________________________*/
    
     public function getLoginAdminTries($username){
        $result=$this->con->query("SELECT lock_count FROM admin WHERE username='".$username."';");
        $num_rows=$result->num_rows;
        if($num_rows==1){
            
            $lock=$result->fetch_assoc();
            $row=$lock['lock_count'];
            return $row;
        }
        else return NULL;
        
    }
    
    //set login tries counter +1
    
     public function setLoginAdminTries($username){
        
        $this->con->query("UPDATE admin SET lock_count=lock_count+1 WHERE username='".$username."';");
    }
    
    
    //lock user account
    
    public function setLockAdmin($username){
        
        $this->con->query("UPDATE admin SET locked=1 WHERE username='".$username."';");
        
    }
    
    //get admin data on username
    public function getAdminRole($username){
        
       $result= $this->con->query("SELECT admin_role_idadmin_role AS role FROM admin WHERE username='".$username."';");
       $row=$result->fetch_assoc();
        $result=$row["role"];
        return $result;
    }
    
/*___________________________________________________________________________________*/
/*__________________________________Password reset___________________________________*/

public function passReset($email){
    
    $this->con->query("UPDATE user SET locked=0, lock_count=0 WHERE email='".$email."';");
}
    
    
    
    /*_______________________________________________________________________*/
    /*________________________Header api key validation______________________*/
    /*_______________________________________________________________________*/
    
    public function isValidApiKey($api_key) {
        
        $result=$this->con->query("SELECT iduser FROM user WHERE api_key='".$api_key."';");
        $num_rows=$result->num_rows;
        if($num_rows==1) return 1;
        else return 0;
    }
    
    /*_______________________________________________________________________*/
    /*______________Functions that require autorization______________________*/
    /*_______________________________________________________________________*/

/*____________________User methods___________________________________________*/
public function getFirstLast($email) {
        $result=$this->con->query("SELECT firstname, lastname FROM user WHERE email='".$email."';");
        $num_rows=$result->num_rows;
        if($num_rows==1){
            return $result;
        }
        else return NULL;
    }
    
//get top 10 user based on achievements

public function getTopTen(){
    $result=$this->con->query("SELECT firstname, lastname, score FROM user ORDER BY score DESC LIMIT 10;");
      
            return $result;
    
    
}


/*_______________________Station methods________________________________*/

//for frontend registration dropdown
public function getAllStations() {
        $result=$this->con->query("SELECT * FROM station;");
      
            return $result;
    }


/*_____________________Donation methodds_______________________________*/

public function getAllDonationsByUser($iduser){
    
    //get donations time, station
    $result=$this->con->query("SELECT station.name AS name, donation.time AS time FROM donation JOIN station ON donation.station_idstation=station.idstation WHERE donation.user_iduser='".$iduser."';");

            return $result;
    }
    



}

?>
