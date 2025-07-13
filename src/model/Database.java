package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:snippets.db";

    public Database() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS snippets (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                code TEXT NOT NULL,
                language TEXT NOT NULL,
                createdAt TEXT NOT NULL
            );
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSnippet(Snippet snippet) {
        String sql = "INSERT INTO snippets (title, code, language, createdAt) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, snippet.getTitle());
            pstmt.setString(2, snippet.getCode());
            pstmt.setString(3, snippet.getLanguage());
            pstmt.setString(4, snippet.getCreatedAt().toString());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Snippet> getAllSnippets() {
        List<Snippet> snippets = new ArrayList<>();
        String sql = "SELECT * FROM snippets";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Snippet snippet = new Snippet(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("code"),
                    rs.getString("language"),
                    LocalDateTime.parse(rs.getString("createdAt"), DateTimeFormatter.ISO_DATE_TIME)
                );
                snippets.add(snippet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return snippets;
    }

    public void deleteSnippet(String title) {
        String sql = "DELETE FROM snippets WHERE title = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
