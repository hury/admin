// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FileUploadController.java

package com.chenxin.authority.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chenxin.authority.common.jackjson.JackJson;
import com.chenxin.authority.common.utils.FileDigest;
import com.chenxin.authority.pojo.ExtReturn;
@Controller
public class FileUploadController
{

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping({"/fileupload"})
	public void processUpload2(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response, PrintWriter writer)
    {
		try {
		      logger.info("start");
		      String fileMD5 = FileDigest.getFileMD5(file.getInputStream());
		      logger.info(fileMD5);

		      String savePath = request.getSession().getServletContext().getRealPath("/upload");

		      String uploadFileName = file.getOriginalFilename();

		      String fileType = StringUtils.substringAfterLast(uploadFileName, ".");
		      logger.debug("文件的MD5：{},上传的文件名：{},文件后缀名：{},文件大小：{}", new Object[] { fileMD5, StringUtils.substringBeforeLast(uploadFileName, "."), fileType, Long.valueOf(file.getSize()) });

		      String dataPath = DateFormatUtils.format(new Date(), new StringBuilder().append("yyyy-MM").append(File.separator).append("dd").toString());

		      String saveName = UUID.randomUUID().toString();

		      String finalPath = new StringBuilder().append(File.separator).append(dataPath).append(File.separator).append(saveName).append("".equals(fileType) ? "" : new StringBuilder().append(".").append(fileType).toString()).toString();
		      logger.debug("savePath:{},finalPath:{}", new Object[] { savePath, finalPath });
		      File saveFile = new File(new StringBuilder().append(savePath).append(finalPath).toString());

		      if (!saveFile.getParentFile().exists()) {
		        saveFile.getParentFile().mkdirs();
		      }

		      FileUtils.writeByteArrayToFile(saveFile, file.getBytes());

		      String returnMsg = JackJson.fromObjectToJson(new ExtReturn(true, "磁盘空间已经满了！"));
		      logger.debug("{}", returnMsg);
		      writer.print(returnMsg);
		    } catch (Exception e) {
		      logger.error("Exception: ", e);
		    } finally {
		      writer.flush();
		      writer.close();
		    }
	}
	@RequestMapping({"/download"})
	public void download(HttpServletRequest request, HttpServletResponse response)
	{
		InputStream input = null;
	    ServletOutputStream output = null;
	    try
	    {
	      String savePath = request.getSession().getServletContext().getRealPath("/upload");

	      String fileType = ".log";

	      String dbFileName = "83tomcat日志测试哦";
	      String downloadFileName = new StringBuilder().append(dbFileName).append(fileType).toString();

	      String finalPath = "\\2011-12\\01\\8364b45f-244d-41b6-bbf48df32064a935";

	      downloadFileName = new String(downloadFileName.getBytes("GBK"), "ISO-8859-1");

	      File downloadFile = new File(new StringBuilder().append(savePath).append(finalPath).toString());

	      if (!downloadFile.getParentFile().exists()) {
	        downloadFile.getParentFile().mkdirs();
	      }

	      if (!downloadFile.isFile())
	      {
	        FileUtils.touch(downloadFile);
	      }
	      response.setContentType("aapplication/vnd.ms-excel ;charset=UTF-8");
	      response.setCharacterEncoding("UTF-8");
	      response.setHeader("content-disposition", new StringBuilder().append("attachment; filename=").append(downloadFileName).toString());
	      input = new FileInputStream(downloadFile);
	      output = response.getOutputStream();
	      IOUtils.copy(input, output);
	      output.flush();
	    } catch (Exception e) {
	      logger.error("Exception: ", e);
	    } finally {
	      IOUtils.closeQuietly(output);
	      IOUtils.closeQuietly(input);
	    }
	  }
}
