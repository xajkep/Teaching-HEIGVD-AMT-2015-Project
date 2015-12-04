package ch.heigvd.amt.amt_project.rest.dto;

import java.util.HashMap;

/**
 *
 * @author xajkep
 */
public class RuleActionDTO {
    private String type;
    private HashMap<String, String> properties = new HashMap<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }
    
    
}
