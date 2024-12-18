package ru.yandex.javacourse.bondarenko.schedule.test.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.javacourse.bondarenko.schedule.manager.*;
import ru.yandex.javacourse.bondarenko.schedule.task.*;

import java.util.List;

class InMemoryTaskManagerTest {

    private InMemoryTaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    public void testAddTaskUpdatesHistory() {
        Task task = new Task(1, "Задача 1", "Описание задачи 1", TaskStatus.NEW);
        taskManager.createTask(task);

        List<Task> history = taskManager.getHistory(); //
        assertEquals(1, history.size(), "История должны иметь одну задачу после добавления");
        assertEquals(task, history.get(0), "Задача должна быть добавлена в историю");
    }

    @Test
    public void testGetTaskUpdatesHistory() {
        Task task = new Task(1, "Задача 1", "Описание задачи 1", TaskStatus.NEW);
        taskManager.createTask(task);
        taskManager.getTaskById(1);

        List<Task> history = taskManager.getHistory();
        assertEquals(1, history.size(), "При получении задачи история обновилась");
        assertEquals(task, history.get(0), "Полученная задача должна быть в истории");
    }

    @Test
    public void testTaskManipulations() {
        Task task1 = new Task(1, "Задача 1", "Описание задачи 1", TaskStatus.NEW);
        Task task2 = new Task(2, "Задача 2", "Описание задачи 2", TaskStatus.NEW);
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        taskManager.getTaskById(1);
        taskManager.getTaskById(2);

        List<Task> history = taskManager.getHistory();
        assertEquals(2, history.size(), "История содержит обе задачи");
        assertTrue(history.contains(task1), "В истории должна быть задача 1");
        assertTrue(history.contains(task2), "В истории должна быть задача 2");
    }
}