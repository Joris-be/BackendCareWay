-- CareWay Database Seed Data
-- Complete and clean data initialization

-- Clean truncate all tables with cascade  
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

-- 1. INSERT MEDECINS
INSERT INTO Medecin(idmedecin, nom, prenom, specialite, rpps, motdepasse, mail) VALUES
(1,'Rousseau','Martin','Medecin generaliste','10123456789','MedPass123','martin.rousseau@gmail.com'),
(2,'Dupont','Marie','Cardiologue','10234567890','MedPass123','marie.dupont@gmail.com');

-- 2. INSERT PATIENTS (56 patients total)
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

-- 3. INSERT PRESCRIPTIONS (60 prescriptions - medecin 1 has 59, medecin 2 has 1)
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
(13,'Consultation hypertension','Ambulance','2026-02-05','2026-02-04',1,9),
(14,'Consultation cardiaque','VSL','2026-02-20','2026-02-19',1,10),
(15,'Bilan diabete type 1','Ambulance','2026-03-10','2026-03-09',1,11),
(16,'Suivi arthrite','VSL','2026-02-25','2026-02-24',1,12),
(17,'Consultation cardiaque','Ambulance','2026-03-15','2026-03-14',1,13),
(18,'Bilan bronchite','VSL','2026-02-28','2026-02-27',1,14),
(19,'Consultation respiratoire','Ambulance','2026-03-08','2026-03-07',1,15),
(20,'Suivi sclerose','VSL','2026-02-10','2026-02-09',1,16),
(21,'Consultation thyroide','Ambulance','2026-03-25','2026-03-24',1,17),
(22,'Bilan poly-arthrite','VSL','2026-01-30','2026-01-29',1,18),
(23,'Consultation hepatite','Ambulance','2026-02-15','2026-02-14',1,19),
(24,'Suivi parkinson','VSL','2026-03-05','2026-03-04',1,20),
(25,'Consultation lupus','Ambulance','2026-03-20','2026-03-19',1,21),
(26,'Bilan insuffisance','VSL','2026-02-12','2026-02-11',1,22),
(27,'Consultation diabete','Ambulance','2026-03-30','2026-03-29',1,23),
(28,'Suivi cardio','VSL','2026-02-18','2026-02-17',1,24),
(29,'Bilan angine','Ambulance','2026-03-22','2026-03-21',1,25),
(30,'Consultation colite','VSL','2026-02-20','2026-02-19',1,26),
(31,'Bilan fibrose','Ambulance','2026-03-10','2026-03-09',1,27),
(32,'Suivi myopathie','VSL','2026-02-25','2026-02-24',1,28),
(33,'Consultation narcolepsie','Ambulance','2026-03-15','2026-03-14',1,29),
(34,'Bilan tremblements','VSL','2026-02-28','2026-02-27',1,30),
(35,'Consultation epilepsie','Ambulance','2026-03-28','2026-03-27',1,31),
(36,'Suivi arterite','VSL','2026-02-20','2026-02-19',1,32),
(37,'Bilan infection','Ambulance','2026-03-18','2026-03-17',1,33),
(38,'Consultation osteo','VSL','2026-02-25','2026-02-24',1,34),
(39,'Suivi endometriose','Ambulance','2026-03-12','2026-03-11',1,35),
(40,'Bilan glaucome','VSL','2026-02-28','2026-02-27',1,36),
(41,'Consultation crohn','Ambulance','2026-03-25','2026-03-24',1,37),
(42,'Suivi tachycardie','VSL','2026-02-10','2026-02-09',1,38),
(43,'Bilan sinusite','Ambulance','2026-03-15','2026-03-14',1,39),
(44,'Consultation bronchectasie','VSL','2026-02-20','2026-02-19',1,40),
(45,'Suivi pulmons','Ambulance','2026-03-08','2026-03-07',1,41),
(46,'Bilan arthrite juv','VSL','2026-02-25','2026-02-24',1,42),
(47,'Consultation lyme','Ambulance','2026-03-20','2026-03-19',1,43),
(48,'Suivi cataracte','VSL','2026-02-15','2026-02-14',1,44),
(49,'Bilan spasticite','Ambulance','2026-03-10','2026-03-09',1,45),
(50,'Consultation TDAH','VSL','2026-02-28','2026-02-27',1,46),
(51,'Suivi bipolaire','Ambulance','2026-03-22','2026-03-21',1,47),
(52,'Bilan hemophilie','VSL','2026-02-18','2026-02-17',1,48),
(53,'Consultation alopecie','Ambulance','2026-03-15','2026-03-14',1,49),
(54,'Suivi vitiligo','VSL','2026-02-25','2026-02-24',1,50),
(55,'Bilan scoliose','Ambulance','2026-03-28','2026-03-27',1,51),
(56,'Consultation ankylose','VSL','2026-02-20','2026-02-19',1,52),
(57,'Suivi dysarthrie','Ambulance','2026-03-18','2026-03-17',1,53),
(58,'Bilan neuropathie','VSL','2026-02-12','2026-02-11',1,54),
(59,'Consultation rosacee','Ambulance','2026-03-25','2026-03-24',1,55),
(60,'Suivi ichtyose','VSL','2026-02-28','2026-02-27',1,56);

-- 4. INSERT TRANSPORTS
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

-- 5. INSERT TRANSPORTEURS
INSERT INTO Transporteur(idtransporteur, nom, prenom, tel, mail, image, idtransport) VALUES
(1,'Durand','Lucas','0611111111','lucas@example.com','https://via.placeholder.com/150',1),
(2,'Leroy','Maxime','0622222222','maxime@example.com','https://via.placeholder.com/150',2),
(3,'Bonnet','Kevin','0633333333','kevin@example.com','https://via.placeholder.com/150',3),
(4,'Fontaine','Antoine','0644444444','antoine@example.com','https://via.placeholder.com/150',4),
(5,'Girard','Benjamin','0655555555','benjamin@example.com','https://via.placeholder.com/150',5),
(6,'Michel','Olivier','0666666666','olivier@example.com','https://via.placeholder.com/150',6);
