// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PropertiesHelper.java

package com.chenxin.authority.common.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import org.springframework.util.StringUtils;

public class PropertiesHelper
{

	public static final int SYSTEM_PROPERTIES_MODE_NEVER = 0;
	public static final int SYSTEM_PROPERTIES_MODE_FALLBACK = 1;
	public static final int SYSTEM_PROPERTIES_MODE_OVERRIDE = 2;
	private int systemPropertiesMode;
	private Properties p;

	public PropertiesHelper(Properties p)
	{
		systemPropertiesMode = 0;
		setProperties(p);
	}

	public PropertiesHelper(Properties p, int systemPropertiesMode)
	{
		this.systemPropertiesMode = 0;
		setProperties(p);
		if (systemPropertiesMode != 0 && systemPropertiesMode != 1 && systemPropertiesMode != 2)
		{
			throw new IllegalArgumentException((new StringBuilder()).append("error systemPropertiesMode mode:").append(systemPropertiesMode).toString());
		} else
		{
			this.systemPropertiesMode = systemPropertiesMode;
			return;
		}
	}

	public Properties getProperties()
	{
		return p;
	}

	public void setProperties(Properties props)
	{
		if (props == null)
		{
			throw new IllegalArgumentException("properties must be not null");
		} else
		{
			p = props;
			return;
		}
	}

	public String getRequiredProperty(String key)
		throws IllegalStateException
	{
		String value = getProperty(key);
		if (isBlankString(value))
			throw new IllegalStateException((new StringBuilder()).append("required property is blank by key=").append(key).toString());
		else
			return value;
	}

