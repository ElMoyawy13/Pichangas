<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $invitado = $_POST["invitado"];
    $invitacion = $_POST["invitacion"];
    $pichanga = $_POST["pichanga"];

    $deleteStatement = mysqli_prepare($con, "DELETE FROM invitacion WHERE indice=?");
    mysqli_stmt_bind_param($deleteStatement, "i",$invitacion);
    mysqli_stmt_execute($deleteStatement);
    
    $statement = mysqli_prepare($con, "INSERT INTO participacion (participante, pichanga, owner) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "iii", $invitado, $pichanga, 0);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>
