package com.vacika.projectstartertemplate.ctrl.v1.crud;

import com.vacika.projectstartertemplate.ctrl.base.BaseCrudControllerImpl;
import com.vacika.projectstartertemplate.domain.Car;
import com.vacika.projectstartertemplate.dto.request.MutateCarRequest;
import com.vacika.projectstartertemplate.dto.response.CarResponse;
import com.vacika.projectstartertemplate.service.management.CarManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cars")
@Tag(name = "Car CRUD Controller")
public class CarCrudController extends BaseCrudControllerImpl<MutateCarRequest, CarResponse, Car, CarManagementService> {
    public CarCrudController(CarManagementService service) {
        super(service);
    }


    @Operation(summary = "Fetch all cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all cars",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))}),
            @ApiResponse(responseCode = "500", description = "Something went wrong on our end",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<Object> getAll() {
        return super.getAll();
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched car by ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))}),
            @ApiResponse(responseCode = "404", description = "Car not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Something went wrong on our end",
                    content = @Content)})
    @Operation(summary = "Fetch car by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@Parameter(description = "The ID of the car to be fetched") @PathVariable("id") Long id) {
        return super.getById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created a new car",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request, field validation failed.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Something went wrong on our end",
                    content = @Content)})
    @Operation(summary = "Create a new car")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody MutateCarRequest request) {
        return super.create(request);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated a car",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request, field validation failed.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid ID supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Something went wrong on our end",
                    content = @Content)})
    @Operation(summary = "Update existing car")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Parameter(description = "The ID of the car to be updated") @PathVariable("id") Long id, @RequestBody MutateCarRequest request) {
        return super.update(id, request);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted a car",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid ID supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Something went wrong on our end",
                    content = @Content)})
    @Operation(summary = "Delete a car by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@Parameter(description = "The ID of the car to be deleted") @PathVariable("id") Long id) {
        return super.delete(id);
    }
}
