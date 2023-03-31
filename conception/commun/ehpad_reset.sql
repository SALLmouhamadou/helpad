DELIMITER $$

DROP PROCEDURE IF EXISTS reset_data;
CREATE DEFINER=`ehpad_user`@`localhost` PROCEDURE reset_data()
BEGIN
	CALL truncate_all_tables();
	-- Réasactiver contraintes de clé étrangère
	SET FOREIGN_KEY_CHECKS = 1;

	BEGIN
	-- Recuperation en cas d'exception
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		-- Annuler la transaction
		ROLLBACK;
		-- Afficher la cause de l'échec
		SHOW ERRORS;
	END;  
	START TRANSACTION;
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
    COMMIT;
  END;
END$$