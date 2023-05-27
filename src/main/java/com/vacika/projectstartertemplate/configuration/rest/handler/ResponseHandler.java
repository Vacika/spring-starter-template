package com.vacika.projectstartertemplate.configuration.rest.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vacika.projectstartertemplate.configuration.rest.response.GenericApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseHandler {
    public static final String OPERATION_SUCCESSFUL_MSG = "Operation successful";
    public static final String SUCCESS_TITLE = "Success";
    private static final ObjectMapper oMapper = new ObjectMapper()
            .setAnnotationIntrospector(new JacksonAnnotationIntrospector())
            .registerModule(new JavaTimeModule())
            .setDateFormat(new StdDateFormat())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


    public static ResponseEntity<Object> generateResponse(String message, String msgTitle, HttpStatus status, Object responseObj) {
        GenericApiResponse response = GenericApiResponse.builder()
                .msg(message != null ? message : OPERATION_SUCCESSFUL_MSG)
                .msgTitle(msgTitle != null ? msgTitle : SUCCESS_TITLE)
                .errCode(status.is2xxSuccessful() ? 0 : status.value())
                .data(responseObj)
                .build();

        return new ResponseEntity<>(oMapper.convertValue(response, Map.class), HttpStatus.OK);
    }

    public static ResponseEntity<Object> success(Object responseObj) {
        GenericApiResponse response = GenericApiResponse.builder()
                .msg(OPERATION_SUCCESSFUL_MSG)
                .msgTitle(SUCCESS_TITLE)
                .errCode(0)
                .data(responseObj)
                .build();
        return new ResponseEntity<>(oMapper.convertValue(response, Map.class), HttpStatus.OK);
    }

    public static ResponseEntity<Object> success(HttpStatus status, Object responseObj) {
        GenericApiResponse response = GenericApiResponse.builder()
                .msg(OPERATION_SUCCESSFUL_MSG)
                .msgTitle(SUCCESS_TITLE)
                .errCode(0)
                .data(responseObj)
                .build();
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> deleted() {
        GenericApiResponse response = GenericApiResponse.builder()
                .msg("Delete operation successful")
                .msgTitle(SUCCESS_TITLE)
                .errCode(0)
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<Object> notImplemented() {
        GenericApiResponse response = GenericApiResponse.builder()
                .msg("Method not implemented.")
                .msgTitle("Failure")
                .errCode(1)
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }

    public static ResponseEntity<Object> created(Object responseObj) {
        GenericApiResponse response = GenericApiResponse.builder()
                .msg(OPERATION_SUCCESSFUL_MSG)
                .msgTitle(SUCCESS_TITLE)
                .errCode(0)
                .data(responseObj)
                .build();
        return new ResponseEntity<>(oMapper.convertValue(response, Map.class), HttpStatus.CREATED);
    }

    public static ResponseEntity<Object> error(Exception ex, HttpStatus status) {
        GenericApiResponse response = GenericApiResponse.builder()
                .msg(ex.getLocalizedMessage())
                .msgTitle(ex.getClass().getSimpleName())
                .errCode(status.value())
                .build();
        return new ResponseEntity<>(oMapper.convertValue(response, Map.class), status);
    }

    public static ResponseEntity<Object> error(String title, String msg, HttpStatus status) {
        GenericApiResponse response = GenericApiResponse.builder()
                .msg(msg)
                .msgTitle(title)
                .errCode(status.value())
                .build();
        return new ResponseEntity<>(oMapper.convertValue(response, Map.class), status);
    }
}