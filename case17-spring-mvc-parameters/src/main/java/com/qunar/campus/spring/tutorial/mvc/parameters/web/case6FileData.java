package com.qunar.campus.spring.tutorial.mvc.parameters.web;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description: case6FileData
 *
 * 文件上传:
 *
 * 注：普通文件，如图片、小文件等等东西。可以参考这个示例
 *
 * 量很大的图片用公司的图片服务。
 * 大文件，可能需要用到http chunk， 自行设计存储
 *
 * 注：文件上传需要考虑多机部署的情况
 *
 * 1. 需要引入两个依赖：
 *
 * <code>
 *     	<!-- Apache Commons FileUpload -->
         <dependency>
         <groupId>commons-fileupload</groupId>
         <artifactId>commons-fileupload</artifactId>
         <version>1.3.1</version>
         </dependency>

         <!-- Apache Commons IO -->
         <dependency>
         <groupId>commons-io</groupId>
         <artifactId>commons-io</artifactId>
         <version>2.4</version>
         </dependency>
  </code>

  2. 配置spring multiPartResolver
 *
 * @author yushen.ma
 * @version 2015-04-01 23:46
 */
@Controller
@RequestMapping("/file")
public class case6FileData {

    @RequestMapping(value = "/upload")
    @ResponseBody
    public void handleFormUpload(@RequestParam("file") MultipartFile file,
                                   HttpServletResponse response) throws IOException {
        byte[] bytes = file.getBytes();
        InputStream inputStream = file.getInputStream();
        // store the bytes somewhere
        // or read from inputStream

        IOUtils.copy(inputStream, response.getOutputStream());

        // 如何存文件..
        // 如何存到某个目录..嗯..够你折腾啦
    }

}
