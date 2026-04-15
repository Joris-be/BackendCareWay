-- Supprimer les données existantes (PostgreSQL: TRUNCATE with CASCADE)
TRUNCATE TABLE qr_codes CASCADE;
TRUNCATE TABLE etape CASCADE;
TRUNCATE TABLE evaluation CASCADE;
TRUNCATE TABLE remboursement CASCADE;
TRUNCATE TABLE note CASCADE;
TRUNCATE TABLE notifications CASCADE;
TRUNCATE TABLE patient_favori CASCADE;
TRUNCATE TABLE prescription CASCADE;
TRUNCATE TABLE transport CASCADE;
TRUNCATE TABLE transporteur CASCADE;
TRUNCATE TABLE medecin CASCADE;
TRUNCATE TABLE patient CASCADE;

-- 1. Medecin (pas de FK)
INSERT INTO Medecin(idmedecin, nom, prenom, specialite, rpps, motdepasse, mail) VALUES
(1,'Rousseau','Martin','Medecin generaliste','10123456789','MedPass123','martin.rousseau@gmail.com'),
(2,'Dupont','Marie','Cardiologue','10234567890','MedPass123','marie.dupont@gmail.com');

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

-- 4. PRESCRIPTION - CRITICAL: Using integer idmedecin values!
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

