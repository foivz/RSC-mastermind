<?php

require_once '../include/DbHandler.php';
require_once '../include/PassHash.php';
require '.././libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();

// User id from db - Global Variable
$user_id = NULL;


/*_________________________________________________________________*/
/*_____________Authentication function_____________________________*/
/*_________________________________________________________________*/

function authenticate(\Slim\Route $route) {
    // Getting request headers
    $headers = apache_request_headers();
    $response = array();
    $app = \Slim\Slim::getInstance();
 
    // Verifying Authorization Header
    if (isset($headers['Authorization'])) {
        $db = new DbHandler();
 
        // get the api key
        $api_key = $headers['Authorization'];
        // validating api key
        if ($db->isValidApiKey($api_key)==0) {
            // api key is not present in users table
            $response["error"] = true;
            $response["message"] = "Please log in.";
            echoRespnse(401, $response);
            $app->stop();
        } else {
           global $user_id;
            // get user primary key id
            $user = $db->getUserId($api_key);
            if ($user != NULL)
                $user_id = $user;   
        }
    } else {
        // api key is missing in header
        $response["error"] = true;
        $response["message"] = "Api key is misssing";
        echoRespnse(400, $response);
        $app->stop();
    }
}


/*_________________________________________________________________________*/
/*_________________Functions that don't require authentication_____________*/
/*_________________________________________________________________________*/


//______________________User register_______________________________________________//

//Mobile user registration

$app->post('/register_mobile', function() use ($app) {
            // check for required params
            
             verifyRequiredParams(array('email', 'password', 'firstname','lastname', 'phone', 'blood_type', 'piercing', 'tattoo', 'sickness', 'birthyear', 'gender', 'weight'));
            
            $response = array();

            // reading post params
            
            $email = $app->request->post('email');
            $password = $app->request->post('password');
            $firstname = $app->request->post('firstname');
            $lastname = $app->request->post('lastname');
            $phone = $app->request->post('phone');
            $blood_type = $app->request->post('blood_type'); 
            $piercing = $app->request->post('piercing');
            $tattoo = $app->request->post('tattoo');  
            $sickness = $app->request->post('sickness');
            $birthyear = $app->request->post('birthyear');
            $gender = $app->request->post('gender');
            $weight = $app->request->post('weight');

            // validating email address
            validateEmail($email);
            
            $db = new DbHandler();
                      //createUser($email, $password, $firstname, $lastname, $role, $phone, $blood_type, $piercing, $tattoo, $sickness, $birthyear, $gender, $weight)
            $res = $db->createUser($email, $password, $firstname, $lastname, $phone, $blood_type, $piercing, $tattoo, $sickness, $birthyear, $gender, $weight);
           
            if ($res == USER_CREATED) {
                $response["error"] = false;
                $response["message"] = "You are successfully registered";
            } else if ($res == USER_CREATE_FAILED) {
                $response["error"] = true;
                $response["message"] = "An error occurred while registereing";
            } else if ($res == USER_EXISTS) {
                $response["error"] = true;
                $response["message"] = "Sorry, this email already exists";
            }
            // echo json response
            echoRespnse(201, $response);
        });
        
        
        
//Admin user registration

$app->post('/register_admin', function() use ($app) {
            // check for required params
        
             verifyRequiredParams(array('username', 'password', 'firstname','lastname', 'phone', 'station'));
            
            $response = array();

            // reading post params
            
            $username = $app->request->post('username');
            $password = $app->request->post('password');
            $firstname = $app->request->post('firstname');
            $lastname = $app->request->post('lastname');
            $phone = $app->request->post('phone');
            $station = $app->request->post('station'); 
            
            $db = new DbHandler();
                      //createUser($email, $password, $firstname, $lastname, $role, $phone, $blood_type, $piercing, $tattoo, $sickness, $birthyear, $gender, $weight)
            $res = $db->createAdmin($username, $password, $firstname, $lastname, $phone, $station);
           
            if ($res == USER_CREATED) {
                $response["error"] = false;
                $response["message"] = "User successfully registered";
            } else if ($res == USER_CREATE_FAILED) {
                $response["error"] = true;
                $response["message"] = "An error occurred while registereing";
            } else if ($res == USER_EXISTS) {
                $response["error"] = true;
                $response["message"] = "Sorry, this username already exists";
            }
            // echo json response
            echoRespnse(201, $response);
        });
/*___________________________________________________________________________________*/



/*_________________________Mobile login_______________________________________________*/

