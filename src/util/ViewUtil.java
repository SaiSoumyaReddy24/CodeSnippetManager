package util;

import javafx.scene.control.TextInputDialog;
import model.Snippet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ViewUtil {

    public static Snippet promptSnippetDialog() {
        TextInputDialog titleDialog = new TextInputDialog();
        titleDialog.setHeaderText("Enter snippet title:");
        Optional<String> titleResult = titleDialog.showAndWait();
        if (titleResult.isEmpty()) return null;

        TextInputDialog langDialog = new TextInputDialog();
        langDialog.setHeaderText("Enter snippet language:");
        Optional<String> langResult = langDialog.showAndWait();
        if (langResult.isEmpty()) return null;

        TextInputDialog codeDialog = new TextInputDialog();
        codeDialog.setHeaderText("Enter snippet code:");
        Optional<String> codeResult = codeDialog.showAndWait();
        if (codeResult.isEmpty()) return null;

        return new Snippet(0, titleResult.get(), codeResult.get(), langResult.get(), LocalDateTime.now());
    }

    public static void exportToFile(List<Snippet> snippets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("snippets_export.txt"))) {
            for (Snippet snippet : snippets) {
                writer.write("Title: " + snippet.getTitle());
                writer.newLine();
                writer.write("Language: " + snippet.getLanguage());
                writer.newLine();
                writer.write("Code: " + snippet.getCode());
                writer.newLine();
                writer.write("Created At: " + snippet.getCreatedAt());
                writer.newLine();
                writer.write("-----");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
