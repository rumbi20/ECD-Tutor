package com.e_learning.controllers.misc;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.io.IOException;

public class SceneCtrl {

    //Generic/Misc Screens
    String login_screen = "/resources/fxml/login_choice.fxml";
    String decision_screen = "/resources/fxml/decision_screen.fxml";
    String choice_screen = "/resources/fxml/choice_selection.fxml";
    String lessons_screen = "/resources/fxml/lessons_screen.fxml";
    String about_screen = "/resources/fxml/about.fxml";

    //Admin Screens
    String admin_students_screen = "/resources/fxml/admin/insert_student_screen.fxml";
    String admin_marks_screen = "/resources/fxml/admin/insert_marks_screen.fxml";
    String admin_login_screen = "/resources/fxml/admin/login_screen.fxml";
    String admin_records_screen = "/resources/fxml/admin/records_view_2.fxml";
    String admin_students_view = "/resources/fxml/admin/students_screen.fxml";

    //User Screens
    String user_image_screen = "/resources/fxml/user_content/image_screen.fxml";
    String user_login_screen = "/resources/fxml/user_content/login_screen.fxml";
    String user_video_screen = "/resources/fxml/user_content/video_screen_2.fxml";
    String user_profile_screen = "/resources/fxml/user_content/profile_screen.fxml";



    public static void switchScene(Scene scene, boolean truth, String title, boolean option){
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(truth);
        stage.setTitle(title);
        if(option == true){
            stage.show();
        } else if(option == false){
            stage.showAndWait();
        } else{
            System.out.println("Invalid Option");
        }

        // stage.show();

    }

    public void login_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(login_screen));
            Scene login_ = new Scene(root);
            switchScene(login_, true, "Login Screen", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    public void decision_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(decision_screen));
            Scene decision = new Scene(root);
            switchScene(decision, true, "Decision Screen", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void choice_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(choice_screen));
            Scene choice = new Scene(root);
            switchScene(choice, true, "Choice Screen", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void lessons_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(lessons_screen));
            Scene lessons = new Scene(root);
            switchScene(lessons, true, "Lessons Screen", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void about_scene() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource(about_screen));
        Scene about = new Scene(root);
        switchScene(about, true, "About Screen", false);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

    public void admin_students_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(admin_students_screen));
            Scene admin_students = new Scene(root);
            switchScene(admin_students, true, "Add Students", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void admin_students_screen(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(admin_students_view));
            Scene admin_students_ = new Scene(root);
            switchScene(admin_students_, true, "View Students", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void admin_marks_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(admin_marks_screen));
            Scene admin_marks = new Scene(root);
            switchScene(admin_marks, true, "Add Student Marks", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void admin_login_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(admin_login_screen));
            Scene admin_login = new Scene(root);
            switchScene(admin_login, true, "Admin Login Screen", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void admin_records_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(admin_records_screen));
            Scene admin_records = new Scene(root);
            switchScene(admin_records, true, "Admin Records Screen", true);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void user_image_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(user_image_screen));
            Scene user_image = new Scene(root);
            switchScene(user_image, true, "Image Lessons", true);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void user_login_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(user_login_screen));
            Scene user_login = new Scene(root);
            switchScene(user_login, true, "User Login Screen", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void user_video_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(user_video_screen));
            Scene user_video = new Scene(root);
            switchScene(user_video, true, "Video Lessons", true);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void user_profile_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(user_profile_screen));
            Scene user_profile = new Scene(root);
            switchScene(user_profile, true, "Profile Screen", true);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
