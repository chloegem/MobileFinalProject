<?php
 
 include "connection.php";

 if (isset($_POST['user_id']) && isset($_POST['date'])){
     $user = $_POST['user_id'];
     $date = $_POST['date'];
     if (empty($user)) {
         echo "Empty user id";
         exit();
     }
     else if(empty($date)){
         echo "Empty date";
         exit();
     }
     else if(isset($_POST['running'])){
         $running = $_POST['running'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET running = ? Where user_id = ? AND date = ?;");
             $query->bind_param("sis", $running, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, running, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $running, $date);
             $query->execute();
             echo "Data Added!";
         }
     } 
     else if (isset($_POST['dancing'])){
         $dancing = $_POST['dancing'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET dancing = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $dancing, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, dancing, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $dancing, $date);
             $query->execute();
             echo "Data Added!";
 
         }
     }
     else if (isset($_POST['boxing'])){
         $boxing = $_POST['boxing'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET boxing = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $boxing, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, boxing, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $boxing, $date);
             $query->execute();
             echo "Data Added!";
 
         }
     }
     else if (isset($_POST['baseball'])){
         $baseball = $_POST['baseball'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET baseball = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $baseball, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, baseball, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $baseball, $date);
             $query->execute();
             echo "Data Added!";
 
         }
     } 
     else if (isset($_POST['basketball'])){
         $basketball = $_POST['basketball'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET basketball = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $basketball, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, basketball, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $basketball, $date);
             $query->execute();
             echo "Data Added!";
 
         }
     }
     else if (isset($_POST['football'])){
         $football = $_POST['football'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET football = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $football, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, football, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $football, $date);
             $query->execute();
             echo "Data Added!";
 
         }
     }
     else if (isset($_POST['swimming'])){
         $swimming = $_POST['swimming'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET swimming = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $swimming, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, swimming, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $swimming, $date);
             $query->execute();
             echo "Data Added!";
 
         }
     }
     else if (isset($_POST['skiing'])){
         $skiing = $_POST['skiing'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET skiing = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $skiing, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, skiing, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $skiing, $date);
             $query->execute();
             echo "Data Added!";
 
         }
     }
     else if (isset($_POST['hiking'])){
         $hiking = $_POST['hiking'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET hiking = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $hiking, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, hiking, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $hiking, $date);
             $query->execute();
             echo "Data Added!";
 
         }
     }
     else if (isset($_POST['gymnastics'])){
         $gymnastics = $_POST['gymnastics'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET gymnastics = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $gymnastics, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, gymnastics, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $gymnastics, $date);
             $query->execute();
             echo "Data Added!";
 
         }
     }
     else if (isset($_POST['tennis'])){
         $tennis = $_POST['tennis'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET tennis = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $tennis, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, tennis, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $tennis, $date);
             $query->execute();
             echo "Data Added!";
         }
     }
     else if (isset($_POST['golf'])) {
         $golf = $_POST['golf'];
         $sql = "SELECT * FROM exercises WHERE user_id = '" . $user . "' AND date = " . $date . "";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $query = $mysqli->prepare("UPDATE exercises SET golf = ? WHERE user_id = ? AND date = ?;");
             $query->bind_param("sis", $golf, $user, $date);
             $query->execute();
             echo "Updated!";
         }
         else{
             $query = $mysqli->prepare("INSERT INTO exercises(user_id, golf, date) VALUES (?, ?, ?);");
             $query->bind_param("iss", $user, $golf, $date);
             $query->execute();
             echo "Data Added!";
 
         }
     }
 }