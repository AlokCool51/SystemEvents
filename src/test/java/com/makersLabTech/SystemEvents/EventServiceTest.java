package com.makersLabTech.SystemEvents;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.makersLabTech.SystemEvents.dto.EventDTO;
import com.makersLabTech.SystemEvents.enums.EventType;
import com.makersLabTech.SystemEvents.model.Event;
import com.makersLabTech.SystemEvents.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    private EventService eventService;

    @Test
    public void createEventShouldRespondWithBadRequestResponse() {
        ResponseEntity result = eventService.createEvent(Collections.emptyList());
        assertEquals(result.getStatusCode(), BAD_REQUEST);
    }


    @Test
    public void createEventShouldRespondWithCreatedResponse() {
        when(dynamoDBMapper.batchWrite(any(),anyCollection())).thenReturn(Collections.emptyList());
        List<EventDTO> events = new ArrayList<>();
        EventDTO eventDTO1 = new EventDTO();
        events.add(eventDTO1);
        ResponseEntity result = eventService.createEvent(events);
        assertEquals(result.getStatusCode(), CREATED);
    }

    @Test
    public  void testEventDTOToEvent() {
        EventDTO eventDTO = new EventDTO(EventType.Registered,"any",LocalDateTime.of(
                2020,Month.JANUARY,26,1,1,1));
        Event event = new Event(EventType.Registered, "any", LocalDateTime.of(
                2020,Month.JANUARY,26,1,1,1));

        assertEquals(eventService.eventDTOToEvent(eventDTO),event);
    }


}
