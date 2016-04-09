/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.discover.service;

import com.gongshw.aragog.common.model.ListPageParseResult;
import com.gongshw.aragog.common.model.ListPageUrl;
import com.gongshw.aragog.common.rpc.PageParser;
import com.gongshw.aragog.common.rpc.RuleSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gongshiwei
 */
@Service
public class ListPageParserDispatcher {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RuleSelector ruleSelector;

	public ListPageParseResult parseListPage(ListPageUrl url) throws Exception {
		PageParser pageParser = ruleSelector.getPageParserByUrl(url.getUrl());
		if (pageParser == null) {
			Exception e = new RuntimeException(url.toString());
			logger.error("fail to found parser for {}", url.getUrl(), e);
			throw e;
		}
		return pageParser.parseListPage(url);
	}
}
