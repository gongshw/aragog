/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gongshiwei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListPageUrl implements Serializable {
    private String url;
    private long foundTime;

    public ListPageUrl(String url) {
        setUrl(url);
        setFoundTime(new Date().getTime());
    }

}
