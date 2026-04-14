package com.careway.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    // Liste des hôpitaux majeurs en France avec données officielles
    private static final List<String> HOSPITALS = Arrays.asList(
            // Île-de-France
            "Hôpital Cochin - 27 rue du Faubourg Saint-Jacques, 75014 Paris",
            "Hôpital Necker-Enfants Malades - 149 rue de Sèvres, 75015 Paris",
            "Hôpital Saint-Louis - 1 avenue Claude Vellefaux, 75010 Paris",
            "Hôpital de la Pitié-Salpêtrière - 47-83 boulevard de l'Hôpital, 75013 Paris",
            "Hôpital Tenon - 4 rue de la Chine, 75020 Paris",
            "Hôpital Lariboisière - 2 rue Ambroise Paré, 75010 Paris",
            "Hôpital Beaujon - 100 boulevard du Général Leclerc, 92110 Clichy",
            "Hôpital Percy - 101 avenue Henri Dunant, 92140 Clamart",
            "Hôpital de Bicêtre - 78-88 avenue de l'Hôpital, 94270 Le Kremlin-Bicêtre",
            "Hôpital Foch - 40 rue Worth, 92150 Suresnes",
            "Hôpital Européen Georges-Pompidou - 56 rue Leblanc, 75015 Paris",

            // Auvergne-Rhône-Alpes
            "CHU Lyon - Hôpital Édouard Herriot - 5 place d'Arsonval, 69003 Lyon",
            "CHU Lyon - Hôpital Louis Pradel - 28 avenue Doyen Lépine, 69500 Bron",
            "CHU Lyon - Hôpital Pierre Wertheimer - 59 boulevard Pinel, 69003 Lyon",
            "CHU Grenoble Alpes - Avenue Maquinaz, 38043 Grenoble",
            "CHU Saint-Étienne - Avenue Albert Raimond, 42270 Saint-Priest-en-Jarez",
            "CHU Clermont-Ferrand - 58 rue de Montalembert, 63000 Clermont-Ferrand",

            // Provence-Alpes-Côte d'Azur
            "CHU Marseille - Hôpital de la Timone - 264 rue Saint-Pierre, 13385 Marseille",
            "CHU Marseille - Hôpital Nord - Chemin des Bourrely, 13915 Marseille",
            "CHU Nice - Hôpital de l'Archet - 151 route Saint-Antoine de Ginestière, 06200 Nice",
            "CHU Nice - Hôpital Saint-Roch - rue Pierre Dévoluy, 06006 Nice",

            // Occitanie
            "CHU Toulouse - Hôpital de Rangueil - Avenue Jean Poulhès, 31059 Toulouse",
            "CHU Toulouse - Hôpital Larrey - 24 Chemin de Pouvourville, 31059 Toulouse",
            "CHU Toulouse - Hôpital Riquet - 80 rue Nagelmackers, 31059 Toulouse",
            "CHU Montpellier - Hôpital Saint-Éloi - 80 avenue Augustin Fliche, 34295 Montpellier",
            "CHU Montpellier - Hôpital Gui de Chauliac - 39 avenue Charles Flahault, 34295 Montpellier",
            "CHU Montpellier - Hôpital Lapeyronie - 371 avenue du Doyen Gaston Giraud, 34095 Montpellier",

            // Bordeaux
            "CHU Bordeaux - Hôpital Gaston Broca - 33 rue Broca, 33000 Bordeaux",
            "CHU Bordeaux - Hôpital Saint-André - 1 rue Jean Burguet, 33075 Bordeaux",
            "CHU Bordeaux - Hôpital Pellegrin - Place Amélie Raba-Léon, 33076 Bordeaux",
            "CHU Bordeaux - Hôpital Cardiologique - Avenue de Magellan, 33604 Pessac",

            // Normandie
            "CHU Rouen - Hôpital Charles Nicolle - 1 rue de Germont, 76031 Rouen",
            "CHU Rouen - Hôpital de Mont-Saint-Aignan - Mont-Saint-Aignan, 76000 Rouen",
            "CHU Caen - CHU Côte de Nacre - Avenue de la Côte de Nacre, 14000 Caen",

            // Pays de la Loire
            "CHU Nantes - Hôpital de l'Hôtel Dieu - 1 place Alexis Ricordeau, 44093 Nantes",
            "CHU Nantes - Hôpital Laënnec - Boulevard Jacques Monod, 44093 Nantes",
            "CHU Angers - Hôpital Larrey - 4 rue Larrey, 49033 Angers",
            "CHU Angers - Hôpital d'Angers - 4 rue Larrey, 49033 Angers",

            // Bretagne
            "CHU Rennes - Hôpital Pontchaillou - 2 rue Henri le Guilloux, 35033 Rennes",
            "CHU Brest - 5 avenue Foch, 29200 Brest",

            // Bourgogne-Franche-Comté
            "CHU Dijon - 2 rue Angélique Ducoudray, 21000 Dijon",
            "CHU Besançon - 2 place Saint-Jacquesn, 25030 Besançon",

            // Nouvelle-Aquitaine
            "CHU Poitiers - Hôpital Jean-Bernard - 40 rue de la Marne, 86021 Poitiers",
            "CHU Poitiers - Hôpital Milétrie - 2 rue de la Milétrie, 86000 Poitiers",
            "CHU Limoges - Dupuytren - 2 avenue Martin Luther King, 87042 Limoges",

            // Centre-Val de Loire
            "CHU Tours - Hôpital Clocheville - 49 boulevard Béranger, 37044 Tours",
            "CHU Tours - Hôpital Bretonneau - 2 boulevard Tonnellé, 37044 Tours",
            "CHU Orléans - 1 rue Porte Madeleine, 45067 Orléans",

            // Hauts-de-France
            "CHU Lille - Hôpital Cardiologique - Boulevard de Belfort, 59000 Lille",
            "CHU Lille - Hôpital Gériatrique - Rue Michel Kulmann, 59000 Lille",
            "CHU Lille - Hôpital Roger Salengro - Rue André Verhaeghe, 59037 Lille",
            "CHU Amiens - 3 avenue Laennec, 80054 Amiens",

            // Alsace
            "CHU Strasbourg - Hôpital de Hautepierre - Avenue Molière, 67098 Strasbourg",
            "CHU Strasbourg - Hôpital Civil - 1 place de l'Hôpital, 67091 Strasbourg",
            "CHU Mulhouse - 20 avenue du Docteur Alfred Kastler, 68100 Mulhouse",

            // Corse
            "CHU Bastia - Quartier Lupino, 20600 Bastia",
            "CHU Ajaccio - Boulevard Lantivy, 20000 Ajaccio",

            // Réunion
            "CHU La Réunion - Allée des Topazes, 97410 Saint-Benoit",

            // Guadeloupe
            "CHU Guadeloupe - Abymes - Boulevard de la Verdure, 97139 Abymes",

            // Martinique
            "CHU Martinique - Fort-de-France - Rue Félix Eboué, 97261 Fort-de-France",

            // Guyane
            "CHU Guyane - Cayenne - Avenue des Flamboyants, 97306 Cayenne");

    public List<String> getAllHospitals() {
        return HOSPITALS;
    }

    public List<String> searchHospitals(String query) {
        if (query == null || query.trim().isEmpty()) {
            return HOSPITALS;
        }

        String searchTerm = query.toLowerCase().trim();
        return HOSPITALS.stream()
                .filter(hospital -> hospital.toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }
}
