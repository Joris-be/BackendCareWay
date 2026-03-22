package com.careway.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careway.dao.NoteRepository;
import com.careway.entity.Note;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // Récupérer toutes les notes
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Récupérer une note par son id
    public Note getNoteById(Integer id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note non trouvée avec l'id : " + id));
    }

    // Créer ou modifier une note
    public Note saveNote(Note note) {
        if (note.getNombreetoiles() < 0 || note.getNombreetoiles() > 5
                || (note.getNombreetoiles() * 2) % 1 != 0) {
            throw new IllegalArgumentException("La note doit être entre 0 et 5 par pas de 0.5");
        }
        return noteRepository.save(note);
    }

    // Supprimer une note
    public void deleteNote(Integer id) {
        noteRepository.deleteById(id);
    }
}