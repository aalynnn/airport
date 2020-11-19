package ro.siit.airport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.siit.airport.domain.Airport;
import ro.siit.airport.model.Search;
import ro.siit.airport.repository.AirportRepository;
import ro.siit.airport.service.AirportService;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Airport> findFilteredAirports(final Search search) {
        List<Airport> list;
        if (search.hasCountry() && search.hasCity()) {
            list = airportRepository.findByCountryAndCity(search.getCountry(), search.getCity());
        } else if (search.hasCountry()) {
            list = airportRepository.findByCountry(search.getCountry());
        } else if (search.hasCity()) {
            list = airportRepository.findByCity(search.getCity());
        } else {
            list = airportRepository.findAll();
        }
        return list;
    }
}
