package com.e_learning.controllers.misc;

import javafx.fxml.*;
import javafx.scene.control.Button;
import com.e_learning.controllers.misc.SceneCtrl;
import javafx.scene.layout.AnchorPane;

public class DecisionCtrl {
    SceneCtrl scene_switcher = new SceneCtrl();

    @FXML
    private AnchorPane decision_screen;

    @FXML
    private Button gallery_;

    @FXML
    private Button video_;

    @FXML
    private void gallery_btn(){
        decision_screen.getScene().getWindow().hide();
        scene_switcher.user_image_scene();
    }

    @FXML
    private void video_btn(){
        decision_screen.getScene().getWindow().hide();
        scene_switcher.user_video_scene();
    }

}
