module MySaper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens SaperPackage.Utilities;
    opens SaperPackage.DataModel;
    opens SaperPackage.Views;
    opens SaperPackage.Boards;
    opens SaperPackage;
}