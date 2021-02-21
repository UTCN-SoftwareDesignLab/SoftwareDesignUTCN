package model;

import java.util.Observable;

/**
 * Created by Alex on 29/03/2017.
 */
public class SampleModel extends Observable {

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
        setChanged();
        notifyObservers();
    }

    private String field;

}
