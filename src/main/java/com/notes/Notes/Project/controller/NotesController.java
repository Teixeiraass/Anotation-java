package com.notes.Notes.Project.controller;

import com.notes.Notes.Project.model.notes.Notes;
import com.notes.Notes.Project.model.user.User;
import com.notes.Notes.Project.service.AuthorizationService;
import com.notes.Notes.Project.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    @Autowired
    private NotesService service;

    @Autowired
    private AuthorizationService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Notes> findAll(){
        return service.findAll();
    }

    @GetMapping("/usuario/{email}")
    public List<Notes> obterNotasPorUsuario(@PathVariable String email) {
        UserDetails usuario = userService.loadUserByUsername(email);
        return service.getNotesByUser(usuario);
    }

    @GetMapping(value = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Notes findById(@PathVariable(value = "id") Long id) throws Exception {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Notes create(@RequestBody Notes notes, Principal principal){
        return service.create(notes, principal).getBody();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Notes update(@RequestBody Notes notes){
        return service.update(notes);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
