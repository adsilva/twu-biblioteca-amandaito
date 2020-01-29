package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repository.MovieRepository;

import java.util.List;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public List<Movie> getListOfMovies(){
        return this.movieRepository.getListOfMovies();
    }

    public void checkoutMovie(Movie movie){
        movie.setCheckout(true);
    }

    public Movie findBookWithIdentifier(Long identifier){
        return getListOfMovies().stream().filter(movie -> movie.getIdentifier().equals(identifier))
                .findFirst().orElse(null);
    }
}
