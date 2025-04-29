package com.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;



public class TodoListController {
    @FXML private TextField inputField;
    @FXML private ListView<TodoItem> listView;
    @FXML private Button addButton, deleteButton, updateButton;

    private ObservableList<TodoItem> items;

    // Inisialisasi daftar to-do
    public void initialize() {
        items = FXCollections.observableArrayList();
        listView.setItems(items);

        // Custom cell dengan checkbox dan label
        listView.setCellFactory(list -> new ListCell<>() {
                private final CheckBox checkBox = new CheckBox();
                private final javafx.scene.control.Label label = new javafx.scene.control.Label();
                private final HBox hbox = new HBox(10, checkBox, label);

                {
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    hbox.setStyle("-fx-padding: 5;");

                    checkBox.setOnAction(e -> {
                        TodoItem item = getItem();
                        if (item != null) {
                            item.setDone(checkBox.isSelected());
                        }
                    });
                }
                @Override
                protected void updateItem(TodoItem item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        checkBox.selectedProperty().unbind();
                        label.textProperty().unbind();

                        checkBox.selectedProperty().bindBidirectional(item.isDoneProperty());
                        label.textProperty().bind(item.taskProperty());
                        label.setStyle(item.isDone() ? "-fx-strikethrough: true;" : "-fx-strikethrough: false;");

                        item.isDoneProperty().addListener((obs, oldVal, newVal) -> {
                            label.setStyle(newVal ? "-fx-strikethrough: true;" : "-fx-strikethrough: false;");
                        });

                        setGraphic(hbox);

                    }
            };
        });
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

    @FXML private ToggleButton themeToggle;

    private boolean darkModeToggle = false;

    @FXML
    private void handleToggleTheme() {
        darkModeToggle = !darkModeToggle;
        Scene scene = themeToggle.getScene();

        // Ganti stylesheet
        scene.getStylesheets().clear();
        if (darkModeToggle) {
            listView.getScene().getStylesheets().add(getClass().getResource("/dark-theme.css").toExternalForm());
            themeToggle.setText("Light Mode");
        } else {
            listView.getScene().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            themeToggle.setText("Dark Mode");
        }
    }
}
