-- 1. Medecin (pas de FK)
INSERT INTO Medecin(idmedecin, nom, prenom, specialite, rpps, motdepasse, mail) VALUES
(1,'Rousseau','Martin','Medecin generaliste','10123456789','MedPass123','kendrick.lamar@gmail.com'),
(2,'Dupont','Marie','Cardiologue','10234567890','MedPass123','marie.dupont@gmail.com');

-- 2. Patient (pas de FK)
INSERT INTO Patient(idpatient, prenom, nom, datenaiss, nss, motdepasse, adresse, maladie, tel, mail, genre, pays, image) VALUES
(1,'Jean','Dupont','1985-03-15','185037512345678','PatPass123','12 rue de la Paix, Paris','Arythmie cardiaque','0612345678','jean.dupont@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(2,'Sophie','Martin','1990-07-22','290077523456789','PatPass123','5 avenue des Fleurs, Lyon','Entorse','0623456789','sophie.martin@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(3,'Pierre','Bernard','1978-11-30','178117534567890','PatPass123','8 boulevard Victor Hugo, Marseille','Migraines','0634567890','pierre.bernard@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(4,'Luc','Moreau','1992-05-20','192057556789012','PatPass123','15 rue de la Republique, Toulouse','Diabete de type 2','0645678901','luc.moreau@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(5,'Claire','Girard','1988-09-10','188097634512345','PatPass123','20 boulevard du Theatre, Bordeaux','Hypertension','0656789012','claire.girard@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(6,'Thomas','Leclerc','1995-12-03','195127745623456','PatPass123','7 avenue Montaigne, Nice','Asthme','0667890123','thomas.leclerc@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop');

-- 3. Transport
INSERT INTO Transport(idtransport, datetransport, lieudepart, lieuarrive, typetransport, idpatient, statut) VALUES
(1,'2026-01-10 08:00:00','12 rue de la Paix, Paris','Hopital Lariboisiere, Paris','Ambulance', 1, 'TERMINE'),
(2,'2026-03-15 09:30:00','12 rue de la Paix, Paris','Clinique du Parc, Paris','VSL', 1, 'TERMINE'),
(3,'2026-01-15 08:45:00','5 avenue des Fleurs, Lyon','Clinique du Parc, Lyon','VSL', 2, 'TERMINE'),
(4,'2026-01-20 10:00:00','8 boulevard Victor Hugo, Marseille','Hopital de la Timone, Marseille','Ambulance', 3, 'TERMINE'),
(5,'2026-02-01 14:30:00','12 rue de la Paix, Paris','Hopital Saint-Antoine, Paris','Ambulance', 1, 'TERMINE'),
(6,'2026-02-05 09:15:00','12 rue de la Paix, Paris','Hopital Bichat, Paris','VSL', 1, 'PLANIFIE'),
(7,'2026-03-30 14:15:00','12 rue de la Paix, Paris','Centre de Reeducation, Paris','Ambulance', 1, 'TERMINE'),
(8,'2026-04-20 15:00:00','12 rue de la Paix, Paris','Hopital Necker, Paris','VSL', 1, 'PLANIFIE'),
(9,'2026-02-10 10:30:00','15 rue de la Republique, Toulouse','Centre Hospitalier Universitaire, Toulouse','Ambulance', 4, 'TERMINE'),
(10,'2026-03-05 14:00:00','15 rue de la Republique, Toulouse','Clinique de l Indre, Toulouse','VSL', 4, 'TERMINE'),
(11,'2026-04-01 09:00:00','20 boulevard du Theatre, Bordeaux','Hopital Saint-Andre, Bordeaux','Ambulance', 5, 'TERMINE'),
(12,'2026-04-10 15:30:00','20 boulevard du Theatre, Bordeaux','Centre de Cardiologie, Bordeaux','VSL', 5, 'PLANIFIE'),
(13,'2026-02-15 11:00:00','7 avenue Montaigne, Nice','Hopital Archet, Nice','Ambulance', 6, 'TERMINE'),
(14,'2026-03-20 13:45:00','7 avenue Montaigne, Nice','Clinique du Val, Nice','VSL', 6, 'TERMINE'),
(15,'2026-04-05 08:30:00','7 avenue Montaigne, Nice','Centre Respiratoire, Nice','Ambulance', 6, 'PLANIFIE');

-- 4. Prescription (FK: idmedecin, idpatient)
INSERT INTO Prescription(idprescription, motifmedical, typetransport, dateprescription, dategeneration, idmedecin, idpatient) VALUES
(1,'Consultation cardiologie','Ambulance','2026-01-10','2026-01-09',1,1),
(2,'Seance de kinesitherapie','VSL','2026-01-15','2026-01-14',2,2),
(3,'Consultation neurologie','Ambulance','2026-01-20','2026-01-19',1,3),
(4,'Bilan diabete','Ambulance','2026-02-10','2026-02-09',1,4),
(5,'Suivi cardiaque','VSL','2026-03-05','2026-03-04',1,4),
(6,'Consultation cardiologie debutante','Ambulance','2026-04-01','2026-03-31',1,5),
(7,'Seance de revalidation','VSL','2026-04-10','2026-04-09',1,5),
(8,'Consultation pneumologie','Ambulance','2026-02-15','2026-02-14',1,6),
(9,'Bilan respiratoire','VSL','2026-03-20','2026-03-19',1,6),
(10,'Suivi asthme','Ambulance','2026-04-05','2026-04-04',1,6);

-- 5. Transporteur (FK: idtransport)
INSERT INTO Transporteur(idtransporteur, nom, prenom, tel, mail, idtransport) VALUES
(1,'Durand','Lucas','0611111111','lucas.durand@gmail.com',1),
(2,'Leroy','Maxime','0622222222','maxime.leroy@gmail.com',2),
(3,'Bonnet','Kevin','0633333333','kevin.bonnet@gmail.com',3),
(4,'Fontaine','Antoine','0644444444','antoine.fontaine@gmail.com',4),
(5,'Girard','Benjamin','0655555555','benjamin.girard@gmail.com',5),
(6,'Michel','Olivier','0666666666','olivier.michel@gmail.com',6),
(7,'Laurent','David','0677777777','david.laurent@gmail.com',7),
(8,'Martin','Christophe','0688888888','christophe.martin@gmail.com',8),
(9,'Richard','Francois','0699999999','francois.richard@gmail.com',9),
(10,'Deschamps','Philippe','0610101010','philippe.deschamps@gmail.com',10);

-- 6. Evaluation (FK: idpatient, idtransport)
INSERT INTO Evaluation(note, commentaire, idpatient, idtransport) VALUES
(4.5,'Tres bon service',1,1),
(4.0,'Bon transport',1,2),
(5.0,'Excellent service',2,3),
(4.8,'Tres professionnel',3,4),
(4.2,'Ponctuel et courtois',4,9),
(4.6,'Excellent confort',4,10),
(4.9,'Service impeccable',5,11),
(4.4,'Transport rapide',6,13),
(4.7,'Conducteur agreable',6,14);

-- 7. Etape - 4 etapes par transport DEPART, ARRIVE_HOPITAL, RDV_FINI, RETOUR_CHEZ_SOI
INSERT INTO Etape(idetape, statut, idtransport) VALUES
-- Transport 1: etapes 1-4
(1,'DEPART',1),
(2,'ARRIVE_HOPITAL',1),
(3,'RDV_FINI',1),
(4,'RETOUR_CHEZ_SOI',1),
-- Transport 2: etapes 5-8
(5,'DEPART',2),
(6,'ARRIVE_HOPITAL',2),
(7,'RDV_FINI',2),
(8,'RETOUR_CHEZ_SOI',2),
-- Transport 3: etapes 9-12
(9,'DEPART',3),
(10,'ARRIVE_HOPITAL',3),
(11,'RDV_FINI',3),
(12,'RETOUR_CHEZ_SOI',3),
-- Transport 4: etapes 13-16
(13,'DEPART',4),
(14,'ARRIVE_HOPITAL',4),
(15,'RDV_FINI',4),
(16,'RETOUR_CHEZ_SOI',4),
-- Transport 5: etapes 17-20
(17,'DEPART',5),
(18,'ARRIVE_HOPITAL',5),
(19,'RDV_FINI',5),
(20,'RETOUR_CHEZ_SOI',5),
-- Transport 6: etapes 21-24
(21,'DEPART',6),
(22,'ARRIVE_HOPITAL',6),
(23,'RDV_FINI',6),
(24,'RETOUR_CHEZ_SOI',6),
-- Transport 7: etapes 25-28
(25,'DEPART',7),
(26,'ARRIVE_HOPITAL',7),
(27,'RDV_FINI',7),
(28,'RETOUR_CHEZ_SOI',7),
-- Transport 8: etapes 29-32
(29,'DEPART',8),
(30,'ARRIVE_HOPITAL',8),
(31,'RDV_FINI',8),
(32,'RETOUR_CHEZ_SOI',8),
-- Transport 9: etapes 33-36
(33,'DEPART',9),
(34,'ARRIVE_HOPITAL',9),
(35,'RDV_FINI',9),
(36,'RETOUR_CHEZ_SOI',9),
-- Transport 10: etapes 37-40
(37,'DEPART',10),
(38,'ARRIVE_HOPITAL',10),
(39,'RDV_FINI',10),
(40,'RETOUR_CHEZ_SOI',10),
-- Transport 11: etapes 41-44
(41,'DEPART',11),
(42,'ARRIVE_HOPITAL',11),
(43,'RDV_FINI',11),
(44,'RETOUR_CHEZ_SOI',11),
-- Transport 12: etapes 45-48
(45,'DEPART',12),
(46,'ARRIVE_HOPITAL',12),
(47,'RDV_FINI',12),
(48,'RETOUR_CHEZ_SOI',12),
-- Transport 13: etapes 49-52
(49,'DEPART',13),
(50,'ARRIVE_HOPITAL',13),
(51,'RDV_FINI',13),
(52,'RETOUR_CHEZ_SOI',13),
-- Transport 14: etapes 53-56
(53,'DEPART',14),
(54,'ARRIVE_HOPITAL',14),
(55,'RDV_FINI',14),
(56,'RETOUR_CHEZ_SOI',14),
-- Transport 15: etapes 57-60
(57,'DEPART',15),
(58,'ARRIVE_HOPITAL',15),
(59,'RDV_FINI',15),
(60,'RETOUR_CHEZ_SOI',15);

-- 8. Note (FK: idtransporteur, idpatient)
INSERT INTO Note(idnote, nombreetoiles, idtransporteur, idpatient) VALUES
(1,5.0,1,1),
(2,4.5,2,2),
(3,4.0,3,3),
(4,4.8,4,4),
(5,4.3,5,4),
(6,4.9,6,5),
(7,4.6,7,5),
(8,4.7,8,6),
(9,4.5,9,6),
(10,4.4,10,4);

-- 9. Remboursement (FK: idtransport)
INSERT INTO Remboursement(idremboursement, montant, tauxprisencharge, statutremboursement, dateremboursement, idtransport, patient_id, description) VALUES
(1,120.50,0.80,'Rembourse','2026-01-15',1,1,'Transport 10/01'),
(2,85.00,0.65,'Rembourse','2026-01-20',2,1,'Transport 15/03'),
(3,200.00,0.90,'Rembourse','2026-01-25',3,2,'Transport 15/01'),
(4,150.75,0.75,'EN_ATTENTE','2026-02-05',5,1,'Transport 01/02 - En cours de remboursement'),
(5,95.50,0.70,'EN_ATTENTE','2026-02-10',6,1,'Transport 05/02 - En cours de remboursement'),
(6,180.00,0.85,'EN_ATTENTE','2026-04-05',7,1,'Transport 30/03 - En cours de remboursement'),
(7,110.25,0.78,'EN_ATTENTE','2026-04-25',8,1,'Transport 20/04 - En cours de remboursement'),
(8,165.50,0.80,'Rembourse','2026-02-15',9,4,'Transport 10/02 - Bilan diabete'),
(9,140.00,0.75,'Rembourse','2026-03-10',10,4,'Transport 05/03 - Suivi cardiaque'),
(10,155.75,0.82,'Rembourse','2026-04-05',11,5,'Transport 01/04 - Consultation cardiologie'),
(11,125.50,0.70,'EN_ATTENTE','2026-04-15',12,5,'Transport 10/04 - Seance revalidation'),
(12,175.00,0.85,'Rembourse','2026-02-20',13,6,'Transport 15/02 - Consultation pneumologie'),
(13,145.50,0.78,'Rembourse','2026-03-25',14,6,'Transport 20/03 - Bilan respiratoire'),
(14,160.00,0.80,'EN_ATTENTE','2026-04-10',15,6,'Transport 05/04 - Suivi asthme');

-- 10. PatientFavoris (FK: idpatient, idtransporteur, type_transport)
INSERT INTO patient_favori(idpatient, idtransporteur, type_transport) VALUES
(1, 1, 'AMBULANCE'),
(1, 2, 'VSL'),
(1, 3, 'TAXI'),
(2, 1, 'AMBULANCE'),
(2, 2, 'VSL'),
(3, 1, 'AMBULANCE'),
(4, 4, 'AMBULANCE'),
(4, 5, 'VSL'),
(5, 6, 'AMBULANCE'),
(5, 7, 'VSL'),
(6, 8, 'AMBULANCE'),
(6, 9, 'VSL');

-- 11. Notifications pour tous les patients
INSERT INTO notifications(patient_id, title, message, type, date, read_status) VALUES
(1, 'Nouvelle prescription', 'Dr. Martin Rousseau vous a cree une prescription : Consultation cardiologie', 'PRESCRIPTION', '2026-01-09', 'false'),
(1, 'Transport programme', 'Votre transport a ete programme pour le 10/01/2026 a 08:00', 'TRANSPORT', '2026-01-09', 'false'),
(1, 'Document disponible', 'Votre prescription PDF est prete a etre telechargee', 'DOCUMENT', '2026-01-09', 'true'),
(2, 'Nouvelle prescription', 'Dr. Dupont Marie vous a cree une prescription : Seance de kinesitherapie', 'PRESCRIPTION', '2026-01-14', 'true'),
(3, 'Nouvelle prescription', 'Dr. Martin Rousseau vous a cree une prescription : Consultation neurologie', 'PRESCRIPTION', '2026-01-19', 'false'),
(4, 'Nouvelle prescription', 'Dr. Martin Rousseau a cree une prescription : Bilan diabete', 'PRESCRIPTION', '2026-02-09', 'false'),
(4, 'Rendez-vous medical', 'Votre transport vers le CHU de Toulouse est programme le 10/02/2026', 'TRANSPORT', '2026-02-08', 'true'),
(4, 'Document disponible', 'Votre bilan diabete PDF est disponible', 'DOCUMENT', '2026-02-09', 'true'),
(5, 'Nouvelle prescription', 'Dr. Martin Rousseau a cree une prescription : Consultation cardiologie debutante', 'PRESCRIPTION', '2026-03-31', 'false'),
(5, 'Transport programme', 'Vous avez un transport prevu le 01/04/2026 a 09:00', 'TRANSPORT', '2026-03-30', 'false'),
(6, 'Nouvelle prescription', 'Dr. Martin Rousseau a cree une prescription : Consultation pneumologie', 'PRESCRIPTION', '2026-02-14', 'true'),
(6, 'Rendez-vous medical', 'Transport vers Hopital Archet Nice le 15/02/2026', 'TRANSPORT', '2026-02-13', 'true'),
(6, 'Remboursement en cours', 'Votre remboursement de 175,00 EUR est en cours de traitement', 'REIMBURSEMENT', '2026-02-20', 'false');

-- 12. QR Codes pour les etapes DEPART uniquement (UN seul QR code par transport)
INSERT INTO qr_codes(code, scanned, generated_at, expires_at, scanned_at, etape_id) VALUES
('550e8400-e29b-41d4-a716-446655440001', true, '2026-01-10 08:00:00', '2026-01-10 10:00:00', '2026-01-10 08:15:00', 1),
('550e8400-e29b-41d4-a716-446655440002', true, '2026-03-15 09:30:00', '2026-03-15 11:30:00', '2026-03-15 09:45:00', 5),
('550e8400-e29b-41d4-a716-446655440003', true, '2026-01-15 08:45:00', '2026-01-15 10:45:00', '2026-01-15 09:00:00', 9),
('550e8400-e29b-41d4-a716-446655440004', true, '2026-01-20 10:00:00', '2026-01-20 12:00:00', '2026-01-20 10:15:00', 13),
('550e8400-e29b-41d4-a716-446655440005', true, '2026-02-01 14:30:00', '2026-02-01 16:30:00', '2026-02-01 14:45:00', 17),
('550e8400-e29b-41d4-a716-446655440006', true, '2026-02-05 09:15:00', '2026-02-05 11:15:00', '2026-02-05 09:30:00', 21),
('550e8400-e29b-41d4-a716-446655440007', true, '2026-03-30 14:15:00', '2026-03-30 16:15:00', '2026-03-30 14:30:00', 25),
('550e8400-e29b-41d4-a716-446655440008', false, '2026-04-19 14:00:00', '2026-04-20 14:00:00', null, 29),
('550e8400-e29b-41d4-a716-446655440009', true, '2026-02-10 10:30:00', '2026-02-10 12:30:00', '2026-02-10 10:45:00', 33),
('550e8400-e29b-41d4-a716-446655440010', true, '2026-03-05 14:00:00', '2026-03-05 16:00:00', '2026-03-05 14:20:00', 37),
('550e8400-e29b-41d4-a716-446655440011', true, '2026-04-01 09:00:00', '2026-04-01 11:00:00', '2026-04-01 09:15:00', 41),
('550e8400-e29b-41d4-a716-446655440012', false, '2026-04-09 15:00:00', '2026-04-10 15:00:00', null, 45),
('550e8400-e29b-41d4-a716-446655440013', true, '2026-02-15 11:00:00', '2026-02-15 13:00:00', '2026-02-15 11:15:00', 49),
('550e8400-e29b-41d4-a716-446655440014', true, '2026-03-20 13:45:00', '2026-03-20 15:45:00', '2026-03-20 14:00:00', 53),
('550e8400-e29b-41d4-a716-446655440015', false, '2026-04-04 08:00:00', '2026-04-05 08:00:00', null, 57);
