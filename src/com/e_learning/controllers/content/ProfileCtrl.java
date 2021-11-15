package com.e_learning.controllers.content;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

import java.sql.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import com.e_learning.modals.AlertModule;
import com.e_learning.modals.SubjectTable;
import com.e_learning.database.Sqlite;
import com.e_learning.controllers.misc.SceneCtrl;
import com.e_learning.controllers.misc.LoginCtrl;
import com.e_learning.app.App;


public class ProfileCtrl implements Initializable{
  SceneCtrl scene_switcher = new SceneCtrl();

  @FXML
  private SplitPane profile_;

  @FXML
  private Button lessonsBtn;

  @FXML
  private Text fnameLabel;

  @FXML
  private Text lnameLabel;

  @FXML
  private Text gradeLabel;

  @FXML
  private Text genderLabel;

  @FXML
  private MenuItem switch_user_;

  @FXML
  private MenuItem lessons_;

  @FXML
  private MenuItem exit;

  @FXML
  private MenuItem log_out;

  @FXML
  private MenuItem close;

  @FXML
  private MenuItem manual_;

  @FXML
  private MenuItem about_;


    @FXML
  TableView<SubjectTable> resultsTable;

  @FXML
  TableColumn<SubjectTable, String> math;

  @FXML
  TableColumn<SubjectTable, String> eng;

  @FXML
  TableColumn<SubjectTable, String> sci;

  @FXML
  TableColumn<SubjectTable, String> ict;

  @FXML
  TableColumn<SubjectTable, String> pe;

    ObservableList<SubjectTable> records;


 static String fname="Maximus";
 static String lname="Lexus";
 static String grade="0A";
 static String gender="Female";



 public static ObservableList<SubjectTable> loadTable(String name) {

    ObservableList<SubjectTable> loadList = FXCollections.observableArrayList();
    String query = "SELECT * FROM student_marks WHERE name = ?";
    try(Connection con = Sqlite.connector();
      PreparedStatement pstmt = con.prepareStatement(query);) {
     pstmt.setString(1, name);

      ResultSet res = pstmt.executeQuery();

      while (res.next()) {

        loadList.add(new SubjectTable(
          res.getString("maths"),
          res.getString("english"),
          res.getString("science"),
          res.getString("pe"),
          res.getString("ict")));
        }
        System.out.println(loadList.size());

      } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      System.out.println(e.getMessage());
      System.out.println("Error loading Table");

    }
    return loadList;
  }
@FXML

//Profile details begin here
 public static void profileScreen(String username){
      String query = "SELECT * FROM student_details WHERE fname = ?";
    try (
      Connection con = Sqlite.connector();
      //TODO Get string from login and pass through here
      PreparedStatement pstmt = con.prepareStatement(query);){

      pstmt.setString(1, username);
      ResultSet res = pstmt.executeQuery();


      while (res.next()) {
        //TODO get rid of clunky code


     fname = res.getString("fname");
     lname = res.getString("lname");
     grade = res.getString ("grade");
     gender = res.getString("gender");
    }
    System.out.println(gender);


  } catch (Exception e) {
    // TODO: handle exception
    e.printStackTrace();
    System.out.println(e.getMessage());
    System.out.println("Error loading Table");

  }
}


@Override

  public void initialize(URL location, ResourceBundle resourceBundle){

  //TODO Fix the reds
  //  profileScreen("Rumbi");
    fnameLabel.setText(fname);
    lnameLabel.setText(lname);
    gradeLabel.setText(grade);
    genderLabel.setText(gender);

    System.out.println(grade);

    //Table View
    math.setCellValueFactory(new PropertyValueFactory<SubjectTable, String>("Maths"));
    eng.setCellValueFactory(new PropertyValueFactory<SubjectTable, String>("English"));
    sci.setCellValueFactory(new PropertyValueFactory<SubjectTable, String>("Science"));
    ict.setCellValueFactory(new PropertyValueFactory<SubjectTable, String>("Ict"));
    pe.setCellValueFactory(new PropertyValueFactory<SubjectTable, String>("Pe"));


    try {
      records = loadTable(fname);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //Set Records
    resultsTable.setItems(records);

  }

  
  @FXML
  private void lesson_screen(){
    profile_.getScene().getWindow().hide();
    scene_switcher.decision_scene();
  }
  @FXML
  private void switch_user() {
    profile_.getScene().getWindow().hide();
    scene_switcher.login_scene();
  }

  @FXML
  private void manual_btn() {
    App app = new App();
    try{
    app.manual_pdf();
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  @FXML
  private void about_btn() {
    // mysqlTable.getScene().getWindow().hide();
    scene_switcher.about_scene();
  }

  @FXML
  private void exitBtn(ActionEvent event) {
    App.closeApp();
  }



}


