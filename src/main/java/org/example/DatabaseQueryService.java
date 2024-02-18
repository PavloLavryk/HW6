package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public List<ResultRow> findMaxProjectsClient() {
        return executeQuery("src/main/resources/find_max_projects_client.sql", rs -> {
            ResultRow row = new ResultRow();
            row.setName(rs.getString("name"));
            row.setValue(rs.getInt("project_count"));
            return row;
        });
    }

    public List<ResultRow> findMaxSalaryWorker() {
        return executeQuery("src/main/resources/find_max_salary_worker.sql", rs -> {
            ResultRow row = new ResultRow();
            row.setName(rs.getString("name"));
            row.setValue(rs.getInt("salary"));
            return row;
        });
    }

    public List<ResultRow> findLongestProject() {
        return executeQuery("src/main/resources/find_longest_project.sql", rs -> {
            ResultRow row = new ResultRow();
            row.setName(rs.getString("id"));
            row.setValue(rs.getInt("project_duration"));
            return row;
        });
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        return executeQuery("src/main/resources/find_youngest_eldest_workers.sql", rs -> {
            YoungestEldestWorker worker = new YoungestEldestWorker();
            worker.setType(rs.getString("type"));
            worker.setName(rs.getString("name"));
            worker.setBirthday(rs.getDate("birthday").toLocalDate());
            return worker;
        });
    }

    public List<ResultRow> printProjectPrices() {
        return executeQuery("src/main/resources/print_project_prices.sql", rs -> {
            ResultRow row = new ResultRow();
            row.setName(rs.getString("name"));
            row.setValue(rs.getInt("price"));
            return row;
        });
    }

    private <T> List<T> executeQuery(String filePath, ResultSetMapper<T> mapper) {
        List<T> result = new ArrayList<>();
        try {
            Connection conn = Database.getInstance().getConnection();
            String sql = new String(Files.readAllBytes(Paths.get(filePath)));
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                T row = mapper.map(rs);
                result.add(row);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error querying database", e);
        }
        return result;
    }

    @FunctionalInterface
    interface ResultSetMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }
}
