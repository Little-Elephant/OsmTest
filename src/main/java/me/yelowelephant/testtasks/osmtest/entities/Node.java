package me.yelowelephant.testtasks.osmtest.entities;

import lombok.Data;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Data
public class Node {
    private Long id;
    private Double lat;
    private Double lon;
    private Map<String, String> tags;

    public Node(JSONObject json){
        this.id = json.getLong("id");
        this.lat = json.getDouble("lat");
        this.lon = json.getDouble("lon");
        JSONObject tags = json.getJSONObject("tags");
        this.tags = new HashMap<String, String>();
        for( String k : tags.keySet()){ this.tags.put(k, tags.getString(k)); }
    }
}
