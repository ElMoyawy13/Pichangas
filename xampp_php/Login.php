<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $email = $_POST["correo"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT name FROM usuario WHERE correo = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $email, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $name);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["name"] = $name;
        $response["correo"] = $email;
        $response["password"] = $password;
    }
    
    echo json_encode($response);
?>