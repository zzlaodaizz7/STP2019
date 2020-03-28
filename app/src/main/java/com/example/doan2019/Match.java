package com.example.doan2019;

import java.io.Serializable;
import java.util.Date;

public class Match implements Serializable {
    private String id;
    private String teamHost;
    private String teamGuest;
    private Date time;
    private String ratio;
    private String pitch;
    private String level;
    private String state;

    public Match(String id, String teamHost, String teamGuest, Date time, String ratio, String pitch, String level, String state){
        this.id = id;
        this.teamHost = teamHost;
        this.teamGuest = teamGuest;
        this.time = time;
        this.ratio = ratio;
        this.pitch = pitch;
        this.level = level;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Match(String teamHost, String state, Date time, String pitch, String level) {
        this.teamHost = teamHost;
        this.time = time;
        this.pitch = pitch;
        this.level = level;
        this.state = state;
    }

    public String getTeamHost() { return teamHost; }

    public void setTeamHost(String _idTeamHost) { this.teamHost = _idTeamHost; }

    public String getTeamGuest() { return teamGuest; }

    public void setTeamGuest(String _idTeamGuest) { this.teamGuest = _idTeamGuest; }

    public Date getTime() { return time; }

    public void setTime(Date time) { this.time = time; }

    public String getRatio() { return ratio; }

    public void setRatio(String ratio) { this.ratio = ratio; }

    public String getPitch() { return pitch; }

    public void setPitch(String _idPitch) { this.pitch = _idPitch; }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }
}
