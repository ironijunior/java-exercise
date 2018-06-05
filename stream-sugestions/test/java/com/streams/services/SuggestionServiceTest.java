package com.streams.services;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.streams.model.Author;
import com.streams.model.Book;
import com.streams.model.Reader;

import static com.streams.model.Genre.FANTASY;
import static com.streams.model.Genre.NON_FICTION;
import static com.streams.model.Genre.TECH;
import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

public class SuggestionServiceTest {


	private Author jemisin = new Author("N.K.", "Jemisin");
	private Author bloch = new Author("Joshua", "Bloch");
	private Author neuvel = new Author("Sylvain", "Neuvel");
	private Author duckworth = new Author("Angela", "Duckworth");
	private Author bennett = new Author("Robert", "Bennett");
	private Book fifthSeason = new Book(jemisin, "The Fifth Season", "0316229296", FANTASY, 5);
	private Book obeliskGate = new Book(jemisin, "The Obelisk Gate", "0356508366", FANTASY, 4);
	private Book sleepingGiants = new Book(neuvel, "Sleeping Giants", "1101886692", FANTASY, 3);
	private Book effectiveJava = new Book(bloch, "Effective Java", " 0321356683", TECH, 5);
	private Book cityOfStairs = new Book(bennett, "City of Stairs", "080413717X", FANTASY, 5);
	private Book grit = new Book(duckworth, "Grit", "1501111108", NON_FICTION, 5);
	private Reader sara = new Reader(18);
	private Reader john = new Reader(18);
	private Reader anastasia = new Reader(44);
	private SuggestionService suggestionService;

	@Before
	public void setUp() {
		sara.addToFavourites(FANTASY);
		sara.addToFavourites(TECH);
		john.addToFavourites(obeliskGate);
		john.addToFavourites(fifthSeason);
		john.addToFavourites(sleepingGiants);
		john.addToFavourites(effectiveJava);
		anastasia.addToFavourites(cityOfStairs);
		Set<Book> books = newHashSet(fifthSeason, obeliskGate, sleepingGiants, effectiveJava, cityOfStairs, grit);
		Set<Reader> readers = newHashSet(sara, john, anastasia);
		suggestionService = new SuggestionService(books, readers);
	}


	@Test
	public void shouldSuggestBookTitlesWithCorrectRating() throws Exception {
		// when:
		Set<String> suggestedBooks = suggestionService.suggestBooks(sara, 5);

		// then:
		assertThat(suggestedBooks).isEqualTo(newHashSet(fifthSeason.getTitle(), effectiveJava.getTitle()));
	}

	@Test
	public void shouldSuggestBookTitlesWithDefaultRatingOfFourOrHigher() throws Exception {
		// when:
		Set<String> suggestedBooks = suggestionService.suggestBooks(sara);

		// then:
		assertThat(suggestedBooks).isEqualTo(newHashSet(fifthSeason.getTitle(), obeliskGate.getTitle(),
				effectiveJava.getTitle()));
	}

	@Test
	public void shouldOnlySuggestBookTitlesFromReadersFavouriteGenres() throws Exception {
		// when:
		Set<String> suggestedBooks = suggestionService.suggestBooks(sara);

		// then:
		assertThat(suggestedBooks).doesNotContain(grit.getTitle());
		assertThat(suggestedBooks).contains(obeliskGate.getTitle());
		assertThat(suggestedBooks).contains(effectiveJava.getTitle());
	}

	@Test
	public void shouldOnlySuggestBookTitlesLikedByOtherReadersOfTheSameAge() {
		// when:
		Set<String> suggestedBooks = suggestionService.suggestBooks(sara);

		// then:
		assertThat(suggestedBooks).doesNotContain(cityOfStairs.getTitle());
		assertThat(suggestedBooks).contains(obeliskGate.getTitle());
	}

	@Test
	public void shouldOnlySuggestBookTitlesOfGivenAuthor() {
		// when:
		Set<String> suggestedBooks = suggestionService.suggestBooks(sara, jemisin);

		// then:
		assertThat(suggestedBooks).isEqualTo(newHashSet(fifthSeason.getTitle(), obeliskGate.getTitle()));
	}

}