-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 28-10-2024 a las 11:09:40
-- Versión del servidor: 5.7.35-0ubuntu0.18.04.2
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cine`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actores`
--

CREATE TABLE `actores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `edad` int(3) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `fotourl` varchar(100) NOT NULL,
  `fotocodif` varchar(10000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `actores`
--

INSERT INTO `actores` (`id`, `nombre`, `edad`, `activo`, `fotourl`, `fotocodif`) VALUES
(92, 'Tom Hanks', 65, 1, '', ''),
(93, 'Al Pacino', 81, 1, '', ''),
(94, 'Brad Pitt', 58, 1, '', ''),
(95, 'Tim Robbins', 63, 1, '', ''),
(96, 'Morgan Freeman', 84, 1, '', ''),
(97, 'Marlon Brando', 80, 0, '', ''),
(99, 'Robert De Niro', 78, 1, '', ''),
(101, 'Leonardo DiCaprio', 47, 1, '', ''),
(102, 'Keanu Reeves', 57, 1, '', ''),
(103, 'Matt Damon', 51, 1, '', ''),
(104, 'Anne Hathaway', 39, 1, '', ''),
(105, 'Cate Blanchett', 52, 1, '', ''),
(106, 'Natalie Portman', 40, 1, '', ''),
(107, 'Michelle Pfeiffer', 63, 1, '', ''),
(108, 'Julia Roberts', 55, 1, 'juliachica.jpg', '/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIADgALQMBIgACEQEDEQH/xAAbAAACAwEBAQAAAAAAAAAAAAAFBgAEBwMCAf/EADQQAAIBAgQDBQYFBQAAAAAAAAECAwQRAAUSITFBcQYTIlFhIzKBkaGxFBWS0fAHFkLB8f/EABcBAQEBAQAAAAAAAAAAAAAAAAMCBAH/xAAdEQADAQEAAgMAAAAAAAAAAAAAAQIRITJBAxIi/9oADAMBAAIRAxEAPwD7/UHtI+aZhLltHJajpWCyEcJJP2HDrfCvSZD+Y1eibV3cfikANr+nz+2PdEoLRM6+JiZSDxJ5X+5w5ZPSpCg1W1vufP0/nrjPdtvTR8cLiBf9rUDD2dMiEC3hUDbAPNeyNMjeyj0l/da1vFyB641GCEaDdRY8MD81p1kpHUrY6dj5euJero+S+YZEaHvoZaeSMl0FwCN2Hl12I6jCjVQNTzNGeW4PmORxp+aqI6pJQtgwuQNuJsfk1v14VM+y+NKoF0BRxqQ6iB0wkUZrkYMlkZmM8l5G20jl5D4XO5wbzOnzlqtY4cvppKc6fblSzkW3NwQVttwv6YGUNBNl8cRceCSJSpHD30Hz3OH7L6qEUq94NdhtbliJfsZTpxyiomhp5IKuSQtCuoM/Ej+DC7nVfm/5iA0NXLTup9nHIYwo8thueu2DSv8AiZKuVT4QADw2AwQhlheJS9+Gx07H447vRXDxdE5o1rqFJQrrZt0kFmGoc+v3t5YDy1UQASRkYLfSWNuv1wU7VTS09Trhv3bLpcDmCdsJNdVM02t0Gp9z68r/AExHvgVTjwcs5qTQ9nXOsO9PIjgg8dLqzAfK2GBJPxGVU9XQVDLGxu2gA6lO44+hxnvamvkfJtUqd0apl7qLmsY3F+tr/EYu9hcymocnDSapaLvLSLa5j32YenmPji1OSTFfs0aloqeaI1Bq0ZxtdqZx87HfHFe8kmZPxAenVSWbutO/pc4vZfWUM9IJUlhdSLhgAb/HC/nmcKsUy0dm0qWkkHuqB98cb4aChnFbRyvTUOkd/IxkJJ9xPPryHQ4FNS5LO8k9RIYVZysSKOCi374qUxSpipc4d2aRoO6lS26slyf1XBHXHaryM1vdNSVEPd6Ay6id1PAj5HBNY8B+26wL2w1ZnnMqxnwU0YUEcL23wR7BMr0tTRkgMDcg+v8AzExMM/EmOUh2oMpomBkkoKdpf8m0DxH188A+20oo8vKkKpqHEaqu1lxMTBJ9HpYmJVJXS+zQMwWsDhhyUDZbdP8AZxYepqqAKiAlH8QAB2PP67/HExMXXGZl1H//2Q=='),
(109, 'Kate Winslet', 46, 1, '', ''),
(110, 'Scarlett Johansson', 37, 1, '', ''),
(111, 'Meryl Streep', 72, 1, '', ''),
(112, 'Emma Stone', 33, 1, '', ''),
(113, 'Charlize Theron', 46, 1, '', '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actores`
--
ALTER TABLE `actores`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actores`
--
ALTER TABLE `actores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
