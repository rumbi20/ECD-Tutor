package com.e_learning.controllers.admin;

//import java.net.URL;
//import java.util.ResourceBundle;

import com.e_learning.database.Sqlite;
import com.e_learning.modals.AlertModule;

//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.control.*;
// import javafx.scene.control.Button;
// import javafx.scene.control.ChoiceBox;
// import javafx.scene.control.TextField;
import javafx.stage.Window;

public class Insert_Marks {


   @FXML
   private Button cancel_insert;

   @FXML
   private Button insert_update;
   
   @FXML
   private TextField name_entry;

   @FXML
   private TextField math_entry;

   @FXML
   private TextField eng_entry;

   @FXML
   private TextField sci_entry;

   @FXML
   private TextField ICT_entry;

   @FXML
   private TextField PE_entry;

   @FXML
   private void insert_rec(ActionEvent event) {
      Window owner = insert_update.getScene().getWindow();
     
      insert_student(owner);
   }

   public void insert_student(Window owner) {
      try {
         if (name_entry.getText().isEmpty() || math_entry.getText().isEmpty()
               || eng_entry.getText().isEmpty() || sci_entry.getText().isEmpty() || ICT_entry.getText().isEmpty() || PE_entry.getText().isEmpty() ) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "Please enter all fields");

         } else {
            Sqlite.insertMarksValues(name_entry.getText(), math_entry.getText(), eng_entry.getText(),
                  sci_entry.getText(), ICT_entry.getText(), PE_entry.getText());
            AlertModule.showAlert(Alert.AlertType.INFORMATION, owner, "Record Added", "Record added successfully");
            // TODO cannot auto reload results after entry
            // TODO Reload is done manually after window closes
            // TableCtrl.reloadTable();;
            insert_update.getScene().getWindow().hide();

         }

         /*
          * if (units_used_entry.getText().isEmpty()) {
          * AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error",
          * "This field is required");
          * 
          * } if (units_left_entry.getText().isEmpty()) {
          * AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error",
          * "This field is required");
          * 
          * }
          */
         // choiceSelector();

      } catch (Exception e) {
         AlertModule.showAlert(Alert.AlertType.ERROR, owner, "System Error", "Something is broken :(");
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
   }

   @FXML
   private void cancel(ActionEvent event) {
      cancel_insert.getScene().getWindow().hide();
   }

}
