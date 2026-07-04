package com.company_name.automation.project.project_name;

public class Constants {

    public static final String email = System.getenv("email");

    public static final String password = System.getenv("password");

    public static class project_details
    {
        public static final String application_url = System.getenv("application_url");

        public static final String test_postgresql_db_url = System.getenv("test_postgresql_db_url");

        public static final String test_postgresql_db_user_name = System.getenv("test_postgresql_db_user_name");

        public static final String test_postgresql_db_password = System.getenv("test_postgresql_db_password");
    }
}
