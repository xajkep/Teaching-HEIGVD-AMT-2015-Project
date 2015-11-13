package ch.heigvd.amt.amt_project.models;

import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author younTheory, mberthouzoz, xajkep
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "EndUser.findByApp", query = "SELECT e FROM EndUser e WHERE e.app.id = :app AND e.app.creator.id = :user"),
  @NamedQuery(name = "EndUser.getNumberOfUserDuringLastDays", query = "SELECT count(e) FROM EndUser e WHERE e.date > :date"),
  @NamedQuery(name = "EndUser.getNumberOfUserByApp", query = "SELECT count(e) FROM EndUser e WHERE e.app.id = :app"),
})

public class EndUser extends AbstractDomainModel<Long>{
    @ManyToOne
    private Application app;
    
    private String name;
    
    private Date date;
    
    @OneToMany(mappedBy = "userRewarded", targetEntity=BadgeAward.class)
    private List<BadgeAward> badgeAwards;
    
    @OneToMany(mappedBy = "userScored", targetEntity=PointAward.class)
    private List<PointAward> pointAwards;
    
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

    public List<BadgeAward> getBadgeAwards() {
        return badgeAwards;
    }

    public void setBadgeAwards(List<BadgeAward> badgeAwards) {
        this.badgeAwards = badgeAwards;
    }

    public List<PointAward> getPointAwards() {
        return pointAwards;
    }

    public void setPointAwards(List<PointAward> pointAwards) {
        this.pointAwards = pointAwards;
    }
    
    
    
}
