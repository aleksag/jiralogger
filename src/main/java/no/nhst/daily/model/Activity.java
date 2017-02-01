package no.nhst.daily.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alex on 31/01/2017.
 */
public class Activity {

    @JsonProperty("activity")
    private String activity;

    @JsonProperty("durationString")
    private String durationString;

    @JsonProperty("percentage")
    private Double percentage;

    @JsonProperty("duration")
    private Integer duration;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Integer getDuration() {
        return duration;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDurationString() {
        return durationString;
    }

    public void setDurationString(String durationString) {
        this.durationString = durationString;
    }
}
