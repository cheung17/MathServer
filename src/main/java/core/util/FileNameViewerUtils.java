package core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取指定目录下所有文件的文件名(只取文件名，不取路径)
 * 
 * @author libing
 * 
 */
public class FileNameViewerUtils {
	public static List<String> getListFiles(String path, String suffix,
			boolean isdepth) {
		List<String> lstFileNames = new ArrayList<String>();
		File file = new File(path);
		return FileNameViewerUtils.listFile(lstFileNames, file, suffix, isdepth);
	}

	private static List<String> listFile(List<String> lstFileNames, File f,
			String suffix, boolean isdepth) {
		// 若是目录, 采用递归的方法遍历子目录
		if (f.isDirectory()) {
			File[] t = f.listFiles();

			for (int i = 0; i < t.length; i++) {
				if (isdepth || t[i].isFile()) {
					listFile(lstFileNames, t[i], suffix, isdepth);
				}
			}
		} else {
			String filePath = f.getAbsolutePath();
			if (!suffix.equals("")) {
				int begIndex = filePath.lastIndexOf("."); // 最后一个.(即后缀名前面的.)的索引
				int beg_Index = filePath.lastIndexOf("\\");
				String tempsuffix = "";
				String filename="";

				if (begIndex != -1) {
					tempsuffix = filePath.substring(begIndex + 1,
							filePath.length());
					filename =  filePath.substring(beg_Index+1, filePath.length());
					if (tempsuffix.equals(suffix)) {
						lstFileNames.add(filename);
					}
				}
			} else {
				lstFileNames.add(filePath);
			}
		}
		return lstFileNames;
	}
}
