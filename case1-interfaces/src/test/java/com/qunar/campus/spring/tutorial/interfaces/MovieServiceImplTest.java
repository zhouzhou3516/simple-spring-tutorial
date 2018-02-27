package com.qunar.campus.spring.tutorial.interfaces;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import com.qunar.campus.spring.tutorial.interfaces.model.Movie;
import junit.framework.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.List;

@Synopsis(name="interface", difficulty = Difficulty.EASY)
public class MovieServiceImplTest {

    @Test
    public void testMoviesDirectedBy() throws Exception {
        MovieServiceImpl movieService = new MovieServiceImpl();
        List<Movie> movies = movieService.moviesDirectedBy("Yushen.ma");

        Assert.assertTrue(CollectionUtils.isNotEmpty(movies));
        Assert.assertEquals("Yushen.ma", movies.get(0).getDirectorName());
        Assert.assertEquals("SpringTutorial", movies.get(0).getName());
    }
}