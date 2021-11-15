package com.e_learning.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.*;
import javafx.event.*;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.util.ResourceBundle;

// import javax.swing.Icon;
import com.e_learning.app.App;
import com.e_learning.modals.AlertModule;
import com.e_learning.modals.Students;
import com.e_learning.database.Sqlite;
import com.e_learning.controllers.misc.SceneCtrl;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import java.nio.file.*;

public class StudentCtrl implements Initializable {
  Stage stage = new Stage();
  SceneCtrl scene_switcher = new SceneCtrl();
  Sqlite sqlite = new Sqlite();
  Window owner = stage.getOwner();

  @FXML
  TableView<Students> mysqlTable;

  @FXML
  TableColumn<Students, String> id_;

  @FXML
  TableColumn<Students, String> fname_;

  @FXML
  TableColumn<Students, String> lname_;

  @FXML
  TableColumn<Students, String> grade_;

  @FXML
  TableColumn<Students, String> gender_;

  @FXML
  TableColumn<Students, String> teacher_;

  @FXML
  private TextField searchBar;

  @FXML
  private MenuItem printQuery;

  @FXML
  private Button subQuery;

  @FXML
  private MenuItem refresh;

  @FXML
  private MenuItem exit;

  @FXML
  private MenuItem add_student;

  @FXML
  private MenuItem switch_user_;

  @FXML
  private MenuItem manual_;

  @FXML
  private MenuItem about_;

  @FXML
  private MenuItem export_current_view;

  @FXML
  private MenuItem delete_by_id_context;

  @FXML
  private Button discard_;

  @FXML
  private Button update_btn;

  @FXML
  private MenuItem exportBtn;

  @FXML
  private MenuItem export_btn;

  @FXML
  private MenuItem export_view;

  @FXML
  private MenuItem export_view_btn;

  @FXML
  private MenuItem delete_by_id;

  @FXML
  private MenuItem view_students;

  @FXML
  private MenuItem view_grades;

  @FXML
  private TextField id_entry;

  @FXML
  private TextField fname_entry;

  @FXML
  private TextField lname_entry;

  @FXML
  private TextField grade_entry;

  @FXML
  private TextField gender_entry;

  @FXML
  private TextField teacher_entry;

  Students content;
  ObservableList<Students> records;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    assert mysqlTable != null : "Failed to load databaseTable";

    id_.setCellValueFactory(new PropertyValueFactory<Students, String>("Student_Id"));
    fname_.setCellValueFactory(new PropertyValueFactory<Students, String>("fname"));
    lname_.setCellValueFactory(new PropertyValueFactory<Students, String>("lname"));
    grade_.setCellValueFactory(new PropertyValueFactory<Students, String>("grade_"));
    gender_.setCellValueFactory(new PropertyValueFactory<Students, String>("gender_"));
    teacher_.setCellValueFactory(new PropertyValueFactory<Students, String>("Teacher_"));

    try {
      records = loadTable();
      table_click_Listener();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // Set Records
    mysqlTable.setItems(records);
  }

