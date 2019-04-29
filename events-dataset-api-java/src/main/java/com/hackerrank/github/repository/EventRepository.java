package com.hackerrank.github.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hackerrank.github.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
	List<Event> findAllByActorIdOrderByIdAsc(Long actorId);
	
	@Query(value="SELECT e "
			+ "     FROM Event e "
			+ "    GROUP BY e "
			+ "    ORDER BY MAX(e.createdAt) DESC, e.actor.login ASC")
	List<Event> findAllByStreak();
	
	List<Event> findAllByOrderByActorIdAscCreatedAtDesc();

	List<Event> findAllByOrderByCreatedAtDesc();
}
