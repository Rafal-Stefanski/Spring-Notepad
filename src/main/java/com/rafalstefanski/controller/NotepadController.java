package com.rafalstefanski.controller;

import com.rafalstefanski.mapping.NotepadMapping;
import com.rafalstefanski.model.Note;
import com.rafalstefanski.repository.NotepadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Optional;

@Controller
//@RequestMapping("/notepad")
public class NotepadController {
    private final NotepadRepo notepadRepo;

    @Autowired
    public NotepadController(NotepadRepo notepadRepo) {
        this.notepadRepo = notepadRepo;
    }

    @GetMapping
    public String getNotes(Model model) {
        model.addAttribute("notepadNotes", notepadRepo.findAll());
        model.addAttribute("newNote", new Note());
        return NotepadMapping.HOME;
    }

    @GetMapping("/edit/{id}")
    public String editNote(@PathVariable long id, Model model) {
        Optional<Note> noteById = notepadRepo.findById(id);
        if (noteById.isPresent()) {
            model.addAttribute("note2Edit", noteById.get());
            return NotepadMapping.NOTE_EDIT;
        }
        return NotepadMapping.REDIRECT_HOME;
    }

    @PostMapping("/modify")
    public String modifyNote(Note noteToSave) {
        Optional<Note> moddedNote = notepadRepo.findById(noteToSave.getId());
        if (moddedNote.isPresent()) {
            noteToSave.setDate(LocalDate.now());
            notepadRepo.save(noteToSave);
            return NotepadMapping.REDIRECT_HOME;
        }
        return NotepadMapping.NOTE_ERROR;
    }

    @PostMapping("/add")
    public String addNote(Note noteToAdd) {
        noteToAdd.setDate(LocalDate.now());
        notepadRepo.save(noteToAdd);
        return NotepadMapping.REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable long id) {
        Optional<Note> noteToDelete = notepadRepo.findById(id);
        if (noteToDelete.isPresent()) {
            notepadRepo.deleteById(id);
        }
        return NotepadMapping.REDIRECT_HOME;
    }

    @GetMapping("/error")
    public String error() {
        return NotepadMapping.NOTE_ERROR;
    }
}
