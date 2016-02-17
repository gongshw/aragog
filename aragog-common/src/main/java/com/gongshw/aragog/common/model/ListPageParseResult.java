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
public class ListPageParseResult {
    private List<DetailPageUrl> detailPageUrls;
    private List<ListPageUrl> listPageUrls;
}
