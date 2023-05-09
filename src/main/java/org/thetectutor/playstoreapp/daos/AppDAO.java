package org.thetectutor.playstoreapp.daos;

import org.thetectutor.playstoreapp.models.App;
import org.thetectutor.playstoreapp.utils.DBConnect;
import org.thetectutor.playstoreapp.utils.DuplicateEntryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppDAO {
    private Connection con;
    private PreparedStatement ps;

    public AppDAO() {
        con = DBConnect.getConnect();
    }


    public boolean insertApp(App app) {
        String sql = "INSERT INTO app_info (app_name, category, age_limit, release_date, version, rating,description) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, app.getAppName());
            ps.setString(2, app.getCategory());
            ps.setInt(3, app.getAgeLimit());
            ps.setDate(4, java.sql.Date.valueOf(app.getDate())); // Convert LocalDate to java.sql.Date
            ps.setDouble(5, app.getVersion());
            ps.setDouble(6, app.getRating());
            ps.setString(7, app.getDescription());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicateEntryException("This app already exist in database!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateAppAgeLimit(int id, int ageLimit) {
        String sql = "UPDATE app_info SET age_limit=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ageLimit);
            ps.setInt(2, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateAppVersion(int id, double version) {
        String sql = "UPDATE app_info SET version=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, version);
            ps.setInt(2, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteApp(int id) {
        String sql = "DELETE FROM app_info WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<App> getAllApps() {
        String sql = "SELECT * FROM app_info";
        List<App> appsList = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                appsList.add(buildApp(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appsList;
    }

    public App getAppById(int id) {
        String sql = "SELECT * FROM app_info WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return buildApp(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public App getByName(String name) {
        String sql = "SELECT * FROM app_info WHERE app_name = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return buildApp(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private App buildApp(ResultSet rs) throws SQLException {
        App app = new App();
        app.setId(rs.getInt("id"));
        app.setAppName(rs.getString("app_name"));
        app.setCategory(rs.getString("category"));
        app.setAgeLimit(rs.getInt("age_limit"));
        app.setDate(rs.getDate("release_date").toLocalDate());
        app.setVersion(rs.getDouble("version"));
        app.setRating(rs.getDouble("rating"));
        app.setDescription(rs.getString("description"));
        return app;
    }
}
