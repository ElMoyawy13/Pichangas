<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $user = $_POST["user"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM usuario WHERE indice = ?");
    mysqli_stmt_bind_param($statement, "i", $user);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $indice, $name, $username, $correo, $nacimiento);
    
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