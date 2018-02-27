package com.qunar.campus.spring.tutorial.interfaces;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.qunar.campus.spring.tutorial.interfaces.model.Movie;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Description: MovieService
 *
 * 这是一个服务类
 *
 * FAQ 1: 什么叫service ?

   @see http://stackoverflow.com/questions/2928325/what-is-the-definition-of-a-service-object
   A SERVICE is an operation offered as an interface that stands alone in the model, without encapsulating state...

   或许你不太理解"stands alone", 以及为什么 "without encapsulating state"，没关系不要着急，在后面的章节会解释

 * FAQ 2: service 和 util 的 差别 :
   @see http://programmers.stackexchange.com/questions/132067/difference-between-a-service-class-and-a-helper-class

 * FAQ 3: 如何运行这些例子？
   一般情况下，在test目录下面会有对应的Test类，你可以试着运行这些test类。但是有些只是示例代码（为了说明一些特殊的问题）就不会有Test

 * @author yushen.ma
 * @version 2015-03-08 00:16
 */
public class MovieServiceImpl implements MovieService {

    // why final ? what is final ?
    final private MovieFinder finder;

    public MovieServiceImpl() {
        // 1. why not pass the implement through the constructor ?
        // 2. you can change it to any implements
        this.finder = new FileMovieFinder();
    }

    /**
     * find the movies that directed by the giving director
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
