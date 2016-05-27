package kishore.surprise.util;

import java.util.Calendar;

/**
 * Created by kishore.p on 26-Jun-15.
 */
public enum MyData {

    instance;

    private StringBuilder storedData = new StringBuilder(Calendar.getInstance().getTime().toString()+"\n");

    public String getStoredData() {
        return storedData.toString();
    }

    public void insert(StringBuilder message) {
        storedData.append(message);
    }

}
