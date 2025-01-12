<?php
class Actores
{

	private $tabla = "actores";
	public $id;
	public $nombre;
	public $edad;
	public $activo;
	public $fotourl;
	public $fotocodif;
	private $conn;

	public function __construct($db)
	{
		$this->conn = $db;
	}

	function leer()
	{
		if ($this->id >= 0) {
			$stmt = $this->conn->prepare("
			SELECT * FROM " . $this->tabla . " WHERE id = ?");
			$stmt->bind_param("i", $this->id);
		} else { //Si no se le pasa id correcto hace un SELECT masivo
			$stmt = $this->conn->prepare("SELECT * FROM " . $this->tabla);
		}
		$stmt->execute();
		$result = $stmt->get_result();
		return $result;
	}

	function leervivos()
	{
		if ($this->activo == 0 || $this->activo == 1) {
			$stmt = $this->conn->prepare("
			SELECT * FROM " . $this->tabla . " WHERE activo = ?");
			$stmt->bind_param("i", $this->activo);
		} else {
			$stmt = $this->conn->prepare("SELECT * FROM " . $this->tabla);
		}
		$stmt->execute();
		$result = $stmt->get_result();
		return $result;
	}

	function insertar()
	{

		$stmt = $this->conn->prepare("
		    INSERT INTO " . $this->tabla . "(`nombre`, `edad`, `activo`, `fotourl`, `fotocodif`)
			VALUES(?,?,?,?,?)");

		$this->nombre = strip_tags($this->nombre);
		$this->edad = strip_tags($this->edad);
		$this->activo = strip_tags($this->activo);

		//Se meten las fotos vacías
		$stmt->bind_param("siiss", $this->nombre, $this->edad, $this->activo,$this->fotourl,$this->fotocodif);
		if ($stmt->execute()) {

			return true;
		}
		return false;
	}


	function actualizar()
	{

		$stmt = $this->conn->prepare("
		    UPDATE " . $this->tabla . " 
			SET nombre= ?, edad = ?, activo = ?, fotourl = ?, fotocodif = ? WHERE id = ?");

		$this->nombre = strip_tags($this->nombre);
		$this->edad = strip_tags($this->edad);
		$this->activo = strip_tags($this->activo);
		$this->id = strip_tags($this->id);
		$this->fotourl = strip_tags($this->fotourl);
		$this->fotocodif = strip_tags($this->fotocodif);
		$stmt->bind_param("siissi", $this->nombre, $this->edad, $this->activo,$this->fotourl,$this->fotocodif, $this->id);

		if ($stmt->execute()) {
			return true;
		}

		return false;
	}



	function borrar()
	{

		$stmt = $this->conn->prepare("
			DELETE FROM " . $this->tabla . " 
			WHERE id = ?");

		$this->id = strip_tags($this->id);
		$stmt->bind_param("i", $this->id);
		if ($stmt->execute()) {
			return true;
		}

		return false;
	}

}
?>