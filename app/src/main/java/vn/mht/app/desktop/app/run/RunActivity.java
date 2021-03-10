package vn.mht.app.desktop.app.run;

import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.*;
import tray.models.CustomStage;
import vn.mht.app.desktop.ActivityScreen;
import vn.mht.app.desktop.FragmentScreen;
import vn.mht.app.desktop.Main;
import vn.mht.app.desktop.MainViewModel;
import vn.mht.app.desktop.app.run.node.config.ConfigFragment;
import vn.mht.app.desktop.app.run.node.room.TalkListFragment;
import vn.mht.app.desktop.app.run.node.system.SystemConfigFragment;
import vn.mht.app.desktop.app.setting.SettingViewModel;

import java.io.IOException;
import java.net.URL;


public class RunActivity implements ActivityScreen {
    private CustomStage stage;
    private Pane rootLayout;
    private AnimationType animationType;
    private TrayAnimation animator;
    private AnimationProvider animationProvider;
    private EventHandler<ActionEvent> onDismissedCallBack, onShownCallback, onClickCallBack;


    private Pane navigatePane;
    private final RunViewModel runViewModel;
    private JFXPopup settingsPopup;
    private StackPane btnOptionSetting;
    private StackPane btnListTalk;
    private StackPane btnQuickFav;
    private Parent rootstack;
    private final FXMLLoader loader;
    private final TalkListFragment mTalkListFragment;
    private final ConfigFragment mConfigFragment;
    private final SystemConfigFragment mSystemConfigFragment;
    private FragmentScreen currentFragment;

    public RunActivity(RunViewModel viewModel, FXMLLoader fxmlLoader, TalkListFragment talkListFragment, ConfigFragment configFragment, SystemConfigFragment systemConfigFragment) {
        runViewModel = viewModel;
        loader = fxmlLoader;
        mTalkListFragment = talkListFragment;
        mConfigFragment = configFragment;
        mSystemConfigFragment = systemConfigFragment;
        initViewComponent();
    }

    private void initViewComponent() {
        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(Main.class.getResourceAsStream("/fonts/icomoon.svg"),
                        "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();

        try {
            rootstack = loader.load();

            rootLayout = (Pane) loader.getNamespace().get("rootLayout");

            navigatePane = (Pane) loader.getNamespace().get("navigatePane");

            btnOptionSetting = (StackPane) loader.getNamespace().get("btnOptionSetting");
            btnListTalk = (StackPane) loader.getNamespace().get("btnListTalk");
            btnQuickFav = (StackPane) loader.getNamespace().get("btnQuickFav");

            FXMLLoader subLoader = new FXMLLoader(getClass().getResource("/fxml/popup/MainPopup.fxml"));
            subLoader.setController(new InputController());
            settingsPopup = new JFXPopup(subLoader.load());
            initStage();
            initAnimations();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAnimations() {
        animationProvider = new AnimationProvider(new FadeAnimation(stage, 0.2), new SlideAnimation(stage, 0.2), new PopupAnimation(stage, 0.2));
        //Default animation type
//        setAnimationType(AnimationType.POPUP);
        setAnimationType(AnimationType.FADE);
    }

    private void initStage() {

        stage = new CustomStage(rootLayout, StageStyle.DECORATED, null);

        JFXDecorator decorator = new JFXDecorator(stage, rootstack);//Custom
        decorator.setCustomMaximize(true);
        decorator.setGraphic(new SVGGlyph(""));//Icon
        decorator.setMaximized(false);

        double width = 800;
        double height = 600;
        try {
            Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
            width = bounds.getWidth() / 4;
            height = bounds.getHeight() / 1.35;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(decorator, width, height);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
                JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
                Main.class.getResource("/css/jfoenix-main-demo.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Demo");
        stage.setFullScreen(false);
        stage.setResizable(true);
//        stage.setLocation(stage.getBottomRight());//nimationType.POPUP

        currentFragment = mTalkListFragment;
        Node childNode = mTalkListFragment.getParentNode();
        showNode(childNode);

        stage.setOnCloseRequest(event -> {
            System.out.println(this.getClass() + "->OnCloseRequest");
        });

//        btnOptionSetting.setOnMouseClicked(e ->
//                settingsPopup.show(btnOptionSetting,
//                        JFXPopup.PopupVPosition.BOTTOM,
//                        JFXPopup.PopupHPosition.LEFT,
//                        16,
//                        -42));
////                settingsPopup.show(optionsBurger,
////                        JFXPopup.PopupVPosition.BOTTOM,
////                        JFXPopup.PopupHPosition.LEFT));

        btnOptionSetting.setOnMouseClicked(event -> {

            if (currentFragment instanceof SystemConfigFragment) {

            } else {
                currentFragment.terminate();
                currentFragment = mSystemConfigFragment;
                showNode(mSystemConfigFragment.getParentNode());
            }
        });
        btnListTalk.setOnMouseClicked(event -> {
            if (currentFragment instanceof TalkListFragment) {

            } else {
                currentFragment.terminate();
                currentFragment = mTalkListFragment;
                showNode(mTalkListFragment.getParentNode());
            }
        });

        btnQuickFav.setOnMouseClicked(event -> {

            if (currentFragment instanceof ConfigFragment) {

            } else {
                currentFragment.terminate();
                currentFragment = mConfigFragment;
                showNode(mConfigFragment.getParentNode());
            }
        });

        runViewModel.getNodeNavigate(node -> showNode(node));
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

    public void showNode(Node newPane) {

        navigatePane.getChildren().setAll(newPane);
    }


    private void onShown() {
        if (onShownCallback != null) {
            onShownCallback.handle(new ActionEvent());
        }


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
        currentFragment.terminate();
        runViewModel.dispose();
        stage.close();
    }

    public static final class InputController {
        @FXML
        private JFXListView<?> toolbarPopupList;

        // close application
        @FXML
        private void submit() {
            if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) {
                Platform.exit();
            }
        }
    }
}
