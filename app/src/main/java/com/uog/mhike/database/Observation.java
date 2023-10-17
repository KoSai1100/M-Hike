package com.uog.mhike.database;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Observation {

    public static final String ID="id";
    public static final String HIKE_ID="hike_id";
    public static final String OBSERVATION="Observation";
    public static final String DATE_TIME="date_time";
    public static final String COMMENT ="comment";
    public static final String D1="d1";

    private Integer id;
    private Integer hikeId;
    private String observation;
    private ZonedDateTime dateTime;
    private String comment;
    private Double d1;  ///location (latitude)

    public Observation(){}

    public Observation(Integer hikeId, String observation, ZonedDateTime dateTime, String comment, Double d1) {
        this.hikeId = hikeId;
        this.observation = observation;
        this.dateTime = dateTime;
        this.comment = comment;
        this.d1 = d1;
    }

    public Observation(Integer id, Integer hikeId, String observation, ZonedDateTime dateTime, String comment, Double d1) {
        this.id = id;
        this.hikeId = hikeId;
        this.observation = observation;
        this.dateTime = dateTime;
        this.comment = comment;
        this.d1 = d1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHikeId() {
        return hikeId;
    }

    public void setHikeId(Integer hikeId) {
        this.hikeId = hikeId;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getD1() {
        return d1;
    }

    public void setD1(Double d1) {
        this.d1 = d1;
    }

    public  long dateTimeToSeconds(){
        return this.dateTime.toEpochSecond();
    }

    public static ZonedDateTime secondsToDateTime(long seconds){
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneId.systemDefault());
    }
}
