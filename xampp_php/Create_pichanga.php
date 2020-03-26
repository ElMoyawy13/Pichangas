<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $name = $_POST["name"];
    $description = $_POST["description"];
    $place = $_POST["place"];
    $email = $_POST["correo"];
    $year = $_POST["year"];
    $month = $_POST["month"];
    $day = $_POST["day"];
    $hour = $_POST["hour"];
    $minute = $_POST["minute"];

    $statement = mysqli_prepare($con, "SELECT indice FROM usuario WHERE correo = ?");
    mysqli_stmt_bind_param($statement, "s", $email);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $indice);
    mysqli_stmt_fetch($statement);

    $statement = mysqli_prepare($con, "INSERT INTO pichanga (nombre, descripcion, lugar, year, month, day, hour, minute, dueno) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssssssssi", $name, $description, $place, $year, $month, $day, $hour, $minute, $indice);
    mysqli_stmt_execute($statement);

    $statement = mysqli_prepare($con, "SELECT indice FROM pichanga WHERE nombre = ? AND descripcion = ? AND lugar = ? AND year = ? AND month = ? AND day = ? AND hour = ? AND minute = ? AND dueno = ?");
    mysqli_stmt_bind_param($statement, "ssssssssi", $name, $description, $place, $year, $month, $day, $hour, $minute, $indice);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $indice_pichanga);
    mysqli_stmt_fetch($statement);

    $statement = mysqli_prepare($con, "INSERT INTO participacion (participante, pichanga, owner) VALUES (?, ?, 1)");
    mysqli_stmt_bind_param($statement, "ii", $indice, $indice_pichanga);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>