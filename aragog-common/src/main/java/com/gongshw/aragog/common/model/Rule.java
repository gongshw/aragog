/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.model;

import java.util.List;

import lombok.Data;

/**
 * @author gongshiwei
 */
@Data
public class Rule {
    private long id;
    private String uuid;
    private String name;
    private List<String> listPageUrlPatterns;
    private List<String> detailPageUrlPatterns;
    private ScriptType scriptType;
    private String environmentInitScript;
    private String listPageParserScript;
    private String detailPageParserScript;
}
