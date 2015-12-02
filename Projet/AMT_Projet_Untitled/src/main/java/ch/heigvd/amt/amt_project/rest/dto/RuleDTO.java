package ch.heigvd.amt.amt_project.rest.dto;

import java.util.List;

/**
 *
 * @author YounTheory
 */
public class RuleDTO {
    private List<RulePropertiesDTO> properties;
    private EventTypeDTO eventType;
    /**
     * @return the properties
     */
    public List<RulePropertiesDTO> getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(List<RulePropertiesDTO> properties) {
        this.properties = properties;
    }
}
