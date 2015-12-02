package ch.heigvd.amt.amt_project.rest.dto;

import java.util.HashMap;


/**
 *
 * @author mberthouoz
 */
public class EventDTO {
    private String type;    
    private String timestamp;
    private String enduser;
    private HashMap<String, String> properties = new HashMap<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEnduser() {
        return enduser;
    }

    public void setEnduser(String enduser) {
        this.enduser = enduser;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }
}
