/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.master.controler;

import com.gongshw.aragog.common.model.ListPageUrl;
import com.gongshw.aragog.common.service.MessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author gongshiwei
 */
@RestController
@RequestMapping("/queue")
@ConditionalOnProperty("aragog.master")
public class QueueController {

	@Autowired
	private MessageQueueService messageQueueService;

	@RequestMapping(value = "/listPageUrl", method = RequestMethod.POST)
	public void addListPageUrl(@RequestBody String url) {
		ListPageUrl listPageUrl = new ListPageUrl(url, new Date());
		messageQueueService.sendListPageUrl(listPageUrl);
	}
}
