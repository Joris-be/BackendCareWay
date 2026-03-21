INSERT INTO Medecin(idmedecin, nom, prenom, specialite, rpps, mail) VALUES
(1,'Lamar','Kendrick','Medecin generaliste','10123456789','kendrick.lamar@gmail.com'),
(2,'Dupont','Marie','Cardiologue','10234567890','marie.dupont@gmail.com'),
(3,'Martin','Pierre','Pediatre','10345678901','pierre.martin@gmail.com'),
(4,'Bernard','Sophie','Dermatologue','10456789012','sophie.bernard@gmail.com'),
(5,'Leclerc','Thomas','Neurologue','10567890123','thomas.leclerc@gmail.com'),
(6,'Moreau','Isabelle','Gynecologue','10678901234','isabelle.moreau@gmail.com'),
(7,'Laurent','Nicolas','Ophtalmologue','10789012345','nicolas.laurent@gmail.com'),
(8,'Simon','Camille','Rhumatologue','10890123456','camille.simon@gmail.com'),
(9,'Michel','Antoine','Psychiatre','10901234567','antoine.michel@gmail.com'),
(10,'Petit','Lucie','Endocrinologue','10012345678','lucie.petit@gmail.com');

INSERT INTO Prescription(idprescription, motifmedical, typetransport, dateprescription, dategeneration) VALUES
(1, 'Consultation cardiologie', 'Ambulance', '2026-01-10', '2026-01-09'),
(2, 'Seance de kinesitherapie', 'VSL', '2026-01-15', '2026-01-14'),
(3, 'Consultation neurologie', 'Ambulance', '2026-01-20', '2026-01-19'),
(4, 'Examen radiologique', 'VSL', '2026-02-01', '2026-01-31'),
(5, 'Dialyse renale', 'Ambulance', '2026-02-05', '2026-02-04'),
(6, 'Consultation ophtalmologie', 'Taxi medical', '2026-02-10', '2026-02-09'),
(7, 'Seance de chimotherapie', 'Ambulance', '2026-02-15', '2026-02-14'),
(8, 'Consultation psychiatrie', 'VSL', '2026-02-20', '2026-02-19'),
(9, 'Examen IRM', 'Ambulance', '2026-03-01', '2026-02-28'),
(10, 'Consultation dermatologie', 'Taxi medical', '2026-03-05', '2026-03-04');

INSERT INTO Patient(idpatient, prenom, nom, datenaiss, nss, adresse, tel, mail, genre, pays) VALUES
(1, 'Jean', 'Dupont', '1985-03-15', '185037512345678', '12 rue de la Paix, Paris', '0612345678', 'jean.dupont@gmail.com', 'M', 'France'),
(2, 'Sophie', 'Martin', '1990-07-22', '290077523456789', '5 avenue des Fleurs, Lyon', '0623456789', 'sophie.martin@gmail.com', 'F', 'France'),
(3, 'Pierre', 'Bernard', '1978-11-30', '178117534567890', '8 boulevard Victor Hugo, Marseille', '0634567890', 'pierre.bernard@gmail.com', 'M', 'France'),
(4, 'Marie', 'Leclerc', '1995-02-14', '295027545678901', '3 rue du Moulin, Bordeaux', '0645678901', 'marie.leclerc@gmail.com', 'F', 'France'),
(5, 'Thomas', 'Moreau', '1965-09-08', '165097556789012', '27 rue des Lilas, Toulouse', '0656789012', 'thomas.moreau@gmail.com', 'M', 'France'),
(6, 'Camille', 'Laurent', '2000-05-19', '200057567890123', '14 impasse des Roses, Nantes', '0667890123', 'camille.laurent@gmail.com', 'F', 'France'),
(7, 'Nicolas', 'Simon', '1972-12-03', '172127578901234', '9 rue de la Republique, Lille', '0678901234', 'nicolas.simon@gmail.com', 'M', 'France'),
(8, 'Isabelle', 'Michel', '1988-04-25', '188047589012345', '21 avenue du Parc, Strasbourg', '0689012345', 'isabelle.michel@gmail.com', 'F', 'France'),
(9, 'Antoine', 'Petit', '1955-08-17', '155087590123456', '6 rue des Pins, Rennes', '0690123456', 'antoine.petit@gmail.com', 'M', 'France'),
(10, 'Lucie', 'Garcia', '1993-01-11', '293017501234567', '18 chemin des Vignes, Montpellier', '0601234567', 'lucie.garcia@gmail.com', 'F', 'France');

