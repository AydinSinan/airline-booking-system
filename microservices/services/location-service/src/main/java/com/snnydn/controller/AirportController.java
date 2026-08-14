package com.snnydn.controller;

import com.snnydn.payload.request.AirportRequest;
import com.snnydn.payload.response.AirportResponse;
import com.snnydn.payload.response.ApiResponse;
import com.snnydn.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @PostMapping
    public ResponseEntity<AirportResponse> createAirport(@Valid @RequestBody AirportRequest airportRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                airportService.createAirport(airportRequest)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<AirportResponse>> getAirportsByCityId(@PathVariable Long cityId) {
        return ResponseEntity.ok(airportService.getAirportByCityId(cityId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable Long id, @Valid @RequestBody AirportRequest request) throws Exception {
        return ResponseEntity.ok(airportService.updateAirport(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirport(@PathVariable Long id) throws Exception {
        airportService.deleteAirport(id);
        return ResponseEntity.ok(new ApiResponse("Airport deleted successfully"));
    }
}
