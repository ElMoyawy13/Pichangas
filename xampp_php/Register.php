<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $name = $_POST["name"];
    $password = $_POST["password"];
    $email = $_POST["correo"];
    $statement = mysqli_prepare($con, "INSERT INTO usuario (name, password, correo) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sss", $name, $password, $email);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>