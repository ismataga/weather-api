package com.folkdev.weather.repository;

import com.folkdev.weather.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<WeatherEntity,String> {
    Optional<WeatherEntity> findFirstByRequestedCityNameOrderByUpdateTimeDesc(String city);
}
