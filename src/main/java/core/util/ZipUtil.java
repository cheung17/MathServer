package core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.tools.zip.ZipOutputStream;

import core.constant.DataBaseTableConstant;

/**
 * zip压缩工具
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class ZipUtil {
	private static String ENCODE = "GBK";
	/***************************************************************************
	 * @param zipPath
	 *            压缩后文件路径
	 * @param sourcePath
	 *            所要压缩的文件路径，可以是目录
	 * @param isZipDir
	 *            是否包括目录
	 * @throws IOException
	 **************************************************************************/
	public static void getZip(String zipPath, String sourcePath,
			boolean isZipDir) throws IOException {
		OutputStream os = new FileOutputStream(zipPath);
		BufferedOutputStream bs = new BufferedOutputStream(os);
		ZipOutputStream zo = new ZipOutputStream(bs);
		zip(sourcePath, new File(sourcePath), zo, isZipDir);
		zo.closeEntry();
		zo.close();
	}

	/*************************************************************************
	 * @param path
	 *            要压缩的路径, 可以是目录, 也可以是文件.
	 * @param basePath
	 *            如果path是目录,它一般为new File(path), 作用是:使输出的zip文件以此目录为根目录
	 * @param zo
	 *            压缩输出流
	 * @param isZipDir
	 *            是否包括目录
	 * @throws IOException
	 **************************************************************************/
	private static void zip(String path, File basePath, ZipOutputStream zo,
			boolean isZipDir) throws IOException {
		zo.setEncoding(ZipUtil.ENCODE);
		File inFile = new File(path);
		File[] files = new File[0];
		if (inFile.isDirectory()) { // 是目录
			files = inFile.listFiles();
		} else if (inFile.isFile()) { // 是文件
			files = new File[1];
			files[0] = inFile;
		}
		byte[] buf = new byte[1024];
		int len;

		for (int i = 0; i < files.length; i++) {
			String pathName = "";
			if (basePath.isDirectory()) {
				pathName = files[i].getPath().substring(
						basePath.getPath().length() + 1);
			} else {
				pathName = files[i].getPath().substring(
						basePath.getParent().length() + 1);
			}

			if (files[i].isDirectory()) {

				zip(files[i].getPath(), basePath, zo, isZipDir);

			} else {
				if (!isZipDir) {
					pathName = files[i].getName();
				}
				FileInputStream fin = new FileInputStream(files[i]);
				zo.putNextEntry((org.apache.tools.zip.ZipEntry) new ZipEntry(pathName));
				while ((len = fin.read(buf)) > 0) {
					zo.write(buf, 0, len);
				}
				fin.close();
			}
		}
	}


	/***************************************************************************
	 * @param zipPath
	 *            压缩后文件路径
	 * @param filePath
	 *            要压缩的文件的路径全名
	 * @throws IOException
	 **************************************************************************/
	public static void getZip(String zipPath, String[] filePath) throws Exception {
		OutputStream os = new FileOutputStream(zipPath);
		BufferedOutputStream bs = new BufferedOutputStream(os);
		ZipOutputStream zo = new ZipOutputStream(bs);
		zip(filePath, zo);
		zo.closeEntry();
		zo.close();
		
	}
	/*************************************************************************
	 * @param path
	 *            要压缩的文件路径全名.
	 * @param zo
	 *            压缩输出流
	 * @throws IOException
	 **************************************************************************/
	private static void zip(String[] path, ZipOutputStream zo) throws IOException {
		byte[] buf = new byte[1024];
		int len;
		zo.setEncoding(ZipUtil.ENCODE);
		for (int i = 0; i < path.length; i++) {
			String pathName = "";
			File file = new File(path[i]);
			if (file.isFile()) {
				pathName = file.getName();
				zo.putNextEntry((org.apache.tools.zip.ZipEntry) new ZipEntry(pathName));
				FileInputStream fin = new FileInputStream(file);     
				while ((len = fin.read(buf)) > 0) {
					zo.write(buf, 0, len);
				}
				fin.close();
				file.delete();

			}
		}
	}
	public static void main(String[] args) throws IOException {
		ZipUtil.getZip("D:\\czj\\工作日志\\2014\\10月\\23\\20131022.zip", "D:\\czj\\工作日志\\2014\\10月\\23\\20131022", true);
//		ZipFile zFile  = new ZipFile(new File(""));
	}
	
	/**************************************************V2 ZipUtil****************************************************************/
	
	@SuppressWarnings({"resource" })
	public static void decompress(String zipPath, String targetPath) throws IOException {
		File file = new File(zipPath);
		if (!file.isFile()) {
			throw new FileNotFoundException("file not exist!");
		}
		if (targetPath == null || "".equals(targetPath)) {
			targetPath = file.getParent();
		}
		ZipFile zipFile = new ZipFile(file);
		Enumeration<? extends ZipEntry> files = (Enumeration<? extends ZipEntry>) zipFile.entries();
		ZipEntry entry = null;
		File outFile = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		while (files.hasMoreElements()) {
			entry = files.nextElement();
			outFile = new File(targetPath + File.separator + entry.getName());
			// 如果条目为目录，则跳向下一个
			if (entry.isDirectory()) {
				outFile.mkdirs();
				continue;
			}
			// 创建目录
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			// 创建新文件
			outFile.createNewFile();
			// 如果不可写，则跳向下一个条目
			if (!outFile.canWrite()) {
				continue;
			}
			try {
				bin = new BufferedInputStream(zipFile.getInputStream(entry));
				bout = new BufferedOutputStream(new FileOutputStream(outFile));
				byte[] buffer = new byte[1024];
				int readCount = -1;
				while ((readCount = bin.read(buffer)) != -1) {
					bout.write(buffer, 0, readCount);
				}
			} finally {
				try {
					bin.close();
					bout.flush();
					bout.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@SuppressWarnings("resource")
	public static InputStream getInputStream(String apk, String filename) throws IOException {
		InputStream is = null;
		ZipFile zip = new ZipFile(apk);// 根据给定的apk文件创建对应的zip文件

		// search for file with given filename
		Enumeration<?> entries = zip.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();

			String entryName = entry.getName();
			if (entryName.equals(filename)) {
				is = zip.getInputStream(entry);
				break;
			}
		}

		return is;
	}
	public static void saveFile(InputStream inputStream, String fileName) {
        OutputStream os = null;
        try {
        	// 2、保存到临时文件
            String path = DataBaseTableConstant.COMPRESS_PIC_TEMP;
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
