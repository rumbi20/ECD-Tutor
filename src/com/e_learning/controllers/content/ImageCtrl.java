package com.e_learning.controllers.content;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.e_learning.modals.AlertModule;

import javafx.fxml.*;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.*;
import com.e_learning.app.App;
import com.e_learning.controllers.misc.SceneCtrl;


public class ImageCtrl implements Initializable {
    SceneCtrl scene_switcher = new SceneCtrl();

    @FXML
    private StackPane image_screen;
    
    @FXML
    private ImageView image_view;

    @FXML
    private MenuItem imageOpen;

    @FXML
    private MenuItem closeApp;
    
    @FXML
    private MenuItem open_maths;

    @FXML
    private MenuItem open_english;

    @FXML
    private MenuItem open_sci;

    @FXML
    private MenuItem open_ict;

    @FXML
    private MenuItem open_pe;

    @FXML
    private MenuItem help_;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem switch_user_;

    @FXML
    private MenuItem logout_;

    @FXML
    private MenuItem profile_;

    @FXML
    private MenuItem about;

    @FXML
    private MenuItem exit_;


    private File input;
    private FileChooser file_Chooser = new FileChooser();

    String imagePath;

    // Window file_open = open_.getScene().getWindow();
    Stage stage = new Stage();
    public String openFile_Chooser() throws MalformedURLException{
        input = file_Chooser.showOpenDialog(stage.getOwner());

        if(input != null){
            try {
                System.out.println(input.toURI().toURL().toString());
                open_media(input.toURI().toURL().toString());
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return input.getPath();
    }

   public void open_media(String file){
    try{
        // image_view = new ImageView(file);
        Image imported_image = new Image(file);
        Double height = imported_image.getHeight();
       Double width = imported_image.getWidth();
       Double stageHeight = stage.getMinHeight();
       Double stageWidth = stage.getMinWidth();
       Double finalHeight= (height * 0.90);
       Double finalWidth= (width * 0.90);
        // image_view.setFitHeight(finalHeight);
        // image_view.setFitWidth(finalWidth);
        image_view.autosize();
        // image_view.isResizable();
        image_view.setPreserveRatio(true);
        image_view.setImage(imported_image);
        System.out.println(finalHeight + " " + finalWidth);
        if(image_view.isPreserveRatio()){
            if(imported_image.getHeight() > imported_image.getWidth()){
                image_view.setFitWidth(imported_image.getWidth());
                image_view.setFitHeight(0);
            } else {
                image_view.setFitWidth(0);
                image_view.setFitHeight(imported_image.getHeight());
            }
        } else {
            image_view.setFitWidth(imported_image.getWidth());
            image_view.setFitHeight(imported_image.getHeight());
        }

        // stage.setAlwaysOnTop(true);
        System.out.println(file);
    } catch(Exception e){
        e.printStackTrace();
        e.getMessage();
    }
   }

   @FXML
   private void openImage(){
     try {
        openFile_Chooser();
    } catch (MalformedURLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   }

   @FXML
   private void exitApp(){
     App.closeApp();
   }

   @FXML
   private void switch_user_(){
       image_screen.getScene().getWindow().hide();
       scene_switcher.login_scene();
   }
   @FXML
   private void profile_(){
       image_screen.getScene().getWindow().hide();
       scene_switcher.user_profile_scene();
   }

   @FXML
   private void about_(){
       image_screen.getScene().getWindow().hide();
       scene_switcher.about_scene();
   }


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){}

}
