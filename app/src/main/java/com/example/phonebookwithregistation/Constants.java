package com.example.phonebookwithregistation;

public class Constants {
    public  static final String DATABASE_NAME="ContactInformation.db";
    public  static final int DATABASE_Version=1;
    public  static final String TABLE_NAME="ContactInformation";
    public  static final String COLUMN_ID="id";
    public  static final String COLUMN_NAME="Name";
    public  static final String COLUMN_LOCATION="Location";
    public  static final String COLUMN_NUMBER="Number";
    public static final String COLUMN_DATE        = "date";

    public static final String CREATE_TABLE  = " CREATE TABLE "+TABLE_NAME+"("
                +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_NAME+" TEXT, "
                +COLUMN_LOCATION+" TEXT, "
                +COLUMN_NUMBER+" TEXT "
                +")";

}
