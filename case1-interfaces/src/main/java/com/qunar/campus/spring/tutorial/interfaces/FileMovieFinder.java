package com.qunar.campus.spring.tutorial.interfaces;

import com.google.common.collect.Lists;
import com.qunar.campus.spring.tutorial.interfaces.model.Movie;

import java.util.List;

/**
 * Description: FileMovieFinder
 *
 * a fake implement *
 *
 * @author yushen.ma
 * @version 2015-03-08 00:22
 */
public class FileMovieFinder implements MovieFinder {
    @Override
    public List<Movie> find() {
        return Lists.newArrayList(new Movie("SpringTutorial", "Yushen.ma"),
                                  new Movie("CampusTraining", "Yaya.zhang")
                );
    }
}
