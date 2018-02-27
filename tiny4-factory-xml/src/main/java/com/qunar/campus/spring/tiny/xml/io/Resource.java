package com.qunar.campus.spring.tiny.xml.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource是spring内部定位资源的接口。这里我们写一个一模一样的
 *
 * @author yushen.ma
 * @version 2015-03-16 22:05
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
