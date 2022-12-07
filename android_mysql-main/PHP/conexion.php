<?php
$mysqlConexion = new mysqli("localhost","root","","android_mysql1");
if($mysqlConexion->connect_error){
    die ("Error de conexion");
}else{
  //  echo "Conexion exitosa";
}