<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $invitado = $_POST["invitado"];
    $invitador = $_POST["invitador"];
    $pichanga = $_POST["pichanga"];
    $statement = mysqli_prepare($con, "INSERT INTO invitacion (invitado, invitador, pichanga) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "iii", $invitado, $invitador, $pichanga);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>