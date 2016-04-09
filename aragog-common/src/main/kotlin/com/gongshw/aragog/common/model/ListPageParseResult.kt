package com.gongshw.aragog.common.model;

import java.io.Serializable

/**
 * @author gongshw
 */
data class ListPageParseResult(
        val detailPageUrls: List<DetailPageUrl>,
        val listPageUrls: List<ListPageUrl>
) : Serializable
