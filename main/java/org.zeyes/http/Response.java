package org.zeyes.http;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private int statusCode;
    private String contentType;
    private Map<String, String> headers;
    private String body;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeader(String key) {
        if (null != headers) {
            return headers.get(key);
        } else {
            return null;
        }
    }

    public void setHeader(String key, String value) {
        if (null == this.headers) {
            this.headers = new HashMap<String, String>();
        }
        this.headers.put(key, value);
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", contentType='" + contentType + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
