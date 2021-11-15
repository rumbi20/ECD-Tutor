package com.e_learning.controllers.content;

import java.io.File;
import java.nio.file.*;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import com.e_learning.app.App;
import com.e_learning.controllers.content.IconFactory.ICONS;
import com.e_learning.modals.AlertModule;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.concurrent.Callable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.binding.Bindings;
import com.e_learning.controllers.misc.SceneCtrl;

public class VideoCtrl implements Initializable {
  SceneCtrl scene_switcher = new SceneCtrl();

    @FXML
    private MediaView media_;

    @FXML
    private Button play_;

    @FXML
    private Button stop_;

    @FXML
    private Button pause_;

    @FXML
    private Button open_;

    @FXML
    private MenuItem play_menu;

    @FXML
    private MenuItem pause_menu;

    @FXML
    private MenuItem stop_menu;

    @FXML
    private MenuItem open_file;

    @FXML
    private MenuItem exitApp;

    @FXML
    private MenuItem exit;

    @FXML
    private VBox parent_;

    @FXML
    private HBox parent_controls;

    @FXML
    private HBox menu_bar;

    @FXML
    private HBox volume_controls;

    @FXML
    private Label volume_;

    @FXML
    private Slider volume_slider;

    // @FXML
    // private Label volume_;

    @FXML
    private Slider video_slider;

    @FXML
    private Label current_time;

    @FXML
    private Label total_time;

    @FXML
    private Label speed_;

    @FXML
    private Button speed_btn;

    @FXML
    private Label full_screen_btn;

    // @FXML
    // private Button full_screen_btn;


    private MediaPlayer mediaPlayer;
    private Media media;

    private File input;
    private FileChooser file_Chooser = new FileChooser();

    private boolean atEndOfVideo = false;
    private boolean isPlaying = false;
    private boolean isMuted = true;

    /*
    *This is for all image views, to have a set height and width. 
    */


    Stage stage = new Stage();
    public File openFile_Chooser(){
        input = file_Chooser.showOpenDialog(stage.getOwner());

        if(input != null){
            System.out.println(input.getAbsoluteFile());
        }
        open_media(input.getAbsoluteFile());
        return input.getAbsoluteFile();
    }

