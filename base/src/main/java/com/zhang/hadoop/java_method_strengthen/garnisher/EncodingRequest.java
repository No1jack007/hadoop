package com.zhang.hadoop.java_method_strengthen.garnisher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 16:54
 **/

/**
 * 装饰者模式：
 * 增强request的getParameter方法，使其编码格式为utf-8
 */
public class EncodingRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    public EncodingRequest(HttpServletRequest request) {
        super(request);
        this.request=request;
    }

    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        try {
            value=new String(value.getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

}
