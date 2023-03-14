DROP SCHEMA IF EXISTS ehpad;
CREATE SCHEMA IF NOT EXISTS ehpad DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE ehpad;

CREATE TABLE medicament(
   id_medicament INT AUTO_INCREMENT,
   nom VARCHAR(70)  NOT NULL,
   fonction VARCHAR(50)  NOT NULL,
   stock INT NOT NULL,
   PRIMARY KEY(id_medicament)
)ENGINE INNODB;

CREATE TABLE fonction(
   id_fonction INT AUTO_INCREMENT,
   nom VARCHAR(80)  NOT NULL,
   PRIMARY KEY(id_fonction)
)ENGINE INNODB;

CREATE TABLE plat(
   id_plat INT AUTO_INCREMENT,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_plat)
)ENGINE INNODB;

CREATE TABLE allergene(
   id_allergene INT AUTO_INCREMENT,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_allergene)
)ENGINE INNODB;

CREATE TABLE personne(
   id_personne INT AUTO_INCREMENT,
   nom VARCHAR(50)  NOT NULL,
   prenom VARCHAR(50)  NOT NULL,
   telephone VARCHAR(15)  NOT NULL,
   email VARCHAR(70)  NOT NULL,
   pwd VARCHAR(64)  NOT NULL,
   date_naissance DATE,
   date_arrivee DATE,
   date_depart DATE,
   PRIMARY KEY(id_personne),
   UNIQUE(telephone),
   UNIQUE(email)
)ENGINE INNODB;

CREATE TABLE statut_candidature(
   id_statut_candidature INT AUTO_INCREMENT,
   PRIMARY KEY(id_statut_candidature)
)ENGINE INNODB;

CREATE TABLE etage(
   id_etage INT AUTO_INCREMENT,
   etage_securise BOOLEAN NOT NULL,
   PRIMARY KEY(id_etage)
)ENGINE INNODB;

CREATE TABLE chambre(
   id_chambre INT AUTO_INCREMENT,
   no_chambre SMALLINT,
   chambre_double BOOLEAN NOT NULL,
   id_etage INT NOT NULL,
   PRIMARY KEY(id_chambre),
   FOREIGN KEY(id_etage) REFERENCES etage(id_etage)
)ENGINE INNODB;

CREATE TABLE employe(
   id_personne INT,
   no_secu VARCHAR(15)  NOT NULL,
   id_fonction INT NOT NULL,
   PRIMARY KEY(id_personne),
   UNIQUE(no_secu),
   FOREIGN KEY(id_personne) REFERENCES personne(id_personne),
   FOREIGN KEY(id_fonction) REFERENCES fonction(id_fonction)
)ENGINE INNODB;

CREATE TABLE infirmiere(
   no_rpps VARCHAR(11) ,
   id_personne INT NOT NULL,
   PRIMARY KEY(no_rpps),
   FOREIGN KEY(id_personne) REFERENCES employe(id_personne)
)ENGINE INNODB;

CREATE TABLE repas(
   id_repas INT AUTO_INCREMENT,
   rang INT NOT NULL,
   jour DATE NOT NULL,
   PRIMARY KEY(id_repas)
)ENGINE INNODB;

CREATE TABLE medecin(
   no_rpps VARCHAR(11) ,
   id_personne INT NOT NULL,
   PRIMARY KEY(no_rpps),
   UNIQUE(id_personne),
   FOREIGN KEY(id_personne) REFERENCES personne(id_personne)
)ENGINE INNODB;

CREATE TABLE pensionnaire(
   id_personne INT,
   no_secu VARCHAR(15)  NOT NULL,
   id_chambre INT NOT NULL,
   PRIMARY KEY(id_personne),
   UNIQUE(no_secu),
   FOREIGN KEY(id_personne) REFERENCES personne(id_personne),
   FOREIGN KEY(id_chambre) REFERENCES chambre(id_chambre)
)ENGINE INNODB;

