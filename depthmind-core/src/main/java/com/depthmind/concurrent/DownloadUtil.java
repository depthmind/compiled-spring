package com.depthmind.concurrent;

import org.mybatis.logging.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class DownloadUtil {

	/**
	 * 下载音频
	 * @param audioUrl
	 * @param source
	 * @return  本地路径
	 * @throws Exception
	 */
	public static String downloadAudioUrl(String audioUrl, String source) throws Exception{
		long startTime = System.currentTimeMillis();
		String imgPath;
		String filePath = "";
		try {
			URL url = new URL(audioUrl);
			InputStream inStream = null;
			if(audioUrl.startsWith("https")){
				HttpsURLConnection conn = HttpKit.initHttps(audioUrl, "GET", null);
				inStream = conn.getInputStream();
			}else{
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5 * 1000);
				inStream = conn.getInputStream();
			}
			String imageSuffix = audioUrl.substring(audioUrl.lastIndexOf(".")+1);
			String fileName = source + System.currentTimeMillis() + "." + imageSuffix;
			String prePath = getFilePath();
			filePath = "/Users/liuhan/Desktop/datacms/htdosc/html/photoAlbum/upload/" + prePath + fileName;
			FileOutputStream fos = null;
			File file = new File(filePath);
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			if(!file.exists())
				file.createNewFile();
			fos = new FileOutputStream(filePath);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);
			int ch =0 ;
			try{
				while((ch=inStream.read())!=-1){
					bufferedOutputStream.write(ch);
				}
			} catch(Exception e){
				e.printStackTrace();
			} finally{
				bufferedOutputStream.close();
				inStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return filePath;
	}
	
	
	private static String getFilePath(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sdf.format(new Date());
		String retPath = now.substring(0,4) + "/";
		retPath += (now.substring(4, 6).startsWith("0")?now.substring(5,6):now.substring(4,6)) + "/";
		retPath += (now.substring(6, 8).startsWith("0")?now.substring(7,8):now.substring(6,8)) + "/";
    	return retPath;
    }

}
