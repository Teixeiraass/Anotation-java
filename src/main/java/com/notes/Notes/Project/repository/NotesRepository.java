package com.notes.Notes.Project.repository;

import com.notes.Notes.Project.model.notes.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
}
