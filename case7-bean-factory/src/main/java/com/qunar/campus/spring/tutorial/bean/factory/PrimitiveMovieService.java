package com.qunar.campus.spring.tutorial.bean.factory;

import com.google.common.collect.Lists;
import com.qunar.campus.spring.tutorial.interfaces.MovieFinder;
import com.qunar.campus.spring.tutorial.interfaces.MovieService;
import com.qunar.campus.spring.tutorial.interfaces.model.Movie;

import java.util.List;

/**
 * Description: PrimitiveMovieService
 *
 * @author yushen.ma
 * @version 2015-03-17 21:47
 */
public class PrimitiveMovieService implements MovieService {

    private int movieAmount;

    private String filePath;

    private MovieFinder movieFinder;

    @Override
    public List<Movie> moviesDirectedBy(String directorName) {
        return Lists.newArrayList();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public MovieFinder getMovieFinder() {
        return movieFinder;
    }

    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    public int getMovieAmount() {
        return movieAmount;
    }

    public void setMovieAmount(int movieAmount) {
        this.movieAmount = movieAmount;
    }
}
