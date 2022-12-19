<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST');
header("Access-Control-Allow-Headers: X-Requested-With");

include("connection.php");

if(isset($_POST["email"]) && $_POST["email"] != "" && isset($_POST["password"]) && $_POST["password"] != ""  ){
    $User = $_POST["email"];
    $Password = $_POST["password"];
}else{
     $response = [];
     $response["success"] = false;   
     echo json_encode($response);
     return; 
 }

$query = $mysqli->prepare("Select * from users WHERE email = ? && password=?");
$query->bind_param("ss", $User, $Password);
$query->execute();

$array = $query->get_result();
$response = [];
while($credentials = $array->fetch_assoc()){
    $response[] = $credentials;
}

if(!$response ){
    $response["success"] = "user_does_not_exit";   
}

else{
  
    $response["success"] = true;   

}
echo json_encode($response);