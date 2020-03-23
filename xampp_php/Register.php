<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $email = $_POST["correo"];
    $statement = mysqli_prepare($con, "INSERT INTO usuario (name, username, password, correo) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssss", $name, $username, $password, $email);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>