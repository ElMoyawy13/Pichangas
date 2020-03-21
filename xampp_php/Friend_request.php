<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $sujeto = $_POST["username"];
    $amigo= $_POST["amigo"];

    $statement = mysqli_prepare($con, "INSERT INTO amistad (sujeto, amigo) VALUES (?, ?)");
    mysqli_stmt_bind_param($statement, "ii", $sujeto, $amigo);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>