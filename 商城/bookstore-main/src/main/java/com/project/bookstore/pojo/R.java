package com.project.bookstore.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data // Lombok 简化开发
public class R extends HashMap {

    /**
     * 任务成功，返回数据与信息
     * @param data 数据
     * @param message 信息
     * @return
     */
    public static R SUCCESS(Object data, String message) {
        R r = new R();
        r.put("code",200);
        r.put("data",data);
        r.put("message",message);
        return r;
    }

    /**
     * 任务成功，只需要返回提示信息即可
     * @param message 需要返回的信息
     * @return
     */
    public static R SUCCESS(String message) {
        R r = new R();
        r.put("code",200);
        r.put("message",message);
        return r;
    }

    /**
     * 任务失败，提示失败信息
     * @param message
     * @return
     */
    public static R FAIL(String message) {
        R r = new R();
        r.put("code",400);
        r.put("message",message);
        return r;
    }

    /* 设置状态码 */
    public static R setCodes(int code){
        R r = new R();
        r.put("code",code);
        return r;
    }

    /* 设置状态码和数据 */
    public static R setDate(int code, Object data){
        R r = new R();
        r.put("code",code);
        r.put("data",data);
        return r;
    }

}
