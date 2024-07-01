package com.example.demo.repository;

import com.example.demo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TaskRepository расширяет интерфейс JpaRepository, предоставляя готовые методы для основных операций CRUD
 * (Create, Read, Update, Delete) над сущностями.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    /**
     * getTasksByStatus - метод позволяет получить список задач с определенным статусом.
     * Он принимает в качестве аргумента объект enum и возвращает список заметок,
     * у которых статус совпадает с заданным.
     *
     * @param status - статус заметки.
     * @return список заметок, имеющих запрошенный статус.
     */
    List<Note> getNoteByStatus(Note.Status status);
}