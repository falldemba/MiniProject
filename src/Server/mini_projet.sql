-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mer 06 Mars 2019 à 07:04
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mini_projet`
--

-- --------------------------------------------------------

--
-- Structure de la table `consult`
--

CREATE TABLE `consult` (
  `Numero` int(11) NOT NULL,
  `ID_Medecin` int(11) NOT NULL,
  `ID_Patient` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `consult`
--

INSERT INTO `consult` (`Numero`, `ID_Medecin`, `ID_Patient`, `date`) VALUES
(23, 1, 32, '2019-03-06'),
(22, 7, 30, '2019-03-06'),
(21, 8, 33, '2019-03-06');

-- --------------------------------------------------------

--
-- Structure de la table `consultation`
--

CREATE TABLE `consultation` (
  `Numero` int(11) NOT NULL,
  `IDMedecin` int(11) NOT NULL,
  `IDPatient` int(11) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE `medecin` (
  `Matricule` int(11) NOT NULL,
  `Nom` varchar(45) DEFAULT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `medecin`
--

INSERT INTO `medecin` (`Matricule`, `Nom`, `date`) VALUES
(1, 'Dr Mamadou Touré', '2019-02-15'),
(2, 'Dr Cheikh Mbacké Gueye', '2019-02-15'),
(3, 'Dr Ibrahima Sokhna', '2019-02-15'),
(4, 'Dr Soda Thiam', '2019-02-15'),
(5, 'Dr Ndaye Awa Keita', '2019-02-15'),
(6, 'Dr Matar Thiaw', '2019-02-15'),
(7, 'Dr Anta Traore', '2019-02-15'),
(8, 'Dr Marie Jeanne Gomis', '2019-02-15');

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

CREATE TABLE `medicament` (
  `Code` varchar(6) NOT NULL,
  `Libelle` varchar(45) DEFAULT NULL,
  `Indications` varchar(45) DEFAULT NULL,
  `Posologie` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `medicament`
--

INSERT INTO `medicament` (`Code`, `Libelle`, `Indications`, `Posologie`) VALUES
('AD45', 'ACT', 'rf', 'Voie Orale'),
('AR77', 'Paracetamol', 'rf ', 'Voie orale'),
('AZ14', 'BILOR', 'Voie orale', 'Voire la notice'),
('NH85', 'Paluject', 'rf', 'Picqure');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `Num_ss` int(11) NOT NULL,
  `Nom` varchar(45) DEFAULT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `patient`
--

INSERT INTO `patient` (`Num_ss`, `Nom`, `date`) VALUES
(27, 'Demba Fall', '2019-02-15'),
(28, 'Matar Diop', '2019-03-05'),
(29, 'Anta Sarr', '2019-03-05'),
(30, 'Awa Diago', '2019-03-05'),
(31, 'Soda Ndiaye', '2019-03-05'),
(32, 'Rosine Mendy', '2019-03-05'),
(33, 'Pascal Fall', '2019-03-05');

-- --------------------------------------------------------

--
-- Structure de la table `prescription`
--

CREATE TABLE `prescription` (
  `id` int(11) NOT NULL,
  `id_consultation` int(11) NOT NULL,
  `id_medicament` varchar(6) NOT NULL,
  `quantite` int(11) NOT NULL,
  `jours` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `prescription`
--

INSERT INTO `prescription` (`id`, `id_consultation`, `id_medicament`, `quantite`, `jours`) VALUES
(1, 22, 'AD45', 10, 5),
(2, 22, 'AR77', 3, 3),
(3, 22, 'NH85', 6, 2),
(4, 23, 'NH85', 8, 4),
(5, 23, 'AD45', 10, 8),
(6, 23, 'AR77', 3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `Numero` int(11) NOT NULL,
  `Login` varchar(45) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Role` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`Numero`, `Login`, `Password`, `Role`) VALUES
(1, 'admin@admin.com', 'admin', 0),
(2, 'java@admin.com', 'java', 0);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `consult`
--
ALTER TABLE `consult`
  ADD PRIMARY KEY (`Numero`),
  ADD KEY `ID_Medecin` (`ID_Medecin`),
  ADD KEY `ID_Patient` (`ID_Patient`);

--
-- Index pour la table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`Numero`,`IDMedecin`,`IDPatient`),
  ADD KEY `fk_Consultation_Consulte1_idx` (`IDMedecin`,`IDPatient`);

--
-- Index pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD PRIMARY KEY (`Matricule`);

--
-- Index pour la table `medicament`
--
ALTER TABLE `medicament`
  ADD PRIMARY KEY (`Code`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`Num_ss`);

--
-- Index pour la table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_consultation` (`id_consultation`),
  ADD KEY `id_medicament` (`id_medicament`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Numero`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `consult`
--
ALTER TABLE `consult`
  MODIFY `Numero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT pour la table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `Numero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `medecin`
--
ALTER TABLE `medecin`
  MODIFY `Matricule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `Num_ss` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT pour la table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `Numero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `fk_Consultation_Consulte1` FOREIGN KEY (`IDMedecin`,`IDPatient`) REFERENCES `consulte` (`Medecin_Matricule`, `Patient_Num_ss`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
