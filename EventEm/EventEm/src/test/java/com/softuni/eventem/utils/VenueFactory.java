package com.softuni.eventem.utils;

import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.request.VenueRequest;
import static com.softuni.eventem.utils.VenueConstants.*;

public final class VenueFactory {
  public  static VenueRequest getVenueRequest(){
    VenueRequest venueRequest = new VenueRequest();
    venueRequest.setName(VENUE_NAME);
    venueRequest.setAddress(VENUE_ADDRESS);
    venueRequest.setCity(VENUE_CITY);
    return venueRequest;
  }
  public static VenueEntity getVenueEntity(){
    VenueEntity venueEntity = new VenueEntity();
    venueEntity.setId(VENUE_ID);
    venueEntity.setName(VENUE_NAME);
    venueEntity.setAddress(VENUE_ADDRESS);
    venueEntity.setCity(VENUE_CITY);
    return venueEntity;
  }

}
