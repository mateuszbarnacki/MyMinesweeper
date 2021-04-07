package SaperPackage.Views;

import SaperPackage.DataModel.Datasource;
import SaperPackage.DataModel.TimeResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * This is a winners window controller.
 */
public class WinnersWindowController {
    @FXML
    private TableView<TimeResult> resultsTable;

    // This function fill the table using the data specified by type of the game.
    public void loadTableView(String nameOfTheType, String custom){
        if(nameOfTheType.equalsIgnoreCase("easy")){
            Task<ObservableList<TimeResult>> task = new GetEasyWinnersFromDatabaseTask();
            resultsTable.itemsProperty().bind(task.valueProperty());
            new Thread(task).start();
        }else if(nameOfTheType.equalsIgnoreCase("medium")){
            Task<ObservableList<TimeResult>> task = new GetMediumWinnersFromDatabaseTask();
            resultsTable.itemsProperty().bind(task.valueProperty());
            new Thread(task).start();
        }else if(nameOfTheType.equalsIgnoreCase("hard")){
            Task<ObservableList<TimeResult>> task = new GetHardWinnersFromDatabaseTask();
            resultsTable.itemsProperty().bind(task.valueProperty());
            new Thread(task).start();
        }else{
            Task<ObservableList<TimeResult>> task = new Task<ObservableList<TimeResult>>() {
                @Override
                protected ObservableList<TimeResult> call() throws Exception {
                    return FXCollections.observableArrayList(Datasource.getInstance().queryCustomWinners(custom));
                }
            };
            resultsTable.itemsProperty().bind(task.valueProperty());
            new Thread(task).start();
        }

    }
}

/**
 * This class load list of easy winners.
 */
class GetEasyWinnersFromDatabaseTask extends Task {
    @Override
    public ObservableList<TimeResult>  call() throws Exception {
        return FXCollections.observableArrayList(Datasource.getInstance().queryEasyWinners());
    }
}

/**
 * This class load list of medium winners.
 */
class GetMediumWinnersFromDatabaseTask extends Task {
    @Override
    public ObservableList<TimeResult>  call() throws Exception {
        return FXCollections.observableArrayList(Datasource.getInstance().queryMediumWinners());
    }
}

/**
 * This class load list of hard winners.
 */
class GetHardWinnersFromDatabaseTask extends Task {
    @Override
    public ObservableList<TimeResult>  call() throws Exception {
        return FXCollections.observableArrayList(Datasource.getInstance().queryHardWinners());
    }
}