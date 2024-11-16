<?php
class PokeTeams
{
	private $host = 'localhost';
	private $user = 'admin';
	private $password = "SXH6obmADJAM";
	private $database = "pokeTeams";

	//SXH6obmADJAM

	public function dameConexion()
	{
		$conn = new mysqli($this->host, $this->user, $this->password, $this->database);
		$conn->set_charset("utf8");
		if ($conn->connect_error) {
			die("Error al conectar con MYSQL" . $conn->connect_error);
		} else {
			return $conn;
		}
	}
}
?>