CREATE TABLE ordonnance(
   no_rpps VARCHAR(11) ,
   id_personne INT,
   jour DATE,
   PRIMARY KEY(no_rpps, id_personne, jour),
   FOREIGN KEY(no_rpps) REFERENCES medecin(no_rpps),
   FOREIGN KEY(id_personne) REFERENCES pensionnaire(id_personne)
)ENGINE INNODB;

CREATE TABLE demande_une_admission(
   id_personne INT,
   jour DATE,
   commentaire TEXT,
   id_statut_candidature INT NOT NULL,
   PRIMARY KEY(id_personne, jour),
   FOREIGN KEY(id_personne) REFERENCES personne(id_personne),
   FOREIGN KEY(id_statut_candidature) REFERENCES statut_candidature(id_statut_candidature)
)ENGINE INNODB;

CREATE TABLE administre_medicament(
   id_personne INT,
   id_medicament INT,
   heure DATETIME,
   no_rpps VARCHAR(11)  NOT NULL,
   PRIMARY KEY(id_personne, id_medicament, heure),
   FOREIGN KEY(id_personne) REFERENCES pensionnaire(id_personne),
   FOREIGN KEY(id_medicament) REFERENCES medicament(id_medicament),
   FOREIGN KEY(no_rpps) REFERENCES infirmiere(no_rpps)
)ENGINE INNODB;

CREATE TABLE repas_plat(
   id_repas INT,
   id_plat INT,
   rang TINYINT NOT NULL,
   PRIMARY KEY(id_repas, id_plat),
   FOREIGN KEY(id_repas) REFERENCES repas(id_repas),
   FOREIGN KEY(id_plat) REFERENCES plat(id_plat)
)ENGINE INNODB;

CREATE TABLE allergene_plat(
   id_plat INT,
   id_allergene INT,
   PRIMARY KEY(id_plat, id_allergene),
   FOREIGN KEY(id_plat) REFERENCES plat(id_plat),
   FOREIGN KEY(id_allergene) REFERENCES allergene(id_allergene)
)ENGINE INNODB;

CREATE TABLE service_repas(
   id_personne INT,
   id_repas INT,
   PRIMARY KEY(id_personne, id_repas),
   FOREIGN KEY(id_personne) REFERENCES pensionnaire(id_personne),
   FOREIGN KEY(id_repas) REFERENCES repas(id_repas)
)ENGINE INNODB;

CREATE TABLE sensibilite_allergene(
   id_personne INT,
   id_allergene INT,
   en_cas_de_crise TEXT NOT NULL,
   PRIMARY KEY(id_personne, id_allergene),
   FOREIGN KEY(id_personne) REFERENCES pensionnaire(id_personne),
   FOREIGN KEY(id_allergene) REFERENCES allergene(id_allergene)
)ENGINE INNODB;

CREATE TABLE prescription(
   id_medicament INT,
   no_rpps VARCHAR(11) ,
   id_personne INT,
   jour DATE,
   posologie VARCHAR(50)  NOT NULL,
   date_debut_traitement DATE NOT NULL,
   date_fin_traitement DATE NOT NULL,
   PRIMARY KEY(id_medicament, no_rpps, id_personne, jour),
   FOREIGN KEY(id_medicament) REFERENCES medicament(id_medicament),
   FOREIGN KEY(no_rpps, id_personne, jour) REFERENCES ordonnance(no_rpps, id_personne, jour)
)ENGINE INNODB;



