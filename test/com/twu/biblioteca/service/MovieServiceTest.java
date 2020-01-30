package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Rating;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.repository.MovieRepositoryImpl;
import org.junit.Test;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieServiceTest {

    List<Movie> listOfMovies = new ArrayList<>();

    private MovieRepository movieRepository = new MovieRepositoryImpl(listOfMovies);

    private MovieService movieService = new MovieService(movieRepository);

    @Test
    public void getListOfMoviesWithSuccess(){
        Movie movie = new Movie("Guerra Mundial Z",
                LocalDate.of(2012,1,12), "Tom Testes", Rating.DEZ, false);

        listOfMovies.add(movie);

        assertEquals(1, movieService.getListOfMovies().size());
    }

    @Test
    public void getMovieRepositoryWithSuccess(){
        MovieRepository movieRep = movieService.getMovieRepository();
        assertNotNull(movieRep);
    }

    @Test
    public void checkoutMovieWithSuccess(){
        Movie movie = new Movie("Testes sucesso",
                LocalDate.of(2020,1,12), "Tom Testes", Rating.DEZ, false);

        movieService.checkoutMovie(movie);

        assertEquals(true, movie.isCheckout());
    }

    @Test
    public void verifyNumberOfMovieWithSuccess(){
        Movie movie = new Movie("Testes sucesso",
                LocalDate.of(2020,1,12), "Tom Testes", Rating.DEZ, false);

        movieService.getListOfMovies().add(movie);
        assertNotNull(movieService.findBookWithIdentifier(movie.getIdentifier()));
    }
}