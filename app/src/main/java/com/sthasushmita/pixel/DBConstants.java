package com.example.sujal.pocketmed;

/**
 * Created by sujal on 6/4/2017.
 */
public class DBConstants {
    final static public int DB_VERSION1 = 1;
    final static public String DB_NAME1 = "POCKETMED";
    final static public String TB_NAME1 = "SignUp";
    final static public String _ID = "id";
    final static public String MED_FULLNAME = "Fullname";
    final static public String MED_EMAIL = "Email";
    final static public String MED_USERNAME = "Username";
    final static public String MED_PASSWORD = "Password";
    final static public String MED_CPASSWORD = "RePpassword";
    final static public String CREATE_QUERY1 = "CREATE TABLE "+TB_NAME1+" ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            MED_FULLNAME+" VARCHAR(20),"+
            MED_EMAIL+" VARCHAR(255),"+
            MED_USERNAME+" VARCHAR(10),"+
            MED_PASSWORD+" VARCHAR(20),"+
            MED_CPASSWORD+" VARCHAR(20));";
    final static String DELETE_QUERY1 = "DELETE TABLE IF EXIST "+TB_NAME1;
}

