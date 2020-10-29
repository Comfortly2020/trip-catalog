package com.comfortly.tripcatalog.models.converters;

import com.comfortly.tripcatalog.lib.LocationData;
import com.comfortly.tripcatalog.lib.TripData;
import com.comfortly.tripcatalog.models.entities.LocationDataEntity;
import com.comfortly.tripcatalog.models.entities.TripDataEntity;

public class LocationDataConverter {

    public static LocationData toDto(LocationDataEntity entity) {

        LocationData dto = new LocationData();
        dto.setId(entity.getId());
        dto.setLocationLat(entity.getLocationLat());
        dto.setLocationLng(entity.getLocationLng());
        dto.setSpeed(entity.getSpeed());
        dto.setAcceleration(entity.getAcceleration());
        dto.setOrientation(entity.getOrientation());
        dto.setTimestamp(entity.getTimestamp());

        return dto;

    }

    public static LocationDataEntity toEntity(LocationData dto) {

        LocationDataEntity entity = new LocationDataEntity();
        entity.setId(dto.getId());
        entity.setLocationLat(dto.getLocationLat());
        entity.setLocationLng(dto.getLocationLng());
        entity.setSpeed(dto.getSpeed());
        entity.setAcceleration(dto.getAcceleration());
        entity.setOrientation(dto.getOrientation());
        entity.setTimestamp(dto.getTimestamp());

        return entity;

    }

}
