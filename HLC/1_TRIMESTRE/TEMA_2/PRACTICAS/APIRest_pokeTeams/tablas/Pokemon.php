<?php
class Pokemon
{

	private $tabla = "pokemon";
	public $N_Pokedex;
	public $Especie;
	public $Denominacion;
	public $Descripcion;
	public $Sprite;
	public $Tipo_1;
	public $Tipo_2;
	public $Tamaño;
	public $Peso;
	public $Habilidades;
	public $Estadisticas;
	private $conn;

	public function __construct($db)
	{
		$this->conn = $db;
	}

	function leer()
	{
		if ($this->N_Pokedex >= 0) {
			$stmt = $this->conn->prepare("
			SELECT * FROM " . $this->tabla . " WHERE N_Pokedex = ?");
			$stmt->bind_param("i", $this->N_Pokedex);
		} else { //Si no se le pasa id correcto hace un SELECT masivo
			$stmt = $this->conn->prepare("SELECT * FROM " . $this->tabla);
		}
		$stmt->execute();
		$result = $stmt->get_result();
		return $result;
	}

	function leerSegunEspecie()
	{
		if ($this->Especie != 0) {
			$stmt = $this->conn->prepare("
			SELECT * FROM " . $this->tabla . " WHERE Especie = ?");
			$stmt->bind_param("s", $this->Especie);
		} else {
			$stmt = $this->conn->prepare("SELECT * FROM " . $this->tabla);
		}
		$stmt->execute();
		$result = $stmt->get_result();
		return $result;
	}

	
	function leerSegunDenominacion()
	{
		if ($this->Denominacion != 0) {
			$stmt = $this->conn->prepare("
			SELECT * FROM " . $this->tabla . " WHERE Denominacion = ?");
			$stmt->bind_param("s", $this->Denominacion);
		} else {
			$stmt = $this->conn->prepare("SELECT * FROM " . $this->tabla);
		}
		$stmt->execute();
		$result = $stmt->get_result();
		return $result;
	}

	function leerSegunTipo1()
	{
		if ($this->Tipo_1 != 0) {
			$stmt = $this->conn->prepare("
			SELECT * FROM " . $this->tabla . " WHERE Tipo_1 = ?");
			$stmt->bind_param("s", $this->Tipo_1);
		} else {
			$stmt = $this->conn->prepare("SELECT * FROM " . $this->tabla);
		}
		$stmt->execute();
		$result = $stmt->get_result();
		return $result;
	}

	function leerSegunTipo2()
	{
		if ($this->Tipo_2 != 0) {
			$stmt = $this->conn->prepare("
			SELECT * FROM " . $this->tabla . " WHERE Tipo_2 = ?");
			$stmt->bind_param("s", $this->Tipo_2);
		} else {
			$stmt = $this->conn->prepare("SELECT * FROM " . $this->tabla);
		}
		$stmt->execute();
		$result = $stmt->get_result();
		return $result;
	}

	function insertar()
	{
		$this->Habilidades = json_encode($this->Habilidades);
    	$this->Estadisticas = json_encode($this->Estadisticas);

		$stmt = $this->conn->prepare("
		    INSERT INTO " . $this->tabla . "(`Especie`, `Denominacion`, `Descripcion`, `Sprite`, `Tipo_1`, `Tipo_2`, `Tamaño`, `Peso`, `Habilidades`, `Estadisticas`)
			VALUES(?,?,?,?,?,?,?,?,?,?)");

			$this->Especie = strip_tags($this->Especie);
			$this->Denominacion = strip_tags($this->Denominacion);
			$this->Descripcion = strip_tags($this->Descripcion);
			$this->Sprite = strip_tags($this->Sprite);
			$this->Tipo_1 = strip_tags($this->Tipo_1);
			$this->Tipo_2 = strip_tags($this->Tipo_2);
			$this->Tamaño = strip_tags($this->Tamaño);
			$this->Peso = strip_tags($this->Peso);
			$this->Habilidades = strip_tags($this->Habilidades);
			$this->Estadisticas = strip_tags($this->Estadisticas);

		$stmt->bind_param("ssssssddss", $this->Especie, $this->Denominacion, $this->Descripcion, $this->Sprite, $this->Tipo_1, $this->Tipo_2, 
    		$this->Tamaño, $this->Peso, $this->Habilidades, $this->Estadisticas);
		if ($stmt->execute()) {

			return true;
		}
		return false;
	}


	function actualizar()
	{
		$this->Habilidades = json_encode($this->Habilidades);
    	$this->Estadisticas = json_encode($this->Estadisticas);

		$stmt = $this->conn->prepare("
		    UPDATE " . $this->tabla . " 
			SET Especie = ?, Denominacion = ?, Descripcion = ?, Sprite = ?, Tipo_1 = ?, Tipo_2 = ?, Tamaño = ?, Peso = ?, Habilidades = ?, Estadisticas = ? 
   			WHERE N_Pokedex = ?");

			$this->Especie = strip_tags($this->Especie);
			$this->Denominacion = strip_tags($this->Denominacion);
			$this->Descripcion = strip_tags($this->Descripcion);
			$this->Sprite = strip_tags($this->Sprite);
			$this->Tipo_1 = strip_tags($this->Tipo_1);
			$this->Tipo_2 = strip_tags($this->Tipo_2);
			$this->Tamaño = strip_tags($this->Tamaño);
			$this->Peso = strip_tags($this->Peso);
			$this->Habilidades = strip_tags($this->Habilidades);
			$this->Estadisticas = strip_tags($this->Estadisticas);
			$this->N_Pokedex = strip_tags($this->N_Pokedex);

		$stmt->bind_param("ssssssddssi", $this->Especie, $this->Denominacion, $this->Descripcion, $this->Sprite, $this->Tipo_1, $this->Tipo_2, 
			$this->Tamaño, $this->Peso, $this->Habilidades, $this->Estadisticas, $this->N_Pokedex);

		if ($stmt->execute()) {
			return true;
		}

		return false;
	}



	function borrar()
	{

		$stmt = $this->conn->prepare("
			DELETE FROM " . $this->tabla . " 
			WHERE N_Pokedex = ?");

		$this->N_Pokedex = strip_tags($this->N_Pokedex);
		$stmt->bind_param("i", $this->N_Pokedex);
		if ($stmt->execute()) {
			return true;
		}

		return false;
	}

}
?>