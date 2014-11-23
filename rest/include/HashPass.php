<?php

class HashPass {
 
 
    // hash password
    public function hashPass($password) {
 
        return md5($password);
    }
 
 
}

?>