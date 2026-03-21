package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>{
    
}
