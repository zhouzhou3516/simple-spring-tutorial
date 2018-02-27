package com.qunar.campus.spring.tutorial.bean.factory;

import com.qunar.campus.spring.tutorial.interfaces.MovieFinder;
import com.qunar.campus.spring.tutorial.interfaces.MovieService;
import com.qunar.campus.spring.tutorial.interfaces.model.Movie;

import java.util.List;

/**
 * Description: ConstructorMovieService
 *
 * 这是一个用构造函数可以初始化的实例
 *
 * @author yushen.ma
 * @version 2015-03-16 23:05
 */
public class ConstructorMovieService implements MovieService {

    private MovieFinder finder;

    public ConstructorMovieService(MovieFinder finder) {
        this.finder = finder;
    }

    @Override
    public List<Movie> moviesDirectedBy(String directorName) {
        // do some thing
        return finder.find();
    }
}
