package vn.mht.app.desktop.app.setting;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.*;
import tray.models.CustomStage;
import vn.mht.app.desktop.ActivityScreen;


import java.io.IOException;


public class SettingActivity implements ActivityScreen {
    private CustomStage stage;
    private Pane rootLayout;
    private AnimationType animationType;
    private TrayAnimation animator;
    private AnimationProvider animationProvider;
    private EventHandler<ActionEvent> onDismissedCallBack, onShownCallback, onClickCallBack;

    private Button btnShow;
    private Button btnHide;
    private TextField myTextField;

    private final SettingViewModel settingViewModel;


    public SettingActivity(SettingViewModel viewModel) {
        settingViewModel = viewModel;
        initViewComponent();
    }

    private void initViewComponent() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingScene.fxml"));
            Parent root = loader.load();

            rootLayout = (Pane) loader.getNamespace().get("rootLayout");
            btnShow = (Button) loader.getNamespace().get("btnShow");
            btnHide = (Button) loader.getNamespace().get("btnHide");
            myTextField = (TextField) loader.getNamespace().get("myTextField");


            initStage();
            initAnimations();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAnimations() {
        animationProvider = new AnimationProvider(new FadeAnimation(stage, 0.2), new SlideAnimation(stage, 0.2), new PopupAnimation(stage, 0.2));
        //Default animation type
        setAnimationType(AnimationType.POPUP);
    }

    private void initStage() {

        stage = new CustomStage(rootLayout, StageStyle.DECORATED, null);
        stage.setFullScreen(false);
        stage.setResizable(false);
        Scene showScene = new Scene(rootLayout);
        stage.setScene(showScene);
        stage.setLocation(stage.getCenter());

        stage.setOnCloseRequest(event -> settingViewModel.dispose());

        btnShow.setOnAction(event -> settingViewModel.updateDateTime());
        btnHide.setOnAction(event -> settingViewModel.hidePannel());


    }

    public void showDateTime(String datetime) {
        myTextField.setText(datetime);
    }

    public boolean isShowing() {
        return animator.isShowing();
    }

    /**
     * Shows and dismisses the tray notification
     *
     * @param dismissDelay How long to delay the start of the dismiss animation
     */
    public void showAndDismiss(Duration dismissDelay) {

        if (isShowing()) {
            dismiss();
        } else {
            stage.show();

            onShown();
            animator.playSequential(dismissDelay);
        }

        onDismissed();
    }

    /**
     * Displays the notification tray
     */
    public void showAndWait() {

        if (!isShowing()) {
            stage.show();

            animator.playShowAnimation();

            onShown();
        }
    }

    /**
     * Dismisses the notifcation tray
     */
    public void dismiss() {

        if (isShowing()) {
            animator.playDismissAnimation();
            onDismissed();
        }
    }

    private void onShown() {
        if (onShownCallback != null) {
            onShownCallback.handle(new ActionEvent());
        }
        settingViewModel.getTimeUpdate(s -> showDateTime(s));
    }

    private void onDismissed() {
        if (onDismissedCallBack != null)
            onDismissedCallBack.handle(new ActionEvent());

    }

    /**
     * Sets an action event for when the tray has been dismissed
     *
     * @param event The event to occur when the tray has been dismissed
     */
    public void setOnDismiss(EventHandler<ActionEvent> event) {
        onDismissedCallBack = event;
    }

    /**
     * Sets an action event for when the tray has been shown
     *
     * @param event The event to occur after the tray has been shown
     */
    public void setOnShown(EventHandler<ActionEvent> event) {
        onShownCallback = event;
    }

    public void setAnimationType(AnimationType type) {
        this.setAnimationType(type, 1.0);
    }

    public void setAnimationType(AnimationType type, double delaySpeedMultiple) {
        animator = animationProvider.findFirstWhere(a -> a.getAnimationType() == type);
        animationType = type;
    }

    public AnimationType getAnimationType() {
        return animationType;
    }


    @Override
    public void terminate() {
        settingViewModel.dispose();
        stage.close();
    }
}
