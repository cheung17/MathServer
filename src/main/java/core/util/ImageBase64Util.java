/**
 * 
 */
package core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Administrator
 * @descripton
 * @date 2015年8月17日 上午9:40:10
 */
public class ImageBase64Util {

	/**
	 * @author libing
	 * @functionName:imageToBase64
	 * @description:将图片文件转化成字节数组字符串，并对其进行Base64编码处理
	 * @param:图片文件存放路径
	 * @return:Base64编码处理后的字符串
	 * @date:2015年8月17日 上午10:10:39
	 */
	public static String imageToBase64(InputStream ins) {
		

		byte[] data = null;
		if (null != ins) {
			try {
				data = new byte[ins.available()];
				ins.read(data);
				ins.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 对字节数组进行Base64编码
		@SuppressWarnings("restriction")
		BASE64Encoder encoder = new BASE64Encoder();
		// 返回Base64编码过的字节数组字符串
		@SuppressWarnings("restriction")
		String base64Str = encoder.encode(data);
		return base64Str;
	}

	public static boolean base64ToImage(String base64, String imagePath) {
		// 图像数据为空，
		if (null == base64) {
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(base64);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imagePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
