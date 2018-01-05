package core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import core.datasource.DataSourceUtil;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class PropUtil {
	public static String ROOT_PATH; // 系统目录
	public static String CLASSPATH_FLODER = "/conf/";// classpath中资源文件的文件夹

	/**
	 * 设置系统更目录
	 * 
	 * @param rOOT_PATH
	 *            the rOOT_PATH to set
	 */
	public static void setROOT_PATH(String rOOT_PATH) {
		ROOT_PATH = rOOT_PATH;
	}

	/**
	 * 读取Properties文件键值
	 * 
	 * @param fname
	 *            文件名
	 * @param key
	 *            键
	 * @return
	 */
	public static String getPropValue(String fname, String key) {

		Properties newprop = loadProp(fname);
		if (newprop != null && newprop.getProperty(key) != null) {
			return newprop.getProperty(key).trim();
		} else {
			return "";
		}

	}

	/**
	 * 从clapath中获取资源文件属性值
	 * 
	 * @param fname
	 * @param key
	 * @return
	 */
	public static String getPropValueFromClasspath(String fname, String key) {

		Properties newprop = loadPropFromClasspath(fname);
		if (newprop != null && newprop.getProperty(key) != null) {
			return newprop.getProperty(key).trim();
		} else {
			return "";
		}

	}

	/**
	 * 读取Properties文件
	 * 
	 * @param fname
	 * @return
	 */
	public static Properties loadProp(String fname) {
		try {

			Properties prop = null;
			Hashtable htmlfileHash = new Hashtable();
			Hashtable htmlfileTime = new Hashtable();
			File f = new File(getPROP_ROOT() + fname + ".properties");
			if (!f.exists()){
				return null;
			}

			long ftime = f.lastModified();
			Long hftime = (Long) htmlfileTime.get(fname);

			if (hftime == null || hftime.longValue() != ftime) {

				prop = new Properties();
				FileInputStream fis = new FileInputStream(f);
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(bis));

				prop.load(reader);
				reader.close();
				bis.close();
				fis.close();

				htmlfileHash.put(fname, prop);
				htmlfileTime.put(fname, new Long(ftime));
			}

			return (Properties) htmlfileHash.get(fname);

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 从classpath中读取Properties文件
	 * 
	 * @param fname
	 * @return
	 */
	public static Properties loadPropFromClasspath(String fname) {
		try {
			Properties res = new Properties();
			InputStream in = new FileInputStream(PropUtil.class.getResource(
					CLASSPATH_FLODER + fname + ".properties").getFile());
			res.load(in);
			in.close();
			return res;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取properties配置文件路径
	 * 
	 * @return the PROP_ROOT
	 */
	public static String getPROP_ROOT() {
		String path = ROOT_PATH + "WEB-INF" + File.separatorChar + "conf";
		File localFile = new File(path);
		if (localFile.exists()) {
			return path + File.separatorChar;
		}
		return null;
	}

	
}
