package com.aio.monomer.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO
 * @author: Mr.chen
 * @date: 2020/3/27 16:31
 * @version: 1.0
 */
public class AioHttpClientUtils {
    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    public static String doGet(String url, Map<String, String> param) {
        return doGet(url, param, null);
    }

    public static String doGet(String url, Map<String, String> param, Map<String, String> headerMap) {
        return doGet(url, param, headerMap, null);
    }

    public static String doPost(String url) {
        return doPost(url, null, null);
    }

    public static String doPost(String url, String param) {
        return doPost(url, param, null);
    }

    public static String doPost(String url, String param, Map<String, String> headerMap) {
        return doPost(url, param, headerMap, null);
    }

    public static String doPostJson(String url, Map<String, String> param) {
        return doPostJson(url, param, null);
    }

    public static String doPostJson(String url, Map<String, String> param, Map<String, String> headerMap) {
        return doPostJson(url, param, headerMap, null);
    }

    public static String doPostHttpEntity(String url, String param) {
        return doPostHttpEntity(url, param, null);
    }

    public static String doPostHttpEntity(String url, String param, Map<String, String> headerMap) {
        return doPostHttpEntity(url, param, headerMap, null);
    }

    public static String doPostJsonHttpEntity(String url, Map<String, String> param) {
        return doPostJsonHttpEntity(url, param, null);
    }

    public static String doPostJsonHttpEntity(String url, Map<String, String> param, Map<String, String> headerMap) {
        return doPostJsonHttpEntity(url, param, headerMap, null);
    }

    public static String doGet(String url, Map<String, String> param, Map<String, String> headerMap, Integer timeout) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(url);
            if (timeout != null) {
                RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
                httpGet.setConfig(config);
            }
            // 添加header头信息
            if (headerMap != null && headerMap.size() > 0) {
                for (String key : headerMap.keySet()) {
                    httpGet.addHeader(key, headerMap.get(key));
                }
            }
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doPost(String url, String param, Map<String, String> headerMap, Integer timeout) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (timeout != null) {
                RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
                httpPost.setConfig(config);
            }
            // 添加header头信息
            if (headerMap != null && headerMap.size() > 0) {
                for (String key : headerMap.keySet()) {
                    httpPost.addHeader(key, headerMap.get(key));
                }
            }
            // 创建参数列表
            if (param != null) {
                StringEntity stringEntity = new StringEntity(param, "utf-8");
                httpPost.setEntity(stringEntity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPostJson(String url, Map<String, String> param, Map<String, String> headerMap, Integer timeout) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (timeout != null) {
                RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
                httpPost.setConfig(config);
            }
            // 添加header头信息
            if (headerMap != null && headerMap.size() > 0) {
                for (String key : headerMap.keySet()) {
                    httpPost.addHeader(key, headerMap.get(key));
                }
            }
            // 创建参数列表
            if (param != null && !param.isEmpty()) {
                List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                for(Map.Entry<String,String> entry : param .entrySet()){
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
                httpPost.setEntity(urlEncodedFormEntity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPostHttpEntity(String url, String param, Map<String, String> headerMap, Integer timeout) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        ByteArrayOutputStream out = null;
        InputStream in = null;
        String result = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (timeout != null) {
                RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
                httpPost.setConfig(config);
            }
            // 添加header头信息
            if (headerMap != null && headerMap.size() > 0) {
                for (String key : headerMap.keySet()) {
                    httpPost.addHeader(key, headerMap.get(key));
                }
            }
            // 创建参数列表
            if (param != null) {
                StringEntity stringEntity = new StringEntity(param, "utf-8");
                httpPost.setEntity(stringEntity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            in = response.getEntity().getContent();
            out = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = in.read(data)) != -1) {
                out.write(data, 0, len);
            }
            Base64 base = new Base64();
            result = base.encodeAsString(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String doPostJsonHttpEntity(String url, Map<String, String> param, Map<String, String> headerMap, Integer timeout) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        ByteArrayOutputStream out = null;
        InputStream in = null;
        String result = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (timeout != null) {
                RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
                httpPost.setConfig(config);
            }
            // 添加header头信息
            if (headerMap != null && headerMap.size() > 0) {
                for (String key : headerMap.keySet()) {
                    httpPost.addHeader(key, headerMap.get(key));
                }
            }
            // 创建参数列表
            if (param != null && !param.isEmpty()) {
                List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                for(Map.Entry<String,String> entry : param .entrySet()){
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
                httpPost.setEntity(urlEncodedFormEntity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            in = response.getEntity().getContent();
            out = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = in.read(data)) != -1) {
                out.write(data, 0, len);
            }
            Base64 base = new Base64();
            result = base.encodeAsString(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
