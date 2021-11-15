package com.e_learning.database;

import java.sql.*;
import com.e_learning.controllers.content.ProfileCtrl;

public class Sqlite {
    private final static String db = "jdbc:sqlite:sqlite/java_data_sqlite3.db";

    ProfileCtrl prof = new ProfileCtrl();

    public static Connection connector() throws SQLException {
        return DriverManager.getConnection(db);
    }

    public boolean check(String search) throws SQLException {
        String QUERY = "SELECT * FROM user_login WHERE name LIKE ?";

        try (Connection conn = connector();

                PreparedStatement pstmt = conn.prepareStatement(QUERY);) {

            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
            // TODO: handle exception
        }
        return false;
    }

    public boolean validateUser(String user, String pass) throws SQLException {

        String QUERY = "SELECT * FROM ecd_tutor WHERE fname = ? AND password = ? ";
        findStudent(user);
        try (Connection conn = connector();

                PreparedStatement pstmt = conn.prepareStatement(QUERY);) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                System.out.println(res.getString("fname"));
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
            // TODO: handle exception
        }
        return false;
    }

    public static String findStudent(String query) {
        String search = "select * from student_login WHERE name LIKE '" + query + "%'";
        try (Connection conn = connector();

                PreparedStatement pstmt = conn.prepareStatement(search);) {
            ResultSet res = pstmt.executeQuery();
            try {
                try {
                    res.next();
                    System.out.println("User name found");
                    ProfileCtrl.profileScreen(query);
                } catch(SQLException e){
                    e.printStackTrace();
                    e.getMessage();
                }
                // if (!res.next()) {
                //     System.out.println("Search failed");
                // }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Nothing found");
            }
        } catch (SQLException e) {
            printSQLException(e);
            // TODO: handle exception
        }
        return query;
    }

    public boolean validateAdmin(String user, String pass) throws SQLException {

        String QUERY = "SELECT * FROM user_login WHERE name = ? AND password = ? ";

        try (Connection conn = connector();

                PreparedStatement pstmt = conn.prepareStatement(QUERY);) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
            // TODO: handle exception
        }
        return false;
    }
    public static void insertStudentValues
       (String fname_entry,
        String lname_entry,
        String grade_entry,
        String gender_entry
        ) {
    String values = "INSERT INTO student_details (fname, lname, grade, gender) VALUES (?, ?, ?, ?)";

    try {
        Connection conn = connector();
        PreparedStatement pstmt = conn.prepareStatement(values);
        pstmt.setString(1, fname_entry);
        pstmt.setString(2, lname_entry);
        pstmt.setString(3, grade_entry);
        pstmt.setString(4, gender_entry);

        pstmt.execute();

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

}

public static void insertMarksValues
   (
    String student_name,
    String math_entry,
    String eng_entry,
    String sci_entry,
    String ict_entry,
    String pe_entry
    ) {
String values = "INSERT INTO student_marks (name, maths, english, science, pe, ict) VALUES (?, ?, ?, ?, ?, ?)";

try {
    Connection conn = connector();
    PreparedStatement pstmt = conn.prepareStatement(values);
    pstmt.setString(1, student_name);
    pstmt.setString(2, math_entry);
    pstmt.setString(3, eng_entry);
    pstmt.setString(4, sci_entry);
    pstmt.setString(5, ict_entry);
    pstmt.setString(6, pe_entry);

    pstmt.execute();

} catch (SQLException e) {
    System.out.println(e.getMessage());
    e.printStackTrace();
}
    }

        public void updateValues( String name, String maths, String english, String science, String pe_, String ict, String id){
        String update = "UPDATE student_marks SET name=?, maths=?, english=?, science=?, pe=?, ict=? WHERE id=?";
        try{
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1, name);
            pstmt.setString(2, maths);
            pstmt.setString(3, english);
            pstmt.setString(4, science);
            pstmt.setString(5, pe_);
            pstmt.setString(6, ict);
            pstmt.setString(7, id);

            pstmt.execute();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
        
    public void updateStudent( String fname, String lname, String grade, String gender, String teacher, String id){
        String update = "UPDATE student_details SET fname=?, lname=?, grade=?, gender=?, teacher_=? WHERE id=?";
        try{
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, grade);
            pstmt.setString(4, gender);
            pstmt.setString(5, teacher);
            pstmt.setString(6, id);

            pstmt.execute();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public void delete_row_by_id( String id_){
        String delete = "DELETE FROM student_marks WHERE id = ? ";
        try{
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(delete);
            pstmt.setString(1, id_);
            
            pstmt.execute();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void delete_student_row_by_id( String id_){
        String delete = "DELETE FROM student_details WHERE id = ? ";
        try{
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(delete);
            pstmt.setString(1, id_);
            
            pstmt.execute();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }

            }
        }
    }

    public Connection checkConnection() {
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(db);

            if (conn != null) {
                System.out.println("DB Connection ok!");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Sqlite sqlite3 = new Sqlite();
        sqlite3.checkConnection(); 
    }

}
