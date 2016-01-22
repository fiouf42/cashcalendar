-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 22 Janvier 2016 à 12:24
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `web-calendar`
--

-- --------------------------------------------------------

--
-- Structure de la table `acheter`
--

CREATE TABLE IF NOT EXISTS `acheter` (
  `IDCOMMANDE` int(11) NOT NULL,
  `IDUTILISATEUR` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDCOMMANDE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `idCommande` int(11) NOT NULL AUTO_INCREMENT,
  `numeroCommande` int(11) DEFAULT NULL,
  `dateCommande` date DEFAULT NULL,
  `idJour` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCommande`),
  KEY `FK_commande_idJour` (`idJour`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `jour`
--

CREATE TABLE IF NOT EXISTS `jour` (
  `idJour` int(11) NOT NULL AUTO_INCREMENT,
  `jourDisponible` tinyint(1) DEFAULT NULL,
  `jourReserve` date DEFAULT NULL,
  `idCommande` int(11) DEFAULT NULL,
  `nomProduit` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idJour`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=681176 ;

--
-- Contenu de la table `jour`
--

INSERT INTO `jour` (`idJour`, `jourDisponible`, `jourReserve`, `idCommande`, `nomProduit`) VALUES
(236594, 0, '2016-01-16', 0, 'je suis un tres beau tricheur'),
(681175, 0, '2016-01-20', 0, 'gqhzg');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `nomProduit` varchar(25) NOT NULL,
  `dicton` text NOT NULL,
  `description` varchar(25) DEFAULT NULL,
  `idJour` int(11) DEFAULT NULL,
  `photo` longblob,
  PRIMARY KEY (`nomProduit`),
  KEY `FK_produit_idJour` (`idJour`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUtilisateur` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `solde` double DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `nom`, `login`, `password`, `email`, `solde`) VALUES
(58954, 'jj', 'jj', 'jj', 'jj', 14),
(70333, 'yassine', 'yassine', 'hklhlk', 'lbljhlj', 16),
(435364, 'riad', 'riad', '1234', 'riad@hotmail.fr', 2),
(911924, 'yassine', 'yassin', 'yassine', 'ljgljg', 14);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_commande_idJour` FOREIGN KEY (`idJour`) REFERENCES `jour` (`idJour`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_produit_idJour` FOREIGN KEY (`idJour`) REFERENCES `jour` (`idJour`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