INSERT INTO Evaluation(idevaluation, note, commentaire) VALUES
(1, 4.5, 'Tres bon service, chauffeur ponctuel et agreable'),
(2, 3.0, 'Transport correct mais leger retard au depart'),
(3, 5.0, 'Excellent service, je recommande vivement'),
(4, 2.5, 'Chauffeur peu courtois, vehicule pas tres propre'),
(5, 4.0, 'Bon transport, conduite souple et securisee'),
(6, 1.5, 'Retard important et mauvaise communication'),
(7, 4.5, 'Tres professionnel, aide a la montee et descente'),
(8, 3.5, 'Service satisfaisant dans l ensemble'),
(9, 5.0, 'Parfait, rien a redire sur la prestation'),
(10, 2.0, 'Vehicule en mauvais etat, trajet inconfortable');

INSERT INTO Transport(idtransport, datetransport, lieudepart, lieuarrive, typetransport) VALUES
(1, '2026-01-10', '12 rue de la Paix, Paris', 'Hopital Lariboisiere, Paris', 'Ambulance'),
(2, '2026-01-15', '5 avenue des Fleurs, Lyon', 'Clinique du Parc, Lyon', 'VSL'),
(3, '2026-01-20', '8 boulevard Victor Hugo, Marseille', 'Hopital de la Timone, Marseille', 'Ambulance'),
(4, '2026-02-01', '3 rue du Moulin, Bordeaux', 'Clinique Saint-Augustin, Bordeaux', 'VSL'),
(5, '2026-02-05', '27 rue des Lilas, Toulouse', 'Hopital Rangueil, Toulouse', 'Ambulance'),
(6, '2026-02-10', '14 impasse des Roses, Nantes', 'CHU de Nantes', 'Taxi medical'),
(7, '2026-02-15', '9 rue de la Republique, Lille', 'Hopital Claude Huriez, Lille', 'Ambulance'),
(8, '2026-02-20', '21 avenue du Parc, Strasbourg', 'Hopital de Hautepierre, Strasbourg', 'VSL'),
(9, '2026-03-01', '6 rue des Pins, Rennes', 'CHU de Rennes', 'Ambulance'),
(10, '2026-03-05', '18 chemin des Vignes, Montpellier', 'Hopital Lapeyronie, Montpellier', 'Taxi medical');

INSERT INTO Etape(idetape, statut) VALUES
(1, 'Depart'),
(2, 'Lieu de RDV'),
(3, 'Fin de consultation'),
(4, 'Arrive'),
(5, 'Depart'),
(6, 'Lieu de RDV'),
(7, 'Fin de consultation'),
(8, 'Arrive'),
(9, 'Depart'),
(10, 'Depart');

INSERT INTO Remboursement(idremboursement, montant, tauxprisencharge, statutremboursement, dateremboursement) VALUES
(1, 120.50, 0.80, 'Rembourse', '2026-01-15'),
(2, 85.00, 0.65, 'Rembourse', '2026-01-20'),
(3, 200.00, 0.90, 'Rembourse', '2026-01-25'),
(4, 150.75, 0.75, 'En attente', '2026-02-05'),
(5, 310.00, 0.95, 'Rembourse', '2026-02-10'),
(6, 95.50, 0.70, 'Refuse', '2026-02-15'),
(7, 420.00, 0.90, 'En attente', '2026-02-20'),
(8, 180.25, 0.80, 'Rembourse', '2026-02-25'),
(9, 260.00, 0.85, 'En attente', '2026-03-05'),
(10, 75.00, 0.60, 'Refuse', '2026-03-10');

INSERT INTO Transporteur(idtransporteur, nom, prenom, tel, mail) VALUES
(1, 'Durand', 'Lucas', '0611111111', 'lucas.durand@gmail.com'),
(2, 'Leroy', 'Maxime', '0622222222', 'maxime.leroy@gmail.com'),
(3, 'Bonnet', 'Kevin', '0633333333', 'kevin.bonnet@gmail.com'),
(4, 'Girard', 'Julien', '0644444444', 'julien.girard@gmail.com'),
(5, 'Roux', 'Alexandre', '0655555555', 'alexandre.roux@gmail.com'),
(6, 'Fournier', 'Baptiste', '0666666666', 'baptiste.fournier@gmail.com'),
(7, 'Morel', 'Sebastien', '0677777777', 'sebastien.morel@gmail.com'),
(8, 'Clement', 'Florian', '0688888888', 'florian.clement@gmail.com'),
(9, 'Gauthier', 'Romain', '0699999999', 'romain.gauthier@gmail.com'),
(10, 'Perrin', 'Yann', '0600000000', 'yann.perrin@gmail.com');

INSERT INTO Note(idnote, nombreetoiles) VALUES
(1, 5.0),
(2, 4.5),
(3, 4.0),
(4, 3.5),
(5, 3.0),
(6, 2.5),
(7, 2.0),
(8, 1.5),
(9, 1.0),
(10, 0.5);