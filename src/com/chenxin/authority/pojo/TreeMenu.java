// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TreeMenu.java

package com.chenxin.authority.pojo;

import java.util.*;

// Referenced classes of package com.chenxin.authority.pojo:
//			BaseModules, Tree

public class TreeMenu {

	private List<BaseModules> list;
	private BaseModules root;

	public TreeMenu(List<BaseModules> list) {
		this.list = list;
		root = new BaseModules();
	}

	private Tree getNodeJson(List<BaseModules> list, BaseModules node) {
		Tree tree = new Tree();
		tree.setId((new StringBuilder()).append("_authority_").append(
				node.getModuleId()).toString());
		tree.setText(node.getModuleName());
		tree.setIconCls(node.getIconCss());
		tree.setChildren(new ArrayList());
		if (list == null)
			return tree;
		if (hasChild(list, node)) {
			List lt = new ArrayList();
			tree.setUrl("");
			tree.setLeaf(node.getLeaf().shortValue() == 1);
			tree.setExpanded(node.getExpanded().shortValue() == 1);
			List childList = getChildList(list, node);
			Iterator it = childList.iterator();
			while (it.hasNext()) {
				BaseModules modules = (BaseModules) it.next();
				lt.add(getNodeJson(list, modules));
			}
			tree.setChildren(lt);
		} else {
			tree.setUrl(node.getModuleUrl());
			tree.setLeaf(node.getLeaf().shortValue() == 1);
			tree.setExpanded(node.getExpanded().shortValue() == 1);
		}
		return tree;
	}

	private boolean hasChild(List<BaseModules> list, BaseModules node) {
		return getChildList(list, node).size() > 0;
	}

	private List<BaseModules> getChildList(List<BaseModules> list,
			BaseModules modules) {
		List li = new ArrayList();
		Iterator it = list.iterator();
		while (it.hasNext()) {

			BaseModules temp = (BaseModules) it.next();
			if (temp.getParentId() == modules.getModuleId())
				li.add(temp);
		}
		return li;
	}

	public Tree getTreeJson() {
		root.setModuleId(Integer.valueOf(0));
		root.setLeaf(Short.valueOf((short) 0));
		root.setExpanded(Short.valueOf((short) 0));
		return getNodeJson(list, root);
	}
}
