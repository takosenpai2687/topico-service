package usyd.elec5619.topicoservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.exception.http.UnknownLocationException;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.service.LocationService;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UserMapper userMapper;

    public String getCity(String ipAddress) {

        final String url = "https://ipapi.co/" + ipAddress + "/json/";

        log.info("ip url: " + url);

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


    public void updateUserLocation(Long id, String clientIp) {
        final String city = getCity(clientIp);
        userMapper.updateUserLocation(id, city);
    }
}
