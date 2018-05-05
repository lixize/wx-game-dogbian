package org.zeyes.http;


import org.zeyes.http.Request;
import org.zeyes.http.Response;
import org.zeyes.http.utils.HttpUtils;

/**
 * Client
 */
public class Client {
    /**
     * 发送请求
     *
     * @param request request对象
     * @return Response
     * @throws Exception
     */
    public static Response execute(Request request) throws Exception {
        switch (request.getMethod()) {
            case GET:
                return HttpUtils.convert(HttpUtils.doGet(request.getHost(), request.getPath(),
                        request.getTimeout(),
                        request.getHeaders(),
                        request.getQuerys()));
            case POST_FORM:
                return HttpUtils.convert(HttpUtils.doPost(request.getHost(), request.getPath(),
                        request.getTimeout(),
                        request.getHeaders(),
                        request.getQuerys(),
                        request.getBodys()));
            case POST_STRING:
                return HttpUtils.convert(HttpUtils.doPost(request.getHost(), request.getPath(),
                        request.getTimeout(),
                        request.getHeaders(),
                        request.getQuerys(),
                        request.getStringBody()));
            case POST_BYTES:
                return HttpUtils.convert(HttpUtils.doPost(request.getHost(), request.getPath(),
                        request.getTimeout(),
                        request.getHeaders(),
                        request.getQuerys(),
                        request.getBytesBody()));
            case PUT_STRING:
                return HttpUtils.convert(HttpUtils.doPut(request.getHost(), request.getPath(),
                        request.getTimeout(),
                        request.getHeaders(),
                        request.getQuerys(),
                        request.getStringBody()));
            case PUT_BYTES:
                return HttpUtils.convert(HttpUtils.doPut(request.getHost(), request.getPath(),
                        request.getTimeout(),
                        request.getHeaders(),
                        request.getQuerys(),
                        request.getBytesBody()));
            case DELETE:
                return HttpUtils.convert(HttpUtils.doDelete(request.getHost(), request.getPath(),
                        request.getTimeout(),
                        request.getHeaders(),
                        request.getQuerys()));
            default:
                throw new IllegalArgumentException(String.format("unsupported method:%s", request.getMethod()));
        }
    }
}