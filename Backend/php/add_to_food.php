<?php
 
include "connection.php";

if (isset($_POST['user_id']) && isset($_POST['date'])) {
    $user = $_POST['user_id'];
    $date = $_POST['date'];

    if (empty($user)) {
        exit();
    }else if(empty($date)){
        exit();
    }else if(isset($_POST['breakfast'])){
        $breakfast = $_POST['breakfast'];
        $sql = "SELECT * FROM food_consumptions WHERE user_id = '" . $user . "' AND date = " . $date . "";
        $result = mysqli_query($mysqli, $sql);

        if (mysqli_num_rows($result) != 0) {
            $query = $mysqli->prepare("UPDATE food_consumptions SET breakfast = ? Where user_id = ? AND date = ?;");
            $query->bind_param("sis", $breakfast, $user, $date);
            $query->execute();
            echo "Updated!";
        }else{
            $query = $mysqli->prepare("INSERT INTO food_consumptions(user_id, breakfast, date) VALUES (?, ?, ?);");
            $query->bind_param("iss", $user, $breakfast, $date);
            $query->execute();
            echo "Data Added!";
        }
    }else if(isset($_POST['lunch'])){
        $lunch = $_POST['lunch'];
        $sql = "SELECT * FROM food_consumptions WHERE user_id = '" . $user . "' AND date = " . $date . "";
        $result = mysqli_query($mysqli, $sql);

        if (mysqli_num_rows($result) != 0) {
            $query = $mysqli->prepare("UPDATE food_consumptions SET lunch = ? Where user_id = ? AND date = ?;");
            $query->bind_param("sis", $lunch, $user, $date);
            $query->execute();
            echo "Updated!";
        }else{
            $query = $mysqli->prepare("INSERT INTO food_consumptions(user_id, breakfast, date) VALUES (?, ?, ?);");
            $query->bind_param("iss", $user, $lunch, $date);
            $query->execute();
            echo "Data Added!";
        }
    }else if(isset($_POST['dinner'])){
        $dinner = $_POST['dinner'];
        $sql = "SELECT * FROM food_consumptions WHERE user_id = '" . $user . "' AND date = " . $date . "";
        $result = mysqli_query($mysqli, $sql);

        if (mysqli_num_rows($result) != 0) {
            $query = $mysqli->prepare("UPDATE food_consumptions SET dinner = ? Where user_id = ? AND date = ?;");
            $query->bind_param("sis", $dinner, $user, $date);
            $query->execute();
            echo "Updated!";
        }else{
            $query = $mysqli->prepare("INSERT INTO food_consumptions(user_id, dinner, date) VALUES (?, ?, ?);");
            $query->bind_param("iss", $user, $dinner, $date);
            $query->execute();
            echo "Data Added!";
        }
    }else if(isset($_POST['snack'])){
        $snack = $_POST['snack'];
        $sql = "SELECT * FROM food_consumptions WHERE user_id = '" . $user . "' AND date = " . $date . "";
        $result = mysqli_query($mysqli, $sql);

        if (mysqli_num_rows($result) != 0) {
            $query = $mysqli->prepare("UPDATE food_consumptions SET snack = ? Where user_id = ? AND date = ?;");
            $query->bind_param("sis", $snack, $user, $date);
            $query->execute();
            echo "Updated!";
        }else{
            $query = $mysqli->prepare("INSERT INTO food_consumptions(user_id, snack, date) VALUES (?, ?, ?);");
            $query->bind_param("iss", $user, $snack, $date);
            $query->execute();
            echo "Data Added!";
        }
    }
}