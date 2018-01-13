-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 13-Jan-2018 às 23:01
-- Versão do servidor: 5.7.19-log
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `forum`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `comentario`
--

DROP TABLE IF EXISTS `comentario`;
CREATE TABLE IF NOT EXISTS `comentario` (
  `idcomentario` int(11) NOT NULL AUTO_INCREMENT,
  `codusuario` int(11) NOT NULL,
  `cod_post` int(11) NOT NULL,
  `comentario` varchar(200) NOT NULL,
  PRIMARY KEY (`idcomentario`),
  KEY `cod_usuario_idx` (`codusuario`),
  KEY `cod_postagem_idx` (`cod_post`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `postagem`
--

DROP TABLE IF EXISTS `postagem`;
CREATE TABLE IF NOT EXISTS `postagem` (
  `idpostagem` int(11) NOT NULL AUTO_INCREMENT,
  `cod_usuario` int(11) NOT NULL,
  `cod_tema` int(11) NOT NULL,
  `postagem` varchar(200) NOT NULL,
  `cod_comentario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpostagem`),
  KEY `cod_usuario_idx` (`cod_usuario`),
  KEY `cod_tema_idx` (`cod_tema`),
  KEY `cod_comentario_idx` (`cod_comentario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sequence`
--

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '0');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tema`
--

DROP TABLE IF EXISTS `tema`;
CREATE TABLE IF NOT EXISTS `tema` (
  `idtema` int(11) NOT NULL AUTO_INCREMENT,
  `tema` varchar(50) NOT NULL,
  PRIMARY KEY (`idtema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome_usuario` varchar(50) NOT NULL,
  `cod_postagem` int(11) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `senha` varchar(8) DEFAULT NULL,
  `cod_coment` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `cod_postagem_idx` (`cod_postagem`),
  KEY `cod_coment_idx` (`cod_coment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `cod_post` FOREIGN KEY (`cod_post`) REFERENCES `postagem` (`idpostagem`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `cod_user` FOREIGN KEY (`codusuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `postagem`
--
ALTER TABLE `postagem`
  ADD CONSTRAINT `cod_comentario` FOREIGN KEY (`cod_comentario`) REFERENCES `comentario` (`idcomentario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `cod_tema` FOREIGN KEY (`cod_tema`) REFERENCES `tema` (`idtema`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `cod_usuario` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `cod_coment` FOREIGN KEY (`cod_coment`) REFERENCES `comentario` (`idcomentario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `cod_postagem` FOREIGN KEY (`cod_postagem`) REFERENCES `postagem` (`idpostagem`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
