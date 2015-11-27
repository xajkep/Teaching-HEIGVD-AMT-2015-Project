package ch.heigvd.amt.amt_project.rest.DTO;

import java.util.List;

/**
 *
 * @author mberthouzoz, yountheory
 */
public class EventTypeDTO {
    private String name;
    private List<EventTypePropertiesDTO> properties;

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
    public List<EventTypePropertiesDTO> getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(List<EventTypePropertiesDTO> properties) {
        this.properties = properties;
    }
     /**
     * @param propertie the propertie to add
     */
    public void addPropertie(EventTypePropertiesDTO propertie) {
        this.properties.add(propertie);
    }
    
    /**
     * @param propertie the propertie to delete
     */
    public void removePropertie(EventTypePropertiesDTO propertie) {
        this.properties.remove(propertie);
    }
}