-- 2. Patient (pas de FK)
INSERT INTO Patient(idpatient, prenom, nom, datenaiss, nss, motdepasse, adresse, maladie, tel, mail, genre, pays, image) VALUES
(1,'Jean','Dupont','1985-03-15','185037512345678','PatPass123','12 rue de la Paix, Paris','Arythmie cardiaque','0612345678','jean.dupont071980@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(2,'Sophie','Martin','1990-07-22','290077523456789','PatPass123','5 avenue des Fleurs, Lyon','Entorse','0623456789','sophie.martin@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(3,'Pierre','Bernard','1978-11-30','178117534567890','PatPass123','8 boulevard Victor Hugo, Marseille','Migraines','0634567890','pierre.bernard@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(4,'Luc','Moreau','1992-05-20','192057556789012','PatPass123','15 rue de la Republique, Toulouse','Diabete de type 2','0645678901','luc.moreau@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(5,'Claire','Girard','1988-09-10','188097634512345','PatPass123','20 boulevard du Theatre, Bordeaux','Hypertension','0656789012','claire.girard@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(6,'Thomas','Leclerc','1995-12-03','195127745623456','PatPass123','7 avenue Montaigne, Nice','Asthme','0667890123','thomas.leclerc@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(7,'Francoise','Laurent','1980-02-14','180027812345679','PatPass123','25 rue Victor Schoelcher, Lille','Fibromyalgie','0678901234','francoise.laurent@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(8,'Michel','Fosse','1975-08-22','175082023456780','PatPass123','10 avenue Foch, Toulouse','Insuffisance renale','0689012345','michel.fosse@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(9,'Martine','Legrand','1992-11-05','192117034567891','PatPass123','30 boulevard Saint-Exupery, Marseille','Hypertension','0690123456','martine.legrand@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(10,'Antoine','Petit','1988-04-17','188047145678902','PatPass123','5 rue des Lilas, Lyon','Arythmie cardiaque','0601234567','antoine.petit@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(11,'Isabelle','Arnould','1987-06-30','187067256789013','PatPass123','18 rue Chamfort, Paris','Diabete type 1','0612345679','isabelle.arnould@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(12,'Robert','Chevallier','1965-01-10','165017367890124','PatPass123','45 avenue Winston Churchill, Bordeaux','Arthrite rheumatoide','0623456780','robert.chevallier@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(13,'Nicole','Gaillard','1982-09-25','182097478901235','PatPass123','8 boulevard Gambetta, Nice','Insuffisance cardiaque','0634567891','nicole.gaillard@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(14,'Fabien','Herve','1990-03-12','190037589012346','PatPass123','22 rue Jean Jaures, Toulouse','Bronchite chronique','0645678902','fabien.herve@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(15,'Sylvie','Imbert','1986-07-08','186077690123457','PatPass123','50 avenue Victor Hugo, Lyon','Asthme','0656789013','sylvie.imbert@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(16,'Yves','Jacquet','1975-11-19','175119701234568','PatPass123','3 rue du Faubourg, Paris','Sclerose en plaques','0667890124','yves.jacquet@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(17,'Stephanie','Keller','1993-05-03','193057811345679','PatPass123','19 boulevard Montparnasse, Bordeaux','Thyroidite','0678901235','stephanie.keller@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(18,'Edward','Lamy','1981-10-27','181107912456780','PatPass123','11 rue de l Estrapade, Nice','Polyarthrite rhumatoide','0689012346','edward.lamy@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(19,'Veronique','Moulton','1991-02-14','191027013567891','PatPass123','27 avenue Montaigne, Marseille','Hepatite C','0690123457','veronique.moulton@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(20,'Richard','Noel','1974-08-06','174087114678902','PatPass123','14 rue du Temple, Toulouse','Maladie de Parkinson','0601234568','richard.noel@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(21,'Beatrice','Olivier','1989-04-29','189047215789013','PatPass123','36 avenue des Champs, Lyon','Lupus','0612345680','beatrice.olivier@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(22,'Patrick','Pelletier','1979-12-11','179127316890124','PatPass123','9 rue Saint-Antoine, Paris','Insuffisance cardiaque','0623456781','patrick.pelletier@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(23,'Jacqueline','Quillard','1985-06-20','185067417901235','PatPass123','23 boulevard Haussmann, Bordeaux','Diabete gestationnel','0634567892','jacqueline.quillard@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(24,'Georges','Renard','1970-01-08','170017518012346','PatPass123','41 rue de Rivoli, Nice','Cardiovasculaire','0645678903','georges.renard@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(25,'Christine','Serfaire','1994-09-30','194097619123457','PatPass123','12 avenue de la Grande Armee, Toulouse','Angine','0656789014','christine.serfaire@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(26,'Bernard','Thomas','1977-03-18','177037720234568','PatPass123','15 rue Casimir Perier, Lyon','Colite ulcerative','0667890125','bernard.thomas@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(27,'Marie-Louise','Urbain','1983-07-25','183077821345679','PatPass123','34 rue Jean Moulin, Marseille','Fibrose pulmonaire','0678901236','marie-louise.urbain@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(28,'Jacques','Vidal','1976-05-14','176057922456780','PatPass123','8 rue de Meaux, Paris','Myopathie','0689012347','jacques.vidal@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(29,'Denise','Wagner','1991-10-02','191107023567891','PatPass123','29 rue Abbesses, Bordeaux','Narcolepsie','0690123458','denise.wagner@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(30,'Claude','Xavier','1980-11-11','180107124678902','PatPass123','44 boulevard de Courcelles, Nice','Tremblements essentiels','0601234569','claude.xavier@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(31,'Helene','Young','1986-02-07','186027225789013','PatPass123','21 rue Oberkampf, Toulouse','Epilepsie','0612345681','helene.young@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(32,'Andre','Zabarella','1972-08-16','172087326890124','PatPass123','38 avenue Pasteur, Lyon','Arterite temporale','0623456782','andre.zabarella@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(33,'Caroline','Martin','1989-01-23','189017427901235','PatPass123','16 rue Ballu, Paris','Mycose','0634567893','caroline.martin@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(34,'Philippe','Noel','1984-09-10','184097528012346','PatPass123','31 avenue Kléber, Bordeaux','Osteoporose','0645678904','philippe.noel@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(35,'Simone','Laroche','1993-03-30','193037629123457','PatPass123','7 rue Thouin, Marseille','Endometriose','0656789015','simone.laroche@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(36,'Thierry','Gervais','1979-06-05','179067730234568','PatPass123','13 avenue Wilson, Nice','Glaucome','0667890126','thierry.gervais@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(37,'Madeleine','Broussard','1987-04-12','187047831345679','PatPass123','46 boulevard Voltaire, Toulouse','Maladie de Crohn','0678901237','madeleine.broussard@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(38,'Vincent','Delarue','1981-12-28','181127932456780','PatPass123','20 rue Saint-Paul, Lyon','Tachycardie','0689012348','vincent.delarue@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(39,'Agnes','Fontaine','1990-08-19','190087033567891','PatPass123','43 rue des Archives, Paris','Sinusite chronique','0690123459','agnes.fontaine@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(40,'Olivier','Guillot','1975-05-03','175057134678902','PatPass123','10 rue Malraux, Bordeaux','Bronchectasie','0601234570','olivier.guillot@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(41,'Veronique','Hubert','1988-10-14','188107235789013','PatPass123','28 rue Marbeuf, Marseille','Coeur pulmonaire','0612345682','veronique.hubert@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(42,'Laurent','Isabelle','1982-07-26','182077336890124','PatPass123','35 rue Boissy d Anglas, Nice','Arthrite juvenile','0623456783','laurent.isabelle@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(43,'Patricia','Jarry','1991-02-09','191027437901235','PatPass123','51 avenue Montparnasse, Toulouse','Maladie de Lyme','0634567894','patricia.jarry@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(44,'Eric','Keiran','1976-11-30','176117538012346','PatPass123','17 rue Claude Bernard, Lyon','Cataracte','0645678905','eric.keiran@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(45,'Genevieve','Lombardi','1985-03-22','185037639123457','PatPass123','26 rue Farnese, Paris','Spasticite','0656789016','genevieve.lombardi@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(46,'Florian','Martel','1993-06-11','193067740234568','PatPass123','39 boulevard Saint-Germain, Bordeaux','TDAH','0667890127','florian.martel@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(47,'Louise','Navarro','1990-09-05','190097841345679','PatPass123','2 rue Monsieur, Marseille','Bipolaire','0678901238','louise.navarro@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(48,'Maxime','Obert','1978-01-15','178017942456780','PatPass123','24 avenue Rapp, Nice','Hemophilie','0689012349','maxime.obert@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(49,'Sarah','Paulin','1987-04-27','187047043567891','PatPass123','37 rue Lauriston, Toulouse','Alopecie','0690123460','sarah.paulin@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(50,'David','Queneau','1981-08-08','181087144678902','PatPass123','14 rue Jouffroy, Lyon','Vitiligo','0601234571','david.queneau@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(51,'Micheline','Renolt','1989-05-19','189057245789013','PatPass123','33 rue Miromesnil, Paris','Scoliose','0612345683','micheline.renolt@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(52,'Jonathan','Samin','1992-12-02','192127346890124','PatPass123','48 rue Bassano, Bordeaux','Ankylose','0623456784','jonathan.samin@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop'),
(53,'Francine','Tarbouriech','1986-10-13','186107447901235','PatPass123','11 avenue George V, Marseille','Dysarthrie','0634567895','francine.tarbouriech@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(54,'Christophe','Utrillo','1974-07-21','174077548012346','PatPass123','30 rue Bayard, Nice','Douleur neuropathique','0645678906','christophe.utrillo@gmail.com','M','France','https://images.unsplash.com/photo-1546961329-78bef0414d7d?w=400&h=400&fit=crop'),
(55,'Annette','Verlaine','1988-02-06','188027649123457','PatPass123','44 rue Le Verrier, Toulouse','Acne rosacee','0656789017','annette.verlaine@gmail.com','F','France','https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop'),
(56,'Fabrice','Willy','1983-09-14','183097750234568','PatPass123','19 rue Chabanais, Lyon','Ichtyose','0667890128','fabrice.willy@gmail.com','M','France','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop');

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
(10,'Suivi asthme','Ambulance','2026-04-05','2026-04-04',1,6),
(11,'Bilan fibromyalgie','Ambulance','2026-01-20','2026-01-19',1,7),
(12,'Suivi insuffisance renale','VSL','2026-01-25','2026-01-24',1,8),
(13,'Consultation hypertension avancee','Ambulance','2026-02-01','2026-01-31',1,9),
(14,'Bilan arythmie cardiaque','Ambulance','2026-02-05','2026-02-04',1,10),
(15,'Medicament insulin - Diabete T1','VSL','2026-02-10','2026-02-09',1,11),
(16,'Suivi polyarthrite rhumatoide','Ambulance','2026-02-15','2026-02-14',1,12),
(17,'Evaluation insuffisance cardiaque','Ambulance','2026-02-20','2026-02-19',1,13),
(18,'Kinesitherapie bronchite chronique','VSL','2026-02-25','2026-02-24',1,14),
(19,'Spirometrie asthme severe','Ambulance','2026-03-01','2026-02-28',1,15),
(20,'IRM sclerose en plaques','Ambulance','2026-03-05','2026-03-04',1,16),
(21,'Consultation endocrinologue thyroid','VSL','2026-03-10','2026-03-09',1,17),
(22,'Examen coagulation hemophilie','Ambulance','2026-03-15','2026-03-14',1,18),
(23,'Biopsie foie hepatite C','Ambulance','2026-03-20','2026-03-19',1,19),
(24,'Evaluation neurologique Parkinson','VSL','2026-03-25','2026-03-24',1,20),
(25,'Bilan lupus systematique','Ambulance','2026-03-30','2026-03-29',1,21),
(26,'Surveillance insuffisance cardiaque','VSL','2026-04-01','2026-03-31',1,22),
(27,'Suivi gestationnel diabete','Ambulance','2026-04-05','2026-04-04',1,23),
(28,'Doppler cardiovasculaire','Ambulance','2026-04-10','2026-04-09',1,24),
(29,'Pharyngite consultation urgente','VSL','2026-04-15','2026-04-14',1,25),
(30,'Coloscopie colite ulcerative','Ambulance','2026-04-20','2026-04-19',1,26),
(31,'EFR fibrose pulmonaire','Ambulance','2026-01-22','2026-01-21',1,27),
(32,'Biopsie musculaire myopathie','VSL','2026-01-27','2026-01-26',1,28),
(33,'Polygraphie narcolepsie','Ambulance','2026-02-03','2026-02-02',1,29),
(34,'Electronystagmographie tremblements','Ambulance','2026-02-08','2026-02-07',1,30),
(35,'EEG et monitoring epilepsie','VSL','2026-02-13','2026-02-12',1,31),
(36,'Angiographie arterite temporale','Ambulance','2026-02-18','2026-02-17',1,32),
(37,'Dermatologie mycose consultation','Ambulance','2026-02-23','2026-02-22',1,33),
(38,'Scan densitometrie osseuse','VSL','2026-03-02','2026-03-01',1,34),
(39,'Echographie pelvis endometriose','Ambulance','2026-03-07','2026-03-06',1,35),
(40,'Tonometrie glaucome suivi','Ambulance','2026-03-12','2026-03-11',1,36),
(41,'Gastroenterologie Crohn','VSL','2026-03-17','2026-03-16',1,37),
(42,'ECG tachycardie','Ambulance','2026-03-22','2026-03-21',1,38),
(43,'Nasofibroscopie sinusite chron','Ambulance','2026-03-27','2026-03-26',1,39),
(44,'Bronchoscopie bronchectasie','VSL','2026-04-02','2026-04-01',1,40),
(45,'Catheterisme coeur pulmonaire','Ambulance','2026-04-07','2026-04-06',1,41),
(46,'Evaluation rhumatologique juvenile','Ambulance','2026-04-12','2026-04-11',1,42),
(47,'PCR serologie maladie Lyme','VSL','2026-01-30','2026-01-29',1,43),
(48,'Ophtalmologie cataracte','Ambulance','2026-02-04','2026-02-03',1,44),
(49,'Consultation neurologue spasticite','Ambulance','2026-02-09','2026-02-08',1,45),
(50,'Psychiatrie TDAH evaluation','VSL','2026-02-14','2026-02-13',1,46),
(51,'Psychiatrie bipolaire monitoring','Ambulance','2026-02-19','2026-02-18',1,47),
(52,'Hematologie hemophilie suivi','Ambulance','2026-02-24','2026-02-23',1,48),
(53,'Dermatologie alopecie traitement','VSL','2026-03-06','2026-03-05',1,49),
(54,'Dermatologie vitiligo uv','Ambulance','2026-03-11','2026-03-10',1,50),
(55,'Orthopedie scoliose bilan','Ambulance','2026-03-16','2026-03-15',1,51),
(56,'Rhumatologie ankylose eval','VSL','2026-03-21','2026-03-20',1,52),
(57,'Neurologie dysarthrie rehab','Ambulance','2026-03-26','2026-03-25',1,53),
(58,'Neuropsychologie douleur neuro','Ambulance','2026-03-31','2026-03-30',1,54),
(59,'Dermatologie rosacee traitement','VSL','2026-04-06','2026-04-05',1,55),
(60,'Dermatologie ichtyose hydration','Ambulance','2026-04-12','2026-04-11',1,56),
(61,'Bilan cardiaque complet','Ambulance','2026-04-15','2026-04-14',1,1),
(62,'Consultation supplementaire arythmie','VSL','2026-04-18','2026-04-17',1,1),
(63,'Echocardiogramme suivi','Ambulance','2026-04-22','2026-04-21',1,1),
(64,'Holter ECG 24h','VSL','2026-04-25','2026-04-24',1,1),
(65,'Epreuve d effort cardiaque','Ambulance','2026-05-01','2026-04-30',1,1),
(66,'Consultation de revalidation','VSL','2026-05-05','2026-05-04',1,1),
(67,'Radiographie thoracique suivi','Taxi','2026-05-08','2026-05-07',1,1),
(68,'Bilan biologique complet','Ambulance','2026-05-12','2026-05-11',1,1),
(69,'Consultation urgence cardiaque','Ambulance','2026-05-15','2026-05-14',1,1),
(70,'Evaluation urgence insuffisance cardiaque','VSL','2026-05-18','2026-05-17',1,1);

-- 5. Transporteur (FK: idtransport)
INSERT INTO Transporteur(idtransporteur, nom, prenom, tel, mail, image, idtransport) VALUES
(1,'Durand','Lucas','0611111111','lucas.durand@gmail.com','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop',1),
(2,'Leroy','Maxime','0622222222','maxime.leroy@gmail.com','https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=400&h=400&fit=crop',2),
(3,'Bonnet','Kevin','0633333333','kevin.bonnet@gmail.com','https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?w=400&h=400&fit=crop',3),
(4,'Fontaine','Antoine','0644444444','antoine.fontaine@gmail.com','https://images.unsplash.com/photo-1508967585872-a54883bb8015?w=400&h=400&fit=crop',4),
(5,'Girard','Benjamin','0655555555','benjamin.girard@gmail.com','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop',5),
(6,'Michel','Olivier','0666666666','olivier.michel@gmail.com','https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=400&h=400&fit=crop',6),
(7,'Laurent','David','0677777777','david.laurent@gmail.com','https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?w=400&h=400&fit=crop',7),
(8,'Martin','Christophe','0688888888','christophe.martin@gmail.com','https://images.unsplash.com/photo-1508967585872-a54883bb8015?w=400&h=400&fit=crop',8),
(9,'Richard','Francois','0699999999','francois.richard@gmail.com','https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop',9),
(10,'Deschamps','Philippe','0610101010','philippe.deschamps@gmail.com','https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=400&h=400&fit=crop',10);

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
