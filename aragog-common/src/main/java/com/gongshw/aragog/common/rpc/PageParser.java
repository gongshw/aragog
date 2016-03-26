/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.rpc;

import com.gongshw.aragog.common.model.DetailPageUrl;
import com.gongshw.aragog.common.model.ListPageParseResult;
import com.gongshw.aragog.common.model.ListPageUrl;
import com.gongshw.aragog.common.model.PageDetail;

/**
 * @author gongshiwei
 */
public interface PageParser {
	void init() throws Exception;

	ListPageParseResult parseListPage(ListPageUrl url) throws Exception;

	PageDetail parseDetailPage(DetailPageUrl url) throws Exception;
}
