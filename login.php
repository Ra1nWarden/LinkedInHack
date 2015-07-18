<?php
   	define("DB_HOST", "localhost");
	// database name
   	define("DB_NAME", "hackthon");
	// Your MySQL username
    define("DB_USER", "root");
	// ...and password
	define("DB_PASSWORD", "");

    // get database credentials
    $mysqli = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME); 
    if ($mysqli->errno) {
         print($mysqli->error); 
         exit();
    }

    $queryJSON = json_decode(file_get_contents("php://input"),true);

    $username = $queryJSON['username'];
    $password = $queryJSON['password'];

    $query= "SELECT * FROM User WHERE username='$username' AND password='$password'";

    $result = $mysqli->query($query);

    $response = array();
    if($result && $result->num_rows==1)
    {
        $response['status'] = 'success';
    }
    else {
        $response['status'] = 'failed';
    }
    
    echo json_encode($response);
?>
