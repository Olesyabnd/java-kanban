package ru.yandex.javacourse.bondarenko.schedule.test.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import ru.yandex.javacourse.bondarenko.schedule.manager.*;
import ru.yandex.javacourse.bondarenko.schedule.task.*;

import java.util.List;

class TaskManagerTest {
    private TaskManager taskManager;
    private HistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        taskManager = new InMemoryTaskManager();
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    public void testTaskEqualityById() {
        Task task1 = new Task(1, "Задача 1", "Описание задачи 1", TaskStatus.NEW);
        Task task2 = new Task(1, "Задача 2", "Описание задачи 2", TaskStatus.NEW);
        assertEquals(task1, task2, "Две задачи равны, если у них одинаковый ID");
    }

    @Test
    public void testEpicEqualityById() {
        Epic epic1 = new Epic(1, "Эпик 1", "Описание эпика 1");
        Epic epic2 = new Epic(1, "Эпик 2", "Описание эпика 2");
        assertEquals(epic1, epic2, "Два эпика равны, если у них одинаковый ID");
    }

    @Test
    public void testSubtaskCannotAddItselfAsChild() {
        Epic epic = new Epic(1, "Эпик 1", "Описание эпика 1");
        Subtask subtask = new Subtask(2, "Подзадача 1", "Описание подзадачи", TaskStatus.NEW, epic.getId());
        subtask.setEpicId(subtask.getId());
        taskManager.createSubtask(subtask);
        assertNotEquals(subtask.getEpicId(), subtask.getId(), "Подзадачу нельзя сделать своим эпиком");
    }

    @Test
    public void testTaskManagerInitialization() {
        assertNotNull(taskManager, "TaskManager инициализирован правильно");
        assertNotNull(historyManager, "HistoryManager инициализирован правильно");
    }

    @Test
    public void testInMemoryTaskManagerCanAddDifferentTaskTypes() {
        Task simpleTask = new Task(1, "Задача", "Описание задачи", TaskStatus.NEW);
        Epic epic = new Epic(2, "Эпик", "Описание эпика");
        Subtask subtask = new Subtask(3, "Подзадача", "Описание подзчадачи", TaskStatus.NEW, epic.getId());

        taskManager.createTask(simpleTask);
        taskManager.createEpic(epic);
        taskManager.createSubtask(subtask);

        assertEquals(simpleTask, taskManager.getTaskById(1), "Задача получена по ID");
        assertEquals(epic, taskManager.getEpicById(2), "Эпик получен по ID");
        assertEquals(subtask, taskManager.getSubtaskById(3), "Подзадача получена по ID");
    }

    @Test
    public void testNoIdConflict() {
        Task task1 = new Task(1, "Задача 1", "Описание задачи 1", TaskStatus.NEW);
        Task task2 = new Task(2, "Задача 2", "Описание задачи 2", TaskStatus.NEW);

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        assertNotEquals(task1.getId(), task2.getId(), "Задачи с разными ID не конфликтуют");
    }

    @Test
    public void testTaskImmutabilityOnAdding() {
        Task task = new Task(1, "Задача", "Описание задачи", TaskStatus.NEW);
        taskManager.createTask(task);

        Task retrievedTask = taskManager.getTaskById(1);
        assertEquals("Задача 1", retrievedTask.getTitle(), "Название задачи не должно измениться");
        assertEquals("Описание задачи 1", retrievedTask.getDescription(), "Описание задачи 1 не должго измениться");
        assertEquals(TaskStatus.NEW, retrievedTask.getStatus(), "Статус задачи 1 не должен измениться");
    }

    @Test
    public void testHistoryManagerPreservesPreviousVersions() {
        Task task = new Task(1, "Задача 1", "Описание задачи 1", TaskStatus.NEW);
        int id = taskManager.createTask(task);

        taskManager.getTaskById(id);
        Task updatedTask = new Task(1, "Задача 1", "Новое описание задачи 1", TaskStatus.IN_PROGRESS);
        taskManager.updateTask(updatedTask);

        List<Task> history = historyManager.getHistory();
        assertFalse(history.isEmpty(), "История не должна быть пустой");


        Task previousTask = history.get(0);
        assertEquals(previousTask.getTitle().equals(task.getTitle()), "Имя задачи в истории должно совпадать с оригинальным именем");
        assertEquals(previousTask.getDescription().equals(task.getDescription()), "Описание задачи в истории должно совпадать с оригинальным описанием");
        assertEquals(previousTask.getStatus().equals(task.getStatus()), "Статус задачи в истории должен совпадать с оригинальным статусом");
    }
}