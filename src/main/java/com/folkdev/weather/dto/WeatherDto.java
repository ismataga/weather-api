package com.folkdev.weather.dto;

import com.folkdev.weather.model.WeatherEntity;

public record WeatherDto (
        String cityName,
        String country,
        Integer temperature
){
    public static WeatherDto convert(WeatherEntity from){
        return new WeatherDto(from.getCityName(), from.getCountry(), from.getTemperature());
    }
}
