package com.example.actvn.service.maps;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapsService {


    @Value("${app.google.api-key}")
    private String apiKey;


    public GeocodingResult getLocationOfAddress(String address, String ward, String district, String provincial) throws InterruptedException, ApiException, IOException {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
        String addressSearch = address +","+ward+","+district+","+provincial;
        GeocodingResult[] results =  GeocodingApi.geocode(context, addressSearch).await();
        return results[0];
    }

}
