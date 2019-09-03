package me.yelowelephant.testtasks.osmtest.utils;

import org.apache.http.client.methods.HttpGet;

public class OsmQueryFactory {

    public HttpGet createRequest(String interpreterUrl, String query){
        HttpGet get = new HttpGet(interpreterUrl+query);
        get.addHeader("Content-Type", "application/x-www-form-urlencoded");
        return get;
    }

    public String getNodesInRangeByTag(Double lat, Double lon, Integer radius, String tag){
        StringBuilder url = new StringBuilder();
        url.append("?data=[out:json];node(around:");
        url.append(radius);
        url.append(",");
        url.append(lat);
        url.append(",");
        url.append(lon);
        url.append(")[");
        url.append(tag);
        url.append("];out;");
        return url.toString();
    }
}
