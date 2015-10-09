package ch.heigvd.amt.amt_project.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author YounTheory, mberthouzoz
 */
@Entity
public class ApiKey extends AbstractDomainModel<Long>{
    @OneToOne
    private Application app;
    
    private String apiKey;
    
    public ApiKey() {
        //TODO : change this generation
        apiKey = String.valueOf(Math.floor((Math.random() * 10123213) + 1));
    }
    
    public ApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    /**
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey the apiKey to set
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
}
