# BackendCareWay Database Schema Analysis

## All Tables

1. **Patient** - Base entity for patients
2. **Medecin** - Doctors/physicians
3. **Transporteur** - Transport carriers/drivers
4. **Transport** - Transport orders/trips
5. **Prescription** - Medical prescriptions
6. **Evaluation** - Transport evaluations by patients
7. **Note** - Ratings given by patients to transporters
8. **Notification** - Patient notifications
9. **PatientFavoris** - Patient's favorite transporters
10. **Etape** - Steps/stages in a transport journey
11. **Remboursement** - Reimbursement records
12. **QRCode** - QR codes for transport tracking
13. **Statut** - Enum (not a DB table)
14. **TypeTransport** - Enum (not a DB table)

---

## Foreign Key Relationships

### Patient (Independent - no FK incoming)
- `idpatient` → No references from outside
- Referenced by: Prescription, Transport, Evaluation, Note, Notification, PatientFavoris, Remboursement

### Medecin (Independent - no FK incoming)
- `idmedecin` → No references from outside
- Referenced by: Prescription

### Transporteur (Independent - no FK incoming)
- `idtransporteur` → No references from outside
- Referenced by: Note, PatientFavoris, Transport

### Transport (References Patient)
- `idpatient` (FK) → Patient.idpatient
- Referenced by: Etape, Evaluation, Remboursement, QRCode (indirectly), Transporteur

### Prescription (References Patient & Medecin)
- `idpatient` (FK) → Patient.idpatient
- `idmedecin` (FK) → Medecin.idmedecin

### Evaluation (References Patient & Transport)
- `idpatient` (FK) → Patient.idpatient
- `idtransport` (FK) → Transport.idtransport

### Note (References Patient & Transporteur)
- `idpatient` (FK) → Patient.idpatient
- `idtransporteur` (FK) → Transporteur.idtransporteur

### Notification (References Patient)
- `patient_id` (FK) → Patient.idpatient

### PatientFavoris (References Patient & Transporteur)
- `idpatient` (FK) → Patient.idpatient
- `idtransporteur` (FK) → Transporteur.idtransporteur

### Etape (References Transport)
- `idtransport` (FK) → Transport.idtransport

### Remboursement (References Transport)
- `idtransport` (FK) → Transport.idtransport
- `patient_id` (FK) → Patient.idpatient (optional)

### QRCode (References Etape)
- `etape_id` (FK) → Etape.idetape (NOT NULL)

---

## Dependency Tree

```
Patient (ROOT - no dependencies)
├── Prescription (depends on Patient, Medecin)
├── Transport (depends on Patient)
│   ├── Etape (depends on Transport)
│   │   └── QRCode (depends on Etape)
│   ├── Evaluation (depends on Patient, Transport)
│   └── Remboursement (depends on Transport, Patient)
├── Evaluation (depends on Patient, Transport)
├── Note (depends on Patient, Transporteur)
└── Notification (depends on Patient)

Medecin (ROOT - no dependencies)
└── Prescription (depends on Medecin, Patient)

Transporteur (ROOT - no dependencies)
├── Note (depends on Transporteur, Patient)
└── PatientFavoris (depends on Transporteur, Patient)

PatientFavoris (depends on Patient, Transporteur)
```

---

## Correct DELETE Order (to avoid FK constraint violations)

**DELETE in this order:**

1. **QRCode** - Most dependent (FK to Etape)
2. **Etape** - Depends on Transport
3. **Evaluation** - Depends on Transport & Patient
4. **Remboursement** - Depends on Transport & Patient
5. **Note** - Depends on Patient & Transporteur
6. **Notification** - Depends on Patient
7. **PatientFavoris** - Depends on Patient & Transporteur
8. **Prescription** - Depends on Patient & Medecin
9. **Transport** - Depends on Patient
10. **Transporteur** - Independent (but can be deleted before Patient)
11. **Medecin** - Independent
12. **Patient** - Root table (can be deleted last)

---

## Sample data.sql DELETE Statements (Template)

```sql
-- Disable foreign key checks temporarily (if using MySQL/MariaDB)
SET FOREIGN_KEY_CHECKS=0;

-- Delete in correct order
DELETE FROM qr_codes;
DELETE FROM etape;
DELETE FROM evaluation;
DELETE FROM remboursement;
DELETE FROM note;
DELETE FROM notifications;
DELETE FROM patient_favori;
DELETE FROM prescription;
DELETE FROM transport;
DELETE FROM transporteur;
DELETE FROM medecin;
DELETE FROM patient;

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS=1;

-- Reset auto-increment counters (if needed)
-- ALTER TABLE qr_codes AUTO_INCREMENT = 1;
-- etc...
```

### For PostgreSQL:

```sql
-- Disable FK checks
ALTER TABLE qr_codes DISABLE TRIGGER ALL;
ALTER TABLE etape DISABLE TRIGGER ALL;
ALTER TABLE evaluation DISABLE TRIGGER ALL;
ALTER TABLE remboursement DISABLE TRIGGER ALL;
ALTER TABLE note DISABLE TRIGGER ALL;
ALTER TABLE notifications DISABLE TRIGGER ALL;
ALTER TABLE patient_favori DISABLE TRIGGER ALL;
ALTER TABLE prescription DISABLE TRIGGER ALL;
ALTER TABLE transport DISABLE TRIGGER ALL;
ALTER TABLE transporteur DISABLE TRIGGER ALL;
ALTER TABLE medecin DISABLE TRIGGER ALL;
ALTER TABLE patient DISABLE TRIGGER ALL;

-- Delete in correct order
DELETE FROM qr_codes;
DELETE FROM etape;
DELETE FROM evaluation;
DELETE FROM remboursement;
DELETE FROM note;
DELETE FROM notifications;
DELETE FROM patient_favori;
DELETE FROM prescription;
DELETE FROM transport;
DELETE FROM transporteur;
DELETE FROM medecin;
DELETE FROM patient;

-- Re-enable FK checks
ALTER TABLE qr_codes ENABLE TRIGGER ALL;
ALTER TABLE etape ENABLE TRIGGER ALL;
ALTER TABLE evaluation ENABLE TRIGGER ALL;
ALTER TABLE remboursement ENABLE TRIGGER ALL;
ALTER TABLE note ENABLE TRIGGER ALL;
ALTER TABLE notifications ENABLE TRIGGER ALL;
ALTER TABLE patient_favori ENABLE TRIGGER ALL;
ALTER TABLE prescription ENABLE TRIGGER ALL;
ALTER TABLE transport ENABLE TRIGGER ALL;
ALTER TABLE transporteur ENABLE TRIGGER ALL;
ALTER TABLE medecin ENABLE TRIGGER ALL;
ALTER TABLE patient ENABLE TRIGGER ALL;
```

---

## Key Insights

1. **QRCode** has the deepest dependency chain: QRCode → Etape → Transport → Patient
2. **Transport** is a critical hub - many tables depend on it
3. **Patient** is a root table that multiple entities reference
4. **PatientFavoris** has a composite foreign key to both Patient and Transporteur
5. **Remboursement** has an optional reference to Patient (nullable `patient_id`)
6. **Notification** can be safely deleted early as it's relatively independent
