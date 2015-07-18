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

    $response = array();
    $response['stories'] = array();

    $query = "SELECT * FROM Story";
    $result = $mysqli->query($query);

    while ($row = $result->fetch_assoc()) {
        $var = array();
        $var['name'] = $row['name'];
        $var['latitude'] = $row['latitude'];
        $var['longitude'] = $row['longitude'];
        $var['storyid'] = $row['storyid'];
        $var['timestamp'] = $row['timestamp'];
        $var['upvote'] = $row['upvote'];
        $var['downvote'] = $row['downvote'];
        $sid = $row['storyid'];
        $query2 = "SELECT url FROM PicsInStory WHERE storyid = '$sid'";
        $result2 = $mysqli->query($query2);
        $temp_urls = array();
        while ($row2 = $result2->fetch_assoc()) {
            $temp_urls[] = $row2['url'];
        }
        $var['urls'] = $temp_urls;


        $query3 = "SELECT commentid, comment, upvote, downvote, answerPic, answerStr FROM CommentsInStory WHERE storyid = '$sid'";
        $result3 = $mysqli->query($query3);
        $temp_comments = array();
        while ($row3 = $result3->fetch_assoc()) {
            $temp_comment = array();
            $temp_comment['commentid'] = $row3['commentid'];
            $temp_comment['comment'] = $row3['comment'];
            $temp_comment['upvote'] = $row3['upvote'];
            $temp_comment['downvote'] = $row3['downvote'];
            $temp_comments[] = $temp_comment;
        }
        $var['comments'] = $temp_comments;


        $response['stories'][] = $var;



    }

    echo json_encode($response);
    

?>