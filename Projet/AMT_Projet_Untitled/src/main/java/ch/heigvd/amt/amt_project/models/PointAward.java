package ch.heigvd.amt.amt_project.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author xajkep
 */
@Entity
public class PointAward extends AbstractDomainModel<Long> {

    @Temporal(TemporalType.DATE)
    private Date date;
    
    private long pts;
    
    private String reason;
    
    @ManyToOne
    private EndUser userScored;
    
    public PointAward() {}
    
    public PointAward(Date date, long pts, String reason) {
        this.date = date;
        this.pts = pts;
        this.reason = reason;
    }
    
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    
    /**
     * @param date the date of the award
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the number of points of the award
     */
    public long getPts() {
        return pts;
    }

    /**
     * @param pts the number of points of the award
     */
    public void setPts(long pts) {
        this.pts = pts;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason of the award
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the end user
     */
    public EndUser getEndUser() {
        return userScored;
    }

    /**
     * @param endUser the end user
     */
    public void setEndUser(EndUser endUser) {
        this.userScored = endUser;
    }
    
    
}
