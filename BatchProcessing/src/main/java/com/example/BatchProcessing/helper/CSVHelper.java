package com.example.BatchProcessing.helper;

public class CSVHelper {
    public static String [] FIELDTOEXTRACT={
            "firstName","lastName","email","gender","contactNo","country","dob"
    };
    public static String HEADERS=String.join(", ",FIELDTOEXTRACT);
}