   public void open_media(File file){
    try{
        media = new Media(file.toURI().toString());

        mediaPlayer = new MediaPlayer(media);

        //To clear any video playing from  before or currently playing during selection
       if(isPlaying == true){
        //TODO try and figure out how to stop media from intefering with each other
        // mediaPlayer.dispose();

        // media_.getMediaPlayer().stop(); 
        // media_.getMediaPlayer().dispose(); 
              ImageView pause_ = new ImageView(IconFactory.getImage(ICONS.PAUSE));
              pause_.setFitWidth(35);
              pause_.setFitHeight(35);
              play_.setGraphic(pause_);

      } 
      if(isPlaying == false){
        
        media_.setMediaPlayer(mediaPlayer);
              ImageView playing_ = new ImageView(IconFactory.getImage(ICONS.PLAY));
              playing_.setFitWidth(35);
              playing_.setFitHeight(35);
              play_.setGraphic(playing_);

       } 
        

        play_.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent actionEvent){
            Button play_button = (Button) actionEvent.getSource();
            bindCurrentTimeLabel();

            if(atEndOfVideo){
              //At the end of the video reset the slider to zero and restart video.
              video_slider.setValue(0);
              atEndOfVideo = false;
              isPlaying = false;
            }

            if (isPlaying){
              //if video is playing change the icon.
              ImageView pause_ = new ImageView(IconFactory.getImage(ICONS.PAUSE));
              pause_.setFitWidth(35);
              pause_.setFitHeight(35);
              play_button.setGraphic(pause_);
              
              mediaPlayer.pause();
              //Video is paused so change to false
              isPlaying = false;
            } else{
              ImageView playing_ = new ImageView(IconFactory.getImage(ICONS.PLAY));
              playing_.setFitWidth(35);
              playing_.setFitHeight(35);
              play_button.setGraphic(playing_);
              mediaPlayer.play();

              isPlaying = true;
            }
          }
        });

        //Set up bindings

        mediaPlayer.volumeProperty().bindBidirectional(volume_slider.valueProperty());

        //Bind value of slider to the volume of the video.
        volume_slider.valueProperty().addListener(new InvalidationListener(){
          @Override
          public void invalidated(Observable observable){
            //set volume to slider value
            mediaPlayer.setVolume(volume_slider.getValue());

            //If the video volume is not set to 0 and is not muted.
            //Set the label unmuted icon.
            if(mediaPlayer.getVolume() !=0.0){
              ImageView unmuted_ = new ImageView(IconFactory.getImage(ICONS.VOLUME));
              unmuted_.setFitWidth(35);
              unmuted_.setFitHeight(35);
              volume_.setGraphic(unmuted_);
              isMuted = false;
            } else {
              //if video is muted set muted speaker icon.
              ImageView mute_ = new ImageView(IconFactory.getImage(ICONS.MUTE));
              mute_.setFitWidth(35);
              mute_.setFitHeight(35);
              volume_.setGraphic(mute_);
              isMuted = true;
            }
          }
        });

        mediaPlayer.play();

        speed_.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent mouseEvent){
            if(speed_.getText().equals("1X")){
              speed_.setText("2X");
              mediaPlayer.setRate(2.0);
            } else{
              speed_.setText("1X");
              mediaPlayer.setRate(1.0);
            }
          }
        });

        //Check is volume is isMuted
        //Volume for media player only deals with values of 0.0 -> 1.0.
        volume_.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent mouseEvent){
            //if video is muted and volume button is clicked
            //which should unmute the video.
            if(isMuted){
              ImageView not_mute = new ImageView(IconFactory.getImage(ICONS.VOLUME));
              not_mute.setFitWidth(35);
              not_mute.setFitHeight(35);

              volume_.setGraphic(not_mute);
              volume_slider.setValue(0.2);
              isMuted = false;
            } else {
              //if video is muted changet the image.
              ImageView mute_ = new ImageView(IconFactory.getImage(ICONS.MUTE));
              mute_.setFitWidth(35);
              mute_.setFitHeight(35);
              volume_.setGraphic(mute_);
              volume_slider.setValue(0);
              isMuted = true;
            }
          }
        });

        volume_.setOnMouseEntered(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent mouseEvent){
            if(volume_controls.lookup("#volume_slider") == null){
              volume_controls.getChildren().add(volume_slider);
              volume_slider.setValue(mediaPlayer.getVolume());
            }
          }
        });

        volume_controls.setOnMouseExited(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent mouseEvent){
            volume_controls.getChildren().remove(volume_slider);
          }
        });

        //Bind height of the video
        parent_.sceneProperty().addListener(new ChangeListener<Scene>(){
          @Override
          public void changed(ObservableValue<? extends Scene> ObservableValue, Scene scene, Scene newScene){
            if(scene == null && newScene !=null){
              //Match height of video to height of scene.
              media_.fitHeightProperty().bind(newScene.heightProperty().subtract(parent_controls.heightProperty().add(20)));
              media_.fitWidthProperty().bind(newScene.widthProperty());
            }
          }
        });


        //bind time
        mediaPlayer.totalDurationProperty().addListener(new ChangeListener<Duration>(){
          @Override
          public void changed(ObservableValue<? extends Duration> ObservableValue, Duration oldTime, Duration newTime){
            //Duration is orginally in milliseconds.
            video_slider.setMax(newTime.toSeconds());
            total_time.setText(getTime(newTime));
          }
        });

        //Value changing property shows if slider is being changed at the moment.
        //when true the current value will be changed.

        video_slider.valueChangingProperty().addListener(new ChangeListener<Boolean>(){
          @Override
          public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean isChanging){
            bindCurrentTimeLabel();
            //once slider has stopped changing the video is set to selected time
            //this is after user stops moving the slider.
            if(!isChanging){
              mediaPlayer.seek(Duration.seconds(video_slider.getValue()));
            }
          }
        });

        video_slider.valueProperty().addListener(new ChangeListener<Number>(){
          @Override
          public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue){
            bindCurrentTimeLabel();
            //get current time of the video in seconds
            double currentTime = mediaPlayer.getCurrentTime().toSeconds();
            if(Math.abs(currentTime - newValue.doubleValue()) > 0.5){
              mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
            }
            labelsMatchEndOfVideo(current_time.getText(), total_time.getText());
          }
        });

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
          @Override
          public void changed(ObservableValue<? extends Duration> observableValue, Duration oldTime, Duration newTime){
            bindCurrentTimeLabel();
            if(!video_slider.isValueChanging()){
              video_slider.setValue(newTime.toSeconds());
            }
            labelsMatchEndOfVideo(current_time.getText(), total_time.getText());
          }
        });

        mediaPlayer.setOnEndOfMedia(new Runnable(){
          @Override
          public void run(){
            play_.setGraphic(new ImageView(IconFactory.getImage(ICONS.RESTART)));
            atEndOfVideo = true;
            //set current time to final time so it doesnt get rounded up.
            //for example the video could end with 00:39 /00:40.
            if(!current_time.textProperty().equals(total_time.textProperty())){
              current_time.textProperty().unbind();
              current_time.setText(getTime(mediaPlayer.getTotalDuration()) + " / ");
            }
          }
        });


    } catch(Exception e){
        e.printStackTrace();
        e.getMessage();
    }
  }


   @Override
   public void initialize(URL url, ResourceBundle resource){

        //TODO fix setgraphic
       ImageView play_icon = new ImageView(IconFactory.getImage(ICONS.PLAY));
        play_icon.setFitWidth(35);
        play_icon.setFitHeight(35);

        play_.setGraphic(play_icon);
              
        ImageView not_mute = new ImageView(IconFactory.getImage(ICONS.VOLUME));
        not_mute.setFitWidth(35);
        not_mute.setFitHeight(35);

        volume_.setGraphic(not_mute);

        speed_.setText("1X");
              
        ImageView full_Screen = new ImageView(IconFactory.getImage(ICONS.FULL_SCREEN));
        full_Screen.setFitWidth(35);
        full_Screen.setFitHeight(35);

        full_screen_btn.setGraphic(full_Screen);
       
        ImageView open_icon = new ImageView(IconFactory.getImage(ICONS.OPEN));
        open_icon.setFitWidth(35);
        open_icon.setFitHeight(35);

        open_.setGraphic(open_icon);
              
       ImageView stop_icon = new ImageView(IconFactory.getImage(ICONS.STOP));
        stop_icon.setFitWidth(35);
        stop_icon.setFitHeight(35);

        stop_.setGraphic(stop_icon);
              

        //TODO replace label with buttons
        //Unable to set height preferences when dealing with icons.

        volume_controls.getChildren().remove(volume_slider);

   
        full_screen_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent mouseEvent){
            Label label = (Label) mouseEvent.getSource();
            Stage stage = (Stage) label.getScene().getWindow();

            if(stage.isFullScreen()){
              stage.setFullScreen(false);
      
              ImageView full_Screen = new ImageView(IconFactory.getImage(ICONS.FULL_SCREEN));
              full_Screen.setFitWidth(35);
              full_Screen.setFitHeight(35);

              full_screen_btn.setGraphic(full_Screen);
            } else {
              stage.setFullScreen(true);

              ImageView exit_full_Screen = new ImageView(IconFactory.getImage(ICONS.EXIT_FULL_SCREEN));
              exit_full_Screen.setFitWidth(35);
              exit_full_Screen.setFitHeight(35);

              full_screen_btn.setGraphic(exit_full_Screen);

              stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
                @Override
                public void handle(KeyEvent keyEvent){
                  if(keyEvent.getCode() == KeyCode.ESCAPE){
                  ImageView full_Screen = new ImageView(IconFactory.getImage(ICONS.FULL_SCREEN));
                  full_Screen.setFitWidth(35);
                  full_Screen.setFitHeight(35);

                  full_screen_btn.setGraphic(full_Screen); 
                  }
                }
              });
            }
          }
        });

   }
  

   public String getTime(Duration time){
     int hours = (int) time.toHours();
     int minutes = (int) time.toMinutes();
     int seconds = (int) time.toSeconds();

     //Fix problem of timer going above 60seconds.
     if(seconds > 59) seconds = seconds % 60;
     if(minutes > 59) minutes = minutes % 60;
     if(hours > 59) hours = hours % 60;

     //Dont show hours unless video has been playing for more than an hour

     if(hours >0) return String.format("%d:%02d:%02d",
        hours,
        minutes,
        seconds);
        else return String.format("%02d:%02d",
        minutes, seconds
        );
   }


   //check the text of the time labels match when at the end of the video.
   public void labelsMatchEndOfVideo(String timeLabel, String totalTimeLabel){
     for(int i=0; i<totalTimeLabel.length(); i++){
       if(timeLabel.charAt(i) != totalTimeLabel.charAt(i)){
         atEndOfVideo = false;
         if(isPlaying) play_.setGraphic(new ImageView(IconFactory.getImage(ICONS.PAUSE)));
         else play_.setGraphic(new ImageView(IconFactory.getImage(ICONS.PLAY)));
         break;
       } else {
         atEndOfVideo = true;
         play_.setGraphic(new ImageView(IconFactory.getImage(ICONS.RESTART)));
       }
     }
   }

   public void bindCurrentTimeLabel(){
     //bind the text label to current time of video.
     //allows time to update along with the video.
     current_time.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
       @Override
       public String call() throws Exception {
         //return hours, minutes and seconds of the video.
         //%d is an integer, usually the time given is in milliseconds.
         return getTime(mediaPlayer.getCurrentTime()) + " / ";
       }
     }, mediaPlayer.currentTimeProperty()));
   }

