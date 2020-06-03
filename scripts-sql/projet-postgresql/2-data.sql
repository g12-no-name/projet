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

INSERT INTO compte (id, pseudo, motdepasse, mail ) VALUES 
  (1, 'Organisateur', 'Organisateur', 'geek@3il.fr' ),
  (2, 'Reste', 'Reste', 'chef@3il.fr' );

ALTER TABLE compte ALTER COLUMN id RESTART WITH 4;





-- Personne

-- Benevole

INSERT INTO benevole (id, nom, prenom, numTel,heureD, heureF) VALUES 
  ( 4, 'RandomFriendly', 'number 1254', '0505050505','15:05','16:00' ),
  ( 5, 'RandomGuy', 'number 7826', '0606060606',null,null ),
  ( 6, 'RandomCat', 'number 9135', '0909090909',null,null );

ALTER TABLE personne ALTER COLUMN id RESTART WITH 6;


INSERT INTO participant (id, nom, prenom, numTel) VALUES 
  ( 1, 'RandomDude', 'number 1254', '0505050505' ),
  ( 2, 'RandomGuy', 'number 7826', '0606060606' ),
  ( 3, 'RandomCat', 'number 9135', '0909090909' );

ALTER TABLE personne ALTER COLUMN id RESTART WITH 6;

--categorieCourse 

INSERT INTO categorieCourse (id, nom) VALUES
  (1, 'mixte'),
  (2, 'femmes'),
  (3, 'hommes'),
  (4, 'V.A.E'),
  (5, 'chats');

ALTER TABLE categorieCourse ALTER COLUMN id RESTART WITH 5;

--typeCourse

INSERT INTO typeCourse (id, nom) VALUES
  (1, 'piti bol d air'),
  (2, 'big fat bol d air'),
  (3, 'juste ici pour la bouffe');

ALTER TABLE typeCourse ALTER COLUMN id RESTART WITH 4;

--typePoste

INSERT INTO typePoste (id, nom) VALUES
  (1, 'bouffe'),
  (2, 'random'),
  (3, 'game');

ALTER TABLE typePoste ALTER COLUMN id RESTART WITH 4;

--Poste

INSERT INTO poste (id, nom, typePoste, heureD, heureF) VALUES
  (1, 'poste 1', 1, '15:05','15:15' ),
  (2, 'poste 4', 3, '08:05','10:15'),
  (3, 'I do', 2, '14:05','16:15'),
  (4, 'not know', 2, '12:05','15:15'),
  (5, 'what to do', 1, '15:05','15:15'),
  (6, 'anymore', 2, '15:05','15:15');

ALTER TABLE poste ALTER COLUMN id RESTART WITH 7;








