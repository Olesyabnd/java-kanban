package ru.yandex.javacourse.bondarenko.schedule.manager;

import ru.yandex.javacourse.bondarenko.schedule.task.*;
import ru.yandex.javacourse.bondarenko.schedule.util.Managers;
import java.util.List;

public interface TaskManager {
    int createTask(Task task);

    int createEpic(Epic epic);

    Integer createSubtask(Subtask subtask);

    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getAllSubtasks();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubtasks();

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    void removeTaskById(int id);

    void removeEpicById(int id);

    void removeSubtaskById(int id);

    List<Subtask> getSubtasksByEpic(Epic epic);

    List<Task> getHistory();

}