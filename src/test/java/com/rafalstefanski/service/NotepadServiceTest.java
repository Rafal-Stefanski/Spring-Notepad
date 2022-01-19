package com.rafalstefanski.service;

import com.rafalstefanski.model.Note;
import com.rafalstefanski.repository.NotepadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//@SpringBootTest
//@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class NotepadServiceTest {
    @Mock
    private NotepadRepo notepadRepo;
    @InjectMocks
    private NotepadServiceImpl notepadService;

    private List<Note> testNotesList() {
        return Arrays.asList(
                new Note(LocalDate.now(), "Instruction Note", "How to use notepad"),
                new Note(LocalDate.now(), "Used technologies", "Whole app is created in Spring boot 2 using Java 8."),
                new Note(LocalDate.now(), "Just third Note", "With some text to be here, lorem ipsum.")
        );
    }

    @Test
    @DisplayName("Should return all notes from the list")
    void shouldFindAll() {
        // given
//        List<Note> testNotes = testNotesList();
        given(notepadRepo.findAll()).willReturn(testNotesList());
        // when
        List<Note> actual = notepadService.findAll();
        // then
        Assertions.assertEquals(3, actual.size());
        verify(notepadRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find one note by Id")
    void shouldFindById() {
        // given
        Note anotherNote = new Note(LocalDate.now(), "Used technologies", "Whole app is created in Spring boot 2 using Java 8.");
        anotherNote.setId(4L);
        // when
        when(notepadRepo.getById(any())).thenReturn(anotherNote);
        // then
        Optional<Note> actual = notepadService.findById(4L);
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(4L, actual.get().getId());
        verify(notepadRepo, times(1)).getById(any());
    }

    @Test
    @DisplayName("Should save Note")
    void shouldSaveNote() {
        // given
        Note addedNote = new Note(LocalDate.now(), "Freshly added Note", "With freshly added description");
        // when
        when(notepadRepo.save(any(Note.class))).thenReturn(addedNote);
        // then
        Note actual = notepadService.saveNote(addedNote);
        Assertions.assertSame(addedNote, actual);
        Assertions.assertEquals("Freshly added Note", actual.getTitle());
        verify(notepadRepo, times(1)).save(addedNote);
    }

    @Test
    @DisplayName("Should delete selected Note by Id")
    void shouldDeleteById() {
        // given
        Note addedNote = new Note(LocalDate.now(), "Freshly added Note", "With freshly added description");
        addedNote.setId(4L);
        // when
        notepadService.deleteById(addedNote.getId());
        // then
        verify(notepadRepo, times(1)).deleteById(4L);
    }
}
