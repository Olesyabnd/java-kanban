package ru.yandex.javacourse.bondarenko.schedule.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import ru.yandex.javacourse.bondarenko.schedule.manager.*;
import ru.yandex.javacourse.bondarenko.schedule.task.*;

import java.util.List;

class HistoryManagerTest {

    private HistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void testAddTaskToHistory() {
        Task task = new Task(1, "Задача 1", "Описание задачи 1", TaskStatus.NEW);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "В истории должна быть одна задача");
        assertEquals(task, history.getFirst(), "Задача должна быть в истории");
    }

    @Test
    void testHistoryLimit() {
        for (int i = 0; i < 12; i++) {
            historyManager.add(new Task(i, "Задача " + i, "Описание задачи " + i, TaskStatus.NEW));
        }

        List<Task> history = historyManager.getHistory();
        assertEquals(10, history.size(), "В истории может быть только 10 задач");
    }

    @Test
    void testGetHistory() {
        Task task1 = new Task(1, "Задача 1", "Описание задачи 1", TaskStatus.NEW);
        Task task2 = new Task(2, "Задача 2", "Описание задачи 2", TaskStatus.NEW);
        historyManager.add(task1);
        historyManager.add(task2);

        List<Task> history = historyManager.getHistory();
        assertTrue(history.contains(task1), "Задача 1 в истории");
        assertTrue(history.contains(task2), "Задача 2 в истории");

    }
}