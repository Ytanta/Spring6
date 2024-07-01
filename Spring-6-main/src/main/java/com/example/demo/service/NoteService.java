package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * NoteService - служебный класс, обеспечивающий функционирование сущности "Заметки" в соответствии с предназначением.
 */
@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    /**
     * addNote - метод добавления новой заметки в ежедневник (базу данных)
     *
     * @param note - заметка.
     * @return - сохранение заметки в БД.
     */
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    /**
     * getAllNotes - метод выдачи всех заметок.
     *
     * @return - список записей, хранящихся в базе данных.
     */
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * getNoteById - метод поиска заметки по ее номеру.
     *
     * @param id - номер заметки.
     * @return - заметку или null (если ее нет).
     */
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    /**
     * updateNote - метод обновления заметки
     *
     * @param note - заметка.
     * @return список сохраненных заметок.
     */
    public Note updateNote(Note note) {
        Note noteById = getNoteById(note.getId());
        noteById.setDescription(note.getDescription());
        noteById.setTitle(note.getTitle());
        noteById.setLocal_date_time(LocalDateTime.now());
        return noteRepository.save(noteById);
    }

    /**
     * deleteNoteById - удаление заметки по ее номеру (id).
     *
     * @param id - номер заметки.
     */
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }


    /**
     * updateStatusTask - метод обновления задачи.
     *
     * @param id     - номер задачи.
     * @param status - статус задачи.
     * @return список сохраненных задач или сообщение об исключении.
     */
    public Note updateNoteStatus(Long id, Note.Status status) {
        Optional<Note> optionalTask = noteRepository.findById(id);
        if (optionalTask.isPresent()) {
            Note task = optionalTask.get();
            task.setStatus(status);
            return noteRepository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    /**
     * getTasksByStatus - метод выдачи сведений о задачах, имеющих определенный статус.
     *
     * @param status - статус задачи.
     * @return - сведения о задачах с запрошенным статусом.
     */
    public List<Note> getNoteByStatus(Note.Status status) {
        return noteRepository.getNoteByStatus(status);
    }
}