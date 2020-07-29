package com.makersLabTech.SystemEvents.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.*;
import com.makersLabTech.SystemEvents.dto.EventDTO;
import com.makersLabTech.SystemEvents.dto.FindEventDTO;
import com.makersLabTech.SystemEvents.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private DynamoDB dynamoDB;

    public ResponseEntity createEvent(List<EventDTO> eventDTOs) {

        if(!eventDTOs.isEmpty()){
            List<Event> events = new ArrayList<>();
            try {
                events = eventDTOs.stream().map(eDTO -> eventDTOToEvent(eDTO)).collect(Collectors.toList());
            } catch (Exception exception) {
                return ResponseEntity.unprocessableEntity().body("422");
            }
            dynamoDBMapper.batchWrite(events, new ArrayList<>());
            return new ResponseEntity<>("The events saved", HttpStatus.CREATED);
        }
        else {
            return ResponseEntity.badRequest().body("400 Bad request");
        }

    }

        public List<String> findAllEvent(FindEventDTO findEventDTO) {

            List<String> events = new ArrayList<>();

            Table table = dynamoDB.getTable("Event");

            Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
            expressionAttributeValues.put(":eventType", findEventDTO.getEventType().toString());
            expressionAttributeValues.put(":eventTime", findEventDTO.getEventTime().toString());
            ItemCollection<ScanOutcome> items = table.scan(
                    "eventType = :eventType AND eventTime > :eventTime",
                    "eventId, eventType, eventPayload, eventTime",
                    null,
                    expressionAttributeValues);

            Iterator<Item> iterator = items.iterator();
            while (iterator.hasNext()) {
                events.add(iterator.next().toJSON().toString());
            }
            return events;
        }

    public Event eventDTOToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setEventTime(eventDTO.getEventTime());
        event.setEventType(eventDTO.getEventType());
        event.setEventPayload(eventDTO.getEventPayload());
        return event;
    }
}
