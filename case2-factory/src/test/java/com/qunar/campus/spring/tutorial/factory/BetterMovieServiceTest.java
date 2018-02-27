package com.qunar.campus.spring.tutorial.factory;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import com.qunar.campus.spring.tutorial.interfaces.MovieFinder;
import com.qunar.campus.spring.tutorial.interfaces.model.Movie;
import junit.framework.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.List;

/**
 * a funny relation:
 *     MovieService from Factory       factory        finder from factory       factory
 * Test ------------------------->  createMovieService -------------------->   create finder
 */
@Synopsis(name="factory", difficulty = Difficulty.EASY)
public class BetterMovieServiceTest {

    @Test
    public void testMoviesDirectedBy() throws Exception {
        //find movies directed by me
        List<Movie> movies = MovieRelativeFactory.INSTANCE
                .buildMovieService().moviesDirectedBy("yushen.ma");

        Assert.assertTrue(CollectionUtils.isNotEmpty(movies));
        Assert.assertEquals("Yushen.ma", movies.get(0).getDirectorName());
        Assert.assertEquals("SpringTutorial", movies.get(0).getName());

        MovieFinder finder1 = MovieRelativeFactory.INSTANCE.buildMovieFinder();
        MovieFinder finder2 = MovieRelativeFactory.INSTANCE.buildMovieFinder();
        System.out.println(finder1 == finder2);
        System.out.println(finder1);
        System.out.println(finder2);

    }

}