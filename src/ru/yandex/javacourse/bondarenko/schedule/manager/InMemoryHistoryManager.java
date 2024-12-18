package ru.yandex.javacourse.bondarenko.schedule.manager;

import ru.yandex.javacourse.bondarenko.schedule.task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private final List<Task> history = new ArrayList<>(10);
    private static final int MAX_HISTORY_SIZE = 10;

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        if (history.size() >= MAX_HISTORY_SIZE) {
            history.removeFirst();
        }
            history.add(task);
        }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}
