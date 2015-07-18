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
    $toUploadStoryID = $queryJSON['storyid'];
    $toUploadImage = $queryJSON['image'];
    $toUploadCommentID = $queryJSON['commentid'];


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

    $query = "INSERT INTO PicsInStory(picid, storyid, url, commentid) VALUES (NULL, '$toUploadStoryID', '$url', '$toUploadCommentID')";

    $result = $mysqli->query($query);

    $response = array();
    $response['source_pic'] = $toUploadImage;
    $response['pic_file_name'] = $filename;
    echo json_encode($response);

?>