<?php

class PassHash {

  

    // this will be used to generate a hash
    public function hash($password) {

        return md5($password);
    }

    

}

?>
