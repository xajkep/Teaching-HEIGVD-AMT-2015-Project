package ch.heigvd.amt.amt_project.rest.DTO;

import java.util.List;

/**
 *
 * @author YounTheory
 */


public class LeaderBoardDTO {
    private String firstName;
    private String lastName;
    private long points;
    private List<BadgeDTO> badges;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the points
     */
    public long getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(long points) {
        this.points = points;
    }

    /**
     * @return the badges
     */
    public List<BadgeDTO> getBadges() {
        return badges;
    }

    /**
     * @param badges the badges to set
     */
    public void setBadges(List<BadgeDTO> badges) {
        this.badges = badges;
    }
    
    
}
