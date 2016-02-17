/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author gongshiwei
 */
@Data
@AllArgsConstructor
public class DetailPageUrl implements Serializable {
    private String url;
    private String foundTime;
}
