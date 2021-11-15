package com.e_learning.controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import java.util.ResourceBundle;

import javax.naming.spi.InitialContextFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.sql.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.media.*;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import com.e_learning.modals.AlertModule;
// import com.e_learning.modals.TableModel;

import com.e_learning.database.Sqlite;
import com.e_learning.controllers.misc.LoginCtrl;
import com.e_learning.controllers.MediaCtrl;


public class LessonsCtrl implements Initializable {
    @FXML
    private SplitPane split_pane;

    @FXML
    private Button play_;

    @FXML
    private Button next_;

    @FXML
    private Button prev_;

    
    @FXML
    private MediaView media_;

    private MediaPlayer mediaPlayer;
    private Media media;
    private File file;

    MediaCtrl media_ctrl = new MediaCtrl();
    // String file = "../../resources/content/videos/vid_3.mp4";

    
        @FXML
        private void alert_(){
            Window owner = play_.getScene().getWindow();
            // playMedia();
            System.out.println("Prev Clicked");
        }
        @FXML
        private void click_(){
            Window owner = play_.getScene().getWindow();
            System.out.println("Pause Clicked");
        }
        
        @FXML
        private void clicker_(){
            Window owner = play_.getScene().getWindow();
            System.out.println("Next Clicked");
        }
        
        
        @Override
        public void initialize(URL location, ResourceBundle resourceBundle){

            //TODO MediaView not responding why. Its causing illegal access because its not found? 
            try{
            file = new File("src/com/resources/content/videos/【GMV】Uh Oh.wav");
            //TODO trouble when trying to create media player why?
            //TODO could be the formats or just a missing line of code?
        
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            media_.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
            } catch(MediaException er){
               
                er.printStackTrace();
               
                System.out.println(er.getMessage());
            }
            try {
                
                
                System.out.println("Fxml Loaded");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage() + "Failed to import FXML");
            }
        }
            public  void playMedia(){
                mediaPlayer.play();
            }


}
