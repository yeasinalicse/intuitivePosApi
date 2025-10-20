package com.intuitive.intuitivepos.login.repository;

import com.intuitive.intuitivepos.dto.LoginResponse;
import io.swagger.v3.core.util.Json;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public LoginResponse callLoginProc(String password) {
        final String call = "{call dbo.SPUserLoginTablet(?)}";

        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(call);
            cs.setString(1, "1234");
            return cs;
        }, (CallableStatement cs) -> {
            List<Object> resultSets = new ArrayList<>();

            boolean hasResults = cs.execute();

            while (true) {
                if (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        if (rs != null) {
                            ResultSetMetaData metaData = rs.getMetaData();
                            int columnCount = metaData.getColumnCount();
                            // collect all rows for this resultset
                            JSONArray rows = new JSONArray();
                            while (rs.next()) {
                                JSONObject row = new JSONObject();
                                for (int i = 1; i <= columnCount; i++) {
                                    Object v = rs.getObject(i);
                                    row.put(metaData.getColumnLabel(i), v == null ? JSONObject.NULL : v);
                                }
                                rows.put(row);
                            }

                            if (rows.length() == 1) {
                                resultSets.add(rows.getJSONObject(0));
                            } else if (rows.length() > 1) {
                                resultSets.add(rows);
                            } else {
                                resultSets.add(rows);
                            }
                        }
                    } catch (SQLException e) {
                        // log / rethrow as runtime if desired
                        e.printStackTrace();
                    }
                } else {
                    int updateCount = cs.getUpdateCount();
                    if (updateCount == -1) {
                        break; // no more results
                    }
                }

                try {
                    hasResults = cs.getMoreResults();
                } catch (SQLException e) {
                    e.printStackTrace();
                    break;
                }
            }

            if (resultSets.isEmpty()) return new LoginResponse(false, "Invalid username or password", null);
            Object data = resultSets.size() == 1 ? resultSets.get(0) : new JSONArray(resultSets);
            return new LoginResponse(true, "Login successful", new JSONArray("[{}]"));
        });
    }
}
