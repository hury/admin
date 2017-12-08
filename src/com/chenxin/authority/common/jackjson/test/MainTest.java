package com.chenxin.authority.common.jackjson.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

import com.chenxin.authority.common.utils.ClassLoaderUtil;
import com.chenxin.authority.common.utils.FileDigest;
import com.chenxin.authority.common.utils.PropertiesHolder;

// Referenced classes of package com.chenxin.authority.common.jackjson.test:
//			User

public class MainTest
{

	public MainTest()
	{
	}

	public static void main(String args[])
		throws Exception
	{
		String str = "";
		System.out.println(DigestUtils.md5Hex("123456"));
		System.out.println(DigestUtils.shaHex(str));
		System.out.println(DigestUtils.sha256Hex(str));
		System.out.println(DigestUtils.sha384Hex(str));
		System.out.println(DigestUtils.sha512Hex(str));
		System.out.println(FileDigest.getFileMD5(new File("D:/apache/tomcat-6.0.35/logs/lottery2.0/all.2011-12-31.txt")));
		System.out.println(FileDigest.getFileMD5(new File("D:/apache/tomcat-6.0.35/logs/lottery2.0/all.2011-12-30.txt")));
		System.out.println(FileDigest.getFileMD5(new File("D:/apache/tomcat-6.0.35/logs/lottery2.0/all.2011-12-29.txt")));
		long start = System.currentTimeMillis();
		System.out.println(FileDigest.getFileMD5(new File("F:/movie/金陵十三钗DVD高清全集.rmvb")));
		System.out.println((new StringBuilder()).append("耗时(毫秒)：").append(System.currentTimeMillis() - start).toString());
		Map maps = FileDigest.getDirMD5(new File("D:/apache/tomcat-6.0.35/logs"), true);
		String key;
		String md5;
		for (Iterator i$ = maps.entrySet().iterator(); i$.hasNext(); System.out.println((new StringBuilder()).append(md5).append(" ").append(key).toString()))
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
			key = (String)entry.getKey();
			md5 = (String)entry.getValue();
		}

	}

	private static void test1()
		throws JsonGenerationException, JsonMappingException, IOException
	{
		User user = new User();
		user.setAge(Integer.valueOf(23));
		user.setName("cx");
		user.setPassword("123456");
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = (new SimpleFilterProvider()).addFilter("myFilter", SimpleBeanPropertyFilter.serializeAllExcept(new String[] {
			"password"
		}));
		String json = mapper.filteredWriter(filters).writeValueAsString(user);
		System.out.println(json);
		InputStreamReader reader = null;
		Properties properties = new Properties();
		InputStream is = ClassLoaderUtil.getResourceAsStream("config/others/config.properties", MainTest.class);
		if (null != is)
		{
			reader = new InputStreamReader(is, "UTF-8");
			properties.load(reader);
		}
		PropertiesHolder p = new PropertiesHolder();
		p.setProperties(properties);
	}
}
