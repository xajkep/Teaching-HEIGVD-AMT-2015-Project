package ch.heigvd.amt.amt_project.models;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author YounTheory, mberthouzoz, xajkep
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ApiKey.exists", query = "SELECT a FROM ApiKey a WHERE a.apiKey = :apikey"),
})
public class ApiKey extends AbstractDomainModel<Long>{
    @OneToOne
    private Application app;
    
    private String apiKey;
    
    public ApiKey(){
        UUID id = UUID.randomUUID();
        apiKey = String.valueOf(id);
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
