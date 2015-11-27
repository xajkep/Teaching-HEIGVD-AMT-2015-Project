package ch.heigvd.amt.amt_project.rest.dto;

import java.util.List;

/**
 *
 * @author mberthouzoz, yountheory
 */
public class EventTypeDTO {
    private String name;
    private List<RulePropertiesDTO> properties;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

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
     /**
     * @param propertie the propertie to add
     */
    public void addPropertie(RulePropertiesDTO propertie) {
        this.properties.add(propertie);
    }
    
    /**
     * @param propertie the propertie to delete
     */
    public void removePropertie(RulePropertiesDTO propertie) {
        this.properties.remove(propertie);
    }
}
