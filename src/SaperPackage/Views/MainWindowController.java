package SaperPackage.Views;

import SaperPackage.Boards.MinesweeperBoard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

/**
 * This class is a controller for main window.
 */

public class MainWindowController {
    @FXML
    private BorderPane borderPane;

    // This function handle the first button. It loads a game window and set properties for easy game board.
    public void handleFirstButton(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("gameWindow.fxml"));
        try {
            borderPane.setCenter(fxmlLoader.load());
        }catch (IOException e){
            e.printStackTrace();
        }

        GameWindowController controller = fxmlLoader.getController();
        controller.createGameScene(8, 8, 10);
    }

    // This function handle the second button. It loads a game window and set properties for medum game board.
    public void handleSecondButton(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("gameWindow.fxml"));
        try {
            borderPane.setCenter(fxmlLoader.load());
        }catch (IOException e){
            e.printStackTrace();
        }

        GameWindowController controller = fxmlLoader.getController();
        controller.createGameScene(16, 16, 40);
    }

    // This function handle the third button. It loads a game window and set properties for hard game board.
    public void handleThirdButton(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("gameWindow.fxml"));
        try {
            borderPane.setCenter(fxmlLoader.load());
        }catch (IOException e){
            e.printStackTrace();
        }

        GameWindowController controller = fxmlLoader.getController();
        controller.createGameScene(30, 16, 99);
    }

    // This function handle the fourth button. It loads a dialog which enable the user to create
    // a custom game board.
    public void handleFourthButton(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("customWindow.fxml"));
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Board creator");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.initOwner(borderPane.getScene().getWindow());

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Can't load the scene");
            e.printStackTrace();
        }

        Optional<ButtonType> result = dialog.showAndWait();
        if((result.isPresent()) && (result.get() == ButtonType.OK)){
            try{
                CustomWindowController controller = fxmlLoader.getController();
                MinesweeperBoard saperBoard = controller.processResult();
                try{
                    FXMLLoader secFXMLLoader = new FXMLLoader();
                    secFXMLLoader.setLocation(getClass().getResource("gameWindow.fxml"));
                    borderPane.setCenter(secFXMLLoader.load());

                    GameWindowController secondWindowController = secFXMLLoader.getController();
                    secondWindowController.createGameScene(saperBoard.getFirstSize(), saperBoard.getSecondSize(), saperBoard.getMines());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch (UnsupportedOperationException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Board creation error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                Optional<ButtonType> res = alert.showAndWait();
                if(res.isPresent()){
                    alert.close();
                }
            }catch (NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Board creation error");
                alert.setHeaderText(null);
                alert.setContentText("Enter the correct data");
                Optional<ButtonType> res = alert.showAndWait();
                if(res.isPresent()){
                    alert.close();
                }
            }

        }else{
            dialog.close();
        }
    }

}