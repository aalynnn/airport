package ro.siit.airport.service;

import ro.siit.airport.domain.Airport;
import ro.siit.airport.model.Search;

import java.util.List;

public interface AirportService {

    List<Airport> findFilteredAirports(Search search);
}
