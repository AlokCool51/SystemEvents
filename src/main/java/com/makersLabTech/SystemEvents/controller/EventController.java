package com.makersLabTech.SystemEvents.controller;

import com.makersLabTech.SystemEvents.dto.EventDTO;
import com.makersLabTech.SystemEvents.dto.FindEventDTO;
import com.makersLabTech.SystemEvents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(path = "/all")
    public List<String> getAllEvents(@RequestBody FindEventDTO findEventDTO){

        return eventService.findAllEvent(findEventDTO);
    }

    @PostMapping(path = "/create")
    public ResponseEntity addEvents(@RequestBody List<EventDTO> eventDTOs){
        return eventService.createEvent(eventDTOs);
    }
}
