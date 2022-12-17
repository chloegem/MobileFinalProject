<?php
 
 include "connection.php";
 
 if (isset($_POST['user_id'])) {
 
 $user_id = $_POST['user_id'];
 
     if (empty($user_id)) {
     echo("Invalid user");
     exit();
     }else{
         $sql = "SELECT * FROM users WHERE user_id = '" . $user_id . "'";
         $result = mysqli_query($mysqli, $sql);
 
         if (mysqli_num_rows($result) != 0) {
             $raw = mysqli_fetch_assoc($result);
             $response[] = $raw;
             $json_respnse = json_encode($response);
             echo $json_respnse;
             exit();
         }else{
             echo("Invalid user");
             exit();
         }
     }
 }