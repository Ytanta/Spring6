package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * NoteController - класс, обрабатывающий входящие HTTP-запросы и возвращают ответы по заметкам в "Записной книжке".
 */
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    /**
     * getAllNotes - метод запроса всех заметок из записной книжки.
     *
     * @return - список задач.
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    /**
     * createNote - метод добавления новых заметок.
     *
     * @param note - заметка.
     * @return - обновленный список.
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.addNote(note), HttpStatus.CREATED);
    }

    /**
     * getNoteById - метод получения заметки по ее номеру.
     *
     * @param id - номер заметки.
     * @return - запись в ежедневнике.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) {
        Note noteById;
        try {
            noteById = noteService.getNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(noteById, HttpStatus.OK);
    }

    /**
     * updateTaskStatus - метод обновления задачи в БД, позволяющий менять статус или описание.
     *
     * @param id     - номер задачи.
     * @param status - статус.
     * @return - обновленные данные.
     */
    @PutMapping("/{id}")
    public Note updateNoteStatus(@PathVariable Long id, @RequestBody Note.Status status) {
        return noteService.updateNoteStatus(id, status);
    }

    /**
     * getTasksByStatus - метод поиска задачи по ее статусу.
     *
     * @param status - статус задачи.
     * @return - характеристики задач, имеющих запрошенный статус.
     */
    @GetMapping("/status/{status}")
    public List<Note> getNoteByStatus(@PathVariable Note.Status status) {
        return noteService.getNoteByStatus(status);
    }



    /**
     * updateNote - метод обновления задачи в БД, позволяющий менять статус или описание.
     *
     * @param note - заметка.
     * @return - обновленные данные.
     */
    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.OK);
    }

    /**
     * deleteNote - удаление заметки по ее номеру.
     *
     * @param id - номер заметки.
     * @return - удаление заметки из БД.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.ok().build();
    }
}