package com.careway.entity;

public enum  Statut {
    DEPART("Départ"),
    ARRIVE_HOPITAL("Arrivé Hôpital"),
    RDV_FINI("RDV Fini"),
    RETOUR_CHEZ_SOI("Retour chez soi");

    private String label;

    Statut(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
