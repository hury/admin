// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseFieldsServiceImpl.java

package com.chenxin.authority.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chenxin.authority.common.jackjson.JackJson;
import com.chenxin.authority.dao.BaseFieldsMapper;
import com.chenxin.authority.pojo.BaseFields;
import com.chenxin.authority.pojo.Criteria;
import com.chenxin.authority.service.BaseFieldsService;
@Service
public class BaseFieldsServiceImpl
	implements BaseFieldsService
{
	@Autowired
	private BaseFieldsMapper baseFieldsMapper;
	private static final Logger logger = LoggerFactory.getLogger(BaseFieldsServiceImpl.class);

	public int countByExample(Criteria example)
	{
		int count = baseFieldsMapper.countByExample(example);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}

	public BaseFields selectByPrimaryKey(String fieldId)
	{
		return baseFieldsMapper.selectByPrimaryKey(fieldId);
	}

	public List<BaseFields> selectByExample(Criteria example)
	{
		return baseFieldsMapper.selectByExample(example);
	}

	public HashMap<String,String> selectAllByExample(Criteria example)
	{
		List list = baseFieldsMapper.selectByExample(example);
		HashMap all = new HashMap();
		LinkedHashMap part = null;
		for (int i = 0; i < list.size(); i++)
		{
			BaseFields baseFields = (BaseFields)list.get(i);
			String key = baseFields.getField();
			if (all.containsKey(key))
			{
				part = (LinkedHashMap)all.get(key);
				part.put(baseFields.getValueField(), baseFields.getDisplayField());
			} else
			{
				part = new LinkedHashMap();
				part.put(baseFields.getValueField(), baseFields.getDisplayField());
				all.put(key, part);
			}
		}

		part = new LinkedHashMap();
		logger.info("开始读取系统默认配置");
		String key;
		String val;
		for (Iterator i$ = all.entrySet().iterator(); i$.hasNext(); part.put(key, val))
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
			key = (String)entry.getKey();
			HashMap value = (HashMap)entry.getValue();
			val = JackJson.fromObjectToJson(value).replaceAll("\\'", "\\\\'");
			logger.info(val);
		}

		logger.info("结束读取系统默认配置");
		return part;
	}
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveFields(Criteria example)
	{
		BaseFields fields = (BaseFields)example.get("fields");
		int result = 0;
		if (fields.getFieldId() == null)
			result = baseFieldsMapper.insertSelective(fields);
		else
			result = baseFieldsMapper.updateByPrimaryKeySelective(fields);
		return result <= 0 ? "00" : "01";
	}
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String delete(Criteria example)
	{
		String fieldId = example.getAsString("fieldId");
		int result = 0;
		result = baseFieldsMapper.deleteByPrimaryKey(fieldId);
		return result <= 0 ? "00" : "01";
	}

}
