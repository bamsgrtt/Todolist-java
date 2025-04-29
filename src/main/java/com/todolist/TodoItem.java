package com.todolist;

public class TodoItem {
    private final String task;
    private boolean isDone;

    public TodoItem(String task) {
        this.task = task;
        this.isDone = false; // Default status adalah belum selesai
    }
    public String getTask() { return task; }

    public boolean isDone() { return isDone;
    }
    public void setDone(boolean done) { isDone = done; }

    @Override
    public String toString() {
        return task + (isDone ? " (done)" : ""); // Menampilkan status tugas
    }
}
