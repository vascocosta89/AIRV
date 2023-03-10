package com.ota.airv.airv.service.authService.impl;

import com.amadeus.Amadeus;
import com.ota.airv.airv.service.authService.AuthService;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class AuthServiceImpl implements AuthService {

    @Value("${application.amadeus.key}")
    private String amadeusKey;

    @Value("${application.amadeus.secret}")
    private String amadeusSecret;

    @Value("${application.amadeus.tokenurl}")
    private String tokenUrl;

}
