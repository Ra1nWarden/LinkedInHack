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
    $latitude = $queryJSON['latitude'];
    $longitude = $queryJSON['longitude'];
    $name = $queryJSON['name'];
    

    $query = "INSERT INTO Story(latitude, longitude, timestamp, upvote, downvote, storyid, name) VALUES 
        ('$latitude', '$longitude', NULL, 0, 0, NULL, '$name')";
    $result = $mysqli->query($query);

    $toUploadImage = $queryJSON['image'];

    $query2 = "SELECT storyid FROM Story WHERE latitude = '$latitude' AND longitude = '$longitude'";
    $result2 = $mysqli->query($query2);

    $storyid_temp = '';
    while ($row = $result2->fetch_assoc()) {
        $storyid_temp = $row['storyid'];
    }

    $file_path = "uploads/";

    // Get image string posted from Android App
    $base = $toUploadImage;
    // Get file name posted from Android App, give an unique name each time
    $time = time();
    $filename = $time.".jpg";
    // Decode Image
    $binary=base64_decode($base);
    header('Content-Type: bitmap; charset=utf-8');
    // Images will be saved under 'www/imgupload/uplodedimages' folder
    $file = fopen($file_path.$filename, 'wb');
    // Create File
    fwrite($file, $binary);
    fclose($file);
    echo 'Image upload complete, Please check your php file directory';

    $url = $file_path.$filename;

    $query3 = "INSERT INTO PicsInStory(picid, storyid, url, commentid) VALUES (NULL, '$storyid_temp', '$url', NULL)";

    $result3 = $mysqli->query($query3);

?>