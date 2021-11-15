package com.e_learning.controllers;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import com.e_learning.modals.AlertModule;

public class MediaCtrl {

        Stage stage = new Stage();
        //     String video_1 = "../../resources/content/videos/Kabaneri_Of_The_Iron_Fortress/vlc-record-2021-03-30-17h48m37s-EP.3.360p.mp4-.mp4";
         
        // Media media = new Media(new File(video_1).toURI().toString());
         
        // MediaPlayer mediaplayer = new MediaPlayer(media);  
         
        // MediaView mediaView = new MediaView (mediaplayer);
        private Media media;
        private MediaPlayer mediaPlayer;
        private MediaView mediaView;
        private String mediaFile ="";

        public void video(String file){
                media = new Media(new File(file).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);

                mediaPlayer.setAutoPlay(true);
                // mediaPlayer.setOnReady(() -> stage.sizeToScene());
                // mediaView = new MediaView(mediaPlayer);
                mediaPlayer.setOnReady(new Runnable() {
                        @Override
                        public void run(){
                                //         mediaplayer.
                        }
                });
                
               
        }

        // public void playVid(){
        //         mediaplayer.play();
        // }

        // public void pauseVid(){
        //         mediaplayer.pause();
        // }

        // public void stopVid(){
        //         mediaplayer.stop();
        // }

                Button button1 = new Button("Play");
        Button button2 = new Button("Pause");
        Button button3 = new Button("Stop");

        //         button1.setOnAction(e -> {
        //     mediaplayer.play();
        // });
         
        // button2.setOnAction(e -> {
        //     mediaplayer.pause();
        // });
         
        // button3.setOnAction(e -> {
        //     mediaplayer.stop();
        // });

}
