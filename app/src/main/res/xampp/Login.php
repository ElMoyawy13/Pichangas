<?php
    $con = mysqli_connect("localhost", "root", "", "usuarios_pichangas");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $username, $password, $age, $correo);
    

    $statement2 = mysqli_prepare($con, "SELECT * FROM user WHERE correo = ? AND password = ?");
    mysqli_stmt_bind_param($statement2, "ss", $correo, $password);
    mysqli_stmt_execute($statement2);
    
    mysqli_stmt_store_result($statement2);
    mysqli_stmt_bind_result($statement2, $userID, $name, $username, $password, $age, $correo);
    


    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement2)){
    $response["success"] = true;  
    $response["name"] = $name;
    $response["age"] = $age;
    $response["username"] = $username;
    $response["password"] = $password;
    $response["correo"] = $correo;
    }
    
    echo json_encode($response);
?>