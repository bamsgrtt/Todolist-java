package com.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TodoListController {
    @FXML private TextField inputField;
    @FXML private ListView<TodoItem> listView;
    @FXML private Button addButton, deleteButton, updateButton;

    private ObservableList<TodoItem> items;

    // Inisialisasi daftar to-do
    public void initialize() {
        items = FXCollections.observableArrayList();
        listView.setItems(items);
    }

    // Menambahkan tugas baru
    @FXML
    private void handleAdd() {
        String text = inputField.getText().trim();
        if (!text.isEmpty()) {
            items.add(new TodoItem(text));
            inputField.clear(); // Bersihkan input setelah menambahkan tugas
        }
    }

    // Menghapus tugas yang dipilih
    @FXML
    private void handleDelete() {
        TodoItem selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            items.remove(selected);
        }
    }

    // Memperbarui status tugas (selesai/belum)
    @FXML
    private void handleUpdate() {
        TodoItem selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setDone(!selected.isDone());
            listView.refresh(); // Segarkan tampilan daftar
        }
    }
}
