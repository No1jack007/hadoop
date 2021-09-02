package com.zhang.hadoop.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description : http调用工具
 * @Author han.yu
 * @Date 2018/7/9
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static PoolingHttpClientConnectionManager connMgr;

    private static RequestConfig requestConfig;

    private static final int MAX_TOTAL = 500;

    private static final int DEFAULT_MAX_PER_ROUTE = 200;

    private static final int CONNECT_TIMEOUT = 60000;

    private static final int SOCKET_TIMEOUT = 60000;

    private static final int CONNECTION_REQUEST_TIMEOUT = 50000;

    private static final String RESPONSE_CHARSET = "UTF-8";

    private static final String DEFAULT_CHARSET = "UTF-8";

    static {
        connMgr = new PoolingHttpClientConnectionManager();
        connMgr.setMaxTotal(MAX_TOTAL);
        connMgr.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        configBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        // 设置从连接池中获取连接的超时时间
        configBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
        requestConfig = configBuilder.build();

        //定时清理连接池中的无效http连接
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("http-client-utils-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //                System.out.println("=====closeIdleHttpConnections===");
                try {
                    connMgr.closeExpiredConnections();
                    connMgr.closeIdleConnections(5, TimeUnit.SECONDS);
                } catch (Exception ex) {
                    logger.error("closeIdleHttpConnections:" + ex.getMessage());
                }
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public static void setHttpParam(int connectTimout, int maxTimeout) {
        Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(connectTimout);
        configBuilder.setSocketTimeout(maxTimeout);
        configBuilder.setConnectionRequestTimeout(maxTimeout);
        requestConfig = configBuilder.build();
    }

    public static String doGet(String url) throws Exception {
        return doGet(url, new HashMap<>());
    }

    public static String doGet(String url, Map<String, Object> params) throws Exception {
        return doGet(url, null, params, DEFAULT_CHARSET);
    }

    public static String doGet(String url, Map<String, String> headerMap, Map<String, Object> params, String charset) throws Exception {
        if (StringUtils.isEmpty(url)) {
            logger.info("请求URL为空");
            return null;
        } else {
            List<NameValuePair> pairList = generateNameValuePairList(params);
            URI uri = generateUri(url, pairList, charset);

            HttpGet httpGet = new HttpGet(uri);
            if (headerMap != null) {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            return send(null, httpGet, false);
        }
    }

    public static String doPost(String url) throws Exception {
        return doPost(url, new HashMap<>(), RESPONSE_CHARSET);
    }

    public static String doPost(String url, Map<String, Object> params, String charset) throws Exception {
        return doPost(url, null, params, charset, false);
    }

    public static String doPost(String url, Map<String, String> headerMap, Map<String, Object> params, String charset, boolean isSSL) throws Exception {
        if (StringUtils.isEmpty(url)) {
            logger.info("请求URL为空");
            return null;
        } else {
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<String, Object> entry = iterator.next();
                if (entry.getValue() == null) {
                    continue;
                }
                BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, charset));

            if (headerMap != null) {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            return send(httpPost, null, isSSL);
        }
    }

    public static String doPostMultipart(String url, Map<String, String> headerMap, Map<String, Object> params, InputStream fileStream, String fileName, String fileParamName, boolean isSSL) throws Exception {
        return doPostMultipart(url, headerMap, params, fileStream, fileName, fileParamName, isSSL,  ContentType.DEFAULT_TEXT);
    }

    public static String doPostMultipart(String url, Map<String, String> headerMap, Map<String, Object> params, InputStream fileStream, String fileName, String fileParamName, boolean isSSL,final ContentType textContentType) throws Exception {
        if (StringUtils.isEmpty(url)) {
            logger.info("请求URL为空");
            return null;
        } else {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName("utf-8"));
            //加上此行代码解决返回中文乱码问题
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            // 文件流
            builder.addBinaryBody(fileParamName, fileStream, ContentType.MULTIPART_FORM_DATA, fileName);
            if (params != null) {
                Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = iterator.next();
                    // 类似浏览器表单提交，对应input的name和value
                    builder.addTextBody(entry.getKey(), entry.getValue().toString(),textContentType);
                }
            }
            HttpEntity entity = builder.build();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            if (headerMap != null) {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            return send(httpPost, null, isSSL);
        }
    }


    public static String doPost(String url, Object json) throws Exception {
        return doPost(url, json, DEFAULT_CHARSET, false);
    }

    public static String doPost(String url, Object json, Map<String, String> headerMap) throws Exception {
        return doPost(url, json, DEFAULT_CHARSET, false, headerMap);
    }

    public static String doPut(String url, boolean isSsl) throws Exception {
        HttpPut httpPut = new HttpPut(url);
        return send(httpPut, null, isSsl);
    }

    private static List<NameValuePair> generateNameValuePairList(Map<String, Object> params) {
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entry = iterator.next();
            pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        return pairList;
    }

    private static URI generateUri(String url, List<NameValuePair> pairList, String charset) throws URISyntaxException {
        URIBuilder URIBuilder = new URIBuilder(url);
        URIBuilder.setCharset(Charset.forName(StringUtils.isEmpty(charset) ? DEFAULT_CHARSET : charset));
        URIBuilder.addParameters(pairList);
        URI uri = URIBuilder.build();
        return uri;
    }

    public static String doPut(String url, Map<String, String> headerMap, Map<String, Object> params, String charset, boolean isSsl) throws Exception {
        if (StringUtils.isEmpty(url)) {
            logger.info("请求URL为空");
            return null;
        } else {
            List<NameValuePair> pairList = generateNameValuePairList(params);
            URI uri = generateUri(url, pairList, charset);
            HttpPut httpPut = new HttpPut(uri);
            if (headerMap != null) {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpPut.setHeader(entry.getKey(), entry.getValue());
                }
            }
            return send(httpPut, null, false);
        }
    }

    public static String doDelete(String url, boolean isSsl) throws Exception {
        HttpDelete httpDelete = new HttpDelete(url);
        return send(null, httpDelete, isSsl);
    }

    public static String doPost(String url, Object json, String charset, boolean isSSL) throws Exception {
        return doPost(url, json, charset, isSSL, new HashMap<>());
    }

    public static String doPost(String url, Object json, String charset, boolean isSSL, Map<String, String> headerMap) throws Exception {
        if (StringUtils.isEmpty(url)) {
            logger.info("请求URL为空");
            return null;
        } else {
            StringEntity stringEntity = new StringEntity(json.toString(), DEFAULT_CHARSET);
            //stringEntity.setContentEncoding("gzip"); //内容编码格式gzip 和deflate
            stringEntity.setContentType("application/json");

            HttpPost httpPost = new HttpPost(url);
            if (headerMap != null) {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httpPost.setEntity(stringEntity);
            return send(httpPost, null, isSSL);
        }
    }

    private static String send(HttpEntityEnclosingRequestBase httpPostOrPut, HttpRequestBase httpGetOrDelete, boolean isSsl) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        InputStream inputStream = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = null;
            if (isSsl) {
                SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((KeyStore) null, new TrustSelfSignedStrategy()).build();
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
                httpClient = HttpClients.custom()
                        .setSSLSocketFactory(sslsf)
                        .setConnectionManager(connMgr)
                        .setRetryHandler(HttpRequestNoRetryHandler.Singleton)
                        .setDefaultRequestConfig(requestConfig)
                        .build();
            } else {
                httpClient = HttpClients.custom()
                        .setConnectionManager(connMgr)
                        .setRetryHandler(HttpRequestNoRetryHandler.Singleton)
                        .setDefaultRequestConfig(requestConfig)
                        .build();
            }
            if (httpPostOrPut != null) {
                response = httpClient.execute(httpPostOrPut);
            } else if (httpGetOrDelete != null) {
                response = httpClient.execute(httpGetOrDelete);
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                inputStream = entity.getContent();
                result = IOUtils.toString(inputStream, RESPONSE_CHARSET);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
            if (httpPostOrPut != null) {
                httpPostOrPut.releaseConnection();
            } else if (httpGetOrDelete != null) {
                httpGetOrDelete.releaseConnection();
            }
        }
        return result;
    }

    /**
     * 临时适配解决邮件发送接口调用 返回异常HTML问题
     *
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String doPostJsonForMail(String url, Object json) throws Exception {
        if (StringUtils.isEmpty(url)) {
            logger.info("请求URL为空");
            return null;
        } else {
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(json.toString(), DEFAULT_CHARSET);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

            CloseableHttpClient httpClient = null;
            InputStream instream = null;
            String result = null;
            CloseableHttpResponse response = null;
            httpClient = HttpClients.custom().setConnectionManager(connMgr).build();
            try {
                httpPost.setConfig(requestConfig);
                response = httpClient.execute(httpPost);
                HttpEntity entity1 = response.getEntity();
                if (entity1 != null) {
                    instream = entity1.getContent();
                    result = IOUtils.toString(instream, DEFAULT_CHARSET);
                }
            } finally {
                if (instream != null) {
                    instream.close();
                }
                if (response != null) {
                    response.close();
                }
            }
            return result;
        }
    }

}

class HttpRequestNoRetryHandler extends DefaultHttpRequestRetryHandler {

    public static final HttpRequestNoRetryHandler Singleton = new HttpRequestNoRetryHandler();

    private HttpRequestNoRetryHandler() {
    }

    @Override
    public boolean retryRequest(
            final IOException exception,
            int executionCount,
            final HttpContext context) {
        return false;
    }
}


