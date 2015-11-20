package ch.heigvd.amt.amt_project.models;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Michaël
 */
@Entity
public class PointAwards extends AbstractDomainModel<Long>{
   
    @ManyToOne
    private EndUser endUser;
    
    private long point;

    private Date date;
    
    private String reason;
    
    /**
     * Constructor
     */
    public PointAwards() {
        
    }
    
    /**
     * Constructor
     * @param endUser define end user
     * @param point number of point
     */
    public PointAwards(EndUser endUser, int point, String reason) {
        this.endUser = endUser;
        this.point = point;
        this.date = new Date();
        this.reason = reason;
    }

    /**
     * 
     * @return EndUser
     */
    public EndUser getEndUser() {
        return endUser;
    }

    /**
     * 
     * @param endUser define end user
     */
    public void setEndUser(EndUser endUser) {
        this.endUser = endUser;
    }

    /**
     * 
     * @return point
     */
    public long getPoint() {
        return point;
    }

    /**
     * 
     * @param point set point
     */
    public void setPoint(long point) {
        this.point = point;
    }

    /**
     * 
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * 
     * @param date set date
     */
    public void setDate(Date date) {
        this.date = date;
    }    
    
    /**
     * 
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * 
     * @param reason define reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }    
}
