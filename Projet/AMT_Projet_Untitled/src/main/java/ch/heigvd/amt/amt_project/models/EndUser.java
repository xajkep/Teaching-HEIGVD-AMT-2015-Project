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
  @NamedQuery(name = "EndUser.getPoints", query = "SELECT SUM(p.point) FROM EndUser e INNER JOIN PointAwards p ON e.pointAwards = p.id WHERE e.id = :user"),
  @NamedQuery(name = "EndUser.getBestUsers", query = ""
          + "SELECT e, SUM(p.point) as sumPoint "
          + "FROM EndUser e INNER JOIN PointAwards p ON e.pointAwards = p.id "
          + "INNER JOIN ApiKey a ON a.app.id = e.app.id "
          + "WHERE a.apiKey = :apikey ORDER BY sumPoint DESC"),
})

public class EndUser extends AbstractDomainModel<Long>{
    @ManyToOne
    private Application app;
    
    private String name;
    
    private Date date;
    
    @OneToMany(mappedBy = "userRewarded", targetEntity=BadgeAward.class)
    private List<BadgeAward> badgeAwards;
    
    @OneToMany(mappedBy = "endUser", targetEntity=PointAwards.class)
    private List<PointAwards> pointAwards;
    
    private long sumPoint;
    
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

    public List<PointAwards> getPointAwards() {
        return pointAwards;
    }

    public void setPointAwards(List<PointAwards> pointAwards) {
        this.pointAwards = pointAwards;
    }
    
    
    public long getSumPoint() {
        return this.sumPoint;
    }
    
    public void setSumPoint(long sumPoint) {
        this.sumPoint = sumPoint;
    }
}
