<?php
    $con = mysqli_connect("localhost", "root", "", "usuarios_pichangas");
    
    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $correo = $_POST["correo"];
    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, age, password, correo) VALUES (?, ?, ?, ?,?)");
    mysqli_stmt_bind_param($statement, "ssiss", $name, $username, $age, $password, $correo);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>