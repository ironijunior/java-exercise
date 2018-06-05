package com.streams.services;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.streams.model.Author;
import com.streams.model.Book;
import com.streams.model.Reader;
import com.streams.model.Genre;

class SuggestionService {

	private final Set<Book> books;
	private final Set<Reader> readers;

	public SuggestionService(Set<Book> books, Set<Reader> readers) {
		this.books = books;
		this.readers = readers;
	}

	Set<String> suggestBooks(Reader reader) {
		Set<Genre> favGenres = reader.getFavouriteGenres();
      	
      	return books.stream()
          .filter(b -> b.getRating() >= 4)
          .filter(b -> favGenres.contains(b.getGenre()))
          .filter(b -> favBooksSameAge(reader).contains(b))
          .map(b -> b.getTitle())
          .collect(Collectors.toSet());
	}

	Set<String> suggestBooks(Reader reader, int rating) {
		Set<Genre> favGenres = reader.getFavouriteGenres();
      	      	
      	return books.stream()
          .filter(b -> b.getRating() >= rating)
          .filter(b -> favGenres.contains(b.getGenre()))
          .filter(b -> favBooksSameAge(reader).contains(b))
          .map(b -> b.getTitle())
          .collect(Collectors.toSet());
	}

	Set<String> suggestBooks(Reader reader, Author author) {
		Set<Genre> favGenres = reader.getFavouriteGenres();
      	
		return books.stream()
          .filter(b -> b.getRating() >= 4)
          .filter(b -> favGenres.contains(b.getGenre()))
          .filter(b -> favBooksSameAge(reader).contains(b))
          .filter(b -> b.getAuthor().equals(author))
          .map(b -> b.getTitle())
          .collect(Collectors.toSet());
	}
  
  	private Set<Book> favBooksSameAge(Reader reader) {
		Set<Reader> setReaders = readers.stream()
			.filter(r -> reader.getAge() == r.getAge())
			.filter(r -> !reader.equals(r))
			.collect(Collectors.toSet());
			
		Set<Book> favBooks = new HashSet<>();
		setReaders.forEach(r -> favBooks.addAll(r.getFavouriteBooks()));
		return favBooks;
	}

}