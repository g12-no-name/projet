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




-- Benevole

INSERT INTO benevole (id, nom, prenom, numTel,heureD, heureF) VALUES 
  ( 4, 'RandomFriendly', 'number 1254', '0505050505','15:05','16:00' ),
  ( 5, 'RandomGuy', 'number 7826', '0606060606',null,null ),
  ( 6, 'RandomCat', 'number 9135', '0909090909',null,null ),
  ( 7, 'arumasha', 'number 1323', '0404040404','12:05','16:00' ),
  ( 8, 'shinobi', 'number 1365', '0707070707','12:05','16:00' ),
  ( 9, 'jiangun', 'number 3156', '0808080808','12:05','16:00' ),
  ( 10, 'tiandi', 'number 2454', '1010101010','15:05','16:00' ),
  ( 11, 'nuxia', 'number 4563', '1111111111','15:05','16:00' ),
  ( 12, 'shaloin', 'number 1345', '1212121212',null,null ),
  ( 13, 'sekiro', 'number 3456', '1313131313',null,null ),
  ( 14, 'dark', 'number 4324', '1414141414',null,null ),
  ( 15, 'soul', 'number 7485', '1515151515','12:05','16:00' ),
  ( 16, 'gwen', 'number 6537', '1616161616','12:05','16:00' ),
  ( 17, 'sansnom', 'number 2456', '1717171717','12:05','16:00' ),
  ( 18, 'gwendolin', 'number 2356', '1818181818','15:05','16:00' ),
  ( 19, 'londor', 'number 6534', '1919191919','15:05','16:00' ),
  ( 20, 'drangleic', 'number 3675', '2020202020','15:05','16:00' ),
  ( 21, 'andre', 'number 7653', '2121212121',null,null ),
  ( 22, 'siegward', 'number 2467', '2222222222',null,null ),
  ( 23, 'pat', 'number 7424', '2323232323',null,null ),
  ( 24, 'yoel', 'number 2456', '2424242424','15:05','16:00' );

ALTER TABLE personne ALTER COLUMN id RESTART WITH 6;

INSERT INTO equipe (id, nom,nbBouffe,typeCourse,catCourse) VALUES
  (1, 'RandomGuy_RandomCat',2,1,1),
  (2, 'RandomDude_RandomTetromino', 5,1,1);

ALTER TABLE typePoste ALTER COLUMN id RESTART WITH 3;


INSERT INTO participant (id, nom, prenom, numTel,equipe) VALUES 
  ( 1, 'RandomDude', 'number 1254', '0505050505',2),
  ( 2, 'RandomGuy', 'number 7826', '0606060606' ,1),
  ( 3, 'RandomCat', 'number 9135', '0909090909' ,1);

ALTER TABLE personne ALTER COLUMN id RESTART WITH 6;



--typePoste

INSERT INTO typePoste (id, nom) VALUES
  (1, 'bouffe'),
  (2, 'random'),
  (3, 'game'),
  (4,'honor'),
  (5,'for');

ALTER TABLE typePoste ALTER COLUMN id RESTART WITH 4;

--Poste

INSERT INTO poste (id, nom, typePoste, heureD, heureF) VALUES
  (1, 'poste 1', 1, '15:05','15:15' ),
  (2, 'poste 4', 3, '08:05','10:15'),
  (3, 'I do', 2, '14:05','16:15'),
  (4, 'not know', 2, '12:05','15:15'),
  (5, 'what to do', 1, '15:05','15:15'),
  (6, 'anymore', 2, '15:05','15:15'),
  (7, 'velo', 2, '12:05','15:15'),
  (8, 'gourdier', 1, '12:05','16:00'),
  (9, 'gardien', 5, '15:05','16:15'),
  (10, 'emissaire', 4, '12:05','15:15'),
  (11, 'spadassin', 4, '15:05','16:15'),
  (12, 'sentinelle',4, '12:05','15:15'),
  (13, 'fleau', 4, '12:05','15:15'),
  (14, 'victimaire', 5, '12:05','16:15'),
  (15, 'hersir', 5, '12:05','15:15'),
  (16, 'berserker', 5, '12:05','16:15'),
  (17, 'valkyrie', 3, '15:05','16:15'),
  (18, 'jarl', 3, '15:05','16:15'),
  (19, 'chamane', 3, '12:05','16:15'),
  (20, 'kensei', 2, '12:05','16:15'),
  (21, 'orochi', 2, '15:05','16:15'),
  (22, 'shugoki', 1, '15:05','15:15');

ALTER TABLE poste ALTER COLUMN id RESTART WITH 7;


-------asignation

INSERT INTO assignation (id, idBenevole,idPoste, heureD, heureF) VALUES
  (1,4,1,'15:05','15:15'),
  (2,5,2,'15:05','15:15'),
  (3,6,3,'15:05','15:15'),
  (4,7,4,'12:05','15:15'),
  (5,8,5,'12:05','15:15'),
  (6,9,6,'12:05','15:15'),
  (7,10,7,'12:05','16:15'),
  (8,11,8,'12:05','16:15'),
  (9,12,9,'12:05','16:15'),
  (10,13,10,'15:05','15:15'),
  (11,14,11,'15:05','15:15'),
  (12,15,12,'15:05','15:15'),
  (13,16,13,'12:05','15:15'),
  (14,17,14,'12:05','15:15'),
  (15,18,15,'12:05','15:15'),
  (16,19,16,'12:05','16:15'),
  (17,20,17,'12:05','16:15'),
  (18,21,18,'15:05','15:15'),
  (19,22,19,'15:05','15:15'),
  (20,23,20,'15:05','15:15'),
  (21,24,21,'12:05','15:15');
  
ALTER TABLE assignation ALTER COLUMN id RESTART WITH 4;









