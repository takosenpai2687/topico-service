package usyd.elec5619.topicoservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import okhttp3.*;
import usyd.elec5619.topicoservice.exception.http.UnknownLocationException;

import java.io.IOException;

@Service
public class LocationService {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getCity(String ipAddress) throws UnknownLocationException {

        final String url = "https://ipapi.co/" + ipAddress + "/json/";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) throw new UnknownLocationException();
            JsonNode jsonNode = objectMapper.readTree(response.body().string());
            return jsonNode.get("city").asText();
        } catch (Exception e) {
            return "Unknown";
        }
    }

}
