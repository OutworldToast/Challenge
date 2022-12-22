package com.example.challenge;

import java.sql.*;


/*

https://mvnrepository.com/artifact/mysql/mysql-connector-java

    <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.31</version>
    </dependency>

    1. Add dependency
    2. Change db
    3. Change password
    4. Enjoy

*/

public class DatabaseConnection {
    static String db = "biopal";
    static String user = "root";
    static String password = "<roott3MP>";

    public static String[][] getQuery(String query) {
        try {
            int rijen = rowCounter(getResultSet(query));
            return toArray(rijen, getResultSet(query));
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void setQuery(String query) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, user, password);
            Statement stmt = con.createStatement();
            stmt.execute(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static ResultSet getResultSet(String query) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static int rowCounter(ResultSet rs) {
        try {
            int size = 0;
            while (rs.next()) {
                size++;
            }
            return size;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    private static String[][] toArray(int rijen, ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            String[][] data = new String[rijen][rsmd.getColumnCount()];
            int rij = 0;
            while (rs.next()) {
                for (int j = 0; j < rsmd.getColumnCount(); j++) {
                    data[rij][j] = rs.getString(j + 1);
                }
                rij++;
            }
            return data;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
