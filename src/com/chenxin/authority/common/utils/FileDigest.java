package com.chenxin.authority.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDigest {
	private static final Logger logger = LoggerFactory
			.getLogger(FileDigest.class);

	public static String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			return getFileMD5(in);
		} catch (Exception e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

	public static String getFileMD5(InputStream in) {
		MessageDigest digest = null;
		byte[] buffer = new byte[1024];
		try {
			digest = MessageDigest.getInstance("MD5");
			int len;
			while ((len = in.read(buffer, 0, 1024)) != -1)
				digest.update(buffer, 0, len);
		} catch (Exception e) {
			logger.error("IOException: ", e);
			return null;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				logger.error("IOException: ", e);
			}
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16).toUpperCase();
	}

	public static Map<String, String> getDirMD5(File file, boolean listChild) {
		if (!file.isDirectory()) {
			return null;
		}

		Map map = new HashMap();

		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if ((f.isDirectory()) && (listChild)) {
				map.putAll(getDirMD5(f, listChild));
			} else {
				String md5 = getFileMD5(f);
				if (md5 != null) {
					map.put(f.getPath(), md5);
				}
			}
		}
		return map;
	}
}
