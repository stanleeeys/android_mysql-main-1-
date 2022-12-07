<?php
if($_SERVER["REQUEST_METHOD"]=="GET"){
    require_once 'conexion.php';
    $id=$_GET['id'];
    $consulta = "SELECT*FROM usuarios WHERE id='".$id."'";
    $resultado=$mysqlConexion ->query($consulta);
    if($mysqlConexion->affected_rows > 0){
        while($row=$resultado->fetch_assoc()){
            $array=$row;
        }
        echo json_encode($array);
    }else{
        echo "No hay registros";
    }
    $resultado->close();
    $mysqlConexion->close();

}