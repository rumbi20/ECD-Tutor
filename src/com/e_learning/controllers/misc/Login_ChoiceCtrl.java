package com.e_learning.controllers.misc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Login_ChoiceCtrl{
  SceneCtrl scene_switcher = new SceneCtrl();
  @FXML
  private AnchorPane login_choice;

  @FXML
  private ImageView gear_icon;

  @FXML
  private ImageView lesson_icon;

  @FXML
  private Button admin_;

  @FXML
  private Button lessons_;

  @FXML
  private void admin_btn(){
    scene_switcher.admin_login_scene();
    login_choice.getScene().getWindow().hide();
  }

  @FXML
  private void lesson_btn(){
    scene_switcher.user_login_scene();
    login_choice.getScene().getWindow().hide();
  }



}
