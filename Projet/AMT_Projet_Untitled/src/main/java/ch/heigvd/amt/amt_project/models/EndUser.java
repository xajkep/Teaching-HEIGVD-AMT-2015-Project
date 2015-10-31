package ch.heigvd.amt.amt_project.models;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author , mberthouzoz
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "EndUser.findByApp", query = "Select e from EndUser e"),
  @NamedQuery(name = "EndUser.getNumberOfUserDuringLastDays", query = "SELECT count(e) FROM EndUser e WHERE e.date > :date")
})

public class EndUser extends AbstractDomainModel<Long>{
    @ManyToOne
    private Application app;
    
    private String name;
    
    private Date date;
    
    public EndUser() {
        
    }

    public EndUser(Application app, String name, Date date)
    {
        this.app = app;
        this.name = name;
        this.date = date;
    }
    
    /**
     * @return the app
     */
    public Application getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(Application app) {
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
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
