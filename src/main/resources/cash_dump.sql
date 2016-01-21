-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 20 Janvier 2016 à 10:21
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `cashcalendar`
--

-- --------------------------------------------------------

--
-- Structure de la table `calendrier`
--

CREATE TABLE IF NOT EXISTS `calendrier` (
  `idCalendrier` int(11) NOT NULL AUTO_INCREMENT,
  `jour` date DEFAULT NULL,
  `disponible` tinyint(1) DEFAULT NULL,
  `idUtilisateur` int(11) DEFAULT NULL,
  `idProduit` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCalendrier`),
  KEY `FK_calendrier_idUtilisateur` (`idUtilisateur`),
  KEY `FK_calendrier_idProduit` (`idProduit`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Contenu de la table `calendrier`
--

INSERT INTO `calendrier` (`idCalendrier`, `jour`, `disponible`, `idUtilisateur`, `idProduit`) VALUES
(1, '2016-01-01', 1, NULL, NULL),
(2, '2016-01-02', 1, NULL, NULL),
(3, '2016-01-03', 1, NULL, NULL),
(4, '2016-01-04', 1, NULL, NULL),
(5, '2016-01-05', 1, NULL, NULL),
(6, '2016-01-06', 1, NULL, NULL),
(7, '2016-01-07', 1, NULL, NULL),
(8, '2016-01-08', 1, NULL, NULL),
(9, '2016-01-09', 1, NULL, NULL),
(10, '2016-01-10', 1, NULL, NULL),
(11, '2016-01-11', 1, NULL, NULL),
(12, '2016-01-12', 1, NULL, NULL),
(13, '2016-01-13', 1, NULL, NULL),
(14, '2016-01-14', 1, NULL, NULL),
(15, '2016-01-15', 1, NULL, NULL),
(16, '2016-01-16', 1, NULL, NULL),
(17, '2016-01-17', 1, NULL, NULL),
(18, '2016-01-18', 1, NULL, NULL),
(19, '2016-01-19', 1, NULL, NULL),
(20, '2016-01-20', 1, NULL, NULL),
(21, '2016-01-21', 1, NULL, NULL),
(22, '2016-01-22', 1, NULL, NULL),
(23, '2016-01-23', 1, NULL, NULL),
(24, '2016-01-24', 1, NULL, NULL),
(25, '2016-01-25', 1, NULL, NULL),
(26, '2016-01-26', 1, NULL, NULL),
(27, '2016-01-27', 1, NULL, NULL),
(28, '2016-01-28', 1, NULL, NULL),
(29, '2016-01-29', 1, NULL, NULL),
(30, '2016-01-30', 1, NULL, NULL),
(31, '2016-01-31', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `idProduit` int(11) NOT NULL AUTO_INCREMENT,
  `nomProduit` varchar(10) DEFAULT NULL,
  `descriptionProduit` varchar(100) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  PRIMARY KEY (`idProduit`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(50) DEFAULT NULL,
  `motDePasse` varchar(32) DEFAULT NULL,
  `solde` double DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `calendrier`
--
ALTER TABLE `calendrier`
  ADD CONSTRAINT `FK_calendrier_idProduit` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`),
  ADD CONSTRAINT `FK_calendrier_idUtilisateur` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
