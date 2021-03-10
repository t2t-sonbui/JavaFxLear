package vn.mht.app.desktop.app.run.node.room;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class StudentListViewCell extends JFXListCell<Student> {


    private Text txtTitle;
    private Text txtDesc;
    private FontIcon icoSound;
    private JFXCheckBox cbEnable;
    private VBox itemPane;
    private FXMLLoader mLoader;
    private ItemSelectedCallback mSelectedCallback;


    public interface ItemSelectedCallback {
        void onItemSlectedChanged(int index, boolean selected);
    }

    public void setOnItemSelectedCallback(ItemSelectedCallback selectedCallback) {
        this.mSelectedCallback = selectedCallback;
    }


    public StudentListViewCell() {
        super();
        if (mLoader == null) {
            mLoader = new FXMLLoader(getClass().getResource("/fxml/talk/ListCell.fxml"));
            try {
                Parent root = mLoader.load();
                txtTitle = (Text) mLoader.getNamespace().get("txtTitle");
                txtDesc = (Text) mLoader.getNamespace().get("txtDesc");
                icoSound = (FontIcon) mLoader.getNamespace().get("icoSound");
                cbEnable = (JFXCheckBox) mLoader.getNamespace().get("cbEnable");
                itemPane = (VBox) mLoader.getNamespace().get("itemPane");
                cbEnable.selectedProperty().addListener((observable, oldValue, selected) -> {
                    if (mSelectedCallback != null) {
                        mSelectedCallback.onItemSlectedChanged(getIndex(), selected);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void updateItem(Student student, boolean empty) {
        super.updateItem(student, empty);
        if (!isEmpty()) {
            txtTitle.setText(String.valueOf(student.getStudentId()));
            txtDesc.setText(student.getName());
//            if (student.getGender().equals(Student.GENDER.MALE)) {
//                icoSound.setIconCode(FontAwesomeRegular.STAR);
//            } else if (student.getGender().equals(Student.GENDER.FEMALE)) {
//                icoSound.setIconCode(FontAwesomeRegular.HEART);
//                icoSound.setIconColor(Paint.valueOf("#AA0000"));
//            } else {
//                icoSound.setIconCode(FontAwesomeRegular.CHECK_CIRCLE);
//            }
            cbEnable.setSelected(student.getSelected());
            setText(null);
            setGraphic(itemPane);
        } else {
            setText(null);
            setGraphic(null);
        }
    }

}
