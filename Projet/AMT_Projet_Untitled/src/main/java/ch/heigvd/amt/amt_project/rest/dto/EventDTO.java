package ch.heigvd.amt.amt_project.rest.dto;

import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.models.RuleProperties;
import java.util.Date;


/**
 *
 * @author mberthouoz
 */
public class EventDTO {
    private String type;    
    private Date timestamp;
    private EndUser enduserId;
    private RuleProperties properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public EndUser getEnduserId() {
        return enduserId;
    }

    public void setEnduserId(EndUser enduserId) {
        this.enduserId = enduserId;
    }

    public RuleProperties getProperties() {
        return properties;
    }

    public void setProperties(RuleProperties properties) {
        this.properties = properties;
    }
}
