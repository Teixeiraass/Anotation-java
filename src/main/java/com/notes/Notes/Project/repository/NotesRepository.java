package com.notes.Notes.Project.repository;

import com.notes.Notes.Project.model.notes.Notes;
import com.notes.Notes.Project.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByUser(UserDetails user);
}
