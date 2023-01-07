package control;

import spark.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import model.Histogram;
import view.HistogramDisplay;

public class WebService {
    private final HistogramBuilder histogramBuilder;

    public WebService(HistogramBuilder histogramBuilder) {
        this.histogramBuilder = histogramBuilder;
    }
    
    public void start() {
        Spark.port(80);
        Spark.get("/histogram", this::getHistogram);
        Spark.post("/histogram", this::createHistogram);
        Spark.put("/histogram/:id", this::updateHistogram);
        Spark.delete("/histogram/:id", this::deleteHistogram);
    }

       
    private String getHistogram(Request request, Response response) {
        String type = request.queryParams("dimension");
        String filter = request.queryParams("filter");
        Histogram histogram = histogramBuilder.build(type, filter);
        String jsonString = serialize(histogram, type);
        new HistogramDisplay(jsonString).execute();
        return serialize(histogram, type);
    }
    
    private String serialize(Histogram histogram, String type) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Map<String, Object> data = new HashMap<>();
        
        data.put("dimension", histogram.getDimension());
        data.put("histogram", histogram.getMap());
        data.put("filters", histogram.getFilters());

        String jsonString = gson.toJson(data);
        return jsonString;
    }

    private String createHistogram(Request request, Response response) {
        String body = request.body();
        Map<String, String> data = parseBody(body);
        String type = data.get("dimension");
        String filter = data.get("filter");
        Histogram histogram = histogramBuilder.build(type, filter);
        String jsonString = serialize(histogram, type);
        return jsonString;
    }
    
    private String updateHistogram(Request request, Response response) {
        String body = request.body();
        Map<String, String> data = parseBody(body);
        String type = data.get("dimension");
        String filter = data.get("filter");
        Histogram newHistogram = histogramBuilder.build(type, filter);
        String jsonString = serialize(newHistogram, type);
        return jsonString;
    }

    
    private String deleteHistogram(Request request, Response response) {
        response.status(200);
        return "Histograma eliminado correctamente";
    }
    
    private Map<String, String> parseBody(String body) {
        Map<String, String> data = new HashMap<>();
        String[] pairs = body.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0];
            String value = keyValue[1];
            data.put(key, value);
        }
        return data;
    }
}
