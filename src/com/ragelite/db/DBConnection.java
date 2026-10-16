package com.ragelite.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:ragelite.db";
    private static Connection connection;

    private DBConnection() {
        // utility class, no instantiation
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
            initializeTables();
        }
        return connection;
    }

    private static void initializeTables() throws SQLException {
        String createUsers = "CREATE TABLE IF NOT EXISTS users (" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "email TEXT UNIQUE NOT NULL," +
                "password TEXT NOT NULL," +
                "role TEXT NOT NULL," +
                "created_at TEXT DEFAULT CURRENT_TIMESTAMP)";

        String createResources = "CREATE TABLE IF NOT EXISTS resources (" +
                "resource_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "type TEXT," +
                "status TEXT," +
                "created_at TEXT DEFAULT CURRENT_TIMESTAMP)";

        String createProjects = "CREATE TABLE IF NOT EXISTS projects (" +
                "project_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL," +
                "description TEXT," +
                "status TEXT," +
                "owner_id INTEGER," +
                "created_at TEXT DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (owner_id) REFERENCES users(user_id))";

        String createProjectTeam = "CREATE TABLE IF NOT EXISTS project_team (" +
                "project_id INTEGER," +
                "user_id INTEGER," +
                "PRIMARY KEY (project_id, user_id)," +
                "FOREIGN KEY (project_id) REFERENCES projects(project_id)," +
                "FOREIGN KEY (user_id) REFERENCES users(user_id))";

        String createDatasets = "CREATE TABLE IF NOT EXISTS datasets (" +
                "dataset_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "project_id INTEGER," +
                "size_mb REAL," +
                "created_at TEXT DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (project_id) REFERENCES projects(project_id))";

        String createTrainingJobs = "CREATE TABLE IF NOT EXISTS training_jobs (" +
                "job_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dataset_id INTEGER," +
                "status TEXT," +
                "started_at TEXT," +
                "finished_at TEXT," +
                "FOREIGN KEY (dataset_id) REFERENCES datasets(dataset_id))";

        String createExperiments = "CREATE TABLE IF NOT EXISTS experiments (" +
                "experiment_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "job_id INTEGER," +
                "metric_name TEXT," +
                "metric_value REAL," +
                "FOREIGN KEY (job_id) REFERENCES training_jobs(job_id))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createUsers);
            stmt.execute(createResources);
            stmt.execute(createProjects);
            stmt.execute(createProjectTeam);
            stmt.execute(createDatasets);
            stmt.execute(createTrainingJobs);
            stmt.execute(createExperiments);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