$app->post('/login_mobile', function() use ($app) {
            // check for required params
            verifyRequiredParams(array('email', 'password'));
            
 
            // reading post params
            $email = $app->request()->post('email');
            $password = $app->request()->post('password');
            $response = array();
 
            $db = new DbHandler();
            
          
            // check for correct email and password
            $func_res=$db->loginMobile($email, $password);
            
            //check login() return state
          if ($func_res==-1) {
                        $response['error'] = true;
                        $response['message'] = 'Wrong username';
                            }
            
           else if ($func_res==1) {
                
                
                // get the user by email
                $user = $db->getUserByEmail($email);
                
                if ($user != NULL) { //ok! username and password correct
                    
                    $user=$user->fetch_assoc();
                    $response["error"] = false;
                    
                    $lock = $user['locked'];
                    //checking user locks
                        if($lock==1){ //user locked
                        $response['error'] = true;
                        $response['message'] = "Your account has been locked. Try reset password.";
                        }
                        else if($lock==0){ //user is not locked
                            $response['active'] = $user['active'];
                            $response['email'] = $user['email'];
                            $response['firstname'] = $user['firstname'];
                            $response['lastname'] = $user['lastname'];
                            $response['phone'] = $user['phone'];
                            $response['score'] = $user['score'];
                            $response['api_key'] = $user['api_key'];
                            $response['birth_year'] = $user['birth_year'];
                            $response['gender'] = $user['gender'];
                            $response['weight'] = $user['weight'];
                            $response['blood_type'] = $user['blood_type'];
                            $response['piercing'] = $user['piercing'];
                            $response['tattoo'] = $user['tattoo'];
                            $response['sickness'] = $user['sickness'];
                          }                  
                        }
                    }// func res=1
                    
                //check login() return state      
                else if ($func_res==0){ 
                    
                                //wrong password
                                //get login counter
                                $try=$db->getLoginTries($email);
                                
                                //if counter ==3, user is locked
                                if($try==LOCK_TRIES){
                                    //set lock in database
                                    $db->setLockUser($email);
                                    $response['error'] = true;
                                    $response['message'] = 'No remaining tries. Account locked!';    
                                }
                                //if counter 3 or more, user is locked...
                                else if($try>LOCK_TRIES){  
                                    $response['error'] = true;
                                    $response['message'] = 'Account locked! Please reset your password.';  
                                }
                                else{
                                $db->setLoginTries($email);
                                $try=$db->getLoginTries($email);
                                $try=LOCK_TRIES-$try;
                                $response['error'] = true;
                                $response['message'] = 'Wrong password. Remaining tries: '.$try;
                                }
                            }
                        //unknown errror, getUserByEmail returned NULL
                        else {
                            // unknown error
                            $response['error'] = true;
                            $response['message'] = "Unknown error. Please try again";
                        }
 
            echoRespnse(200, $response);
        });
        
        
        /*_________________________Admin login_______________________________________________*/

$app->post('/login_admin', function() use ($app) {
            // check for required params
            verifyRequiredParams(array('username', 'password'));
            
            // reading post params
            $username = $app->request()->post('username');
            $password = $app->request()->post('password');
            $response = array();
 
            $db = new DbHandler();
            
            // check for correct email and password
            $func_res=$db->loginAdmin($username, $password);
            
            //check login() return state
          if ($func_res==-1) {
                        $response['error'] = true;
                        $response['message'] = 'Wrong username';
                            }
            
           else if ($func_res==1) {
                
                
                // get the user by username
                $user = $db->getUserByUsername($username);
                
                if ($user != NULL) { //ok! username and password correct
                    
                    
                    $user=$user->fetch_assoc();
                    $response["error"] = false;
                    
                    $lock = $user['locked'];
                    //checking user locks
                        if($lock==1){ //user locked
                        $response['error'] = true;
                        $response['message'] = "Your account has been locked. Try reset password.";
                        }
                        else if($lock==0){ //user is not locked
                            $response['username'] = $user['username'];
                            $response['firstname'] = $user['firstname'];
                            $response['lastname'] = $user['lastname'];
                            $response['phone'] = $user['phone'];
                            $response['station'] = $user['station_idstation'];
                            $response['api_key'] = $user['api_key'];
                          }                  
                        }
                    }// func res=1
                    
                //check login() return state      
                else if ($func_res==0){ 
                    
                                //wrong password
                                //get login counter
                                $try=$db->getLoginAdminTries($username);
                                
                                //if counter ==3, user is locked
                                if($try==LOCK_TRIES){
                                    //set lock in database
                                    $db->setLockAdmin($username);
                                    $response['error'] = true;
                                    $response['message'] = 'No remaining tries. Account locked!';    
                                }
                                //if counter 3 or more, user is locked...
                                else if($try>LOCK_TRIES){  
                                    $response['error'] = true;
                                    $response['message'] = 'Account locked! Please reset your password.';  
                                }
                                else{
                                $db->setLoginAdminTries($username);
                                $try=$db->getLoginAdminTries($username);
                                $try=LOCK_TRIES-$try;
                                $response['error'] = true;
                                $response['message'] = 'Wrong password. Remaining tries: '.$try;
                                }
                            }
                        //unknown errror, getUserByEmail returned NULL
                        else {
                            // unknown error
                            $response['error'] = true;
                            $response['message'] = "Unknown error. Please try again";
                        }
 
            echoRespnse(200, $response);
        });


/*___________________________________________________________________________________*/
/*__________________________________Password reset___________________________________*/

