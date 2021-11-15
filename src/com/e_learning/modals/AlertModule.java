package com.e_learning.modals;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class AlertModule {
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String msg){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.initOwner(owner);
        alert.showAndWait();
    }    
}
