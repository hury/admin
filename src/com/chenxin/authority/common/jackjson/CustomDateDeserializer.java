// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CustomDateDeserializer.java

package com.chenxin.authority.common.jackjson;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomDateDeserializer.class);
	private static final String DATE[] = { "yyyy-MM-dd" };

	public Date deserialize(JsonParser parser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		try {
			return DateUtils.parseDate(parser.getText(), DATE);
		} catch (ParseException e) {
			logger.error("ParseException: ", e);
		}
		return null;
	}

}
