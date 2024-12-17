package ru.yandex.javacourse.bondarenko.schedule.util;

import ru.yandex.javacourse.bondarenko.schedule.manager.*;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
