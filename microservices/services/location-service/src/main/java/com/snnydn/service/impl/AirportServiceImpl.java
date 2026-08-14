package com.snnydn.service.impl;

import com.snnydn.mapper.AirportMapper;
import com.snnydn.model.Airport;
import com.snnydn.model.City;
import com.snnydn.payload.request.AirportRequest;
import com.snnydn.payload.response.AirportResponse;
import com.snnydn.repository.AirportRepository;
import com.snnydn.repository.CityRepository;
import com.snnydn.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    @Override
    public AirportResponse createAirport(AirportRequest request) throws Exception {

        if(airportRepository.findByIataCode(request.getIataCode()).isPresent()) {
            throw new Exception("Airport with given IATA Code is already exist");
        }

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new Exception("City not found!"));

        Airport airport = AirportMapper.toEntity(request);
        airport.setCity(city);

        Airport savedAirport = airportRepository.save(airport);

        return AirportMapper.toResponse(savedAirport);
    }

    @Override
    public AirportResponse getAirportById(Long id) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport not exist with given ID")
        );

        return AirportMapper.toResponse(airport);
    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest airportRequest) throws Exception {
        Airport existingAirport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport not exist with given ID" + id)
        );
        if (airportRequest.getIataCode() != null
                && !existingAirport.getIataCode().equals(airportRequest.getIataCode())
                && airportRepository.findByIataCode(airportRequest.getIataCode()).isPresent()) {
            throw new Exception("Airport with Iata Code Already Exist");
        }
        AirportMapper.updateEntity(airportRequest, existingAirport);
        Airport updatedAirport = airportRepository.save(existingAirport);

        return AirportMapper.toResponse(updatedAirport);
    }

    @Override
    public void deleteAirport(Long id) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport not exist with given ID ")
        );
        airportRepository.delete(airport);

    }

    @Override
    public List<AirportResponse> getAirportByCityId(Long cityId) {

        return airportRepository.findByCityId(cityId).stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }
}
