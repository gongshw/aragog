/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.model;

import java.util.List;

/**
 * @author gongshiwei
 */
data class Rule(
        val id: Long,
        val uuid: String,
        val name: String,
        val listPageUrlPatterns: List<String>,
        val detailPageUrlPatterns: List<String>,
        val scriptType: ScriptType,
        val environmentInitScript: String,
        val listPageParserScript: String,
        val detailPageParserScript: String
)