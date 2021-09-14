<?php

require "conn.php";

$user_name = $_POST["user_name"];
//$mysql_qry = "SELECT * FROM emotions WHERE username like '$user_name';";
$mysql_qry = "SELECT emotion FROM emotions ORDER BY id DESC LIMIT 1;";

$result = $conn->query($mysql_qry);

if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
    echo $row["emotion"];
  }
}

else {
    echo "Error:" . $mysql_qry . "<br>" . $conn->error;
}

$conn -> close();

?>
