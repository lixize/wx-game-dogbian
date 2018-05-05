package org.zeyes.http;

import org.zeyes.http.enums.Method;

import java.util.Map;

public class Request {
    private Method method;                      // （必选）请求方法
    private String host;                        // （必选）Host
    private String path;                        // （必选）Path
    private int timeout;                        // （必选）超时时间,单位毫秒,设置零默认使用Constants.DEFAULT_TIMEOUT
    private Map<String, String> headers;        // （可选） HTTP头
    private Map<String, String> querys;         // （可选） Querys
    private Map<String, String> bodys;          // （可选）表单参数
    private String stringBody;                  // （可选）字符串Body体
    private byte[] bytesBody;                   // （可选）字节数组类型Body体

    public Request() {
    }

    public Request(Method method, String host, String path, int timeout) {
        this.method = method;
        this.host = host;
        this.path = path;
        this.timeout = timeout;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getQuerys() {
        return querys;
    }

    public void setQuerys(Map<String, String> querys) {
        this.querys = querys;
    }

    public Map<String, String> getBodys() {
        return bodys;
    }

    public void setBodys(Map<String, String> bodys) {
        this.bodys = bodys;
    }

    public String getStringBody() {
        return stringBody;
    }

    public void setStringBody(String stringBody) {
        this.stringBody = stringBody;
    }

    public byte[] getBytesBody() {
        return bytesBody;
    }

    public void setBytesBody(byte[] bytesBody) {
        this.bytesBody = bytesBody;
    }
}
