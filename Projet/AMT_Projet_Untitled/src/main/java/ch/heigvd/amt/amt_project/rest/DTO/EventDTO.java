package ch.heigvd.amt.amt_project.rest.dto;

import java.util.List;

/**
 *
 * @author mberthouzoz
 */
public class EventDTO {
    private String name;
    private List<EventPropertiesDTO> properties;

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
    public List<EventPropertiesDTO> getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(List<EventPropertiesDTO> properties) {
        this.properties = properties;
    }
     /**
     * @param propertie the propertie to add
     */
    public void addPropertie(EventPropertiesDTO propertie) {
        this.properties.add(propertie);
    }
    
    /**
     * @param propertie the propertie to delete
     */
    public void removePropertie(EventPropertiesDTO propertie) {
        this.properties.remove(propertie);
    }
}
