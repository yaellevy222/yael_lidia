package com.example.sporteam.sporteam;

import java.util.List;

/**
 * Created by redbend on 7/13/16.
 */
public class GroupDetailsPOJO  {

    private String _id;
    private String type;
    private String location;
    private String recur;

    private String whenday;
    private String whenfromtime;
    private String minplayers;
    private String maxplayers;

    private String whentotime;
    private String[] players;
    private String lat;
    private String lon;

    private String name;

    public GroupDetailsPOJO(){}

    public GroupDetailsPOJO(String id, String type, String location, String recur,
                            String whenday, String whenfromtime, String minplayers, String maxplayers,
                            String whentotime, String[] players, String lat, String lon, String name ){
        this._id = id;
        this.type = type;
        this.location = location;
        this.recur = recur;

        this.whenday = whenday;
        this.whenfromtime = whenfromtime;
        this.minplayers = minplayers;
        this.maxplayers = maxplayers;

        this.whentotime = whentotime;
        this.players = players;
        this.lat = lat;
        this.lon = lon;

        this.name = name;

    }
////////////////////////////////////////////

    public String getId()
    {
        return _id;
    }
    public void setId(String id)
    {
        this._id = id;
    }

    public String getLocation()
    {
        return location;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getRecur()
    {
        return recur;
    }
    public void setRecur(String recur)
    {
        this.recur = recur;
    }

//////////////////////////////////////////

    public String getWhenday()
    {
        return whenday;
    }
    public void setWhenday(String whenday)
    {
        this.whenday = whenday;
    }

    public String getWhenfromtime()
    {
        return whenfromtime;
    }
    public void setWhenfromtime(String whenfromtime)
    {
        this.whenfromtime = whenfromtime;
    }

    public String getMinplayers()
    {
        return minplayers;
    }
    public void setMinplayers(String minplayers)
    {
        this.minplayers = minplayers;
    }

    public String getmaxplayers()
    {
        return maxplayers;
    }
    public void setMaxplayers(String maxplayers)
    {
        this.maxplayers = maxplayers;
    }


/////////////////////////////////////

    public String getWhentotime()
    {
        return whentotime;
    }
    public void setWhentotime(String whentotime)
    {
        this.whentotime = whentotime;
    }

    public String[] getPlayers()
    {
        return players;
    }
    public void setPlayers(String[] players)
    {
        this.players = players;
    }

    public String getLat()
    {
        return lat;
    }
    public void setLat(String lat)
    {
        this.lat = lat;
    }


    public String getLon()
    {
        return lon;
    }
    public void setLon(String lon)
    {
        this.lon = lon;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }


}
