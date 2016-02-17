/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.master.parser;

import com.gongshw.aragog.common.model.DetailPageUrl;
import com.gongshw.aragog.common.model.ListPageParseResult;
import com.gongshw.aragog.common.model.ListPageUrl;
import com.gongshw.aragog.common.model.PageDetail;
import com.gongshw.aragog.common.rpc.PageParser;
import com.gongshw.aragog.common.service.HttpClientService;
import com.gongshw.aragog.common.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

/**
 * @author gongshiwei
 */
@Component
@ConditionalOnProperty("aragog.master")
public class BrokenParser implements PageParser {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HttpClientService httpClient;

	@Override
	public void init() {

	}

	@Override
	public ListPageParseResult parseListPage(ListPageUrl url) {
		ListenableFuture<String> future = httpClient.get(url.getUrl());
		String response = null;
		try {
			response = future.get();
		} catch (InterruptedException e) {
			ExceptionUtils.throwRuntimeException(e);
		} catch (ExecutionException e) {
			ExceptionUtils.throwRuntimeException(e.getCause());
		}
		logger.debug(response);
		ListPageParseResult result = new ListPageParseResult();
		result.setListPageUrls(Arrays.asList(
				new ListPageUrl(url.getUrl()),
				new ListPageUrl(url.getUrl())
		));
		result.setDetailPageUrls(Collections.emptyList());
		return result;
	}

	@Override
	public PageDetail parseDetailPage(DetailPageUrl url) {
		throw new RuntimeException();
	}
}
