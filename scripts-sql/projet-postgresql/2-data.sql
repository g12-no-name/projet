SET search_path TO projet;


-- Supprimer toutes les données
DELETE FROM compte;
DELETE FROM benevole;
DELETE FROM participant;
DELETE FROM equipe;
DELETE FROM poste;
DELETE FROM typePoste;
DELETE FROM typeCourse;
DELETE FROM categorieCourse;
DELETE FROM personne;


-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES 
  (1, 'Organisateur', 'Organisateur', 'geek@3il.fr' ),
  (2, 'Reste', 'Reste', 'chef@3il.fr' ),

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;



-- Personne

INSERT INTO participant (id, nom, prenom, numTel) VALUES 
  ( 1, 'RandomDude', 'number 1254', '0505050505' ),
  ( 2, 'RandomGuy', 'number 7826', '0606060606' ),
  ( 3, 'RandomCat', 'number 9135', '0909090909' );

ALTER TABLE personne ALTER COLUMN id RESTART WITH 4;

--categorieCourse 

INSERT INTO categorieCourse (id, nom) VALUES
  (1, 'mixte'),
  (2, 'femmes'),
  (3, 'hommes'),
  (4, 'chats'),
  (5, 'autres');

ALTER TABLE categorieCourse ALTER COLUMN id RESTART WITH 5;

--typeCourse

INSERT INTO typeCourse (id, nom) VALUES
  (1, 'piti bol d'air'),
  (2, 'big fat bol d'air');
  (3, 'juste ici pour la bouffe')

ALTER TABLE typeCourse ALTER COLUMN id RESTART WITH 4;

-- Benevole

INSERT INTO benevole (id, nom, prenom, numTel) VALUES 
  ( 1, 'RandomFriendly', 'number 1254', '0505050505' ),
  ( 2, 'RandomGuy', 'number 7826', '0606060606' ),
  ( 3, 'RandomCat', 'number 9135', '0909090909' );

ALTER TABLE benevole ALTER COLUMN id RESTART WITH 4;











