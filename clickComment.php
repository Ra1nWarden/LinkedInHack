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
    $storyID = $queryJSON['storyid'];
    $commentid = $queryJSON['commentid'];

    $query="SELECT answerStr, answerPic FROM CommentsInStory WHERE storyid = '$storyID' AND commentid = '$commentid'";
    $result = $mysqli->query($query);

    $response = array();
    while ($row = $result->fetch_assoc()) {
        if ($row['answerPic']) {
            $response['answerPic'] = $row['answerPic'];
        }
        if ($row['answerStr']) {
            $response['answerStr'] = $row['answerStr'];
        }
    }

    echo json_encode($response);

?>
