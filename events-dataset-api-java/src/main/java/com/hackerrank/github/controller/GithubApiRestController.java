package com.hackerrank.github.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.github.exception.BadRequestException;
import com.hackerrank.github.exception.NotFoundException;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.service.interfaces.GithubApiRestService;

@RestController
public class GithubApiRestController {
	
	@Autowired
	private GithubApiRestService service;
	
	@DeleteMapping(path="/erase")
	public ResponseEntity<?> delete() {
		service.deleteAllEvents();
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(path="/events")
	public ResponseEntity<?> create(@Valid @RequestBody Event request) {
		try {
			service.createEvent(request);
		} catch(NotFoundException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(path="/events")
	public List<Event> getEvents() {
		return service.getAllEvents();
	}
	
	@GetMapping(path="/events/actors/{actorId}")
	public ResponseEntity<List<Event>> getActorEvents(
			@PathVariable Long actorId) {
		
		try {
			return ResponseEntity.ok(service.getEventsFromActor(actorId));
		} catch(NotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping(path="/actors")
	public ResponseEntity<?> update(@Valid @RequestBody Actor actor) {
		try {
			service.updateActorAvatar(actor);
		} catch(NotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.build();
		} catch(BadRequestException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path="/actors")
	public ResponseEntity<List<Actor>> getActors() {
		return ResponseEntity.ok(service.getAllActors());
	}
	
	@GetMapping(path="/actors/streak")
	public ResponseEntity<List<Actor>> getStreak() {
		return ResponseEntity.ok(service.getActorsByMaximumStreak());
	}
	
}