	public String getNullIfBlank(String key)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return null;
		else
			return value;
	}

	public String getNullIfEmpty(String key)
	{
		String value = getProperty(key);
		if (value == null || "".equals(value))
			return null;
		else
			return value;
	}

	public String getAndTryFromSystem(String key)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			value = getSystemProperty(key);
		return value;
	}

	private String getSystemProperty(String key)
	{
		String value = System.getProperty(key);
		if (isBlankString(value))
			value = System.getenv(key);
		return value;
	}

	public Integer getInteger(String key)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return null;
		else
			return Integer.valueOf(Integer.parseInt(value));
	}

	public int getInt(String key, int defaultValue)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return defaultValue;
		else
			return Integer.parseInt(value);
	}

	public int getRequiredInt(String key)
		throws IllegalStateException
	{
		return Integer.parseInt(getRequiredProperty(key));
	}

	public Long getLong(String key)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return null;
		else
			return Long.valueOf(Long.parseLong(value));
	}

	public long getLong(String key, long defaultValue)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return defaultValue;
		else
			return Long.parseLong(value);
	}

	public long getRequiredLong(String key)
		throws IllegalStateException
	{
		return Long.parseLong(getRequiredProperty(key));
	}

	public Boolean getBoolean(String key)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return null;
		else
			return Boolean.valueOf(Boolean.parseBoolean(value));
	}

	public boolean getBoolean(String key, boolean defaultValue)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return defaultValue;
		else
			return Boolean.parseBoolean(value);
	}

	public boolean getRequiredBoolean(String key)
		throws IllegalStateException
	{
		return Boolean.parseBoolean(getRequiredProperty(key));
	}

	public Float getFloat(String key)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return null;
		else
			return Float.valueOf(Float.parseFloat(value));
	}

	public float getFloat(String key, float defaultValue)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return defaultValue;
		else
			return Float.parseFloat(value);
	}

	public float getRequiredFloat(String key)
		throws IllegalStateException
	{
		return Float.parseFloat(getRequiredProperty(key));
	}

	public Double getDouble(String key)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return null;
		else
			return Double.valueOf(Double.parseDouble(value));
	}

	public double getDouble(String key, double defaultValue)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return defaultValue;
		else
			return Double.parseDouble(value);
	}

	public double getRequiredDouble(String key)
		throws IllegalStateException
	{
		return Double.parseDouble(getRequiredProperty(key));
	}

	public URL getURL(String key) throws IllegalArgumentException {
		try {
			return new URL(getProperty(key));
		} catch (MalformedURLException e) {
		}
		throw new IllegalArgumentException("Property " + key
				+ " must be a valid URL (" + getProperty(key) + ")");
	}

	public Object getClassInstance(String key) throws IllegalArgumentException {
		String s = getProperty(key);
		if ((s == null) || ("".equals(s.trim())))
			throw new IllegalArgumentException("Property " + key
					+ " must be a valid classname  : " + key);
		try {
			return Class.forName(s).newInstance();
		} catch (ClassNotFoundException nfe) {
			throw new IllegalArgumentException(s
					+ ": invalid class name for key " + key, nfe);
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(s
					+ ": class could not be reflected " + s, e);
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	public Object getClassInstance(String key, Object defaultinstance)
		throws IllegalArgumentException
	{
		return containsKey(key) ? getClassInstance(key) : defaultinstance;
	}

	public String[] getStringArray(String key)
	{
		String v = getProperty(key);
		if (v == null)
			return new String[0];
		else
			return StringUtils.tokenizeToStringArray(v, ", \t\n\r\f");
	}

	public int[] getIntArray(String key)
	{
		return toIntArray(getStringArray(key));
	}

	public Properties getStartsWithProperties(String prefix)
	{
		if (prefix == null)
			throw new IllegalArgumentException("'prefix' must be not null");
		Properties props = getProperties();
		Properties result = new Properties();
		Iterator i$ = props.entrySet().iterator();
		do
		{
			if (!i$.hasNext())
				break;
			java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
			String key = (String)entry.getKey();
			if (key != null && key.startsWith(prefix))
				result.put(key.substring(prefix.length()), entry.getValue());
		} while (true);
		return result;
	}

	public Object setProperty(String key, int value)
	{
		return setProperty(key, String.valueOf(value));
	}

	public Object setProperty(String key, long value)
	{
		return setProperty(key, String.valueOf(value));
	}

	public Object setProperty(String key, float value)
	{
		return setProperty(key, String.valueOf(value));
	}

	public Object setProperty(String key, double value)
	{
		return setProperty(key, String.valueOf(value));
	}

	public Object setProperty(String key, boolean value)
	{
		return setProperty(key, String.valueOf(value));
	}

	public String getProperty(String key, String defaultValue)
	{
		String value = getProperty(key);
		if (isBlankString(value))
			return defaultValue;
		else
			return value;
	}

	public String getProperty(String key)
	{
		String propVal = null;
		if (systemPropertiesMode == 2)
			propVal = getSystemProperty(key);
		if (propVal == null)
			propVal = p.getProperty(key);
		if (propVal == null && systemPropertiesMode == 1)
			propVal = getSystemProperty(key);
		return propVal;
	}

	public Object setProperty(String key, String value)
	{
		return p.setProperty(key, value);
	}

	public void clear()
	{
		p.clear();
	}

	public Set<Map.Entry<Object, Object>> entrySet()
	{
		return p.entrySet();
	}

	public Enumeration<?> propertyNames()
	{
		return p.propertyNames();
	}

	public boolean contains(Object value)
	{
		return p.contains(value);
	}

	public boolean containsKey(Object key)
	{
		return p.containsKey(key);
	}

	public boolean containsValue(Object value)
	{
		return p.containsValue(value);
	}

	public Enumeration<Object> elements()
	{
		return p.elements();
	}

	public Object get(Object key)
	{
		return p.get(key);
	}

	public boolean isEmpty()
	{
		return p.isEmpty();
	}

	public Enumeration<Object> keys()
	{
		return p.keys();
	}

	public Set<Object> keySet()
	{
		return p.keySet();
	}

	public void list(PrintStream out)
	{
		p.list(out);
	}

	public void list(PrintWriter out)
	{
		p.list(out);
	}

	public void load(InputStream inStream)
		throws IOException
	{
		p.load(inStream);
	}

	public void loadFromXML(InputStream in)
		throws IOException, InvalidPropertiesFormatException
	{
		p.loadFromXML(in);
	}

	public Object put(Object key, Object value)
	{
		return p.put(key, value);
	}

	public void putAll(Map<? extends Object, ? extends Object> t)
	{
		p.putAll(t);
	}

	public Object remove(Object key)
	{
		return p.remove(key);
	}

	/**
	 * @deprecated Method save is deprecated
	 */

	public void save(OutputStream out, String comments)
	{
		p.save(out, comments);
	}

	public int size()
	{
		return p.size();
	}

	public void store(OutputStream out, String comments)
		throws IOException
	{
		p.store(out, comments);
	}

	public void storeToXML(OutputStream os, String comment, String encoding)
		throws IOException
	{
		p.storeToXML(os, comment, encoding);
	}

	public void storeToXML(OutputStream os, String comment)
		throws IOException
	{
		p.storeToXML(os, comment);
	}

	public Collection values()
	{
		return p.values();
	}

	public String toString()
	{
		return p.toString();
	}

	public static Properties restoreFromString(String str)
	{
		if (str == null)
			return new Properties();
		Properties p = new Properties();
		try
		{
			p.load(new ByteArrayInputStream(str.getBytes()));
		}
		catch (IOException e)
		{
			throw new IllegalStateException((new StringBuilder()).append("restore properties from String occer error. str:").append(str).toString(), e);
		}
		return p;
	}

	private static boolean isBlankString(String value)
	{
		return value == null || "".equals(value.trim());
	}

	private static int[] toIntArray(String array[])
	{
		int result[] = new int[array.length];
		for (int i = 0; i < array.length; i++)
			result[i] = Integer.parseInt(array[i]);

		return result;
	}
}
