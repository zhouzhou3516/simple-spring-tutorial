package com.qunar.campus.spring.tutorial.interfaces;

import com.qunar.campus.spring.tutorial.interfaces.model.Movie;

import java.util.List;

/**
 * Description: MovieService
 *
 * 都说Service一定要写成interface
 * 真的是这样的吗
 *
 * @author yushen.ma
 * @version 2015-03-10 14:34
 */
public interface MovieService {


    List<Movie> moviesDirectedBy(final String directorName);

}
