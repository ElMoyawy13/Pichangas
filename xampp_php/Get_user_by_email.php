<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $email = $_POST["correo"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM usuario WHERE correo = ?");
    mysqli_stmt_bind_param($statement, "i", $email);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $indice, $name, $username, $correo, $password, $nacimiento);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["name"] = $name;
        $response["username"] = $username;
        $response["correo"] = $correo;
        $response["nacimiento"] = $nacimiento;
    }
    
    echo json_encode($response);
?>