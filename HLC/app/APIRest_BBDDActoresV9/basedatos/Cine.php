<?php
class Cine
{
	private $host = 'localhost';
	private $user = 'admin';
	private $password = "oRf8eAlVJBky";
	private $database = "cine";

	//53XGcnhRn9lF

	public function dameConexion()
	{
		$conn = new mysqli($this->host, $this->user, $this->password, $this->database);
		$conn->set_charset("utf8"); //Para evitar problemas con tildes, ñ y caracteres no estandar
		if ($conn->connect_error) {
			die("Error al conectar con MYSQL" . $conn->connect_error);
		} else {
			return $conn;
		}
	}
}
?>
