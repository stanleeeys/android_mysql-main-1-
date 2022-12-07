<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $id=$_POST['id'];
    $eliminar="DELETE FROM usuarios WHERE id='".$id."'";
    $resultado=$mysqlConexion->query($eliminar);
    if($mysqlConexion->affected_rows>0){
        if($resultado==true){
            echo "Usuario borrado exitosamente";
        }else{
            echo "Error al borrar el usuario";
        }
    }
    $mysqlConexion;
}
?>