package com.depthmind.concurrent;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.net.ssl.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("deprecation")
public class HttpKit {
    private static final String DEFAULT_CHARSET = "UTF-8";

    	public static final String _GET  = "GET"; // GET
    	public static final String _POST = "POST";// POST
    	public static final String _PUT = "PUT";
	    public static String METHOD_DELETE = "DELETE";
	   

	    /**
	     * 初始化http请求参数
	     *
	     * @param url
	     * @param method
	     * @param headers
	     * @return
	     * @throws IOException
	     */
	    private static HttpURLConnection initHttp(String url, String method, Map<String, String> headers) throws IOException {
	        URL _url = new URL(url);
	        HttpURLConnection http = (HttpURLConnection) _url.openConnection();
	        // 连接超时
	        http.setConnectTimeout(60000);
	        // 读取超时 --服务器响应比较慢，增大时间
	        http.setReadTimeout(60000);
	        http.setRequestMethod(method);
	        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
	        if (null != headers && !headers.isEmpty()) {
	            for (Entry<String, String> entry : headers.entrySet()) {
	                http.setRequestProperty(entry.getKey(), entry.getValue());
	            }
	        }
	        http.setDoOutput(true);
	        http.setDoInput(true);
	        //http.connect();
	        return http;
	    }
	
	    /**
	     * 初始化http请求参数
	     *
	     * @param url
	     * @param method
	     * @return
	     * @throws IOException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws KeyManagementException
	     */
	    public static HttpsURLConnection initHttps(String url, String method, Map<String, String> headers) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
	        TrustManager[] tm = {new MyX509TrustManager()};
	        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	        sslContext.init(null, tm, new java.security.SecureRandom());
	        // 从上述SSLContext对象中得到SSLSocketFactory对象
	        SSLSocketFactory ssf = sslContext.getSocketFactory();
	        URL _url = new URL(url);
	        HttpsURLConnection http = (HttpsURLConnection) _url.openConnection();
	        // 设置域名校验
	        http.setHostnameVerifier(new HttpKit().new TrustAnyHostnameVerifier());
	        // 连接超时
	        http.setConnectTimeout(60000);
	        // 读取超时 --服务器响应比较慢，增大时间
	        http.setReadTimeout(60000);
	        http.setRequestMethod(method);
	        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
	        if (null != headers && !headers.isEmpty()) {
	            for (Entry<String, String> entry : headers.entrySet()) {
	                http.setRequestProperty(entry.getKey(), entry.getValue());
	            }
	        }
	        http.setSSLSocketFactory(ssf);
	        http.setDoOutput(true);
	        http.setDoInput(true);
	        //http.connect();
	        return http;
	    }
	
	    /**
	     * 初始化http请求参数
	     *
	     * @param url
	     * @param method
	     * @return
	     * @throws IOException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws KeyManagementException
	     */
	    public static HttpsURLConnection initSearchHttps(String url, String method, Map<String, String> headers) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
	        TrustManager[] tm = {new MyX509TrustManager()};
	        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	        sslContext.init(null, tm, new java.security.SecureRandom());
	        // 从上述SSLContext对象中得到SSLSocketFactory对象
	        SSLSocketFactory ssf = sslContext.getSocketFactory();
	        URL _url = new URL(url);
	        HttpsURLConnection http = (HttpsURLConnection) _url.openConnection();
	        // 设置域名校验
	        http.setHostnameVerifier(new HttpKit().new TrustAnyHostnameVerifier());
	        // 连接超时
	        http.setConnectTimeout(25000);
	        // 读取超时 --服务器响应比较慢，增大时间
	        http.setReadTimeout(25000);
	        http.setRequestMethod(method);
	        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
	        if (null != headers && !headers.isEmpty()) {
	            for (Entry<String, String> entry : headers.entrySet()) {
	                http.setRequestProperty(entry.getKey(), entry.getValue());
	            }
	        }
	        http.setSSLSocketFactory(ssf);
	        http.setDoOutput(true);
	        http.setDoInput(true);
	        http.setRequestProperty("Content-type", "application/json");
	        http.setRequestProperty("charset", "UTF-8");
	        http.connect();
	        return http;
	    }
	
	    /**
	     * @return 返回类型:
	     * @throws IOException
	     * @throws UnsupportedEncodingException
	     * @throws NoSuchProviderException
	     * @throws NoSuchAlgorithmException
	     * @throws KeyManagementException
	     * @description 功能描述: get 请求
	     */
	    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException {
	        HttpURLConnection http = null;
	        if (isHttps(url)) {
	            http = initHttps(initParams(url, params), _GET, headers);
	        } else {
	            http = initHttp(initParams(url, params), _GET, headers);
	        }
	        http.connect();
	        InputStream in = http.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        StringBuffer bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null) {
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (http != null) {
	            http.disconnect();// 关闭连接
	        }
	        return bufferRes.toString();
	    }
	
	    /**
	     * @return 返回类型:
	     * @throws IOException
	     * @throws UnsupportedEncodingException
	     * @throws NoSuchProviderException
	     * @throws NoSuchAlgorithmException
	     * @throws KeyManagementException
	     * @description 功能描述: get 请求
	     */
	    public static String get(String url) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException {
	        return get(url, null);
	    }
	
	    /**
	     * @return 返回类型:
	     * @throws IOException
	     * @throws NoSuchProviderException
	     * @throws NoSuchAlgorithmException
	     * @throws KeyManagementException
	     * @throws UnsupportedEncodingException
	     * @description 功能描述: get 请求
	     */
	    public static String get(String url, Map<String, String> params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException {
	        return get(url, params, null);
	    }
	
	    /**
	     * @return 返回类型:
	     * @throws IOException
	     * @throws NoSuchProviderException
	     * @throws NoSuchAlgorithmException
	     * @throws KeyManagementException
	     * @description 功能描述: POST 请求
	     */
	    public static String post(String url, String params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
	        HttpURLConnection http = null;
	        if (isHttps(url)) {
	            http = initHttps(url, _POST, null);
	        } else {
	            http = initHttp(url, _POST, null);
	        }
	        http.connect();
	        OutputStream out = http.getOutputStream();
	        out.write(params.getBytes(DEFAULT_CHARSET));
	        out.flush();
	        out.close();
	
	        InputStream in = http.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        StringBuffer bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null) {
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (http != null) {
	            http.disconnect();// 关闭连接
	        }
	        return bufferRes.toString();
	    }
	
	    /**
	     * POST请求，设置请求头
	     *
	     * @param url
	     * @param params
	     * @param headers
	     * @return
	     * @throws KeyManagementException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws IOException
	     */
	    public static String post(String url, String params, Map<String, String> headers) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
	        HttpURLConnection http = null;
	        if (isHttps(url)) {
	            http = initHttps(url, _POST, headers);
	        } else {
	            http = initHttp(url, _POST, headers);
	        }
	        http.connect();
	        OutputStream out = http.getOutputStream();
	        out.write(params.getBytes(DEFAULT_CHARSET));
	        out.flush();
	        out.close();
	
	        InputStream in = http.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        StringBuffer bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null) {
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (http != null) {
	            http.disconnect();// 关闭连接
	        }
	        return bufferRes.toString();
	    }
	
	    /**
	     * 上传媒体文件
	     *
	     * @param url
	     * @param
	     * @param file
	     * @return
	     * @throws IOException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws KeyManagementException
	     */
	    public static String upload(String url, File file) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
	        String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL"; // 定义数据分隔线
	        StringBuffer bufferRes = null;
	        URL urlGet = new URL(url);
	        HttpURLConnection conn = (HttpURLConnection) urlGet.openConnection();
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        conn.setUseCaches(false);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
	        conn.setRequestProperty("Charsert", "UTF-8");
	        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
	
	        OutputStream out = new DataOutputStream(conn.getOutputStream());
	        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
	        StringBuilder sb = new StringBuilder();
	        sb.append("--");
	        sb.append(BOUNDARY);
	        sb.append("\r\n");
	        sb.append("Content-Disposition: form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
	        sb.append("Content-Type:application/octet-stream\r\n\r\n");
	        byte[] data = sb.toString().getBytes();
	        out.write(data);
	        DataInputStream fs = new DataInputStream(new FileInputStream(file));
	        int bytes = 0;
	        byte[] bufferOut = new byte[1024];
	        while ((bytes = fs.read(bufferOut)) != -1) {
	            out.write(bufferOut, 0, bytes);
	        }
	        out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
	        fs.close();
	        out.write(end_data);
	        out.flush();
	        out.close();
	
	        // 定义BufferedReader输入流来读取URL的响应
	        InputStream in = conn.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null) {
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (conn != null) {
	            // 关闭连接
	            conn.disconnect();
	        }
	        return bufferRes.toString();
	    }
	
	    /**
	     * 上传文件
	     *
	     * @param url
	     * @param file
	     * @param param
	     * @param header
	     * @return
	     * @throws IOException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws KeyManagementException
	     */
	    public static String upload(String url, File file, Map<String, String> param, Map<String, String> header) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
	        String boundary = "-----------------------------" + System.currentTimeMillis(); // 定义数据分隔线
	        StringBuffer bufferRes = null;
	        HttpURLConnection conn = null;
	        if (isHttps(url)) {
	            conn = initHttps(url, _POST, header);
	        } else {
	            conn = initHttp(url, _POST, header);
	        }
	        conn.setUseCaches(false);
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	        conn.connect();
	        OutputStream out = new DataOutputStream(conn.getOutputStream());
	        StringBuilder sb1 = new StringBuilder();
	        sb1.append("--" + boundary + "\r\n");
	        //fileFormParam 为该form的名称
	        sb1.append("Content-Disposition: form-data;name=\"" + (param.get("fileFormParam") == null ? "media" : param.get("fileFormParam")) + "\";filename=\"" + file.getName() + "\"\r\n");
	        sb1.append("Content-Type:application/octet-stream\r\n\r\n");
	        out.write(sb1.toString().getBytes());
	        System.out.print(sb1.toString());
	        DataInputStream fs = new DataInputStream(new FileInputStream(file));
	        int bytes = 0;
	        byte[] bufferOut = new byte[1024];
	        while ((bytes = fs.read(bufferOut)) != -1) {
	            out.write(bufferOut, 0, bytes);
	        }
	        out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
	        fs.close();
	        StringBuilder sb2 = new StringBuilder();
	        if (param != null && param.keySet().size() > 0) {
	            Iterator<String> it = param.keySet().iterator();
	            for (; it.hasNext(); ) {
	                String key = it.next();
	                if ("fileFormParam".equals(key)) {
	                    continue;
	                }
	                sb2.append("--" + boundary + "\r\n");
	                sb2.append("Content-Disposition: form-data;name=\"");
	                sb2.append(key);
	                sb2.append("\";\r\n\r\n");
	                sb2.append(param.get(key));
	                sb2.append("\r\n");
	            }
	        }
	        sb2.append("--" + boundary + "--\r\n\r\n");
	        out.write(sb2.toString().getBytes());
	        System.out.print(sb2.toString());
	        out.flush();
	        out.close();
	
	        // 定义BufferedReader输入流来读取URL的响应
	        InputStream in = conn.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null) {
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (conn != null) {
	            // 关闭连接
	            conn.disconnect();
	        }
	        return bufferRes.toString();
	    }
	

	
	    private static void mkDir(File file) {
	        if (file.getParentFile().exists()) {
	            file.mkdir();
	        } else {
	            mkDir(file.getParentFile());
	            file.mkdir();
	        }
	    }
	
	    private static String getFileTypeByContentType(String contentType) {
	        String fileType = "file";
	        if ("image/jpeg".equals(contentType)) {
	            fileType = "image";
	        } else if ("audio/mpeg".equals(contentType) || "audio/amr".equals(contentType)) {
	            fileType = "audio";
	        } else if ("video/mp4".equals(contentType) || "video/mpeg4".equals(contentType)) {
	            fileType = "video";
	        }
	        return fileType;
	    }
	
	    private static String getDirByFiletype(String fileType) {
	        String fileDir = "file";
	        if ("amr".equals(fileType.toLowerCase()) || "mp3".equals(fileType.toLowerCase()) || "wav".equals(fileType.toLowerCase())) {
	            fileDir = "audio";
	        } else if ("jpg".equals(fileType.toLowerCase()) || "jpeg".equals(fileType.toLowerCase()) || "gif".equals(fileType.toLowerCase()) || "png".equals(fileType.toLowerCase())) {
	            fileDir = "image";
	        } else if ("mp4".equals(fileType.toLowerCase()) || "avi".equals(fileType.toLowerCase()) || "mpeg".equals(fileType.toLowerCase()) || "mpg".equals(fileType.toLowerCase())) {
	            fileDir = "video";
	        }
	        return fileDir;
	    }
	
	
	    private static String judgeType(String contentType) {
	        String fileExt = "";
	        if ("image/jpeg".equals(contentType)) {
	            fileExt = ".jpg";
	        } else if ("audio/mpeg".equals(contentType)) {
	            fileExt = ".mp3";
	        } else if ("audio/amr".equals(contentType)) {
	            fileExt = ".amr";
	        } else if ("video/mp4".equals(contentType)) {
	            fileExt = ".mp4";
	        } else if ("video/mpeg4".equals(contentType)) {
	            fileExt = ".mp4";
	        }
	        return fileExt;
	    }
	
	    /**
	     * 功能描述: 构造请求参数
	     *
	     * @return 返回类型:
	     * @throws UnsupportedEncodingException
	     */
	    public static String initParams(String url, Map<String, String> params) throws UnsupportedEncodingException {
	        if (null == params || params.isEmpty()) {
	            return url;
	        }
	        StringBuilder sb = new StringBuilder(url);
	        if (url.indexOf("?") == -1) {
	            sb.append("?");
	        }
	        sb.append(map2Url(params));
	        return sb.toString();
	    }
	
	    /**
	     * map构造url
	     *
	     * @return 返回类型:
	     * @throws UnsupportedEncodingException
	     */
	    public static String map2Url(Map<String, String> paramToMap) throws UnsupportedEncodingException {
	        if (null == paramToMap || paramToMap.isEmpty()) {
	            return null;
	        }
	        StringBuffer url = new StringBuffer();
	        boolean isfist = true;
	        for (Entry<String, String> entry : paramToMap.entrySet()) {
	            if (isfist) {
	                isfist = false;
	            } else {
	                url.append("&");
	            }
	            url.append(entry.getKey()).append("=");
	            String value = entry.getValue();
	            if (StringUtils.isNotEmpty(value)) {
	                url.append(URLEncoder.encode(value, DEFAULT_CHARSET));
	            }
	        }
	        return url.toString();
	    }
	
	    /**
	     * 检测是否https
	     *
	     * @param url
	     */
	    private static boolean isHttps(String url) {
	        return url.startsWith("https");
	    }
	
	
	    public static String uploadToBjh(String url, File file, Map<String, String> param, Map<String, String> header, String boundary,String start_offset) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
	        StringBuffer bufferRes = null;
	        HttpURLConnection conn = null;
	        if (isHttps(url)) {
	            conn = initHttps(url, _POST, header);
	        } else {
	            conn = initHttp(url, _POST, header);
	        }
	        // 连接超时
	        conn.setConnectTimeout(60000);
	        // 读取超时 --服务器响应比较慢，增大时间
	        conn.setReadTimeout(60000);
	        System.out.println("====60000");
	        conn.setUseCaches(false);
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	        conn.connect();
	        OutputStream out = new DataOutputStream(conn.getOutputStream());
	        StringBuilder sb1 = new StringBuilder();
	        sb1.append("--" + boundary + "\r\n") ;
	        //fileFormParam 为该form的名称
	        sb1.append("Content-Disposition: form-data;name=\""+(param.get("fileFormParam")==null?"file":param.get("fileFormParam"))+"\";filename=\""+ file.getName() + "\";file="+"\r\n");
	        sb1.append("Content-Type:application/octet-stream\r\n\r\n");
	        out.write(sb1.toString().getBytes());
	        System.out.print("sb1.toString:"+sb1.toString());
	        DataInputStream fs = new DataInputStream(new FileInputStream(file));
	        int bytes = 0;
	        byte[] bufferOut = new byte[10*1024*1024];
	        int q=0;
	        System.out.println("=file.getpath()="+file.getPath());
	        RandomAccessFile raf = new RandomAccessFile(file.getPath(), "r");
	        raf.seek(Long.parseLong(start_offset));
	        while ((bytes = raf.read(bufferOut)) != -1) {
	            out.write(bufferOut, 0, bytes);
	            break;
	        }
	
	        System.out.println("=q="+q);
	        System.out.println("=bytes="+out.toString().length());
	        out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
	        fs.close();
	        StringBuilder sb2 = new StringBuilder();
	        if(param!=null&&param.keySet().size()>0){
	            Iterator<String> it = param.keySet().iterator();
	            for(; it.hasNext();) {
	                String key=it.next();
	                if("fileFormParam".equals(key)){
	                    continue;
	                }
	                sb2.append("--" + boundary + "\r\n");
	                sb2.append("Content-Disposition: form-data;name=\"");
	                sb2.append(key);
	                sb2.append("\";\r\n\r\n");
	                sb2.append(param.get(key));
	                sb2.append("\r\n");
	            }
	        }
	        sb2.append("--"+boundary + "--\r\n\r\n");
	        out.write(sb2.toString().getBytes());
	        System.out.print(sb2.toString());
	        out.flush();
	        out.close();
	
	        // 定义BufferedReader输入流来读取URL的响应
	        InputStream in = conn.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null){
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (conn != null) {
	            // 关闭连接
	            conn.disconnect();
	        }
	        return bufferRes.toString();
	    }
	
	    /**
	     * https 域名校验
	     *
	     * @param
	     * @param
	     * @return
	     */
	    public class TrustAnyHostnameVerifier implements HostnameVerifier {
	        public boolean verify(String hostname, SSLSession session) {
	            return true;// 直接返回true
	        }
	    }
	

	
	    /**
	     * 按流形式post
	     *
	     * @param url
	     * @param json
	     * @return
	     */
	    public static String httpPost(String url, String json) throws Exception {
	        String returnValue = null;
	        URL uri = null;
	        try {
	            uri = new URL(url);
	            HttpURLConnection conn = null;
	            if (isHttps(url)) {
	                conn = initHttps(url, "POST", null);
	            } else {
	                conn = (HttpURLConnection) uri.openConnection();
	            }
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setUseCaches(false);
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-type", "application/json");
	            //长度是实体的二进制长度
	            conn.setRequestProperty("Content-Length", String.valueOf(json.toString().length()));
	            // 连接超时
    			conn.setConnectTimeout(60000);
    	        // 读取超时 --服务器响应比较慢，增大时间
    			conn.setReadTimeout(60000);
	            conn.connect();
	            // json参数
	            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	            out.write(json.getBytes("utf-8"));
	            out.flush();
	            out.close();
	
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    conn.getInputStream(), "utf-8"));
	            String lines;
	            StringBuffer sb = new StringBuffer();
	            while ((lines = reader.readLine()) != null) {
	                lines = new String(lines.getBytes(), "utf-8");
	                sb.append(lines);
	            }
	            reader.close();
	            returnValue = sb.toString();
	            // 关闭连接
	            conn.disconnect();
	            return returnValue;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
        
        /**
    	 * 编码器请求
    	 * @param url
    	 * @param json
    	 * @param method
    	 * @return
    	 */
    	public static String httpRequest(String url, String json, String method,  Map<String, String> headers) throws Exception {
    		String returnValue = null;
    		URL uri = null;
    		try {
    			uri = new URL(url);
                HttpURLConnection conn = null;
                if(isHttps(url)){
                	conn = initHttps(url, "POST", null);
                }else{
                	conn = (HttpURLConnection) uri.openConnection();
                }
    			conn.setDoOutput(true);
    			conn.setDoInput(true);
    			conn.setUseCaches(false);
    			conn.setRequestMethod(method);
    			conn.setRequestProperty("Content-type","application/json");
    			//长度是实体的二进制长度
    			conn.setRequestProperty("Content-Length", String.valueOf(json.toString().length()));
    			// 连接超时
    			conn.setConnectTimeout(60000);
    	        // 读取超时 --服务器响应比较慢，增大时间
    			conn.setReadTimeout(60000);
    			if (null != headers && !headers.isEmpty()) {
    	            for (Entry<String, String> entry : headers.entrySet()) {
    	            	conn.setRequestProperty(entry.getKey(), entry.getValue());
    	            }
    	        }
    			conn.connect();
    			// json参数
    			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
    			out.write(json.getBytes());
    			out.flush();
    			out.close();

    			BufferedReader reader = new BufferedReader(new InputStreamReader(
    					conn.getInputStream(),"utf-8"));
    			String lines;
    			StringBuffer sb = new StringBuffer();
    			while ((lines = reader.readLine()) != null) {
    				lines = new String(lines.getBytes(), "utf-8");
    				sb.append(lines);
    			}
    			reader.close();
    			returnValue = sb.toString();
    			// 关闭连接
    			conn.disconnect();
    			return returnValue;
    		} catch (Exception e) {
    			throw e;
    		}
    	}
    	
    	/**
         * 按流形式post加header
         * @param url
         * @param json
         * @return
         */
        public static String httpPut(String url, String json, Map<String, String> headers) throws Exception{
            String returnValue = null;
            URL uri = null;
            try {
                uri = new URL(url);
                HttpURLConnection conn = null;
                if(isHttps(url)){
                	conn = initHttps(url, _PUT, headers);
                }else{
                	conn = (HttpURLConnection) uri.openConnection();
                }
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setUseCaches(false);
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-type","application/json");
                conn.setConnectTimeout(25000);
    	        // 读取超时 --服务器响应比较慢，增大时间
                conn.setReadTimeout(25000);
                //长度是实体的二进制长度
                conn.setRequestProperty("Content-Length", String.valueOf(json.toString().length()));
                if (null != headers && !headers.isEmpty()) {
    	            for (Entry<String, String> entry : headers.entrySet()) {
    	            	conn.setRequestProperty(entry.getKey(), entry.getValue());
    	            }
    	        }
                conn.connect();
                // json参数
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.write(json.getBytes());
                out.flush();
                out.close();
 
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(),"utf-8"));
                String lines;
                StringBuffer sb = new StringBuffer();
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sb.append(lines);
                }
                reader.close();
                returnValue = sb.toString();
                // 关闭连接
                conn.disconnect();
                return returnValue;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }


	    /**
	     * 按流形式post加header
	     *
	     * @param url
	     * @param json
	     * @return
	     */
	    public static String httpPost(String url, String json, Map<String, String> headers) throws Exception {
	        String returnValue = null;
	        URL uri = null;
	        try {
	            uri = new URL(url);
	            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setUseCaches(false);
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-type", "application/json");
	            //长度是实体的二进制长度
	            conn.setRequestProperty("Content-Length", String.valueOf(json.toString().length()));
	            // 连接超时
    			conn.setConnectTimeout(60000);
    	        // 读取超时 --服务器响应比较慢，增大时间
    			conn.setReadTimeout(60000);
	            if (null != headers && !headers.isEmpty()) {
	                for (Entry<String, String> entry : headers.entrySet()) {
	                    conn.setRequestProperty(entry.getKey(), entry.getValue());
	                }
	            }
	            conn.connect();
	            // json参数
	            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	            out.write(json.getBytes());
	            out.flush();
	            out.close();
	
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    conn.getInputStream(), "utf-8"));
	            String lines;
	            StringBuffer sb = new StringBuffer();
	            while ((lines = reader.readLine()) != null) {
	                lines = new String(lines.getBytes(), "utf-8");
	                sb.append(lines);
	            }
	            reader.close();
	            returnValue = sb.toString();
	            // 关闭连接
	            conn.disconnect();
	            return returnValue;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
	
	    /**
	     * 下载资源
	     *
	     * @param
	     * @return
	     */
	    public static void downloadHttps(String urlStr, String preSystem, String localSystemPath) {
	
	        try {
	            HttpsURLConnection conn = initHttps(urlStr, "GET", null);
	            InputStream inStream = conn.getInputStream();
	
	            String filePath = localSystemPath + urlStr.substring(urlStr.lastIndexOf("/") + 1);//urlStr.replace(preSystem, "");
	            System.out.println("urlStr========" + urlStr);
	            System.out.println("filePath========" + filePath);
	            FileOutputStream fos = null;
	            File file = new File(filePath);
	            if (!file.getParentFile().exists())
	                file.getParentFile().mkdirs();
	            if (!file.exists())
	                file.createNewFile();
	            fos = new FileOutputStream(filePath);
	            int ch = 0;
	            try {
	                while ((ch = inStream.read()) != -1) {
	                    fos.write(ch);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                fos.close();
	                inStream.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	    /**
	     * 初始化http请求参数
	     *
	     * @param url
	     * @param method
	     * @param headers
	     * @return
	     * @throws IOException
	     */
	    public static HttpURLConnection initSearchHttp(String url, String method, Map<String, String> headers) throws IOException {
	        URL _url = new URL(url);
	        HttpURLConnection http = (HttpURLConnection) _url.openConnection();
	        // 连接超时
	        http.setConnectTimeout(25000);
	        // 读取超时 --服务器响应比较慢，增大时间
	        http.setReadTimeout(25000);
	        http.setRequestMethod(method);
	        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
	        if (null != headers && !headers.isEmpty()) {
	            for (Entry<String, String> entry : headers.entrySet()) {
	                http.setRequestProperty(entry.getKey(), entry.getValue());
	            }
	        }
	        http.setDoOutput(true);
	        http.setDoInput(true);
	        http.setRequestProperty("Content-type", "application/json");
	        http.setRequestProperty("charset", "UTF-8");
	        http.connect();
	        return http;
	    }
	
	    private static final String BOUNDARY = "----------HV2ymHFg03ehbqgZCaKO6jyH";
	
	    /**
	     * 以form-data形式，由java向PHP发送数据;
	     *
	     * @param serverUrl
	     * @param map
	     * @return
	     * @throws Exception
	     * @author Luthor Chou
	     * 2018年10月30日上午11:02:18
	     */
	    public static String sendMessageToHudong(String serverUrl, Map<String, String> map) throws Exception {
	        // 向服务器发送post请求
	        URL url = new URL(serverUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	
	        // 发送POST请求必须设置如下两行
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setUseCaches(false);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Connection", "Keep-Alive");
	        connection.setRequestProperty("Charset", "UTF-8");
	        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
	        // 头
	        String boundary = BOUNDARY;
	        // 传输内容
	        StringBuffer contentBody = new StringBuffer("--" + BOUNDARY);
	        // 尾
	        String endBoundary = "\r\n--" + boundary + "--\r\n";
	        OutputStream out = connection.getOutputStream();
	
	        // 处理文字形式的POST请求
	        Iterator<Entry<String, String>> it = map.entrySet().iterator();
	        while (it.hasNext()) {
	            Entry<String, String> entry = it.next();
	            contentBody.append("\r\n")
	                    .append("Content-Disposition: form-data; name=\"")
	                    .append(entry.getKey() + "\"")
	                    .append("\r\n")
	                    .append("\r\n")
	                    .append(entry.getValue())
	                    .append("\r\n")
	                    .append("--")
	                    .append(boundary);
	        }
	        String boundaryMessage1 = contentBody.toString();
	        out.write(boundaryMessage1.getBytes("utf-8"));
	        out.write(endBoundary.getBytes("utf-8"));
	        out.flush();
	        out.close();
	
	        // 从服务器获得回答的内容
	        String strLine = "";
	        String strResponse = "";
	        InputStream in = connection.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        while ((strLine = reader.readLine()) != null) {
	            strResponse += strLine + "\n";
	        }
	        strResponse = unicodeToString(strResponse);
	        return strResponse;
	    }
	
	    public static String unicodeToString(String str) {
	        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
	        Matcher matcher = pattern.matcher(str);
	        char ch;
	        while (matcher.find()) {
	            ch = (char) Integer.parseInt(matcher.group(2), 16);
	            str = str.replace(matcher.group(1), ch + "");
	        }
	        return str;
	    }
	
	    /**
	     * 上传文件（抖音)
	     *
	     * @param url
	     * @param file
	     * @param param
	     * @param header
	     * @param boundary
	     * @return
	     * @throws IOException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws KeyManagementException
	     */
	    public static String uploadToDouyin(String url, File file, Map<String, String> param, Map<String, String> header, String boundary) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
	        StringBuffer bufferRes = null;
	        HttpURLConnection conn = null;
	        if (isHttps(url)) {
	            conn = initHttps(url, _POST, header);
	        } else {
	            conn = initHttp(url, _POST, header);
	        }
	        conn.setUseCaches(false);
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	        conn.connect();
	        OutputStream out = new DataOutputStream(conn.getOutputStream());
	        StringBuilder sb1 = new StringBuilder();
	        sb1.append("--" + boundary + "\r\n");
	        //fileFormParam 为该form的名称
	        sb1.append("Content-Disposition: form-data;name=\"" + (param.get("fileFormParam") == null ? "media" : param.get("fileFormParam")) + "\";filename=\"" + file.getName() + "\"\r\n");
	        sb1.append("Content-Type:application/octet-stream\r\n\r\n");
	        out.write(sb1.toString().getBytes());
	        System.out.print(sb1.toString());
	        DataInputStream fs = new DataInputStream(new FileInputStream(file));
	        int bytes = 0;
	        byte[] bufferOut = new byte[1024];
	        while ((bytes = fs.read(bufferOut)) != -1) {
	            out.write(bufferOut, 0, bytes);
	        }
	        out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
	        fs.close();
	        StringBuilder sb2 = new StringBuilder();
	        if (param != null && param.keySet().size() > 0) {
	            Iterator<String> it = param.keySet().iterator();
	            for (; it.hasNext(); ) {
	                String key = it.next();
	                if ("fileFormParam".equals(key)) {
	                    continue;
	                }
	                sb2.append("--" + boundary + "\r\n");
	                sb2.append("Content-Disposition: form-data;name=\"");
	                sb2.append(key);
	                sb2.append("\";\r\n\r\n");
	                sb2.append(param.get(key));
	                sb2.append("\r\n");
	            }
	        }
	        sb2.append("--" + boundary + "--\r\n\r\n");
	        out.write(sb2.toString().getBytes());
	        System.out.print(sb2.toString());
	        out.flush();
	        out.close();
	
	        // 定义BufferedReader输入流来读取URL的响应
	        InputStream in = conn.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null) {
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (conn != null) {
	            // 关闭连接
	            conn.disconnect();
	        }
	        return bufferRes.toString();
	    }
	    /**
	     * 上传分片视频（抖音）
	     * @param url
	     * @param file
	     * @param param
	     * @param header
	     * @param boundary
	     * @param start_index
	     * @param bufferOut
	     * @return
	     * @throws IOException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws KeyManagementException
	     */
	    public static String partUploadDouyin(String url, File file, Map<String, Object> param, Map<String, String> header,
				String boundary,String start_index,byte[] bufferOut)
				throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
			// String boundary = "-----------------------------"+System.currentTimeMillis();
			// // 定义数据分隔线
			StringBuffer bufferRes = null;
			HttpURLConnection conn = null;
			if (isHttps(url)) {
				conn = HttpKit.initHttps(url, _POST, header);
			} else {
				conn = HttpKit.initHttp(url, _POST, header);
			}
			conn.setUseCaches(false);
			conn.setReadTimeout(1000*300);
			conn.setConnectTimeout(1000*300);
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			conn.connect();
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			StringBuilder sb1 = new StringBuilder();
			sb1.append("--" + boundary + "\r\n");
			// fileFormParam 为该form的名称
			sb1.append("Content-Disposition: form-data;name=\""
					+ (param.get("fileFormParam") == null ? "media" : param.get("fileFormParam")) + "\";filename=\""
					+ file.getName() + "\"\r\n");
			sb1.append("Content-Type:application/octet-stream\r\n\r\n");
			out.write(sb1.toString().getBytes());
			System.out.print(sb1.toString());
			DataInputStream fs = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			//byte[] bufferOut = new byte[1024*1024*10];
			int q=0;			
	        System.out.println("=file.getpath()="+file.getPath());
	        RandomAccessFile raf = new RandomAccessFile(file.getPath(), "r");
	        long fileIndex = Long.parseLong(start_index);
	        long start_offset = 1024*1024*10*(fileIndex-1);//设置每片文件上传开始点
	        raf.seek(start_offset);
	        while ((bytes = raf.read(bufferOut)) != -1) { 
	            out.write(bufferOut, 0, bytes);
	            break;
	        }
			out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
			fs.close();
			StringBuilder sb2 = new StringBuilder();
			if (param != null && param.keySet().size() > 0) {
				Iterator<String> it = param.keySet().iterator();
				for (; it.hasNext();) {
					String key = it.next();
					if ("fileFormParam".equals(key)) {
						continue;
					}
					sb2.append("--" + boundary + "\r\n");
					sb2.append("Content-Disposition: form-data;name=\"");
					sb2.append(key);
					sb2.append("\";\r\n\r\n");
					sb2.append(param.get(key));
					sb2.append("\r\n");
				}
			}
			sb2.append("--" + boundary + "--\r\n\r\n");
			out.write(sb2.toString().getBytes());
			System.out.print(sb2.toString());
			out.flush();
			out.close();

			// 定义BufferedReader输入流来读取URL的响应
			InputStream in = conn.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String valueString = null;
			bufferRes = new StringBuffer();
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			in.close();
			if (conn != null) {
				// 关闭连接
				conn.disconnect();
			}
			return bufferRes.toString();
		}
	
	    /**
	     * 上传文件（头条)
	     *
	     * @param url
	     * @param file
	     * @param param
	     * @param header
	     * @param boundary
	     * @return
	     * @throws IOException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws KeyManagementException
	     */
	    public static String uploadToToutiao(String url, File file, Map<String, String> param, Map<String, String> header, String boundary) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
	        StringBuffer bufferRes = null;
	        HttpURLConnection conn = null;
	        if (isHttps(url)) {
	            conn = initHttps(url, _POST, header);
	        } else {
	            conn = initHttp(url, _POST, header);
	        }
			conn.setConnectTimeout(300 * 1000);//将响应超时时间设置为300秒；
			conn.setReadTimeout(300 * 1000);//读取时间也设定为一样；
	        conn.setUseCaches(false);
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	        conn.connect();
	        OutputStream out = new DataOutputStream(conn.getOutputStream());
	        StringBuilder sb1 = new StringBuilder();
	        sb1.append("--" + boundary + "\r\n");
	        //fileFormParam 为该form的名称
	        sb1.append("Content-Disposition: form-data;name=\"" + (param.get("fileFormParam") == null ? "media" : param.get("fileFormParam")) + "\";filename=\"" + file.getName() + "\"\r\n");
	        sb1.append("Content-Type:application/octet-stream\r\n\r\n");
	        out.write(sb1.toString().getBytes());
	        System.out.print(sb1.toString());
	        DataInputStream fs = new DataInputStream(new FileInputStream(file));
	        int bytes = 0;
	        byte[] bufferOut = new byte[1024];
	        while ((bytes = fs.read(bufferOut)) != -1) {
	            out.write(bufferOut, 0, bytes);
	        }
	        out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
	        fs.close();
	        StringBuilder sb2 = new StringBuilder();
	        if (param != null && param.keySet().size() > 0) {
	            Iterator<String> it = param.keySet().iterator();
	            for (; it.hasNext(); ) {
	                String key = it.next();
	                if ("fileFormParam".equals(key)) {
	                    continue;
	                }
	                sb2.append("--" + boundary + "\r\n");
	                sb2.append("Content-Disposition: form-data;name=\"");
	                sb2.append(key);
	                sb2.append("\";\r\n\r\n");
	                sb2.append(param.get(key));
	                sb2.append("\r\n");
	            }
	        }
	        sb2.append("--" + boundary + "--\r\n\r\n");
	        out.write(sb2.toString().getBytes());
	        System.out.print(sb2.toString());
	        out.flush();
	        out.close();
	
	        // 定义BufferedReader输入流来读取URL的响应
	        InputStream in = conn.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null) {
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (conn != null) {
	            // 关闭连接
	            conn.disconnect();
	        }
	        return bufferRes.toString();
	    }
	
	
	    /**
	     * 上传文件（企鹅号)
	     * @param url
	     * @param file
	     * @param param
	     * @param header
	     * @param boundary
	     * @return
	     * @throws IOException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws KeyManagementException
	     */
	    public static String uploadToQiEhao(String url,File file,Map<String,String> param,Map<String,String> header,String boundary,String start_offset) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
	        StringBuffer bufferRes = null;
	        HttpURLConnection conn = null;
	        if (isHttps(url)) {
	            conn = initHttps(url, _POST, header);
	        } else {
	            conn = initHttp(url, _POST, header);
	        }
	        // 连接超时
	        conn.setConnectTimeout(60000);
	        // 读取超时 --服务器响应比较慢，增大时间
	        conn.setReadTimeout(60000);
	        System.out.println("====60000");
	        conn.setUseCaches(false);
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	        conn.connect();
	        OutputStream out = new DataOutputStream(conn.getOutputStream());
	        StringBuilder sb1 = new StringBuilder();
	        sb1.append("--" + boundary + "\r\n") ;
	        //fileFormParam 为该form的名称
	        sb1.append("Content-Disposition: form-data;name=\""+(param.get("fileFormParam")==null?"media":param.get("fileFormParam"))+"\";filename=\""+ file.getName() + "\"\r\n");
	        sb1.append("Content-Type:application/octet-stream\r\n\r\n");
	        out.write(sb1.toString().getBytes());
	        System.out.print(sb1.toString());
	        DataInputStream fs = new DataInputStream(new FileInputStream(file));
	        int bytes = 0;
	        byte[] bufferOut = new byte[50*1024*1024];
	        int q=0;
	        System.out.println("=file.getpath()="+file.getPath());
	        RandomAccessFile raf = new RandomAccessFile(file.getPath(), "r");
	        if(StringUtils.isNotBlank(start_offset)){//大于100M需要分片 设置每片不超过100M   任何值都可以
	            raf.seek(Long.parseLong(start_offset));
	            while ((bytes = raf.read(bufferOut)) != -1) {
	                out.write(bufferOut, 0, bytes);  break;
	            }
	        }else{//小于100M走这里
	            while ((bytes = fs.read(bufferOut)) != -1) {
	                out.write(bufferOut, 0, bytes);
	                q++;
	            }
	        }
	
	        System.out.println("=q="+q);
	        System.out.println("=bytes="+out.toString().length());
	        out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
	        fs.close();
	        StringBuilder sb2 = new StringBuilder();
	        if(param!=null&&param.keySet().size()>0){
	            Iterator<String> it = param.keySet().iterator();
	            for(; it.hasNext();) {
	                String key=it.next();
	                if("fileFormParam".equals(key)){
	                    continue;
	                }
	                sb2.append("--" + boundary + "\r\n");
	                sb2.append("Content-Disposition: form-data;name=\"");
	                sb2.append(key);
	                sb2.append("\";\r\n\r\n");
	                sb2.append(param.get(key));
	                sb2.append("\r\n");
	            }
	        }
	        sb2.append("--"+boundary + "--\r\n\r\n");
	        out.write(sb2.toString().getBytes());
	        System.out.print(sb2.toString());
	        out.flush();
	        out.close();
	
	        // 定义BufferedReader输入流来读取URL的响应
	        InputStream in = conn.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null){
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (conn != null) {
	            // 关闭连接
	            conn.disconnect();
	        }
	        return bufferRes.toString();
	    }
	    
	    /**
	     * 上传视频到快手
	     * @param url
	     * @param file
	     * @param param
	     * @param header
	     * @param boundary
	     * @return
	     * @throws IOException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws KeyManagementException
	     */
	    public static String uploadToKuaiShou(String url,File file,RandomAccessFile raf) throws Exception {
		    	StringBuffer bufferRes = null;
		        HttpURLConnection conn = null;
		        if (isHttps(url)) {
		            conn = initHttps(url, _POST, null);
		        } else {
		            conn = initHttp(url, _POST, null);
		        }
		 	    try {
		 	        // 设置通用的请求属性
		 	        conn.setRequestProperty("accept", "*/*");
		 	        conn.setRequestProperty("connection", "Keep-Alive");
		 	        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		 	        conn.setRequestProperty("Content-Type", "application/octet-stream");
		 	        // 发送POST请求必须设置如下两行
		 	        conn.setDoOutput(true);
		 	        conn.setDoInput(true);        
	
		 	        //*****************************************************\
		 	        OutputStream out = new DataOutputStream(conn.getOutputStream());
		 	        DataInputStream fs = new DataInputStream(new FileInputStream(file));
		 	        int bytes = 0;
		 	        byte[] bufferOut = new byte[3*1024*1024];//分片时候每片不能大于4M
		 	        int q=0;
		 	        System.out.println("=file.getpath()="+file.getPath());
		             if(null!=raf){//大于200M需要分片 设置每片不能大于4M
		 	            while ((bytes = raf.read(bufferOut)) != -1) {
		 	            	raf.seek(raf.getFilePointer());
		 	                out.write(bufferOut, 0, bytes);  
		 	                break;
		 	            }
		 	        }else{//小于200M走这里
		 	            while ((bytes = fs.read(bufferOut)) != -1) {
		 	                out.write(bufferOut, 0, bytes);
		 	                q++;
		 	            }
		 	        }
	//	 	        out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
		 	        fs.close();
		 	        
		 	        
	//	 	        StringBuilder sb2 = new StringBuilder();
	//	 	        if(param!=null&&param.keySet().size()>0){
	//	 	            Iterator<String> it = param.keySet().iterator();
	//	 	            for(; it.hasNext();) {
	//	 	                String key=it.next();
	//	 	                if("fileFormParam".equals(key)){
	//	 	                    continue;
	//	 	                }
	//	 	                sb2.append("--" + boundary + "\r\n");
	//	 	                sb2.append("Content-Disposition: form-data;name=\"");
	//	 	                sb2.append(key);
	//	 	                sb2.append("\";\r\n\r\n");
	//	 	                sb2.append(param.get(key));
	//	 	                sb2.append("\r\n");
	//	 	            }
	//	 	        }
	//	 	        sb2.append("--"+boundary + "--\r\n\r\n");
	//	 	        out.write(sb2.toString().getBytes());
	//	 	        System.out.print(sb2.toString());
		 	        out.flush();
		 	        out.close();
		 	
		 	        // 定义BufferedReader输入流来读取URL的响应
		 	        InputStream inn = conn.getInputStream();
		 	        BufferedReader read = new BufferedReader(new InputStreamReader(inn, "UTF-8"));
		 	        String valueString = null;
		 	        bufferRes = new StringBuffer();
		 	        while ((valueString = read.readLine()) != null){
		 	            bufferRes.append(valueString);
		 	        }
		 	        inn.close();
		 	        if (conn != null) {
		 	            // 关闭连接
		 	            conn.disconnect();
		 	        }
		 	        System.out.println("=====66======"+bufferRes.toString());
		 	    } catch (Exception e) {
		 	        System.out.println("异常," + e.getMessage());
		 	        e.printStackTrace();
		 	    }
		 		
		 	    return bufferRes.toString();
		    }
	    /**
	     * post请求   发送标题+图片
	     * @param urlStr
	     * @param textMap
	     * @param fileMap
	     * @param contentType
	     * @return
	     */
	    public static String postKuaiShou(String urlStr, Map<String, String> textMap,Map<String, String> fileMap,String contentType) {
	        String res = "";
	        HttpURLConnection conn = null;
	        // boundary就是request头和上传文件内容的分隔符
	        String BOUNDARY = "---------------------------123821742118716"; 
	        try {
	            URL url = new URL(urlStr);
	            conn = (HttpURLConnection) url.openConnection();
	            conn.setConnectTimeout(5000);
	            conn.setReadTimeout(30000);
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setUseCaches(false);
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Connection", "Keep-Alive");
	            // conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
	            conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
	            OutputStream out = new DataOutputStream(conn.getOutputStream());
	            // text
	            if (textMap != null) {
	                StringBuffer strBuf = new StringBuffer();
	                Iterator iter = textMap.entrySet().iterator();
	                while (iter.hasNext()) {
	                    Entry entry = (Entry) iter.next();
	                    String inputName = (String) entry.getKey();
	                    String inputValue = (String) entry.getValue();
	                    if (inputValue == null) {
	                        continue;
	                    }
	                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
	                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
	                    strBuf.append(inputValue);
	                }
	                out.write(strBuf.toString().getBytes());
	            }
	            // file
	            if (fileMap != null) {
	                Iterator iter = fileMap.entrySet().iterator();
	                while (iter.hasNext()) {
	                    Entry entry = (Entry) iter.next();
	                    String inputName = (String) entry.getKey();
	                    String inputValue = (String) entry.getValue();
	                    if (inputValue == null) {
	                        continue;
	                    }
	                    File file = new File(inputValue);
	                    String filename = file.getName();
	                    
	                    //没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
	                    contentType = new MimetypesFileTypeMap().getContentType(file);
	                    //contentType非空采用filename匹配默认的图片类型
	                    if(!"".equals(contentType)){
	                        if (filename.endsWith(".png")) {
	                            contentType = "image/png"; 
	                        }else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".jpe")) {
	                            contentType = "image/jpeg";
	                        }else if (filename.endsWith(".gif")) {
	                            contentType = "image/gif";
	                        }else if (filename.endsWith(".ico")) {
	                            contentType = "image/image/x-icon";
	                        }
	                    }
	                    if (contentType == null || "".equals(contentType)) {
	                        contentType = "application/octet-stream";
	                    }
	                    StringBuffer strBuf = new StringBuffer();
	                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
	                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
	                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
	                    out.write(strBuf.toString().getBytes());
	                    DataInputStream in = new DataInputStream(new FileInputStream(file));
	                    int bytes = 0;
	                    byte[] bufferOut = new byte[1024];
	                    while ((bytes = in.read(bufferOut)) != -1) {
	                        out.write(bufferOut, 0, bytes);
	                    }
	                    in.close();
	                }
	            }
	            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
	            out.write(endData);
	            out.flush();
	            out.close();
	            // 读取返回数据
	            StringBuffer strBuf = new StringBuffer();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                strBuf.append(line).append("\n");
	            }
	            res = strBuf.toString();
	            reader.close();
	            reader = null;
	        } catch (Exception e) {
	            System.out.println("发送POST请求出错。" + urlStr);
	            e.printStackTrace();
	        } finally {
	            if (conn != null) {
	                conn.disconnect();
	                conn = null;
	            }
	        }
	        return res;
	    }
	    
	    /**
	     * 调用third包下百家号分片上传POST请求，增大响应超时时间，设置请求头
	     *
	     * @param url
	     * @param params
	     * @param headers
	     * @return
	     * @throws KeyManagementException
	     * @throws NoSuchAlgorithmException
	     * @throws NoSuchProviderException
	     * @throws IOException
	     */
	    public static String BJHUploadPost(String url, String params, Map<String, String> headers) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
	        HttpURLConnection http = null;
	        if (isHttps(url)) {
	            http = initHttps(url, _POST, headers);
	        } else {
	            http = initHttp(url, _POST, headers);
	        }
	        http.setConnectTimeout(500 * 1000);//将响应超时时间设置为500秒；
	        http.setReadTimeout(500 * 1000);//读取时间也设定为一样；
	        http.connect();
	        OutputStream out = http.getOutputStream();
	        out.write(params.getBytes(DEFAULT_CHARSET));
	        out.flush();
	        out.close();

	        InputStream in = http.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        StringBuffer bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null) {
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (http != null) {
	            http.disconnect();// 关闭连接
	        }
	        return bufferRes.toString();
	    }
	}


/**
 * 证书管理
 */
class MyX509TrustManager implements X509TrustManager {

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }
}