$app->post('/passmailreset', function() use ($app) {
            // check for required params
            
             verifyRequiredParams(array('email'));
            
            $response = array();

            // reading post params
            
            $email = $app->request->post('email');
            $pass=randomPassword();
            $header = 'From: blood@mstrmnd.tk';
            $link="http://mstrmnd.tk?rest/v1/passmailnew/email=".$email;
            $msg="Click on link to reset pass:";
            
            $send=mail($email,"Your new password",$msg, $header);
            
            if($send==FALSE){
                $response["error"] = true;
                $response["message"] = "Sending mail failed";
                
            }else{
                $response["error"] = false;
                $response["message"] = "Mail sent sucessfully";
                
            }
            
            // echo json response
            echoRespnse(201, $response);
        });
        
$app->post('/passmailnew', function() use ($app) {
            // check for required params
            
             verifyRequiredParams(array('email'));
            
            $response = array();

            // reading post params
            
            $email = $app->request->post('email');
            
            //set locked & lock_counter to 0 in db
            $result=passReset($email);
            
            if($result==FALSE){
                $response["error"] = true;
                $response["message"] = "Error. Pass not changed.";
                
            }else{
                $response["error"] = false;
                $response["message"] = "Password reset sucessfully";
                
            }
            
            // echo json response
            echoRespnse(201, $response);
        });
        
        
//__________________________Stations_________________________________________________

//get all stations for admin user

$app->get('/stations', function() {
            
            $response = array();
            $db = new DbHandler();
 
            // fetching all stations
            $result = $db->getAllStations();
 
            $response["error"] = false;
            $response["stations"] = array();
 
            // looping through result and preparing stations
            while ($station = $result->fetch_assoc()) {
                $tmp = array();
                $tmp["idstation"] = $station["idstation"];
                $tmp["name"] = $station["name"];
                $tmp["adress"] = $station["adress"];
                $tmp["phone"] = $station["phone"];
                $tmp["city"] = $station["city"];
                array_push($response["stations"], $tmp);
            }
 
            echoRespnse(200, $response);
        });
        


/*____________________________________________________________________________________*/
/*________________________________Tasks that require validation_______________________*/
/*____________________________________________________________________________________*/

/*________________________________User apis___________________________________________*/

$app->get('/userdata/firstlast/:email', 'authenticate', function($email) {
            global $user_id;
            $response = array();
            $db = new DbHandler();
 
            // get user data
            $result = $db->getFirstLast($email);
 
            if ($result != NULL) {
                $result=$result->fetch_assoc();
                
                $response["error"] = false;
                $response["firstname"] = $result["firstname"];
                $response["lastname"] = $result["lastname"];
                
                echoRespnse(200, $response);
            } else {
                $response["error"] = true;
                $response["message"] = "Resource not exists.";
                echoRespnse(404, $response);
            }
        });
        

/*___________________________________________________________________________________*/


/*__________________________Station apis______________________________________________*/

//get all stations for admin user

$app->get('/stations', 'authenticate', function() {
            global $user_id;
            $response = array();
            $db = new DbHandler();
 
            // fetching all stations
            $result = $db->getStationsAdmin($user_id);
 
            $response["error"] = false;
            $response["stations"] = array();
 
            // looping through result and preparing stations
            while ($station = $result->fetch_assoc()) {
                $tmp = array();
                $tmp["idstation"] = $station["idstation"];
                $tmp["name"] = $station["name"];
                $tmp["adress"] = $station["adress"];
                $tmp["phone"] = $station["phone"];
                $tmp["city"] = $station["city"];
                array_push($response["stations"], $tmp);
            }
 
            echoRespnse(200, $response);
        });


/*____________________________________________________________________________________*/

     

/**
 * Verifying required params posted or not
 */
function verifyRequiredParams($required_fields) {
    $error = false;
    $error_fields = "";
    $request_params = array();
    $request_params = $_REQUEST;
    // Handling PUT request params
    if ($_SERVER['REQUEST_METHOD'] == 'PUT') {
        $app = \Slim\Slim::getInstance();
        parse_str($app->request()->getBody(), $request_params);
    }
    foreach ($required_fields as $field) {
        if (!isset($request_params[$field]) || strlen(trim($request_params[$field])) <= 0) {
            $error = true;
            $error_fields .= $field . ', ';
        }
    }

    if ($error) {
        // Required field(s) are missing or empty
        // echo error json and stop the app
        $response = array();
        $app = \Slim\Slim::getInstance();
        $response["error"] = true;
        $response["message"] = 'Required field(s) ' . substr($error_fields, 0, -2) . ' is missing or empty';
        echoRespnse(400, $response);
        $app->stop();
    }
}

/**
 * Validating email address
 */
function validateEmail($email) {
    $app = \Slim\Slim::getInstance();
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $response["error"] = true;
        $response["message"] = 'Email address is not valid';
        echoRespnse(400, $response);
        $app->stop();
    }
}

/**
 * Echoing json response to client
 * @param String $status_code Http response code
 * @param Int $response Json response
 */
function echoRespnse($status_code, $response) {
    $app = \Slim\Slim::getInstance();
    // Http response code
    $app->status($status_code);

    // setting response content type to json
    $app->contentType('application/json');

    echo json_encode($response);
}

$app->run();
?>