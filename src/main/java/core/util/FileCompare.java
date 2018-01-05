package core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import oracle.net.aso.MD5;
import core.util.Util;

/**
 * 
 * @author cwj
 *
 */
public class FileCompare {
	/**
	 * 用MD5比较两个文件是否相同。
	 * 
	 * @param src
	 *            文件对象。
	 * @param des
	 *            文件对象。
	 * @return
	 */
	public static boolean MD5Compare(File src, File des) {
		String md5_src = getFileMD5(src);
		String md5_des = getFileMD5(des);
		if (!Util.isBlank(md5_des) && !Util.isBlank(md5_src) && md5_src.equals(md5_des)) {
			return true;
		}
		return false;
	}

	/**
	 * 用MD5比较两个文件是否相同。
	 * 
	 * @param src
	 *            文件对象。
	 * @param des
	 *            文件对象。
	 * @return
	 */
	public static boolean MD5Compare(InputStream src, InputStream des) {
		String md5_src = getFileMD5(src);
		String md5_des = getFileMD5(des);
		if (!Util.isBlank(md5_des) && !Util.isBlank(md5_src) && md5_src.equals(md5_des)) {
			return true;
		}
		return false;
	}

	/**
	 * 用MD5比较两个文件是否相同。
	 * 
	 * @param src
	 *            文件对象。
	 * @param des
	 *            文件对象。
	 * @return
	 */
	public static boolean MD5Compare(InputStream src, File des) {
		String md5_src = getFileMD5(src);
		String md5_des = getFileMD5(des);
		if (!Util.isBlank(md5_des) && !Util.isBlank(md5_src) && md5_src.equals(md5_des)) {
			return true;
		}
		return false;
	}

	/**
	 * 计算文件的 MD5 值，用于比较两个文件是否相同。
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return getFileMD5(in);
	}

	/**
	 * 计算文件的 MD5 值，用于比较两个文件是否相同。
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileMD5(InputStream in) {
		if (in == null) {
			return null;
		}
		MessageDigest digest = null;
		byte buffer[] = new byte[8192];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			while ((len = in.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			//BigInteger bigInt = new BigInteger(1, digest.digest());
//			return bigInt.toString(16);
			return byteArrayToHexString(digest.digest());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 用sha1比较两个文件是否相同。
	 * 
	 * @param src
	 *            文件对象。
	 * @param des
	 *            文件对象。
	 * @return
	 */
	public static boolean ShaCompare(File src, File des) {
		String sha_src = getFileSha1(src);
		String sha_des = getFileSha1(des);
		if (!Util.isBlank(sha_src) && !Util.isBlank(sha_des) && sha_src.equals(sha_des)) {
			return true;
		}
		return false;
	}

	/**
	 * 用sha1比较两个文件是否相同。
	 * 
	 * @param src
	 *            文件输入流
	 * @param des
	 *            文件输入流
	 * @return
	 */
	public static boolean ShaCompare(InputStream src, InputStream des) {
		String sha_src = getFileSha1(src);
		String sha_des = getFileSha1(des);
		if (!Util.isBlank(sha_src) && !Util.isBlank(sha_des) && sha_src.equals(sha_des)) {
			return true;
		}
		return false;
	}

	/**
	 * 用sha1比较两个文件是否相同。
	 * 
	 * @param src
	 *            文件输入流
	 * @param des
	 *            文件对象
	 * @return
	 */
	public static boolean ShaCompare(InputStream src, File des) {
		String sha_src = getFileSha1(src);
		String sha_des = getFileSha1(des);
		if (!Util.isBlank(sha_src) && !Util.isBlank(sha_des) && sha_src.equals(sha_des)) {
			return true;
		}
		return false;
	}

	/**
	 * 计算文件的 SHA-1 值，用于比较两个文件是否相同。
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileSha1(File file) {
		if (!file.isFile()) {
			return null;
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return getFileSha1(in);
	}

	/**
	 * 计算文件的 SHA-1 值，用于比较两个文件是否相同。
	 * 
	 * @param in
	 * @return
	 */
	public static String getFileSha1(InputStream in) {
		if (in == null) {
			return null;
		}
		MessageDigest digest = null;
		byte buffer[] = new byte[8192];
		int len;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			while ((len = in.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			BigInteger bigInt = new BigInteger(1, digest.digest());
			return bigInt.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
