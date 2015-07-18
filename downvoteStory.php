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
        print($mysqli->error); exit();
    }

    $queryJSON = json_decode(file_get_contents("php://input"),true);
    $storyID = $queryJSON['storyid'];

    $query="UPDATE Story SET downvote = downvote + 1 WHERE storyid = '$storyID'";

    $result = $mysqli->query($query);

?>
