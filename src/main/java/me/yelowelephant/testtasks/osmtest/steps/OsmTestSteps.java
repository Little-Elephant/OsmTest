package me.yelowelephant.testtasks.osmtest.steps;

import me.yelowelephant.testtasks.osmtest.entities.Node;
import me.yelowelephant.testtasks.osmtest.utils.HttpRequestExecutor;
import me.yelowelephant.testtasks.osmtest.utils.OsmQueryFactory;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class OsmTestSteps {
    private HttpRequestExecutor http;
    private OsmQueryFactory osmRequests;

    private final String[] APIPOOL = {"http://overpass-api.de/api/interpreter","http://overpass.osm.rambler.ru/cgi/interpreter", "http://overpass.openstreetmap.fr/api/interpreter"};
    private final Integer RETRIES = 10;

    public OsmTestSteps(){
        this.http = new HttpRequestExecutor();
        this.osmRequests = new OsmQueryFactory();
    }

    public List<Node> getListByTypeAndRange(String type, double lat, double lon, Integer range) throws IOException {
        List<Node> nodes = new ArrayList<Node>();
        String query = osmRequests.getNodesInRangeByTag(lat, lon, range, type);
        JSONObject resp = executeQuery(query);
        JSONArray elements = resp.getJSONArray("elements");
        for(int i=0; i<elements.length(); i++) {
        nodes.add(new Node(elements.getJSONObject(i)));
        }
        return nodes;

    }

    private JSONObject executeQuery(String query){
        Integer i=0;
        while (i<RETRIES){
            for(String api : APIPOOL){
                try {
                    HttpResponse response = http.execute(osmRequests.createRequest(api, query));
                    if (response.getStatusLine().getStatusCode()== 200){
                        JSONObject resp = new JSONObject(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));
                        return resp;
                    }
                }
                catch(Exception e){
                    //#TODO Log exception
                }
            }
            i++;
        }
        throw new RuntimeException("Unable to get response from pool of OSM servers");
    }


}
