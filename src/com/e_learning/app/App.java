package com.e_learning.app;

import java.sql.SQLException;
import com.e_learning.database.Sqlite;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import com.e_learning.controllers.misc.SceneCtrl;
import com.qoppa.pdfViewerFX.PDFViewer;
import com.qoppa.pdf.PDFException;

public class App extends Application {

    SceneCtrl scene_switcher = new SceneCtrl();
    Sqlite sqlite = new Sqlite();
    Stage stage = new Stage();

    @Override
    public void start(Stage stage) throws Exception {

        scene_switcher.login_scene();
    }

    public static void main(String[] args) throws SQLException {
        Sqlite.main(args);
        launch();
    }

    public void manual_pdf(){
        PDFViewer pdf_ = new PDFViewer();
        try{
            pdf_.loadPDF(getClass().getResource("/resources/content/pdf/manual.pdf"));
            Scene pdf_manual = new Scene(pdf_);
            stage.setScene(pdf_manual);
            stage.show();
        } catch (PDFException e){
            e.printStackTrace();
        }
    }

    public static void closeApp() {
        Platform.exit();
        System.exit(0);
    }

}
