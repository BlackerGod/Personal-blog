package ZMQ.utli;

import ZMQ.model.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONUtil {

    public static String serialize(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(Constant.DATE_PATTERN));
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON序列化失败:"+object,e);
        }
    }

    public static <T> T deserialize(InputStream is,Class<T> clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(Constant.DATE_PATTERN));
        try {
            return objectMapper.readValue(is,clazz);
        } catch (IOException e) {
            throw new RuntimeException("JSON反序列化失败:",e);
        }
    }

    public static void main(String[] args) {
        Article article = new Article();
        article.setUserId(3);
        article.setContent("我来也");
        article.setCreateTime(new Date());
        String json = serialize(article);
        System.out.println(json);
    }
}
