package com.e_learning.controllers.admin;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import com.e_learning.database.Sqlite;


public class ExportExcel {


    public static void exportToExcel(Window owner){
        try {

        String query = "SELECT * FROM student_marks";
        String query2 = "SELECT * FROM student_details";
        Connection conn = Sqlite.connector();
        ResultSet res = conn.createStatement().executeQuery(query);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet worksheet = wb.createSheet("Exported psqlTable");
        XSSFRow header = worksheet.createRow(0);

        header.createCell(0).setCellValue("Name");
        header.createCell(1).setCellValue("Maths");
        header.createCell(2).setCellValue("English");
        header.createCell(3).setCellValue("Science");
        header.createCell(4).setCellValue("History");
        header.createCell(5).setCellValue("Geography");

        worksheet.autoSizeColumn(0);
        worksheet.setColumnWidth(1, 200*25);
        worksheet.setColumnWidth(2, 200*25);
        worksheet.autoSizeColumn(3);
        worksheet.autoSizeColumn(4);
        worksheet.autoSizeColumn(5);

        worksheet.setZoom(150); //scale of zoom 150%

        int index = 1;
        while(res.next()){

            XSSFRow row = worksheet.createRow(index);
            row.createCell(0).setCellValue(res.getString("name"));
            row.createCell(1).setCellValue(res.getString("maths"));
            row.createCell(2).setCellValue(res.getString("english"));
            row.createCell(3).setCellValue(res.getString("science"));
            row.createCell(4).setCellValue(res.getString("pe"));
            row.createCell(5).setCellValue(res.getString("ict"));

            index++;
        }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export Excel Document");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")
                );
                File saveFile = fileChooser.showSaveDialog(owner);
                String savePath = "Exported Database.xlsx";
                FileOutputStream save_file = new FileOutputStream(savePath);
                fileChooser.setInitialFileName("Exported Excel Database");
                //TODO Set initial filename not working when saving?

                Path src = Paths.get(savePath);
                Path dest = Paths.get(saveFile.getAbsolutePath());
                dest.toUri().toURL().toString();
                StandardCopyOption REPLACE_EXISTING = StandardCopyOption.REPLACE_EXISTING;
                StandardCopyOption COPY_ATTRIBUTES = StandardCopyOption.COPY_ATTRIBUTES;
                LinkOption NOFOLLOW_LINKS = LinkOption.NOFOLLOW_LINKS;

                if(saveFile !=null){
                try {
                    //try to save workbook
                    wb.write(save_file);
                    try {
                        //try to copy and delete file
                        Files.copy(src, dest, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                        Files.delete(src);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                } catch(IOException e){
                    e.printStackTrace();

                }
            }
        save_file.close();
        res.close();
        wb.close();
    } catch (SQLException e){
        e.printStackTrace();
    }
catch (IOException ex){
            ex.printStackTrace();
        }
   }
    
   public static void export_StudentsToExcel(Window owner){
        try { 
        String query = "SELECT * FROM student_details";
        Connection conn = Sqlite.connector();
        ResultSet res = conn.createStatement().executeQuery(query);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet worksheet = wb.createSheet("Exported psqlTable");
        XSSFRow header = worksheet.createRow(0);

        header.createCell(0).setCellValue("Student ID");
        header.createCell(1).setCellValue("First Name");
        header.createCell(2).setCellValue("Surname");
        header.createCell(3).setCellValue("Grade");
        header.createCell(4).setCellValue("Gender");
        header.createCell(5).setCellValue("Teacher");

        worksheet.autoSizeColumn(0);
        worksheet.setColumnWidth(1, 200*25);
        worksheet.setColumnWidth(2, 200*25);
        worksheet.autoSizeColumn(3);
        worksheet.autoSizeColumn(4);
        worksheet.autoSizeColumn(5);

        worksheet.setZoom(150); //scale of zoom 150%

        int index = 1;
        while(res.next()){

            XSSFRow row = worksheet.createRow(index);
            row.createCell(0).setCellValue(res.getString("id"));
            row.createCell(1).setCellValue(res.getString("fname"));
            row.createCell(2).setCellValue(res.getString("lname"));
            row.createCell(3).setCellValue(res.getString("grade"));
            row.createCell(4).setCellValue(res.getString("gender"));
            row.createCell(5).setCellValue(res.getString("teacher_"));

            index++;
        }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export Excel Document");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")
                );
                File saveFile = fileChooser.showSaveDialog(owner);
                String savePath = "Exported Database.xlsx";
                FileOutputStream save_file = new FileOutputStream(savePath);
                fileChooser.setInitialFileName("Exported Excel Database");
                //TODO Set initial filename not working when saving?

                Path src = Paths.get(savePath);
                Path dest = Paths.get(saveFile.getAbsolutePath());
                dest.toUri().toURL().toString();
                StandardCopyOption REPLACE_EXISTING = StandardCopyOption.REPLACE_EXISTING;
                StandardCopyOption COPY_ATTRIBUTES = StandardCopyOption.COPY_ATTRIBUTES;
                LinkOption NOFOLLOW_LINKS = LinkOption.NOFOLLOW_LINKS;

                if(saveFile !=null){
                try {
                    //try to save workbook
                    wb.write(save_file);
                    try {
                        //try to copy and delete file
                        Files.copy(src, dest, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                        Files.delete(src);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                } catch(IOException e){
                    e.printStackTrace();

                }
            }
        save_file.close();
        res.close();
        wb.close();
    } catch (SQLException e){
        e.printStackTrace();
    }
catch (IOException ex){
            ex.printStackTrace();
        }
   }
}
