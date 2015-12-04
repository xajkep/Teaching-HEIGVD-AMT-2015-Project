package ch.heigvd.amt.amt_project.rest.dto;

import java.util.HashMap;

/**
 *
 * @author xajkep
 */
public class RuleConditionDTO {
    private EventTypeDTO type;
    private HashMap<String, String> properties = new HashMap<>();

    public EventTypeDTO getType() {
        return type;
    }

    public void setType(EventTypeDTO type) {
        this.type = type;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }
    
    
}
