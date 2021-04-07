package SaperPackage.Views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * This class is a name getter controller.
 */

public class NameGetterWindowController {
    @FXML
    private TextField nameGetter;
    @FXML
    private Label label;

    public String getName(){
        return nameGetter.getText();
    }

    // If text field is empty function display an error message. Otherwise it display nothing.
    public void validateField(MouseEvent mouseEvent) {
        if(nameGetter.getText().isEmpty()){
            label.setVisible(true);
        }else{
            label.setVisible(false);
        }
    }
}
