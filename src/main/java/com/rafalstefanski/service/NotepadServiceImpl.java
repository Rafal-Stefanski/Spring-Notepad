package com.rafalstefanski.service;

import com.rafalstefanski.model.Note;
import com.rafalstefanski.repository.NotepadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotepadServiceImpl implements NotepadService{
    private final NotepadRepo notepadRepo;

    @Autowired
    public NotepadServiceImpl(NotepadRepo notepadRepo) {
        this.notepadRepo = notepadRepo;
    }

    @Override
    public List<Note> findAll() {
        return notepadRepo.findAll();
    }

    @Override
    public Optional<Note> findById(long id) {
        return Optional.of(notepadRepo.getById(id));
    }

    @Override
    public Note saveNote(Note note) {
        return notepadRepo.save(note);
    }

    @Override
    public void deleteById(long id) {
        notepadRepo.deleteById(id);
    }
}
