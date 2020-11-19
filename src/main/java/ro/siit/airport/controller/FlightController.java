package ro.siit.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airport.domain.Flight;
import ro.siit.airport.repository.FlightRepository;

import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/flights/{flightNo}")
    public ModelAndView displayFlightByNumber(@PathVariable("flightNo") final String flightNo) {
        final ModelAndView mav = new ModelAndView("flight-by-number");
        final List<Flight> flights = flightRepository.findByFlightNo(flightNo);
        final String flight = flights.stream()
                .findFirst()
                .map(f -> f.getAirline().getName() + " " + f.getAirline().getIata()
                        + "\n" + f.getDepartureAirport().getName() + " " + f.getDepartureAirport().getCountry()
                        + "\n" + f.getArrivalAirport().getName() + " " + f.getArrivalAirport().getCity())
                .orElse("no data");
        mav.addObject("flight", flight);
        return mav;
    }
}
