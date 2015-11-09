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
public class BadgeAward extends AbstractDomainModel<Long>{
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToOne
    private Badge badge;
    
    @ManyToOne
    private EndUser userRewarded;
    
    public BadgeAward() {}
    
    public BadgeAward(Badge badge, EndUser endUser, Date date) {
        this.badge = badge;
        this.userRewarded = endUser;
        this.date = date;
    }

    /**
     * @return the date of the badge award
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param the date of the badge award
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the badge
     */
    public Badge getBadge() {
        return badge;
    }

    /**
     * @param the badge
     */
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    /**
     * @return user rewarded
     */
    public EndUser getEndUser() {
        return userRewarded;
    }
    
    /**
     * @param user rewarded
     */
    public void setEndUser(EndUser endUser) {
        this.userRewarded = endUser;
    }
    
}
