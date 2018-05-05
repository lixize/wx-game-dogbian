package org.zeyes.http.utils;

/*
 * Http工具类
 */

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.zeyes.http.Response;
import org.zeyes.http.constant.Constants;
import org.zeyes.http.constant.ContentType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.*;

public class HttpUtils {

    /**
     * HTTP GET
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */
    public static HttpResponse doGet(String host, String path,
                                     int connectTimeout,
                                     Map<String, String> headers,
                                     Map<String, String> querys)
            throws Exception {
        CloseableHttpClient httpClient = SslUtil.SslHttpClientBuild();
        HttpGet request = new HttpGet(buildUrl(host, path, querys));
        request.setConfig(requestConfigBuild(connectTimeout));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        return httpClient.execute(request);
    }

    /**
     * POST Form
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param bodys
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path,
                                      int connectTimeout,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      Map<String, String> bodys)
            throws Exception {
        CloseableHttpClient httpClient = SslUtil.SslHttpClientBuild();
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        request.setConfig(requestConfigBuild(connectTimeout));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, Constants.ENCODING);
            formEntity.setContentType(ContentType.CONTENT_TYPE_FORM);
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }

    /**
     * POST String
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path,
                                      int connectTimeout,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      String body)
            throws Exception {
        CloseableHttpClient httpClient = SslUtil.SslHttpClientBuild();
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        request.setConfig(requestConfigBuild(connectTimeout));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, Constants.ENCODING));
        }
        return httpClient.execute(request);
    }

    /**
     * POST Stream
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path,
                                      int connectTimeout,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      byte[] body)
            throws Exception {
        CloseableHttpClient httpClient = SslUtil.SslHttpClientBuild();
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        request.setConfig(requestConfigBuild(connectTimeout));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        return httpClient.execute(request);
    }

    /**
     * PUT String
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String host, String path,
                                     int connectTimeout,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      String body)
            throws Exception {
        CloseableHttpClient httpClient = SslUtil.SslHttpClientBuild();
        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        request.setConfig(requestConfigBuild(connectTimeout));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, Constants.ENCODING));
        }
        return httpClient.execute(request);
    }

    /**
     * PUT Stream
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String host, String path,
                                     int connectTimeout,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      byte[] body)
            throws Exception {
        CloseableHttpClient httpClient = SslUtil.SslHttpClientBuild();
        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        request.setConfig(requestConfigBuild(connectTimeout));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        return httpClient.execute(request);
    }

    /**
     * DELETE
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doDelete(String host, String path,
                                     int connectTimeout,
                                     Map<String, String> headers,
                                     Map<String, String> querys)
            throws Exception {
        CloseableHttpClient httpClient = SslUtil.SslHttpClientBuild();
        HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        request.setConfig(requestConfigBuild(connectTimeout));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        return httpClient.execute(request);
    }

    /**
     * 读取超时时间
     *
     * @param timeout
     * @return 超时时间
     */
    private static int getTimeout(int timeout) {
        if (timeout == 0) {
            return Constants.DEFAULT_TIMEOUT;
        }

        return timeout;
    }

    /**
     * 设置超时时间，请求超时默认5000
     * @param connTimout 连接超时
     * @return 配置信息
     */
    private static RequestConfig requestConfigBuild(int connTimout) {
        return requestConfigBuild(getTimeout(connTimout), 5000, 5000);
    }

    /**
     * 设置超时时间
     * @param connTimout
     * @param requestTimeout
     * @param socketTimeout
     * @return 配置信息
     */
    private static RequestConfig requestConfigBuild(int connTimout, int requestTimeout, int socketTimeout) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connTimout)   //设置连接超时时间
                .setConnectionRequestTimeout(requestTimeout) // 设置请求超时时间
                .setSocketTimeout(socketTimeout)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        return requestConfig;
    }

    /**
     * 转换HttpResponse为Response
     * @param response
     * @return
     * @throws IOException
     */
    public static Response convert(HttpResponse response) throws IOException {
        Response res = new Response();

        if (null != response) {
            res.setStatusCode(response.getStatusLine().getStatusCode());
            for (Header header : response.getAllHeaders()) {
                res.setHeader(header.getName(), header.getValue());
            }
            res.setContentType(res.getHeader("Content-Type"));
            res.setBody(readStreamAsStr(response.getEntity().getContent()));

        } else {
            // 服务器无回应
            res.setStatusCode(500);
        }
        return res;
    }

    /**
     * 将流转换为字符串
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String readStreamAsStr(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        WritableByteChannel dest = Channels.newChannel(bos);
        ReadableByteChannel src = Channels.newChannel(is);
        ByteBuffer bb = ByteBuffer.allocate(4096);

        while (src.read(bb) != -1) {
            bb.flip();
            dest.write(bb);
            bb.clear();
        }
        src.close();
        dest.close();

        return new String(bos.toByteArray(), Constants.ENCODING);
    }

    /**
     * 构建FormEntity
     *
     * @param formParam
     * @return
     * @throws UnsupportedEncodingException
     */
    private static UrlEncodedFormEntity buildFormEntity(Map<String, String> formParam)
            throws UnsupportedEncodingException {
        if (formParam != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

            for (String key : formParam.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, formParam.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, Constants.ENCODING);
            formEntity.setContentType(ContentType.CONTENT_TYPE_FORM);
            return formEntity;
        }

        return null;
    }

    /**
     * 拼接Url
     * @param host
     * @param path
     * @param querys
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isBlank(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append(Constants.SPE3);
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append(Constants.SPE4);
                        sbQuery.append(URLEncoder.encode(query.getValue(), Constants.ENCODING));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append(Constants.SPE5).append(sbQuery);
            }
        }
        //System.out.println(sbUrl.toString());
        return sbUrl.toString();
    }
}
