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

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Integer id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note non trouvée"));
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNote(Integer id) {
        noteRepository.deleteById(id);
    }
}
