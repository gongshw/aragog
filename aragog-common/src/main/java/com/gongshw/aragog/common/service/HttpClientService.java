/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;

/**
 * @author gongshiwei
 */
@Service
public class HttpClientService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public ListenableFuture<String> get(String url) {
		logger.debug("getting url {}", url);
		Request request = new Request.Builder().url(url).build();
		return execute(request);
	}

	@Async
	private ListenableFuture<String> execute(Request request) {
		OkHttpClient client = new OkHttpClient();
		Response response;
		try {
			response = client.newCall(request).execute();
			return new AsyncResult<>(response.body().string());
		} catch (IOException e) {
			throw new RuntimeException("fail to execute request " + request, e);
		}
	}
}
