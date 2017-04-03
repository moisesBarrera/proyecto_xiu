-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-04-2017 a las 04:17:46
-- Versión del servidor: 5.6.26
-- Versión de PHP: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sgg`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE IF NOT EXISTS `alumnos` (
  `idAlumnos` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `curp` varchar(45) DEFAULT NULL,
  `matricula` varchar(45) DEFAULT NULL,
  `usuario_idusuario` int(11) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `grupo_idgrupo` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`idAlumnos`, `nombre`, `apellido`, `genero`, `curp`, `matricula`, `usuario_idusuario`, `observaciones`, `grupo_idgrupo`) VALUES
(1, 'Ernesto', 'Galeana', 'Masculino', 'asdsa', 'sadas', 1, '23', 1),
(2, 'Ernesto', 'Galeanas', 'Masculino', 'asdsa', 'sadas', 1, '23', 2),
(3, 'Israel', 'Padilla', 'Masculino', 'adadsasd', '12312', 1, 'hola', 2),
(4, 'Mois', 'Barrera', 'Masculino', 'sdadas', '1231', 1, 'ds', 2),
(5, 'Galeana', 'Ernesto', 'Masculino', '1dsa', '2321', 1, 'sds', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`idAlumnos`),
  ADD KEY `fk_Alumnos_usuario1_idx` (`usuario_idusuario`),
  ADD KEY `fk_Alumnos_grupo1_idx` (`grupo_idgrupo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `idAlumnos` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD CONSTRAINT `fk_Alumnos_grupo1` FOREIGN KEY (`grupo_idgrupo`) REFERENCES `grupo` (`idgrupo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Alumnos_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuarios` (`idusuarios`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
