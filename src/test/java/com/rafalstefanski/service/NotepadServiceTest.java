package com.rafalstefanski.service;

import com.rafalstefanski.model.Note;
import com.rafalstefanski.repository.NotepadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class NotepadServiceTest {

    private NotepadService notepadService; // = new NotepadServiceImpl(notepadRepo);

    @BeforeEach
    void setUpRepository() {
        // given
        NotepadRepo notepadRepo = mock(NotepadRepo.class);
        Note initialNote = new Note(LocalDate.now(), "Instruction Note", "How to use notepad");
        Note anotherNote = new Note(LocalDate.now(), "Used technologies", "Whole app is created in Spring boot 2 using Java 8.");
        Note thirdNote = new Note(LocalDate.now(), "Just third Note", "With some text to be here, lorem ipsum.");
        List<Note> AllNotes = Arrays.asList(initialNote, anotherNote, thirdNote);
        doReturn(AllNotes).when(notepadRepo).findAll();

        notepadService = new NotepadServiceImpl(notepadRepo);
    }

    @Test
    @DisplayName("Should return all notes from the list")
    void shouldFindAll() {
        // when
        List<Note> actual = notepadService.findAll();
        // then
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    @DisplayName("Should find one note by Id")
    void shouldFindById() {
    }


    @Test
    @DisplayName("Should save Note")
    void shouldSaveNote() {
    }

    @Test
    void deleteById() {
    }
}