/*
 * This section is for user actions.
 * More like where we call the FXML actions
*/

   @FXML
    private void open_File(){
        openFile_Chooser();
    }

    @FXML
    private void stop_btn(){
        mediaPlayer.stop();
    }

    @FXML
    private void play_btn(){
        mediaPlayer.play();
    }

    @FXML
    private void pause_btn(){
        mediaPlayer.pause();
    }

    @FXML
    private void close_App(){
        App.closeApp();
    }

   @FXML
   private void switch_user_(){
       parent_.getScene().getWindow().hide();
       scene_switcher.login_scene();
   }
   @FXML
   private void profile_(){
       parent_.getScene().getWindow().hide();
       scene_switcher.user_profile_scene();
   }

   @FXML
   private void about_(){
       parent_.getScene().getWindow().hide();
       scene_switcher.about_scene();
   }


}

/*
*IconFactory is to manage all the icons.
*The below method does not work, as its not possible,
* to recall a node/component twice in the same scene.
  //Image icon_full_screen = new Image(new File(full_screen_image).toURI().toString());
  //full_screen_icon = new ImageView(icon_full_screen);
  //full_screen_icon.setFitWidth(35);
  //full_screen_icon.setFitHeight(35);
*This way is easier to deal
*/

class IconFactory{
    /*
    *This format does not work for IconFactory
    // String play_image = "/resources/content/icons/video_player/play.png";
    *Recommended file paths are below.
    */
    static String play_image ="/resources/content/icons/video_player/play_icon.png";
    static String pause_image = "/resources/content/icons/video_player/pause_icon.png";
    static String stop_image = "/resources/content/icons/video_player/stop_icon.png";
    static String volume_image = "/resources/content/icons/video_player/volume.png";
    static String restart_image = "/resources/content/icons/video_player/restart.png";
    static String full_screen_image = "/resources/content/icons/video_player/full_screen.png";
    static String mute_image = "/resources/content/icons/video_player/mute.png";
    static String exit_full_screen_image = "/resources/content/icons/video_player/exit_full_screen.png";
    static String open_image = "/resources/content/icons/video_player/eject.png";


