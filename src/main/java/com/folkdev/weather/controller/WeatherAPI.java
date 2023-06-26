package com.folkdev.weather.controller;

import com.folkdev.weather.controller.validation.CityNameConstraint;
import com.folkdev.weather.dto.WeatherDto;
import com.folkdev.weather.service.WeatherService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/weather")
@Validated
@Tag(name = "Open weather API v1", description ="Open weather API for filter by city for current temprature" )
public class WeatherAPI {
    private final WeatherService weatherService;

    public WeatherAPI(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(
            method = "GET",
            summary = "search the current weather report of the city",
            description = "search the current weather report of the city name filter. The api has the rate limiting. 10 request per minute",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The current weather report of the city",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = WeatherDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "City name is wrong. Re-try with a valid city name",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("{city}")
    @RateLimiter(name = "basic")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable("city") @CityNameConstraint   @NotBlank  String city) {
        return ResponseEntity.ok(weatherService.getWeatherByCityName(city));

    }
}
