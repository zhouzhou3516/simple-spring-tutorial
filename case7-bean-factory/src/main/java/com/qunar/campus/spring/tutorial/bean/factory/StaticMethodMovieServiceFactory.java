package com.qunar.campus.spring.tutorial.bean.factory;

import com.qunar.campus.spring.tutorial.interfaces.FileMovieFinder;
import com.qunar.campus.spring.tutorial.interfaces.MovieService;

/**
 * Description: StaticMethodMovieServiceFactory
 *
 * @author yushen.ma
 * @version 2015-03-16 23:21
 */
public class StaticMethodMovieServiceFactory {

    public static MovieService build() {
        return new ConstructorMovieService(new FileMovieFinder());
    }

    public MovieService buildViaBean() {
        return new ConstructorMovieService(new FileMovieFinder());
    }
}
