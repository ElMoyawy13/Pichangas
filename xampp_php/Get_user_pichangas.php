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
    
    $statement = mysqli_prepare($con, "SELECT pichanga FROM participacion WHERE participante = ?");
    mysqli_stmt_bind_param($statement, "i", $indice);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $participacion);

    $pichangas = array();

    while(mysqli_stmt_fetch($statement)){
        $statement2 = mysqli_prepare($con, "SELECT indice, nombre, fecha FROM pichanga WHERE indice = ?");
        mysqli_stmt_bind_param($statement2, "i", $participacion);
        mysqli_stmt_execute($statement2);
        mysqli_stmt_store_result($statement2);
        mysqli_stmt_bind_result($statement2, $pichangaIndex, $pichangaName, $pichangaDate);
        while(mysqli_stmt_fetch($statement2)) {
            $match = array();
            $match["id"] = $pichangaIndex;
            $match["name"] = $pichangaName;
            $match["date"] = $pichangaDate;
            array_push($pichangas, $match);
        }
    }

    $response["success"] = true;  
    $response["pichangas"] = $pichangas;

    echo json_encode($response);
?>