  public static ObservableList<Students> loadTable() {

    ObservableList<Students> loadList = FXCollections.observableArrayList();

    try {
      Connection con = Sqlite.connector();

      ResultSet res = con.createStatement().executeQuery("SELECT * FROM student_details");

      while (res.next()) {

        loadList.add(new Students(res.getString("id"), res.getString("fname"), res.getString("lname"),
            res.getString("grade"), res.getString("gender"), res.getString("teacher_")));
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

  public static ObservableList<Students> searchDB(String query, Window owner) {
    ObservableList<Students> queryList = FXCollections.observableArrayList();
    String search = "SELECT * FROM student_marks WHERE name LIKE '" + query + "%'";
    try (Connection conn = Sqlite.connector();

        PreparedStatement pstmt = conn.prepareStatement(search);) {
      ResultSet res = pstmt.executeQuery();

      try {
        while (res.next()) {
          queryList.add(new Students(res.getString("id"), res.getString("fname"), res.getString("lname"),
              res.getString("grade"), res.getString("gender"), res.getString("teacher_")));
        }
        // Error still triggered even if results are found
        // if(!res.next()){
        // AlertModule.showAlert(Alert.AlertType.ERROR, owner, "System Error", "Failed
        // to load results from DB");

        // System.out.println("Search failed");
        // }
      } catch (Exception e) {
        AlertModule.showAlert(Alert.AlertType.ERROR, owner, "System Error", "Nothing matches your search");
        System.out.println(e);
        System.out.println("Nothing found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      // TODO: handle exception
    }
    return queryList;
  }

  public void deleteRow() {
    ObservableList<Students> selectedRow, allRows;
    allRows = mysqlTable.getItems();
    selectedRow = mysqlTable.getSelectionModel().getSelectedItems();

    String id_ = mysqlTable.getSelectionModel().getSelectedItem().getStudent_Id();
    sqlite.delete_student_row_by_id(id_);
    selectedRow.forEach(allRows::remove);
    reloadBtn();
  }

  public void table_click_Listener() {
    mysqlTable.getSelectionModel().selectedItemProperty().addListener((obs, old_selection, new_selection) -> {
      id_entry.setText(new_selection.getStudent_Id());
      fname_entry.setText(new_selection.getFname());
      lname_entry.setText(new_selection.getLname());
      grade_entry.setText(new_selection.getGrade_());
      gender_entry.setText(new_selection.getGender_());
      teacher_entry.setText(new_selection.getTeacher());
    });
  }

  @FXML
  private void exitBtn(ActionEvent event) {
    App.closeApp();
  }

  @FXML
  private void add_student_screen() {
    add_student();
  }

  public void add_student() {
    scene_switcher.admin_students_scene();
  }

  @FXML
  private void printBtn(ActionEvent event) throws IOException {
    Window owner = mysqlTable.getScene().getWindow();
    ExportExcel.export_StudentsToExcel(owner);

  }

  @FXML
  private void searchBtn(ActionEvent event) {

    Window owner = subQuery.getScene().getWindow();
    try {
      records.removeAll();
      records = searchDB(searchBar.getText(), owner);
      mysqlTable.setItems(records);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // TODO cannot make static reference to FXML as they would crash program
  @FXML
  private void reloadBtn() {
    // Clear current view then load results
    records.removeAll();
    records = loadTable();
    mysqlTable.setItems(records);
  }

  @FXML
  private void export_tableView() {
    Workbook wb = new HSSFWorkbook();
    Sheet spreadsheet = wb.createSheet("Current TableView");

    Row row = spreadsheet.createRow(0);

    for (int i = 0; i < mysqlTable.getColumns().size(); i++) {
      row.createCell(i).setCellValue(mysqlTable.getColumns().get(i).getText());
    }

    for (int x = 0; x < mysqlTable.getItems().size(); x++) {
      row = spreadsheet.createRow(x + 1);
      for (int y = 0; y < mysqlTable.getColumns().size(); y++) {
        if (mysqlTable.getColumns().get(y).getCellData(x) != null) {
          row.createCell(y).setCellValue(mysqlTable.getColumns().get(y).getCellData(x).toString());
        } else {
          row.createCell(y).setCellValue("");
        }
      }
    }
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Export Excel Document");
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
        new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
        new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
        new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
        new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
    try {
      File saveFile = fileChooser.showSaveDialog(owner);
      String savePath = "Exported TableView.xlsx";
      FileOutputStream save_file = new FileOutputStream(savePath);
      fileChooser.setInitialFileName("Exported Table");
      // TODO Set initial filename not working when saving?

      Path src = Paths.get(savePath);
      Path dest = Paths.get(saveFile.getAbsolutePath());

      StandardCopyOption REPLACE_EXISTING = StandardCopyOption.REPLACE_EXISTING;
      StandardCopyOption COPY_ATTRIBUTES = StandardCopyOption.COPY_ATTRIBUTES;
      LinkOption NOFOLLOW_LINKS = LinkOption.NOFOLLOW_LINKS;

      if (saveFile != null) {
        try {
          // try to save workbook
          wb.write(save_file);

          // try to copy and delete file
          Files.copy(src, dest, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
          Files.delete(src);
          wb.close();
        } catch (IOException e) {
          e.getMessage();
          e.printStackTrace();
        }
      }
    } catch (FileNotFoundException e) {
      e.getMessage();
      e.printStackTrace();
    }
  }

  @FXML
  private void export_rec() {
    Window owner = mysqlTable.getScene().getWindow();
    ExportExcel.exportToExcel(owner);
  }

  @FXML
  private void delete_by_id_btn() {
    deleteRow();
  }

  @FXML
  private void switch_user() {
    mysqlTable.getScene().getWindow().hide();
    scene_switcher.login_scene();
  }

  @FXML
  private void confirm_update() {
    try {
      sqlite.updateStudent(fname_entry.getText(), lname_entry.getText(), grade_entry.getText(),
          gender_entry.getText(), teacher_entry.getText(), id_entry.getText());
      AlertModule.showAlert(Alert.AlertType.CONFIRMATION, owner, "Action Completed", "Record updated successfully");
    } catch (Exception e) {
      AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Failed to complete action", "Failed to update record");
      e.getMessage();
      e.printStackTrace();
    }
    reloadBtn();
  }

  @FXML
  private void discard_changes() {
    id_entry.clear();
    fname_entry.clear();
    lname_entry.clear();
    grade_entry.clear();
    gender_entry.clear();
    teacher_entry.clear(); 
  }

  @FXML
  private void manual_btn() {
  }

  @FXML
  private void about_btn() {
    scene_switcher.about_scene();
  }

  @FXML
  private void view_grades_(){
    mysqlTable.getScene().getWindow().hide(); 
   scene_switcher.admin_records_scene(); 
  }
}
