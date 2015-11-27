package ch.heigvd.amt.amt_project.rest.dto;

import java.net.URI;

/**
 *
 * @author xajkep
 */
public class PointAwardDTO {
    private URI href;
    long numberOfPoints;
    String reason;

    public long getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(long numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public String getReason() {
        return reason;
    }

    public URI getHref() {
        return href;
    }

    public void setHref(URI href) {
        this.href = href;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
}