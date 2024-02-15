package com.notes.Notes.Project.service;

import com.notes.Notes.Project.model.Notes;
import com.notes.Notes.Project.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    @Autowired
    private NotesRepository repository;

    public List<Notes> findAll(){
        return repository.findAll();
    }

    public Notes findById(Long id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new Exception("Não foi possivel encontrar a nota"));
    }

    public Notes create(Notes notes){
        return repository.save(notes);
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
