package com.notes.Notes.Project.service;

import com.notes.Notes.Project.model.notes.Notes;
import com.notes.Notes.Project.model.user.User;
import com.notes.Notes.Project.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Service
public class NotesService {
    @Autowired
    private NotesRepository repository;

    @Autowired
    private AuthorizationService userService;

    public List<Notes> findAll(){
        return repository.findAll();
    }

    public List<Notes> getNotesByUser(UserDetails user){
        return repository.findByUser(user);
    }

    public Notes findById(Long id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new Exception("Não foi possivel encontrar a nota"));
    }

    public ResponseEntity<Notes> create(@RequestBody Notes notes, Principal principal){
        UserDetails user = userService.loadUserByUsername(principal.getName());

        Notes note = new Notes();
        note.setTitle(notes.getTitle());
        note.setContent(notes.getContent());
        note.setDescription(notes.getDescription());
        note.setNotesType(notes.getNotesType());
        note.setUser((User) user);

        Notes createdNote = repository.save(note);
        return ResponseEntity.ok(createdNote);
    }

    public Notes update(Notes notes){

        var entity = repository.findById(notes.getId()).orElseThrow();
        entity.setTitle(notes.getTitle());
        entity.setDescription(notes.getDescription());
        entity.setContent(notes.getContent());
        entity.setNotesType(notes.getNotesType());

        return repository.save(notes);
    }

    public void delete(Long id)throws Exception{
        var entity = repository.findById(id).orElseThrow(() -> new Exception("Não foi possivel encontrar a nota"));
        repository.delete(entity);
    }

}
