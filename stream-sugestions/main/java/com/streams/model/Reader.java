package com.streams.model;

import java.util.Set;

import com.google.common.collect.Sets;

public class Reader {

	private final Set<Book> favouriteBooks = Sets.newHashSet();
	private final Set<Genre> favouriteGenres = Sets.newHashSet();
	private int age;

	public Reader(int age) {
		this.age = age;
	}

	public void addToFavourites(Book book) {
		favouriteBooks.add(book);
	}

	public void addToFavourites(Genre genre) {
		favouriteGenres.add(genre);
	}

	public void removeFromFavourites(Book book) {
		favouriteBooks.remove(book);
	}

	public void removeFromFavourites(Genre genre) {
		favouriteGenres.remove(genre);
	}

	public int getAge() {
		return age;
	}

	public Set<Book> getFavouriteBooks() {
		return Sets.newHashSet(favouriteBooks);
	}

	public Set<Genre> getFavouriteGenres() {
		return Sets.newHashSet(favouriteGenres);
	}

	@Override
	public int hashCode() {
		int result = favouriteBooks.hashCode();
		result = 31 * result + favouriteGenres.hashCode();
		result = 31 * result + age;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Reader reader = (Reader) o;

		if (age != reader.age) return false;
		if (!favouriteBooks.equals(reader.favouriteBooks)) return false;
		return favouriteGenres.equals(reader.favouriteGenres);
	}
}
