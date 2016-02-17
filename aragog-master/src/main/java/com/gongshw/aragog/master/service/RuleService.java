/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.master.service;

import com.gongshw.aragog.common.rpc.RuleSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.gongshw.aragog.master.parser.BrokenParser;
import com.gongshw.aragog.common.rpc.PageParser;

/**
 * @author gongshiwei
 */
@Service
@ConditionalOnProperty("aragog.master")
public class RuleService implements RuleSelector {

    @Autowired
    private BrokenParser brokenParser;

    public PageParser getPageParserByUrl(String url) {
        return brokenParser;
    }
}
