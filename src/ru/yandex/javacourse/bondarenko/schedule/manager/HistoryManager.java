package ru.yandex.javacourse.bondarenko.schedule.manager;

import ru.yandex.javacourse.bondarenko.schedule.task.Task;

import java.util.List;

public interface HistoryManager {
    void add(Task task);

    List<Task> getHistory();
}
