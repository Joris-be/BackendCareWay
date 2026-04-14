package com.careway.service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.careway.entity.Patient;
import com.careway.entity.Medecin;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.borders.SolidBorder;

@Service
public class PrescriptionPDFService {

    public byte[] generatePrescriptionPDF(Patient patient, Medecin medecin, PrescriptionFormData formData)
            throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.setMargins(15, 15, 15, 15);

        // ===== TITRE =====
        document.add(new Paragraph("PRESCRIPTION MÉDICALE DE TRANSPORT")
                .setFontSize(14)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(12));

        // ===== SECTION 1: DONNÉES PATIENT =====
        addSectionTitle(document, "DONNÉES PATIENT");

        Table patientTable = new Table(2);
        patientTable.setWidth(UnitValue.createPercentValue(100));

        addDataRow(patientTable, "Nom Patient:", patient.getNom());
        addDataRow(patientTable, "Prénom Patient:", patient.getPrenom());
        addDataRow(patientTable, "NSS:", patient.getNss());
        addDataRow(patientTable, "Date de Naissance:", formatDate(patient.getDatenaiss()));
        addDataRow(patientTable, "Adresse:", patient.getAdresse());

        document.add(patientTable);
        document.add(new Paragraph(" ").setFontSize(6));

        // ===== SECTION 2: DONNÉES MÉDECIN =====
        addSectionTitle(document, "DONNÉES MÉDECIN");

        Table medecinTable = new Table(2);
        medecinTable.setWidth(UnitValue.createPercentValue(100));

        addDataRow(medecinTable, "Médecin:", medecin.getNom() != null ? medecin.getNom() : "");
        addDataRow(medecinTable, "Spécialité:", medecin.getSpecialite() != null ? medecin.getSpecialite() : "");
        addDataRow(medecinTable, "RPPS:", medecin.getRpps() != null ? medecin.getRpps() : "");
        addDataRow(medecinTable, "Date de Prescription:", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        document.add(medecinTable);
        document.add(new Paragraph(" ").setFontSize(6));

        // ===== SECTION 3: INFORMATIONS PRESCRIPTION =====
        addSectionTitle(document, "INFORMATIONS PRESCRIPTION");

        Table prescTable = new Table(2);
        prescTable.setWidth(UnitValue.createPercentValue(100));

        // Situation médicale
        String situationText = formData.getSituation1() != null && formData.getSituation1().length > 0
                ? formData.getSituation1()[0]
                : "";
        addDataRow(prescTable, "Situation Médicale:", situationText);

        // Date AT/MP
        if (formData.getDate_at_mp() != null && !formData.getDate_at_mp().isEmpty()) {
            addDataRow(prescTable, "Date à la Médecine de Travail:", formData.getDate_at_mp());
        }

        // Mode de transport
        addDataRow(prescTable, "Mode de Transport:",
                formData.getMode_transport() != null ? formData.getMode_transport() : "");

        // Type de véhicule
        addDataRow(prescTable, "Type de Véhicule:",
                formData.getVehiculeType() != null ? formData.getVehiculeType() : "");

        // Trajet - Lieu de Départ
        String lieuDepart = getAddressForLocation(formData.getTrajet_depart(),
                formData.getTrajet_depart_autre(), formData.getTrajet_depart_structure(), patient);
        addDataRow(prescTable, "Lieu de Départ:", lieuDepart);

        // Trajet - Lieu d'Arrivée
        String lieuArrivee = getAddressForLocation(formData.getTrajet_arrivee(),
                formData.getTrajet_arrivee_autre(), formData.getTrajet_arrivee_structure(), patient);
        addDataRow(prescTable, "Lieu d'Arrivée:", lieuArrivee);

        // Date du transport programmé
        if (formData.getDate_transport() != null && !formData.getDate_transport().isEmpty()) {
            addDataRow(prescTable, "Date du Transport Programmé:", formData.getDate_transport());
        }

        // Nombre de transports
        if (formData.getNombre_transports() != null) {
            addDataRow(prescTable, "Nombre de Transports:", formData.getNombre_transports().toString());
        }

        // Exonération
        String exoText = formData.getExoneration() != null && formData.getExoneration().length > 0
                ? formData.getExoneration()[0]
                : "";
        addDataRow(prescTable, "Exonération:", exoText);

        // Pension militaire
        String pensionText = formData.getPension_militaire() != null && formData.getPension_militaire().length > 0
                ? formData.getPension_militaire()[0]
                : "";
        addDataRow(prescTable, "Pension Militaire:", pensionText);

        document.add(prescTable);

        document.close();
        return baos.toByteArray();
    }

    private void addSectionTitle(Document doc, String title) {
        Paragraph titlePara = new Paragraph(title)
                .setFontSize(10)
                .setBold()
                .setPadding(5)
                .setMarginTop(8)
                .setMarginBottom(4);
        doc.add(titlePara);
    }

    private void addDataRow(Table table, String label, String value) {
        Cell labelCell = new Cell();
        labelCell.setBorder(new SolidBorder(1));
        labelCell.setPadding(6);
        labelCell.add(new Paragraph(label).setFontSize(9).setBold());
        table.addCell(labelCell);

        Cell valueCell = new Cell();
        valueCell.setBorder(new SolidBorder(1));
        valueCell.setPadding(6);
        valueCell.add(new Paragraph(value != null ? value : "").setFontSize(9));
        table.addCell(valueCell);
    }

