package ru.yandex.javacourse.bondarenko.schedule;

import ru.yandex.javacourse.bondarenko.schedule.task.*;
import ru.yandex.javacourse.bondarenko.schedule.manager.TaskManager;
import ru.yandex.javacourse.bondarenko.schedule.util.Managers;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager inMemoryTaskManager = Managers.getDefault();
        exampleTasks(inMemoryTaskManager);
        printAllTasks(inMemoryTaskManager);
    }

    private static void exampleTasks(TaskManager manager) {
        Task task1 = new Task(1, "Задача 1", "Описание задачи 1", TaskStatus.NEW);
        Task task2 = new Task(2, "Задача 2", "Описание задачи 2", TaskStatus.NEW);
        manager.createTask(task1);
        manager.createTask(task2);
        Epic epic1 = new Epic(3, "Эпик 1", "Описание эпика 1");
        Subtask subtask1 = new Subtask(4, "Подзадача 1", "Описание подзадачи 1", TaskStatus.NEW, epic1.getId());
        Subtask subtask2 = new Subtask(5, "Подзадача 2", "Описание подзадачи 2", TaskStatus.NEW, epic1.getId());
        Epic epic2 = new Epic(6, "Эпик 2", "Описание эпика 2");
        Subtask subtask3 = new Subtask(7, "Подзадача 3", "Описание подзадачи 3", TaskStatus.NEW, epic2.getId());
        manager.createEpic(epic1);
        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);
        manager.createEpic(epic2);
        manager.createSubtask(subtask3);

        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }
        System.out.println("Подзадачи эпика 1:");
        for (Subtask subtask : manager.getSubtasksByEpic(epic1)) {
            System.out.println(subtask);
        }
        System.out.println("Подзадачи эпика 2:");
        for (Subtask subtask : manager.getSubtasksByEpic(epic2)) {
            System.out.println(subtask);
        }
        task1.setStatus(TaskStatus.NEW);
        task2.setStatus(TaskStatus.IN_PROGRESS);
        subtask1.setStatus(TaskStatus.IN_PROGRESS);
        subtask2.setStatus(TaskStatus.DONE);
        subtask3.setStatus(TaskStatus.DONE);

        manager.updateTask(task1);
        manager.updateTask(task2);
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask2);
        manager.updateEpic(epic1);
        manager.updateSubtask(subtask3);
        manager.updateEpic(epic2);

        System.out.println("Статусы после изменений:");
        System.out.println("Задача 1: " + manager.getAllTasks().get(0).getStatus());
        System.out.println("Задача 2: " + manager.getAllTasks().get(1).getStatus());
        System.out.println("Подзадача 1: " + manager.getAllSubtasks().get(0).getStatus());
        System.out.println("Подзадача 2: " + manager.getAllSubtasks().get(1).getStatus());
        System.out.println("Эпик 1: " + manager.getAllEpics().get(0).getStatus());
        System.out.println("Подзадача 3: " + manager.getAllSubtasks().get(2).getStatus());
        System.out.println("Эпик 2: " + manager.getAllEpics().get(1).getStatus());

        manager.removeTaskById(1);
        manager.removeEpicById(3);

        System.out.println("Списки после удаления:");
        System.out.println("Список задач:");
        System.out.println(manager.getAllTasks());
        System.out.println("Список эпиков:");
        System.out.println(manager.getAllEpics());
        System.out.println("Список подзадач:");
        System.out.println(manager.getAllSubtasks());
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getAllEpics()) {
            System.out.println(epic);

        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
