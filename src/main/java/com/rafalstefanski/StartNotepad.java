package com.rafalstefanski;

import com.rafalstefanski.model.Note;
import com.rafalstefanski.repository.NotepadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StartNotepad {

    private final NotepadRepo notepadRepo;

    @Autowired
    public StartNotepad(NotepadRepo notepadRepo) {
        this.notepadRepo = notepadRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Note initialNote = new Note(LocalDate.now(), "Instruction Note", "How to use notepad: " +
                " First field contains form for adding new notes to the list, the newest appear at the bottom, each note can be edited or removed from the list.");
        Note anotherNote = new Note(LocalDate.now(), "Used technologies", "Whole app is created in Spring boot 2 using Java 8. Notes are added, removed or edited in MySql Database, through JPA Hibernate.");


        notepadRepo.save(initialNote);
        notepadRepo.save(anotherNote);
    }
}
