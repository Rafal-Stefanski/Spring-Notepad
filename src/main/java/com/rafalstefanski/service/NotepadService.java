package com.rafalstefanski.service;

import com.rafalstefanski.model.Note;

import java.util.List;
import java.util.Optional;

public interface NotepadService {
    List<Note> findAll();

    Optional<Note> findById(long id);

    Note saveNote(Note note);

    void deleteById(long id);
}
