package com.careway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.careway.dao.NoteRepository;
import com.careway.dto.NoteDTO;
import com.careway.entity.Note;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
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
    public NoteDTO saveNote(Note note) {
        if (note.getNombreetoiles() < 0 || note.getNombreetoiles() > 5
                || (note.getNombreetoiles() * 2) % 1 != 0) {
            throw new IllegalArgumentException("La note doit être entre 0 et 5 par pas de 0.5");
        }
        return toDTO(noteRepository.save(note));
    }

    // Supprimer une note
    public void deleteNote(Integer id) {
        noteRepository.deleteById(id);
    }
}