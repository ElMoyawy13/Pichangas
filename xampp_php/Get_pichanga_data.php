<?php
    $con = mysqli_connect("localhost", "root", "", "pichangas");
    
    $ID = $_POST["ID"];
    
    $statement = mysqli_prepare($con, "SELECT nombre, descripcion, lugar, fecha, hora, dueno FROM pichanga WHERE indice = ?");
    mysqli_stmt_bind_param($statement, "i", $ID);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $name, $description, $place, $date, $time, $owner);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["name"] = $name;
        $response["description"] = $description;
        $response["place"] = $place;
        $response["date"] = $date;
        $response["time"] = $time;
        $response["owner"] = $owner;
        
        $participants = array();
        $statement2 = mysqli_prepare($con, "SELECT participante, owner FROM participacion WHERE pichanga = ?");
        mysqli_stmt_bind_param($statement2, "i", $ID);
        mysqli_stmt_execute($statement2);
        mysqli_stmt_store_result($statement2);
        mysqli_stmt_bind_result($statement2, $participante, $thisIsTheOwner);
        while(mysqli_stmt_fetch($statement2)){
            $player = array();
            $statement3 = mysqli_prepare($con, "SELECT name, correo FROM usuario WHERE indice = ?");
            mysqli_stmt_bind_param($statement3, "i", $participante);
            mysqli_stmt_execute($statement3);
            mysqli_stmt_store_result($statement3);
            mysqli_stmt_bind_result($statement3, $participante_name, $participante_correo);
            while(mysqli_stmt_fetch($statement3)){
                $player["ID"] = $participante;
                $player["name"] = $participante_name;
                $player["email"] = $participante_correo;
                $player["owner"] = $thisIsTheOwner;
            }
            array_push($participants, $player);
        }
        $response["participants"] = $participants;
    }
    
    echo json_encode($response);
?>