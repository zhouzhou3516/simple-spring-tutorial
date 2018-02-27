package com.qunar.campus.spring.tutorial.factory;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.qunar.campus.spring.tutorial.interfaces.MovieFinder;
import com.qunar.campus.spring.tutorial.interfaces.MovieService;
import com.qunar.campus.spring.tutorial.interfaces.model.Movie;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Description: BetterMovieService
 *
 * 一个新的Movie Service
 *
 * @author yushen.ma
 * @version 2015-03-10 14:23
 */
public class BetterMovieService implements MovieService{

    private final MovieFinder finder;

    public BetterMovieService() {
        // get instance from
        finder = MovieRelativeFactory.INSTANCE.buildMovieFinder();
    }

    /**
     * 这是我从 MovieService 里面copy过来的
     * @param directorName the name of director
     * @return list of the movie
     */
    public List<Movie> moviesDirectedBy(final String directorName) {

        List<Movie> result = this.finder.find();

        //为什么不用allTheMovies.isEmpty()
        //Effective Java Item 47 Learn and Use Libraries
        if (CollectionUtils.isEmpty(result)) {
            return Lists.newArrayList(); // this is just a factory pattern implements
        }
        Iterables.removeIf(result, new Predicate<Movie>() {
            @Override
            public boolean apply(Movie input) {
                return input == null || !StringUtils.equalsIgnoreCase(directorName, input.getDirectorName());
            }
        });
        return result;
    }
}
