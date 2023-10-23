package usyd.elec5619.topicoservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import okhttp3.*;
import usyd.elec5619.topicoservice.exception.http.UnknownLocationException;

import java.io.IOException;

@Service
public interface LocationService {

    String getCity(String ipAddress);

    void updateUserLocation(Long id, String clientIp);

}
