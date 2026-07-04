package com.company_name.automation.common.utilities;

import java.sql.*;

public class DataBase {

    public Connection connect = null;

    public ResultSet rs = null;

    public String query;

    public DataBase setConnection(String dbType, String url, String userName, String password) throws Exception {
        switch (dbType) {
            case "MySQL":
                Class.forName("com.mysql.cj.jdbc.Driver");
                break;

            case "Oracle":
                Class.forName("oracle.jdbc.driver.OracleDriver");
                break;

            case "SQLServer":
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                break;

            case "RedShift":
                Class.forName("com.amazon.redshift.jdbc.Driver");
                break;

            case "Postgresql":
                Class.forName("org.postgresql.Driver");
                break;
        }
        try {
            connect = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public DataBase query(String query){
        try{
            this.query = query;
            Statement stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return this;
    }

    public void close() throws Exception {
        connect.close();
    }



}
