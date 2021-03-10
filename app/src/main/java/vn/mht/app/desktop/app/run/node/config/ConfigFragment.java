package vn.mht.app.desktop.app.run.node.config;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import vn.mht.app.desktop.FragmentNode;
import vn.mht.app.desktop.FragmentScreen;

import java.io.IOException;

public class ConfigFragment extends FragmentNode<ConfigViewModel> implements FragmentScreen {

    private Button saveButton;

    private final ConfigViewModel viewModel;
    private final FXMLLoader loader;
    private Node parentNode;

    public ConfigFragment(ConfigViewModel viewModel, FXMLLoader fxmlLoader) {
        this.viewModel = viewModel;
        loader = fxmlLoader;
        initViewComponent();
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void initViewComponent() {
        try {
            parentNode = loader.load();//Parent root = loader.load();
            saveButton = (Button) loader.getNamespace().get("saveButton");
            initStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initStage() {
        viewModel.getTimeUpdate(s -> showDateTime(s));

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent paramT) {
                viewModel.updateDateTime();
            }
        });

    }

    public void showDateTime(String datetime) {
        System.out.println(datetime);
    }

    @Override
    public void terminate() {
        System.out.println("Fragment terminate()");
        viewModel.dispose();
    }
}
