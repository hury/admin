// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   WebUtils.java

package com.chenxin.authority.common.utils;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

public class WebUtils {

	public WebUtils() {
	}

	public static HashMap<String,String> getPraramsAsMap(HttpServletRequest request) {
		HashMap hashMap = new HashMap();
		Map map = request.getParameterMap();
		Iterator keyIterator = map.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			String value = ((String[]) (String[]) map.get(key))[0];
			hashMap.put(key, value);
		}

		return hashMap;
	}
}