-- DEBUT DES VALEURS DE TEST
INSERT INTO personne(id_personne, nom, prenom, date_naissance, date_arrivee, email, telephone, pwd) VALUES 
    (1, 'HADDOCK', 'Archibald', '1920-06-01', CURDATE(), 'archibald.haddock@lemans.fr', '+33659847264', 'password'),
	(2, 'ISTIS', 'Larme', '1918-11-11', CURDATE(), 'larme.istis@pastis.fr', '+33688888888', 'password'),
    (3, 'TERIEUR', 'Alex', '1932-12-24', CURDATE(), 'papa.noel@pole-nord.com', '+3966598482530', 'password'),
    (4, 'TERIEUR', 'Alain', '1938-02-14', CURDATE(), 'cupidon@love.com', '+33662984715', 'password'),
    (5, 'CURRY', 'Marie', '1960-09-21', CURDATE(), 'marieeaucurry@infirmiere.fr', '+33666666666', 'password'),
    (6, 'BRETON', 'Anna', '1991-11-11', CURDATE(), 'bretagnelibre@insoumis.fr', '+33699999999', 'password'),
    (7, 'BEBOU', 'Amelie', '1975-05-05', CURDATE(), 'bebou@docteur.fr', '+33611111111', 'password');

		INSERT INTO etage(etage_securise)
		VALUES (false),
		(true),
		(false),
		(false);

		INSERT INTO chambre(no_chambre, chambre_double, id_etage)
		VALUES (201, false, 2),
		(202, false, 2),
		(203, false, 2),
		(204, false, 2),
		(301, false, 3),
		(302, true, 3),
		(303, true, 3),
		(304, false, 3),
		(401, false, 4),
		(402, true, 4),
		(403, true, 4),
		(404, false, 4);

		INSERT INTO pensionnaire(no_secu, id_chambre, id_personne)
		VALUES (120069135923302, 1, 1),
		(118119135923555, 2, 2),
		(132069135923666, 4, 3),
		(138069135923777, 3, 4);

		INSERT INTO fonction(nom)
		VALUES ('infirmiere'),
		('cuisinier'),
		("personnel_administratif"),
		('comptable');

		INSERT INTO employe(no_secu, id_fonction, id_personne)
		VALUES (160099135923888, 1, 5);

		INSERT INTO infirmiere(no_rpps, id_personne)
		VALUES (12345678901, 5);

		INSERT INTO medicament(nom, fonction, stock)
		VALUES ("Doliprane", 'antalgique', 50),
		("Aspegic", 'antalgique', 80),
		("céphalexine", 'antibiotique', 5);

		INSERT INTO medecin(no_rpps, id_personne)
		VALUES (98765432109, 7);

		INSERT INTO ordonnance(id_personne, no_rpps, jour)
		VALUES (1, 98765432109, CURDATE());

		INSERT INTO prescription(id_medicament, id_personne, no_rpps, jour, posologie, date_debut_traitement, date_fin_traitement)
		VALUES (1, 1, 98765432109, CURDATE(), "- Paracétamol 1000mg : 3 comprimés par jour", curdate(), '2023-07-14');

		INSERT INTO allergene(nom)
		VALUES ("gluten"),
		("soja"),
		("arachide"),
		("lait"),
		("moutarde"),("céleri");

		INSERT INTO plat(nom)
		VALUES ("steak-frites"),
		("moules-frites"),
		("tomates farcies"),
		("lasagnes"),
		("couscous"),
		("purée cabillaud"),
		("pâtes carbonara"),
		("gratin de brocolis"),
		("salade de pâtes"),
		("chèvre chaud"),
		("gaspacho"),
		("tomate mozzarella"),
		("velouté de légumes"),
		("mousse au chocolat"),
		("salade de fruits"),
		("tarte aux poires"),
		("tiramisu"),
		("fromage blanc"),
		("crumble aux pommes");

		INSERT INTO repas(rang, jour)
		VALUES (1, '2022-12-26'),
		(2, '2022-12-26'),
		(3, '2022-12-26'),
		(1, '2022-12-27'),
		(2, '2022-12-27'),
		(3, '2022-12-27'),
		(1, '2022-12-28'),
		(2, '2022-12-28'),
		(3, '2022-12-28'),
		(1, '2022-12-29'),
		(2, '2022-12-29'),
		(3, '2022-12-29'),
		(1, '2022-12-30'),
		(2, '2022-12-30'),
		(3, '2022-12-30'),
		(1, '2022-12-31'),
		(2, '2022-12-31'),
		(3, '2022-12-31'),
		(1, '2023-01-01'),
		(2, '2023-01-01'),
		(3, '2023-01-01');