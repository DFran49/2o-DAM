-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.5.2-MariaDB-ubu2404 - mariadb.org binary distribution
-- SO del servidor:              debian-linux-gnu
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para empresa
CREATE DATABASE IF NOT EXISTS `empresa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci */;
USE `empresa`;

-- Volcando estructura para tabla empresa.departamentos
CREATE TABLE IF NOT EXISTS `departamentos` (
  `id_departamento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_departamento` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_departamento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla empresa.departamentos: ~5 rows (aproximadamente)
INSERT INTO `departamentos` (`id_departamento`, `nombre_departamento`) VALUES
	(1, 'Ventas'),
	(2, 'Administración'),
	(3, 'Limpieza'),
	(4, 'Logística'),
	(5, 'Dirección');

-- Volcando estructura para tabla empresa.empleados
CREATE TABLE IF NOT EXISTS `empleados` (
  `id_empleado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL DEFAULT '',
  `salario` decimal(10,2) NOT NULL DEFAULT 0.00,
  `fecha_ingreso` date NOT NULL,
  `id_departamento` int(11) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  KEY `FK_empleados_departamentos` (`id_departamento`),
  CONSTRAINT `FK_empleados_departamentos` FOREIGN KEY (`id_departamento`) REFERENCES `departamentos` (`id_departamento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla empresa.empleados: ~6 rows (aproximadamente)
INSERT INTO `empleados` (`id_empleado`, `nombre`, `salario`, `fecha_ingreso`, `id_departamento`) VALUES
	(1, 'Luis', 0.00, '2025-03-11', 1),
	(2, 'Laura', 2890.76, '2025-03-11', 4),
	(3, 'Guillermo', 32183.32, '2025-03-11', 3),
	(4, 'Jose', 879132.67, '2025-03-11', 1),
	(5, 'Andrea', 123243.32, '2025-03-11', 2),
	(6, 'Geronimo', 1999.32, '2025-03-11', 3);


-- Volcando estructura de base de datos para tienda_nueva
CREATE DATABASE IF NOT EXISTS `tienda_nueva` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci */;
USE `tienda_nueva`;

-- Volcando estructura para tabla tienda_nueva.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id_cliente` int(11) NOT NULL,
  `nombre_completo` varchar(100) NOT NULL DEFAULT '0',
  `correo` varchar(150) NOT NULL DEFAULT '0',
  `telefono` varchar(15) NOT NULL DEFAULT '0',
  `fecha_migracion` date DEFAULT NULL,
  PRIMARY KEY (`id_cliente`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla tienda_nueva.clientes: ~7 rows (aproximadamente)
INSERT INTO `clientes` (`id_cliente`, `nombre_completo`, `correo`, `telefono`, `fecha_migracion`) VALUES
	(1, 'Juan', 'juan@mail.com', '666666666', '2025-03-11'),
	(2, 'Raúl', 'raul@mail.com', '555555555', '2025-03-11'),
	(3, 'Julián', 'julian@mail.com', '444444444', '2025-03-11'),
	(4, 'Maurisio', 'mauro@mail.com', '333333333', '2025-03-11'),
	(5, 'Alejandro', 'alesis@mail.com', '222222222', '2025-03-11'),
	(10, 'a', 'a', '666', NULL),
	(11, 'a', 'a', '666', NULL);


-- Volcando estructura de base de datos para tienda_vieja
CREATE DATABASE IF NOT EXISTS `tienda_vieja` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci */;
USE `tienda_vieja`;

-- Volcando estructura para tabla tienda_vieja.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL DEFAULT '0',
  `email` varchar(150) NOT NULL DEFAULT '0',
  `telefono` varchar(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla tienda_vieja.clientes: ~5 rows (aproximadamente)
INSERT INTO `clientes` (`id`, `nombre`, `email`, `telefono`) VALUES
	(1, 'Juan', 'juan@mail.com', '666666666'),
	(2, 'Raúl', 'raul@mail.com', '555555555'),
	(3, 'Julián', 'julian@mail.com', '444444444'),
	(4, 'Maurisio', 'mauro@mail.com', '333333333'),
	(5, 'Alejandro', 'alesis@mail.com', '222222222');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
