package com.hackerrank.github.service.interfaces;

import java.util.List;

import com.hackerrank.github.exception.NotFoundException;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;

public interface GithubApiRestService {

	void deleteAllEvents();

	void createEvent(Event event) throws NotFoundException;

	List<Event> getAllEvents();

	List<Event> getEventsFromActor(Long actorId);

	void updateActorAvatar(Actor actor);

	List<Actor> getAllActors();

	List<Actor> getActorsByMaximumStreak();
	
	
}
