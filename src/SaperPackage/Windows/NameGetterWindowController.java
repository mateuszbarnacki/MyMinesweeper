package SaperPackage.Windows;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class NameGetterWindowController {
    @FXML
    private TextField nameGetter;
    @FXML
    private Label label;

    public String getName(){
        return nameGetter.getText();
    }

    public void validateField(MouseEvent mouseEvent) {
        if(nameGetter.getText().isEmpty()){
            label.setVisible(true);
        }else{
            label.setVisible(false);
        }
    }
}
