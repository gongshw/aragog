/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.master.parser;

import com.gongshw.aragog.common.model.ListPageParseResult;
import com.gongshw.aragog.common.model.DetailPageUrl;
import com.gongshw.aragog.common.model.ListPageUrl;
import com.gongshw.aragog.common.model.PageDetail;
import com.gongshw.aragog.common.rpc.PageParser;

/**
 * @author gongshiwei
 */
public class GroovyParser implements PageParser {
	@Override
	public void init() throws Exception {

	}

	@Override
	public ListPageParseResult parseListPage(ListPageUrl url) throws Exception {
		return null;
	}

	@Override
	public PageDetail parseDetailPage(DetailPageUrl url) throws Exception {
		return null;
	}
}
