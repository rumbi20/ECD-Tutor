package com.e_learning.controllers.misc;


import java.net.URL;

import java.util.ResourceBundle;

import com.e_learning.controllers.content.ProfileCtrl;
import com.e_learning.database.Sqlite;
import com.e_learning.modals.AlertModule;



import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.e_learning.controllers.misc.SceneCtrl;


import javafx.stage.Window;

public class LoginCtrl {
    SceneCtrl scene_switcher = new SceneCtrl();

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPass;

    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button loginBtn;

    @FXML private Label username;

    Sqlite sqlite = new Sqlite();
    public static String name;

    @FXML
    private void login_Btn(ActionEvent event) {

        Window owner = loginBtn.getScene().getWindow();
        if (userName.getText().isEmpty()) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "User Name is required");
            return;
        }
        if (userPass.getText().isEmpty()) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "Password is required");
            return;

        }
        boolean auth;

        try {
            auth = Authenticate();
            if (auth) {
                System.out.println("User Found");
                AlertModule.showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful", "Welcome");
                loginBtn.getScene().getWindow().hide();
                ProfileCtrl.profileScreen(userName.getText());
                scene_switcher.user_profile_scene();
            } else {
                System.out.println("False");
                AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "Incorrect Details");
                System.out.println("Didnt find user");
                userName.clear();
                userPass.clear();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void initialize() {
        // Doesnt do much system loads either way
        try {
            System.out.println("Fxml Loaded");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Failed to import FXML");
        }

    }

    private boolean Authenticate() throws Exception {

        boolean val = sqlite.validateUser(userName.getText(), userPass.getText());
        if (val) {
            System.out.println("User Authenticated");
            return true;
        } else {
            System.out.println("Failed To Authenticate");
            return false;
        }

    }
}
