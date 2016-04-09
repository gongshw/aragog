package com.gongshw.aragog.common.rpc;

import com.gongshw.aragog.common.service.HttpClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author       : gongshw
 * Created At   : 16/1/31.
 */
public abstract class AbstractPageParser implements PageParser {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private HttpClientService httpClient;

	protected Logger getLogger() {
		return logger;
	}

	protected HttpClientService getHttpClient() {
		return httpClient;
	}

	void setHttpClient(HttpClientService httpClient) {
		this.httpClient = httpClient;
	}
}
