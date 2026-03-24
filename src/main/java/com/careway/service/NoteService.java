package com.careway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.careway.dao.NoteRepository;
import com.careway.dao.PatientRepository;
import com.careway.dao.TransporteurRepository;
import com.careway.dto.NoteDTO;
import com.careway.entity.Note;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final PatientRepository patientRepository;
    private final TransporteurRepository transporteurRepository;

    public NoteService(NoteRepository noteRepository,
                       PatientRepository patientRepository,
                       TransporteurRepository transporteurRepository) {
        this.noteRepository = noteRepository;
        this.patientRepository = patientRepository;
        this.transporteurRepository = transporteurRepository;
    }

    // Convertir Note en NoteDTO
    private NoteDTO toDTO(Note note) {
        NoteDTO dto = new NoteDTO();
        dto.setIdnote(note.getIdnote());
        dto.setNombreetoiles(note.getNombreetoiles());
        dto.setIdpatient(note.getPatient() != null ? note.getPatient().getIdpatient() : null);
        dto.setIdtransporteur(note.getTransporteur() != null ? note.getTransporteur().getIdtransporteur() : null);
        return dto;
    }

    // Récupérer toutes les notes
    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer une note par son id
    public NoteDTO getNoteById(Integer id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note non trouvée avec l'id : " + id));
        return toDTO(note);
    }

    // Créer ou modifier une note
    public NoteDTO saveNote(NoteDTO dto) {
        if (dto.getNombreetoiles() < 0 || dto.getNombreetoiles() > 5
                || (dto.getNombreetoiles() * 2) % 1 != 0) {
            throw new IllegalArgumentException("La note doit être entre 0 et 5 par pas de 0.5");
        }

        Note note = new Note();
        note.setIdnote(dto.getIdnote());
        note.setNombreetoiles(dto.getNombreetoiles());

        if (dto.getIdpatient() != null) {
            note.setPatient(patientRepository.findById(dto.getIdpatient())
                    .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id : " + dto.getIdpatient())));
        }
        if (dto.getIdtransporteur() != null) {
            note.setTransporteur(transporteurRepository.findById(dto.getIdtransporteur())
                    .orElseThrow(() -> new RuntimeException("Transporteur non trouvé avec l'id : " + dto.getIdtransporteur())));
        }

        return toDTO(noteRepository.save(note));
    }

    // Supprimer une note
    public void deleteNote(Integer id) {
        noteRepository.deleteById(id);
    }
}