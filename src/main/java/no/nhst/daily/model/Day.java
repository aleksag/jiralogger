package no.nhst.daily.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alex on 31/01/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {

    @JsonProperty("date")
    private String date;

    @JsonProperty("activities")
    private Activity[] activities;

    public Activity[] getActivities() {
        return activities;
    }

    public void setActivities(Activity[] activities) {
        this.activities = activities;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
