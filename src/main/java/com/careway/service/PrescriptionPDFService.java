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
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.colors.ColorConstants;

@Service
public class PrescriptionPDFService {

    /**
     * Génère un PDF de prescription en remplissant une ordonnance de transport
     * 
     * @param patient  Données du patient
     * @param medecin  Données du médecin
     * @param formData Données du formulaire de prescription
     * @return Bytes du PDF généré
     */
    public byte[] generatePrescriptionPDF(Patient patient, Medecin medecin, PrescriptionFormData formData)
            throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Police standard
        PdfFont font = PdfFontFactory.createFont();
        PdfFont boldFont = PdfFontFactory.createFont();

        // En-tête du document
        document.add(new Paragraph("PRESCRIPTION DE TRANSPORT MÉDICAL")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(14)
                .setBold());

        document.add(new Paragraph("\n"));

        // Section Informations Patient
        document.add(createSectionTitle("DONNÉES PATIENT", boldFont, font));

        Table patientTable = new Table(2);
        patientTable.setWidth(UnitValue.createPercentValue(100));

        addTableRow(patientTable, "Nom Patient:", patient.getNom(), boldFont, font);
        addTableRow(patientTable, "Prénom Patient:", patient.getPrenom(), boldFont, font);
        addTableRow(patientTable, "NSS:", patient.getNss(), boldFont, font);
        addTableRow(patientTable, "Date de Naissance:", formatDate(patient.getDatenaiss()), boldFont, font);
        addTableRow(patientTable, "Maladie/Pathologie:", patient.getMaladie(), boldFont, font);
        addTableRow(patientTable, "Téléphone:", patient.getTel(), boldFont, font);
        addTableRow(patientTable, "Adresse:", patient.getAdresse(), boldFont, font);

        document.add(patientTable);
        document.add(new Paragraph("\n"));

        // Section Informations Médecin
        document.add(createSectionTitle("DONNÉES MÉDECIN", boldFont, font));

        Table medecinTable = new Table(2);
        medecinTable.setWidth(UnitValue.createPercentValue(100));

        addTableRow(medecinTable, "Médecin:", medecin.getNom(), boldFont, font);
        addTableRow(medecinTable, "Spécialité:", medecin.getSpecialite(), boldFont, font);
        addTableRow(medecinTable, "RPPS:", medecin.getRpps(), boldFont, font);
        addTableRow(medecinTable, "Date de Prescription:", formatDate(new Date()), boldFont, font);

        document.add(medecinTable);
        document.add(new Paragraph("\n"));

        // Section Motif et type de transport
        document.add(createSectionTitle("INFORMATIONS PRESCRIPTION", boldFont, font));

        Table prescriptionTable = new Table(2);
        prescriptionTable.setWidth(UnitValue.createPercentValue(100));

        addTableRow(prescriptionTable, "Situation Médicale:", formatArray(formData.getSituation1()), boldFont, font);
        addTableRow(prescriptionTable, "Date à la Médecine de Travail:", formData.getDate_at_mp(), boldFont, font);
        addTableRow(prescriptionTable, "Mode de Transport:", formData.getMode_transport(), boldFont, font);
        addTableRow(prescriptionTable, "Lieu de Départ:", formData.getTrajet_depart(), boldFont, font);
        addTableRow(prescriptionTable, "Lieu d'Arrivée:", formData.getTrajet_arrivee(), boldFont, font);
        addTableRow(prescriptionTable, "Nombre de Transports:", String.valueOf(formData.getNombre_transports()),
                boldFont, font);
        addTableRow(prescriptionTable, "Exonération:", formatArray(formData.getExoneration()), boldFont, font);
        addTableRow(prescriptionTable, "Pension Militaire:", formatArray(formData.getPension_militaire()), boldFont,
                font);

        document.add(prescriptionTable);
        document.add(new Paragraph("\n"));

        // Pied de page avec informations légales
        document.add(new Paragraph("Document généré automatiquement le " + formatDate(new Date()))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10)
                .setFontColor(ColorConstants.GRAY));

        document.close();

        return baos.toByteArray();
    }

    /**
     * Crée une cellule de titre de section
     */
    private Paragraph createSectionTitle(String text, PdfFont boldFont, PdfFont font) {
        return new Paragraph(text)
                .setBold()
                .setFontSize(12)
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setMarginBottom(10);
    }

    /**
     * Ajoute une ligne à une table avec label et valeur
     */
    private void addTableRow(Table table, String label, String value, PdfFont boldFont, PdfFont font) {
        Cell labelCell = new Cell().add(new Paragraph(label).setBold());
        labelCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(labelCell);

        Cell valueCell = new Cell().add(new Paragraph(value != null ? value : "N/A"));
        table.addCell(valueCell);
    }

    /**
     * Formate une date au format dd/MM/yyyy
     */
    private String formatDate(Date date) {
        if (date == null)
            return "N/A";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    /**
     * Formate un array en chaîne séparée par des virgules
     */
    private String formatArray(String[] array) {
        if (array == null || array.length == 0)
            return "Aucune";
        return String.join(", ", array);
    }

    /**
     * Classe helper pour les données du formulaire de prescription
     */
    public static class PrescriptionFormData {
        private String[] situation1;
        private String date_at_mp;
        private String mode_transport;
        private String trajet_depart;
        private String trajet_depart_autre;
        private String trajet_depart_structure;
        private String trajet_arrivee;
        private String trajet_arrivee_autre;
        private String trajet_arrivee_structure;
        private Integer nombre_transports;
        private String[] exoneration;
        private String[] pension_militaire;

        // Constructeurs
        public PrescriptionFormData() {
        }

        public PrescriptionFormData(String[] situation1, String date_at_mp, String mode_transport,
                String trajet_depart, String trajet_depart_autre, String trajet_depart_structure,
                String trajet_arrivee, String trajet_arrivee_autre, String trajet_arrivee_structure,
                Integer nombre_transports, String[] exoneration, String[] pension_militaire) {
            this.situation1 = situation1;
            this.date_at_mp = date_at_mp;
            this.mode_transport = mode_transport;
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
    }
}
