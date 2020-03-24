<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $email = $_POST["correo"];
    
    $response = array();
    $response["success"] = false;

    $statement = mysqli_prepare($con, "SELECT indice FROM usuario WHERE correo = ?");
    mysqli_stmt_bind_param($statement, "s", $email);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $indice);
    mysqli_stmt_fetch($statement);
$response["indice"] = $indice;
    
    $statement = mysqli_prepare($con, "SELECT amigo FROM amistad WHERE sujeto = ?");
    mysqli_stmt_bind_param($statement, "i", $indice);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $amigo);

    $friends = array();

    while(mysqli_stmt_fetch($statement)){
        $statement2 = mysqli_prepare($con, "SELECT name, correo FROM usuario WHERE indice = ?");
        mysqli_stmt_bind_param($statement2, "i", $amigo);
        mysqli_stmt_execute($statement2);
        mysqli_stmt_store_result($statement2);
        mysqli_stmt_bind_result($statement2, $name, $correo);
        while(mysqli_stmt_fetch($statement2)) {
            $amigo = array();
            $amigo["name"] = $name;
            $amigo["correo"] = $correo;
            array_push($friends, $amigo);
        }
    }

    $statement = mysqli_prepare($con, "SELECT sujeto FROM amistad WHERE amigo = ? AND aceptado = 1");
    mysqli_stmt_bind_param($statement, "i", $indice);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $amigo);    

    while(mysqli_stmt_fetch($statement)){
        $statement2 = mysqli_prepare($con, "SELECT name, correo FROM usuario WHERE indice = ?");
        mysqli_stmt_bind_param($statement2, "i", $amigo);
        mysqli_stmt_execute($statement2);
        mysqli_stmt_store_result($statement2);
        mysqli_stmt_bind_result($statement2, $name, $correo);
        while(mysqli_stmt_fetch($statement2)) {
            $amigo = array();
            $amigo["name"] = $name;
            $amigo["correo"] = $correo;
            array_push($friends, $amigo);
        }
    }
    
    $response["success"] = true;  
    $response["friends"] = $friends;

    echo json_encode($response);
?>