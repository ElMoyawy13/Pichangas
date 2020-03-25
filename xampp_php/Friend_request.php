<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $sujeto = $_POST["sujeto"];
    $amigo= $_POST["amigo"];

    $statement = mysqli_prepare($con, "SELECT indice FROM usuario WHERE correo = ?");
    mysqli_stmt_bind_param($statement, "s", $sujeto);
    mysqli_stmt_execute($statement);    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $indice_sujeto);
    mysqli_stmt_fetch($statement);

    $statement = mysqli_prepare($con, "SELECT indice FROM usuario WHERE correo = ?");
    mysqli_stmt_bind_param($statement, "s", $amigo);
    mysqli_stmt_execute($statement);    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $indice_amigo);
    mysqli_stmt_fetch($statement);

    $statement = mysqli_prepare($con, "INSERT INTO amistad (sujeto, amigo) VALUES (?, ?)");
    mysqli_stmt_bind_param($statement, "ii", $indice_sujeto, $indice_amigo);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>