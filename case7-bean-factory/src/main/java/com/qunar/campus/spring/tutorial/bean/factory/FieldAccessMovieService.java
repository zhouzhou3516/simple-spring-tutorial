package com.qunar.campus.spring.tutorial.bean.factory;

import com.qunar.campus.spring.tutorial.interfaces.MovieFinder;
import com.qunar.campus.spring.tutorial.interfaces.MovieService;
import com.qunar.campus.spring.tutorial.interfaces.model.Movie;

import java.util.List;

/**
 * Description: FieldAccessMovieService
 *
 * field初始化
 *
 * @author yushen.ma
 * @version 2015-03-16 23:13
 */
public class FieldAccessMovieService implements MovieService {

    private MovieFinder finder;

    @Override
    public List<Movie> moviesDirectedBy(String directorName) {
        // do some thing
        return finder.find();
    }

    public MovieFinder getFinder() {
        return finder;
    }

    public void setFinder(MovieFinder finder) {
        this.finder = finder;
    }
}
