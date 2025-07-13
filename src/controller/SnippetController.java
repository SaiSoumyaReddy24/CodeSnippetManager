package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Database;
import model.Snippet;
import util.ViewUtil;

import java.time.LocalDateTime;

public class SnippetController {

    @FXML
    private TableView<Snippet> snippetTable;

    @FXML
    private TableColumn<Snippet, String> titleColumn;

    @FXML
    private TableColumn<Snippet, String> languageColumn;

    @FXML
    private TableColumn<Snippet, String> createdColumn;

    @FXML
    private TextField titleField;

    @FXML
    private TextField languageField;

    @FXML
    private TextArea codeArea;

    private Database database;

    @FXML
    public void initialize() {
        database = new Database();

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        createdColumn.setCellValueFactory(cellData ->
    new SimpleStringProperty(cellData.getValue().getCreatedAt().toString()));


        snippetTable.getItems().addAll(database.getAllSnippets());
    }

    @FXML
    private void handleAdd() {
        String title = titleField.getText();
        String language = languageField.getText();
        String code = codeArea.getText();

        if (title.isBlank() || language.isBlank() || code.isBlank()) {
            showAlert("Please fill in all fields.");
            return;
        }

        Snippet snippet = new Snippet(0, title, code, language, LocalDateTime.now());
        database.addSnippet(snippet);
        snippetTable.getItems().add(snippet);

        titleField.clear();
        languageField.clear();
        codeArea.clear();
    }

    @FXML
    private void handleDeleteSnippet() {
        Snippet selected = snippetTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            database.deleteSnippet(selected.getTitle());
            snippetTable.getItems().remove(selected);
        } else {
            showAlert("No snippet selected to delete.");
        }
    }

    @FXML
    private void handleExport() {
        ViewUtil.exportToFile(snippetTable.getItems());
        showAlert("Exported to 'snippets_export.txt'");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
