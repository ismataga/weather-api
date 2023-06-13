package com.folkdev.weather.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;

import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
public class WeatherEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String requestedCityName;
    private String cityName;
    private String country;
    private Integer temperature;
    private LocalDateTime updateTime;
    private LocalDateTime responseLocalTime;

    public WeatherEntity(String id, String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updateTime, LocalDateTime responseLocalTime) {
        this.id = id;
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updateTime = updateTime;
        this.responseLocalTime = responseLocalTime;
    }
    public WeatherEntity(String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updateTime, LocalDateTime responseLocalTime) {
        this.id = "id";
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updateTime = updateTime;
        this.responseLocalTime = responseLocalTime;
    }

    public WeatherEntity() {

    }


}
