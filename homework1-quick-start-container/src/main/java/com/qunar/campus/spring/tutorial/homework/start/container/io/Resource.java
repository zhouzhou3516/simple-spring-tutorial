package com.qunar.campus.spring.tutorial.homework.start.container.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liqingzhou on 17/7/21.
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
