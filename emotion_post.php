<?php

require "conn.php";

$name = $_POST["name"];
$emotion_level = $_POST["emotion_level"];
$date = date_create()->format('Y-m-d H:i:s');

$mysql_qry = "INSERT INTO emotions (username, emotion, date) VALUES ('$name', '$emotion_level', '$date')";

if ($conn -> query ($mysql_qry) === TRUE) {
    echo "insert success";
}
else {
    echo "Error:" . $mysql_qry . "<br>" . $conn->error;
}

$conn -> close();

?>
