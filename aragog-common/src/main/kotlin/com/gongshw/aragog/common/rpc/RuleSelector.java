/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.rpc;

import org.springframework.stereotype.Service;

/**
 * @author gongshiwei
 */
@Service
public interface RuleSelector {
	PageParser getPageParserByUrl(String url);
}
