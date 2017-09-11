# SQL Manager 2005 Lite for MySQL 3.7.0.1
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : biblioteca


SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `biblioteca`;

CREATE DATABASE `biblioteca`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

USE `biblioteca`;

#
# Structure for the `categorias` table : 
#

DROP TABLE IF EXISTS `categorias`;

CREATE TABLE `categorias` (
  `id_categoria` int(11) NOT NULL auto_increment,
  `descripcion` varchar(50) default NULL,
  PRIMARY KEY  (`id_categoria`),
  UNIQUE KEY `id_categoria` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tipo_elementos` table : 
#

DROP TABLE IF EXISTS `tipo_elementos`;

CREATE TABLE `tipo_elementos` (
  `id_tipoelemento` int(11) NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `cantMaxReservasPend` int(11) default NULL,
  PRIMARY KEY  (`id_tipoelemento`),
  UNIQUE KEY `id_tipoelemento` (`id_tipoelemento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `elementos` table : 
#

DROP TABLE IF EXISTS `elementos`;

CREATE TABLE `elementos` (
  `id_elemento` int(11) NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `stock` int(11) default NULL,
  `autor` varchar(50) default NULL,
  `genero` varchar(50) default NULL,
  `descripcion` varchar(200) default NULL,
  `id_tipoelemento` int(11) default NULL,
  PRIMARY KEY  (`id_elemento`),
  UNIQUE KEY `id_elemento` (`id_elemento`),
  KEY `id_tipoelemento` (`id_tipoelemento`),
  CONSTRAINT `elementos_tipoelemento_fk` FOREIGN KEY (`id_tipoelemento`) REFERENCES `tipo_elementos` (`id_tipoelemento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `personas` table : 
#

DROP TABLE IF EXISTS `personas`;

CREATE TABLE `personas` (
  `id_persona` int(11) NOT NULL auto_increment,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `dni` varchar(50) default NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `estado` tinyint(1) default NULL,
  `id_categoria` int(11) default NULL,
  PRIMARY KEY  (`id_persona`),
  UNIQUE KEY `id_persona` (`id_persona`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `personas_categoria_fk` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `reservas` table : 
#

DROP TABLE IF EXISTS `reservas`;

CREATE TABLE `reservas` (
  `id_elemento` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `fecha_registro` date NOT NULL,
  `fecha_inicio` date default NULL,
  `fecha_fin` date default NULL,
  `detalle` varchar(50) default NULL,
  `estado` varchar(50) default NULL,
  PRIMARY KEY  (`id_elemento`,`id_persona`,`fecha_registro`),
  KEY `id_elemento` (`id_elemento`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `reservas_persona_fk` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`),
  CONSTRAINT `reservas_elemento_fk` FOREIGN KEY (`id_elemento`) REFERENCES `elementos` (`id_elemento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `categorias` table  (LIMIT 0,500)
#

INSERT INTO `categorias` (`id_categoria`, `descripcion`) VALUES 
  (1,'Usuario'),
  (2,'Administrador'),
  (3,'Encargado');

COMMIT;

#
# Data for the `tipo_elementos` table  (LIMIT 0,500)
#

INSERT INTO `tipo_elementos` (`id_tipoelemento`, `nombre`, `cantMaxReservasPend`) VALUES 
  (1,'Libro',2),
  (2,'Revistas',3),
  (3,'Peliculas',2);

COMMIT;

#
# Data for the `elementos` table  (LIMIT 0,500)
#

INSERT INTO `elementos` (`id_elemento`, `nombre`, `stock`, `autor`, `genero`, `descripcion`, `id_tipoelemento`) VALUES 
  (1,'Harry Potter y la Piedra Filosofal',2,'J K Rowling','Fantasia','Harry Potter es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, considerada una de las sagas más importantes de la historia. ',1),
  (2,'Don Quijote de la mancha',1,'Miguel Cervantes','Novela Española','Don Quijote de la Mancha? es una novela escrita por el español Miguel de Cervantes Saavedra.',1),
  (5,'Descubrimiento America',2,'Nat Geo','Historia','Sin descripcion',2),
  (6,'Papeles en el viento',1,'Eduardo Sacheri','Novela','Sin descripcion',1),
  (7,'El señor de los Anillos',1,'J R R','Fantasia','Sin descripcion',1);

COMMIT;

#
# Data for the `personas` table  (LIMIT 0,500)
#

INSERT INTO `personas` (`id_persona`, `nombre`, `apellido`, `dni`, `usuario`, `password`, `estado`, `id_categoria`) VALUES 
  (1,'Franco','Pololo','33123456','polo1995','123456789',1,1),
  (2,'Manuel','Ellena','37774145','manu_landeta','123456789',1,1),
  (3,'Roman','Bressan','37896541','roman.94','123456789',1,1);

COMMIT;

#
# Data for the `reservas` table  (LIMIT 0,500)
#

INSERT INTO `reservas` (`id_elemento`, `id_persona`, `fecha_registro`, `fecha_inicio`, `fecha_fin`, `detalle`, `estado`) VALUES 
  (1,1,'2017-09-11','2017-09-11','2017-10-20','Sin detalle','Activa'),
  (1,1,'2017-09-12','2017-09-12','2017-11-15','Sin detalle','Activa'),
  (7,3,'2017-09-11','2017-09-11','2017-09-17','Sin detalle','Activa'),
  (7,3,'2017-10-11','2017-10-11','2017-12-23','Sin detalle','Activa');

COMMIT;

