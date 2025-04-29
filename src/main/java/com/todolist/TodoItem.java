package com.todolist;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TodoItem {
    private final StringProperty task = new SimpleStringProperty();
    private final BooleanProperty isDone = new SimpleBooleanProperty(false);

    public TodoItem(String task) {
        this.task.set(task) ;
    }
    public String getTask() { return task.get(); }
    public void setTask(String value) { task.set(value); }
    public StringProperty taskProperty() { return task; }

    public boolean isDone() { return isDone.get(); }
    public void setDone(boolean value) { isDone.set(value); }
    public BooleanProperty isDoneProperty() { return isDone; }

    @Override
    public String toString() {
        return getTask() + (isDone() ? " (done)" : ""); // Menampilkan status tugas
    }
}
