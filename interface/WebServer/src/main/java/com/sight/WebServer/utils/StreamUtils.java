package com.sight.WebServer.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;

public class StreamUtils {

    /**
     * @param inputStream inputStream
     * @return 字符串转换之后的
     */
    public static String streamToString(InputStream inputStream) {
        try(BufferedReader br =new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            StringBuilder builder = new StringBuilder();
            String output;
            while((output = br.readLine())!=null){
                builder.append(output);
            }
            return builder.toString();
        }  catch (IOException e) {
           throw new RuntimeException("Http failed",e);
        }
    }

    

    public static byte[] readBytes(ServletInputStream inputStream) {
        return streamToString(inputStream).getBytes(Charset.forName("UTF-8"));
    }
}