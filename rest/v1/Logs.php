<?php


Class Logs{
    
    
    public function writeLogToFile($message){
        
        $file = '../../logging.log';
        $log=$message."\n";
        
        file_put_contents($file, $log, FILE_APPEND | LOCK_EX);
        
    }
    
}


?>