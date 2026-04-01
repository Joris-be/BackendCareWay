-- 1. Medecin (pas de FK)
INSERT INTO Medecin(idmedecin, nom, prenom, specialite, rpps, motdepasse, mail) VALUES
(1,'Lamar','Kendrick','Medecin generaliste','10123456789','MedPass123','kendrick.lamar@gmail.com'),
(2,'Dupont','Marie','Cardiologue','10234567890','MedPass123','marie.dupont@gmail.com'),
(3,'Martin','Pierre','Pediatre','10345678901','MedPass123','pierre.martin@gmail.com'),
(4,'Bernard','Sophie','Dermatologue','10456789012','MedPass123','sophie.bernard@gmail.com'),
(5,'Leclerc','Thomas','Neurologue','10567890123','MedPass123','thomas.leclerc@gmail.com');

-- 2. Patient (pas de FK)
INSERT INTO Patient(idpatient, prenom, nom, datenaiss, nss, motdepasse, adresse, maladie, tel, mail, genre, pays) VALUES
(1,'Jean','Dupont','1985-03-15','185037512345678','PatPass123','12 rue de la Paix, Paris','Arythmie cardiaque','0612345678','jean.dupont@gmail.com','M','France'),
(2,'Sophie','Martin','1990-07-22','290077523456789','PatPass123','5 avenue des Fleurs, Lyon','Entorse', '0623456789','sophie.martin@gmail.com','F','France'),
(3,'Pierre','Bernard','1978-11-30','178117534567890','PatPass123','8 boulevard Victor Hugo, Marseille','Migraines','0634567890','pierre.bernard@gmail.com','M','France'),
(4,'Marie','Leclerc','1995-02-14','295027545678901','PatPass123','3 rue du Moulin, Bordeaux','Diabète type 2','0645678901','marie.leclerc@gmail.com','F','France'),
(5,'Thomas','Moreau','1965-09-08','165097556789012','PatPass123','27 rue des Lilas, Toulouse','Insuffisance rénale','0656789012','thomas.moreau@gmail.com','M','France');

-- Ajouter les images pour les patients
UPDATE Patient SET image = 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop' WHERE idpatient = 1;

-- 3. Transport (pas de FK)
INSERT INTO Transport(idtransport, datetransport, lieudepart, lieuarrive, typetransport) VALUES
(1,'2026-01-10','12 rue de la Paix, Paris','Hopital Lariboisiere, Paris','Ambulance'),
(2,'2026-01-15','5 avenue des Fleurs, Lyon','Clinique du Parc, Lyon','VSL'),
(3,'2026-01-20','8 boulevard Victor Hugo, Marseille','Hopital de la Timone, Marseille','Ambulance'),
(4,'2026-02-01','3 rue du Moulin, Bordeaux','Clinique Saint-Augustin, Bordeaux','VSL'),
(5,'2026-02-05','27 rue des Lilas, Toulouse','Hopital Rangueil, Toulouse','Ambulance');

-- 4. Prescription (FK: idmedecin, idpatient)
INSERT INTO Prescription(idprescription, motifmedical, typetransport, dateprescription, dategeneration, idmedecin, idpatient) VALUES
(1,'Consultation cardiologie','Ambulance','2026-01-10','2026-01-09',1,1),
(2,'Seance de kinesitherapie','VSL','2026-01-15','2026-01-14',2,2),
(3,'Consultation neurologie','Ambulance','2026-01-20','2026-01-19',3,3),
(4,'Examen radiologique','VSL','2026-02-01','2026-01-31',4,4),
(5,'Dialyse renale','Ambulance','2026-02-05','2026-02-04',5,5);

-- 5. Transporteur (FK: idtransport)
INSERT INTO Transporteur(idtransporteur, nom, prenom, tel, mail, idtransport) VALUES
(1,'Durand','Lucas','0611111111','lucas.durand@gmail.com',1),
(2,'Leroy','Maxime','0622222222','maxime.leroy@gmail.com',2),
(3,'Bonnet','Kevin','0633333333','kevin.bonnet@gmail.com',3),
(4,'Girard','Julien','0644444444','julien.girard@gmail.com',4),
(5,'Roux','Alexandre','0655555555','alexandre.roux@gmail.com',5);

-- 6. Evaluation (FK: idpatient, idtransport)
INSERT INTO Evaluation(idevaluation, note, commentaire, idpatient, idtransport) VALUES
(1,4.5,'Tres bon service',1,1),
(2,3.0,'Transport correct mais leger retard',2,2),
(3,5.0,'Excellent service',3,3),
(4,2.5,'Chauffeur peu courtois',4,4),
(5,4.0,'Bon transport',5,5);

-- 7. Etape (FK: idtransport)
INSERT INTO Etape(idetape, statut, idtransport) VALUES
(1,'DEPART',1),
(2,'ARRIVE_HOPITAL',2),
(3,'RDV_FINI',3),
(4,'RETOUR_CHEZ_SOI',4),
(5,'DEPART',5);

-- 8. Note (FK: idtransporteur, idpatient)
INSERT INTO Note(idnote, nombreetoiles, idtransporteur, idpatient) VALUES
(1,5.0,1,1),
(2,4.5,2,2),
(3,4.0,3,3),
(4,3.5,4,4),
(5,3.0,5,5);

-- 9. Remboursement (FK: idtransport)
INSERT INTO Remboursement(idremboursement, montant, tauxprisencharge, statutremboursement, dateremboursement, idtransport) VALUES
(1,120.50,0.80,'Rembourse','2026-01-15',1),
(2,85.00,0.65,'Rembourse','2026-01-20',2),
(3,200.00,0.90,'Rembourse','2026-01-25',3),
(4,150.75,0.75,'En attente','2026-02-05',4),
(5,310.00,0.95,'Rembourse','2026-02-10',5);

-- 10. PatientFavoris (FK: idpatient, idtransporteur)
INSERT INTO patient_favori(idpatient, idtransporteur, type_transport) VALUES
-- Patient 1 : Jean Dupont
(1, 1, 'AMBULANCE'),
(1, 2, 'VSL'),
(1, 3, 'TAXI'),
-- Patient 2 : Sophie Martin
(2, 1, 'AMBULANCE'),
(2, 2, 'VSL'),
(2, 3, 'TAXI'),
-- Patient 3 : Pierre Bernard
(3, 1, 'AMBULANCE'),
(3, 2, 'VSL'),
(3, 3, 'TAXI'),
-- Patient 4 : Marie Leclerc
(4, 1, 'AMBULANCE'),
(4, 2, 'VSL'),
(4, 3, 'TAXI'),
-- Patient 5 : Thomas Moreau
(5, 1, 'AMBULANCE'),
(5, 2, 'VSL'),
(5, 3, 'TAXI');