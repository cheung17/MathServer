package core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 文件操作类
 * 
 * @author chenweju
 * 
 * @since Apr 24, 2015
 */
public class FileUtil {

    /**
     * 读取ftl模板产生html文件
     * 
     * @param ftlPath
     * @param ftl
     * @param htmlPath
     * @param htmlName
     * @param map
     * @throws IOException
     * @throws TemplateException
     */
    public static ResultInfo generateHtml(String templatePath,
	    String templateName, String htmlPath, String htmlName,
	    Map<String, Object> map) throws IOException, TemplateException {
	ResultInfo ri = new ResultInfo(1, "生成成功");
	Configuration freemarkerCfg = new Configuration();
	File templateFile = new File(templatePath);
	if (!templateFile.exists()) {
	    return new ResultInfo(-1, "模板文件夹不存在");
	}
	freemarkerCfg.setDirectoryForTemplateLoading(templateFile);
	freemarkerCfg.setEncoding(Locale.getDefault(), "GBK");
	Template template = freemarkerCfg.getTemplate(templateName);
	template.setEncoding("GBK");

	File f = new File(htmlPath);
	if (!f.exists()) {
	    if (!f.mkdirs())
		System.out.print("创建文件夹失败");
	}
	File htmlFile = new File(htmlPath + htmlName);
	if (htmlFile.exists()) {
	    htmlFile.delete();
	}
	htmlFile.createNewFile();
	Writer out = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(htmlFile), "GBK"));
	template.process(map, out);
	out.flush();
	out.close();
	return ri;
    }

    /**
     * 解析模板，返回解析后的字符串
     * 
     * @param templatePath
     * @param templateName
     * @param map
     * @return
     * @throws Exception
     */
    public static String processTemplate(String templatePath,
	    String templateName, Map<String, Object> map) throws Exception {
	Configuration freemarkerCfg = new Configuration();
	File templateFile = new File(templatePath);
	if (!templateFile.exists()) {
	    return "";
	}
	freemarkerCfg.setDirectoryForTemplateLoading(templateFile);
	freemarkerCfg.setEncoding(Locale.getDefault(), "GBK");
	freemarkerCfg.setNumberFormat("0.######");
	Template template = freemarkerCfg.getTemplate(templateName);
	template.setEncoding("GBK");
	StringWriter writer = new StringWriter();
	template.process(map, writer);
	return writer.toString();
    }

    /**
     * 读取文件，返回文件内容
     * 
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String readFileContent(String filePath) throws Exception {
	File file = new File(filePath);
	if (!file.exists()) {
	    return "";
	}

	StringBuffer localStringBuffer = new StringBuffer();
	InputStreamReader localFileReader = null;
	BufferedReader localBufferedReader = null;
	String str = "";
	try {
	    localFileReader = new InputStreamReader(new FileInputStream(file),
		    "GBK");
	    localBufferedReader = new BufferedReader(localFileReader);
	    str = localBufferedReader.readLine();
	    while (str != null) {
		localStringBuffer.append(str).append("\n");
		str = localBufferedReader.readLine();
	    }
	} catch (Exception e) {
	    LogUtil.error(FileUtil.class, e);
	} finally {
	    try {
		localBufferedReader.close();
		localFileReader.close();
	    } catch (Exception localException2) {
	    }
	}
	return localStringBuffer.toString();
    }

    /**
     * 根据源文件、目标文件路径复制文件
     * 
     * @param sourceFilePath
     * @param destFilePath
     * @param destFileName
     * @throws Exception
     */
    public static void copyfileByFilepath(String sourceFilePath,
	    String destFilePath, String destFileName) throws Exception {
	String sourceFileContent = readFileContent(sourceFilePath);
	copyfile(sourceFileContent, destFilePath, destFileName);
    }

    /**
     * 根据源文件内容复制生成新文件
     * 
     * @param sourceFileContent
     * @param destFilePath
     * @param destFileName
     * @throws Exception
     */
    public static void copyfile(String sourceFileContent, String destFilePath,
	    String destFileName) throws Exception {
	File destFile = new File(destFilePath);
	if (!destFile.exists()) {
	    destFile.mkdirs();
	}
	File file = new File(destFilePath + destFileName);
	OutputStreamWriter write = null;
	BufferedWriter writer = null;
	try {
	    write = new OutputStreamWriter(new FileOutputStream(file), "GBK");
	    writer = new BufferedWriter(write);
	    writer.write(sourceFileContent);
	} catch (Exception e) {
	    throw e;
	} finally {
	    if (null != writer) {
		writer.flush();
		writer.close();
	    }
	    if (null != write) {
		write.close();
	    }
	}
    }

    /**
     * 保存生成数据文件
     * 
     * @param stream
     * @param path
     * @param filename
     * @throws IOException
     */
    public static void saveFileFromData(String data, String path,
	    String filename) throws IOException {
	File pathFile = new File(path);
	if (!pathFile.exists()) {
	    pathFile.mkdirs();
	}
	File file = new File(path + filename);
	if (!file.exists()) {
	    file.createNewFile();
	}

	FileOutputStream fs = new FileOutputStream(file);
	byte[] buffer = data.getBytes();
	int byteread = buffer.length;
	fs.write(buffer, 0, byteread);
	fs.flush();
	fs.close();
    }

    /**
     * 
     * @title saveFileFromData
     * @description 保存数据
     * @param data
     * @param path
     * @param filename
     * @param charset
     * @throws IOException
     */
    public static void saveFileFromData(String data, String path,
	    String filename, String charset) throws IOException {
	File pathFile = new File(path);
	if (!pathFile.exists()) {
	    pathFile.mkdirs();
	}
	File file = new File(path + filename);
	if (!file.exists()) {
	    file.createNewFile();
	}

	FileOutputStream fs = new FileOutputStream(file);
	byte[] buffer = data.getBytes(charset);
	int byteread = buffer.length;
	fs.write(buffer, 0, byteread);
	fs.flush();
	fs.close();
    }

    /**
     * 保存生成文件
     * 
     * @param stream
     * @param path
     * @param filename
     * @throws IOException
     */
    public static void saveFileFromInputStream(InputStream stream, String path,
	    String filename) throws IOException {
	File pathFile = new File(path);
	if (!pathFile.exists()) {
	    pathFile.mkdirs();
	}
	File file = new File(path + filename);
	if (!file.exists()) {
	    file.createNewFile();
	}
	FileOutputStream fs = new FileOutputStream(file);
	byte[] buffer = new byte[1024 * 1024];
	int byteread = 0;
	while ((byteread = stream.read(buffer)) != -1) {
	    fs.write(buffer, 0, byteread);
	    fs.flush();
	}
	fs.close();
	stream.close();
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * 
     * @param filePath
     * @return
     */
    public static boolean deleteDir(String filePath) {
	File dir = new File(filePath);
	if (dir.exists()) {
	    return deleteDir(dir);
	}
	return true;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * 
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
	if (dir.isDirectory()) {
	    String[] children = dir.list();
	    for (int i = 0; i < children.length; i++) {
		boolean success = deleteDir(new File(dir, children[i]));
		if (!success) {
		    return false;
		}
	    }
	}
	// 目录此时为空，可以删除
	return dir.delete();
    }

    public static void getAllFileContent(String inFloderPath, String outFilePath)
	    throws Exception {
	File file = new File(inFloderPath);
	if (file.exists() && file.isDirectory()) {
	    File[] childFiles = file.listFiles();// 找出所有子目录
	    for (int i = 0; childFiles != null && i < childFiles.length; i++) {
		File f = childFiles[i];
		if (f.isDirectory()) {
		    getAllFileContent(f.getAbsolutePath(), outFilePath);
		} else {
		    FileWriter writer = new FileWriter(outFilePath, true);
		    writer.write(readFileContent(f.getAbsolutePath()));
		    writer.close();
		    System.out.println("写入文件：" + f.getAbsolutePath());
		}
	    }
	}
    }

    public static void main(String[] args) {
	try {
	    FileUtil.getAllFileContent(
		    "D:\\workspace\\platform_jar\\src\\work",
		    "D:\\doc\\test.txt");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
