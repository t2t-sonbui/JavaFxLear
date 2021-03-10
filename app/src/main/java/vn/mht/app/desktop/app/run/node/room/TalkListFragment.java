package vn.mht.app.desktop.app.run.node.room;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import vn.mht.app.desktop.FragmentNode;
import vn.mht.app.desktop.FragmentScreen;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TalkListFragment extends FragmentNode<TalkListViewModel> implements FragmentScreen {
    private AnchorPane rootLayout;

    private JFXButton saveButton;
    private JFXListView<Student> listView;

    private ObservableList<Student> studentObservableList;

    private final TalkListViewModel settingViewModel;
    private final FXMLLoader loader;
    private Node parentNode;

    public TalkListFragment(TalkListViewModel viewModel, FXMLLoader fxmlLoader) {
        settingViewModel = viewModel;
        loader = fxmlLoader;
        initViewComponent();
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void initViewComponent() {
        try {
            parentNode = loader.load();//Parent root = loader.load();
            rootLayout = (AnchorPane) loader.getNamespace().get("rootLayout");
            listView = (JFXListView<Student>) loader.getNamespace().get("listView");
            saveButton = (JFXButton) loader.getNamespace().get("saveButton");
            initStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initStage() {
        settingViewModel.getTimeUpdate(s -> showDateTime(s));
        studentObservableList = FXCollections.observableArrayList();
        //add some Students
        studentObservableList.addAll(
                new Student("John Doe", Student.GENDER.MALE),
                new Student("Jane Doe", Student.GENDER.FEMALE, true),
                new Student("Donte Dunigan", Student.GENDER.MALE),
                new Student("Gavin Genna", Student.GENDER.MALE, true),
                new Student("Darin Dear", Student.GENDER.MALE),
                new Student("Pura Petty", Student.GENDER.FEMALE),
                new Student("Herma Hines", Student.GENDER.FEMALE)
        );

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> paramObservableValue, Student studentOld, Student student) {
                System.out.println("List item selected: " + student);
            }
        });
        listView.setItems(studentObservableList);
        StudentListViewCell.ItemSelectedCallback itemSelectedCallback = (index, selected) -> {
            System.out.println("index:" + index + " with selected:" + selected);
            studentObservableList.get(index).setSelected(selected);
        };

        listView.setCellFactory(
                studentListView -> {
                    StudentListViewCell studentListViewCell = new StudentListViewCell();
                    studentListViewCell.setOnItemSelectedCallback(itemSelectedCallback);
                    return studentListViewCell;
                }
        );

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent paramT) {
                for (Student employee : studentObservableList) {
                    System.out.println(employee.getSelected());
                }
                Date now = new Date();
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
                String dateTimeString = df.format(now);
                // Dữ liệu Model
                Student oldStudent = studentObservableList.get(1);
                Student newStudent = new Student(dateTimeString, oldStudent.getGender(), oldStudent.getSelected());
                studentObservableList.set(1, newStudent);
                settingViewModel.updateDateTime();
            }
        });

    }

    public void showDateTime(String datetime) {
        System.out.println(datetime);
    }


    @Override
    public void terminate() {
        System.out.println("Fragment terminate()");
        settingViewModel.dispose();
    }
}
