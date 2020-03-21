<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $pichanga = $_POST["pichanga"];
    
    $statement = mysqli_prepare($con, "SELECT participante FROM participacion WHERE pichanga = ?");
    mysqli_stmt_bind_param($statement, "i", $pichanga);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $participante);
    
    $response = array();
    $response["success"] = false;  
    $response["participants"] = array();
    
    while(mysqli_stmt_fetch($statement)){
        $userStatement = mysqli_prepare($con, "SELECT name FROM usuario WHERE indice = ?");
        mysqli_stmt_bind_param($userStatement, "i", $participante);
        mysqli_stmt_execute($userStatement);
        mysqli_stmt_store_result($userStatement);
        mysqli_stmt_bind_result($userStatement, $name);
        
        while(mysqli_stmt_fetch($userStatement)) {
            $user = array();
            $user["id"] = $participante;
            $user["name"] = $name;
            $response["participants"[] = $user;
        }
        $response["success"] = true;  
    }
    
    echo json_encode($response);
?>