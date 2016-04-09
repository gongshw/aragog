package com.gongshw.aragog.common.rpc;

import com.gongshw.aragog.common.service.HttpClientService;
import groovy.lang.GroovyClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author       : gongshw
 * Created At   : 16/1/31.
 */
@Component
public class ParserBuilder {

	@Autowired
	private HttpClientService httpClientWorker;

	public PageParser wrapGroovyPageParer(String script)
			throws Exception {
		GroovyClassLoader classLoader = new GroovyClassLoader(getClass().getClassLoader());
		Class<? extends AbstractPageParser> pageParserClass = classLoader.parseClass(script);
		AbstractPageParser pageParser = pageParserClass.newInstance();
		pageParser.init();
		pageParser.setHttpClient(httpClientWorker);
		return pageParser;
	}
}
