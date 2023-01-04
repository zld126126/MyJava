package com.dongtech.myexcel;
import java.util.Arrays;

public class ExcelUtils {
    private static String[] ExcelExtension = {"xls","xlsx","XLS","XLSX"};

    public static final boolean CheckExtension(String extension){
        return Arrays.asList(ExcelExtension).contains(extension);
    }
}
