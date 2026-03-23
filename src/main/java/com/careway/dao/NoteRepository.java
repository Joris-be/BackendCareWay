package com.careway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careway.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>{
    
    //a integre ds mes transport pas encore fait mais on devra faire al modif dessus et pr chaque transport terminé en vert on pourra note le transport et trtansporteur et il y aura un filtrage
}
