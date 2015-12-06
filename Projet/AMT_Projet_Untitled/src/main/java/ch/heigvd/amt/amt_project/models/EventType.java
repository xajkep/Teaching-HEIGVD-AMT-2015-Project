package ch.heigvd.amt.amt_project.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author YounTheory
 */

@Entity
@NamedQueries({
  @NamedQuery(name = "EventType.findByName", query = "SELECT e FROM EventType e WHERE e.app.id = :app AND e.name = :name"),
})
@Table(uniqueConstraints=
           @UniqueConstraint(columnNames = {"eventtype_id", "name"})) 
public class EventType extends AbstractDomainModel<Long>{
    
    private String name;
    
    @ManyToOne
    private Application app;
    
    public EventType() {}
    
    public EventType(String name, Application app) {
        this.name = name;
        this.app = app;
    }

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
     * 
     * @return app Application
     */
    public Application getApp() {
        return app;
    }

    /**
     * 
     * @param app Application
     */
    public void setApp(Application app) {
        this.app = app;
    }
    
}