    private String formatDate(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    private boolean contains(String[] array, String value) {
        if (array == null)
            return false;
        for (String item : array) {
            if (item != null && item.equals(value))
                return true;
        }
        return false;
    }

    private boolean isSelected(String value, String checkValue) {
        return value != null && value.equals(checkValue);
    }

    /**
     * Détermine l'adresse à afficher selon le type de lieu sélectionné
     */
    private String getAddressForLocation(String locationType, String autreAdresse, String structureAdresse,
            Patient patient) {
        if (locationType == null || locationType.isEmpty()) {
            return "";
        }

        switch (locationType) {
            case "domicile":
                return patient.getAdresse() != null ? patient.getAdresse() : "Domicile du patient";
            case "autre":
                return autreAdresse != null && !autreAdresse.isEmpty() ? autreAdresse : "Autre lieu";
            case "structure":
                return structureAdresse != null && !structureAdresse.isEmpty() ? structureAdresse
                        : "Structure de santé";
            case "hospital":
                return structureAdresse != null && !structureAdresse.isEmpty() ? structureAdresse
                        : "Hôpital";
            default:
                return locationType;
        }
    }

    /**
     * Classe helper pour les données du formulaire de prescription
     */
    public static class PrescriptionFormData {
        private String[] situation1;
        private String date_at_mp;
        private String mode_transport;
        private String vehiculeType;
        private String trajet_depart;
        private String trajet_depart_autre;
        private String trajet_depart_structure;
        private String trajet_arrivee;
        private String trajet_arrivee_autre;
        private String trajet_arrivee_structure;
        private Integer nombre_transports;
        private String date_transport;
        private String[] exoneration;
        private String[] pension_militaire;

        // Constructeurs
        public PrescriptionFormData() {
        }

        public PrescriptionFormData(String[] situation1, String date_at_mp, String mode_transport,
                String vehiculeType, String trajet_depart, String trajet_depart_autre,
                String trajet_depart_structure, String trajet_arrivee, String trajet_arrivee_autre,
                String trajet_arrivee_structure, Integer nombre_transports, String[] exoneration,
                String[] pension_militaire) {
            this.situation1 = situation1;
            this.date_at_mp = date_at_mp;
            this.mode_transport = mode_transport;
            this.vehiculeType = vehiculeType;
            this.trajet_depart = trajet_depart;
            this.trajet_depart_autre = trajet_depart_autre;
            this.trajet_depart_structure = trajet_depart_structure;
            this.trajet_arrivee = trajet_arrivee;
            this.trajet_arrivee_autre = trajet_arrivee_autre;
            this.trajet_arrivee_structure = trajet_arrivee_structure;
            this.nombre_transports = nombre_transports;
            this.exoneration = exoneration;
            this.pension_militaire = pension_militaire;
        }

        // Getters et Setters
        public String[] getSituation1() {
            return situation1;
        }

        public void setSituation1(String[] situation1) {
            this.situation1 = situation1;
        }

        public String getDate_at_mp() {
            return date_at_mp;
        }

        public void setDate_at_mp(String date_at_mp) {
            this.date_at_mp = date_at_mp;
        }

        public String getMode_transport() {
            return mode_transport;
        }

        public void setMode_transport(String mode_transport) {
            this.mode_transport = mode_transport;
        }

        public String getVehiculeType() {
            return vehiculeType;
        }

        public void setVehiculeType(String vehiculeType) {
            this.vehiculeType = vehiculeType;
        }

        public String getTrajet_depart() {
            return trajet_depart;
        }

        public void setTrajet_depart(String trajet_depart) {
            this.trajet_depart = trajet_depart;
        }

        public String getTrajet_depart_autre() {
            return trajet_depart_autre;
        }

        public void setTrajet_depart_autre(String trajet_depart_autre) {
            this.trajet_depart_autre = trajet_depart_autre;
        }

        public String getTrajet_depart_structure() {
            return trajet_depart_structure;
        }

        public void setTrajet_depart_structure(String trajet_depart_structure) {
            this.trajet_depart_structure = trajet_depart_structure;
        }

        public String getTrajet_arrivee() {
            return trajet_arrivee;
        }

        public void setTrajet_arrivee(String trajet_arrivee) {
            this.trajet_arrivee = trajet_arrivee;
        }

        public String getTrajet_arrivee_autre() {
            return trajet_arrivee_autre;
        }

        public void setTrajet_arrivee_autre(String trajet_arrivee_autre) {
            this.trajet_arrivee_autre = trajet_arrivee_autre;
        }

        public String getTrajet_arrivee_structure() {
            return trajet_arrivee_structure;
        }

        public void setTrajet_arrivee_structure(String trajet_arrivee_structure) {
            this.trajet_arrivee_structure = trajet_arrivee_structure;
        }

        public Integer getNombre_transports() {
            return nombre_transports;
        }

        public void setNombre_transports(Integer nombre_transports) {
            this.nombre_transports = nombre_transports;
        }

        public String[] getExoneration() {
            return exoneration;
        }

        public void setExoneration(String[] exoneration) {
            this.exoneration = exoneration;
        }

        public String[] getPension_militaire() {
            return pension_militaire;
        }

        public void setPension_militaire(String[] pension_militaire) {
            this.pension_militaire = pension_militaire;
        }

        public String getDate_transport() {
            return date_transport;
        }

        public void setDate_transport(String date_transport) {
            this.date_transport = date_transport;
        }
    }
}
