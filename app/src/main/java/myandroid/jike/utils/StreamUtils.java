package myandroid.jike.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by caiyiqi on 2017/8/27.
 */

public class StreamUtils {
    public static String convertStream(InputStream inputStream) {
        String result = "";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                bos.write(data, 0, len);
                bos.flush();
            }
            result = new String(bos.toByteArray(), "utf-8");
            result = bos.toString();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
