package SaperPackage.DataModel;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class describe a single game result. It stores username and time.
 */

public class TimeResult {
    private SimpleStringProperty name;
    private SimpleStringProperty time;

    public TimeResult(){
        this.name = new SimpleStringProperty();
        this.time = new SimpleStringProperty();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public void setTime(String time){
        this.time.set(time);
    }

    public String getName() {
        return name.get();
    }

    public String getTime(){
        return time.get();
    }
}
