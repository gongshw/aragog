/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.service;

import com.gongshw.aragog.common.configuration.AragogConstant;
import com.gongshw.aragog.common.model.DetailPageUrl;
import com.gongshw.aragog.common.model.ListPageUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.jms.ConnectionFactory;
import java.util.List;

/**
 * @author gongshiwei
 */
@Service
public class MessageQueueService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${aragog.discover.enable}")
    private boolean discoverWorkerEnabled;

    @Autowired
    private ConnectionFactory aragogQueueConnectionFactory;

    private JmsTemplate getJmsTemplate() {
        return new JmsTemplate(aragogQueueConnectionFactory);
    }

    public void sendListPageUrls(List<ListPageUrl> urls) {
        Assert.notNull(urls);
        if (!discoverWorkerEnabled) {
            throw new RuntimeException("aragog.worker.discover.enable is false");
        }
        urls.forEach(this::sendListPageUrl);
    }

    public void sendListPageUrl(ListPageUrl url) {
        Assert.notNull(url);
        if (!discoverWorkerEnabled) {
            throw new RuntimeException("aragog.worker.discover.enable is false");
        }
        MessageCreator messageCreator = session -> session.createObjectMessage(url);
        getJmsTemplate().send(AragogConstant.LIST_PAGE_URL_DESTINATION_NAME, messageCreator);
        logger.debug("send {} to LIST_PAGE_URL_DESTINATION", url);
    }

    public void sendDetailPageUrls(List<DetailPageUrl> urls) {
        Assert.notNull(urls);
    }
}