    private static HashMap<ICONS, Image> images = new HashMap<ICONS, Image>();

    public enum ICONS{
        PLAY, PAUSE, STOP, NEXT, OPEN, RESTART, VOLUME, MUTE, FULL_SCREEN, EXIT_FULL_SCREEN
    }

    public static Image getImage(ICONS icon){
        if(!images.containsKey(icon)){
            switch (icon){
                case PLAY:
                        images.put(icon, new Image(IconFactory.class.getResourceAsStream(play_image)));
                        break;
                case PAUSE:
                        images.put(icon, new Image(IconFactory.class.getResourceAsStream(pause_image)));
                        break;
                case VOLUME:
                        images.put(icon, new Image(IconFactory.class.getResourceAsStream(volume_image)));
                        break;
                case RESTART:
                        images.put(icon, new Image(IconFactory.class.getResourceAsStream(restart_image)));
                        break;
                case FULL_SCREEN:
                        images.put(icon, new Image(IconFactory.class.getResourceAsStream(full_screen_image)));
                        break;
                case MUTE:
                        images.put(icon, new Image(IconFactory.class.getResourceAsStream(mute_image)));
                        break;
                case EXIT_FULL_SCREEN:
                        images.put(icon, new Image(IconFactory.class.getResourceAsStream(exit_full_screen_image)));
                        break;
                case STOP:
                        images.put(icon, new Image(IconFactory.class.getResourceAsStream(stop_image)));
                        break;
                case OPEN:
                        images.put(icon, new Image(IconFactory.class.getResourceAsStream(open_image)));
                        break;
                        default:
                            return null;
            }
        }
        return images.get(icon);
    }
}