<?php 
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php'; 
    $nombre=$_POST["nombre"]; 
    $email=$_POST["email"]; 
    $telefono=$_POST["telefono"]; 
    $pass=$_POST["pass"]; 
    $query="INSERT INTO usuarios(nombre,email,telefono,pass) 
    VALUES('".$nombre."','".$email."','".$telefono."','".$pass."')"; 
    $resultado=$mysqlConexion->query($query); 
    if($resultado==true){ 
        echo "El usuario se inserto de forma exitosa"; 
    }else{ 
        echo "Error al insertar el usuario"; 
    } 
   // $resultado->close(); 
    $mysqlConexion->close(); 
}