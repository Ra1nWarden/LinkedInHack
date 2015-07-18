<?php
   	define("DB_HOST", "localhost");
	// database name
   	define("DB_NAME", "hackthon");
	// Your MySQL username
    define("DB_USER", "root");
	// ...and password
	define("DB_PASSWORD", "");

    $mysqli = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME); 
    if ($mysqli->errno) {
        print($mysqli->error); 
        exit();
    }

    $queryJSON = json_decode(file_get_contents("php://input"),true);
    $storyid = $queryJSON['storyid'];
    $comment = $queryJSON['comment'];
    
    $query = "INSERT INTO CommentsInStory(commentid, storyid, comment, upvote, downvote, answerPic, answerStr) 
        VALUES (NULL, '$storyid', '$comment', 0, 0, NULL, NULL)";

    $result = $mysqli->query($query);
    

?>