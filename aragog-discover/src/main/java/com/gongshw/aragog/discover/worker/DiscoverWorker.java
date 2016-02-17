/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.discover.worker;

import com.gongshw.aragog.common.configuration.AragogConstant;
import com.gongshw.aragog.common.model.ListPageParseResult;
import com.gongshw.aragog.common.model.ListPageUrl;
import com.gongshw.aragog.common.service.MessageQueueService;
import com.gongshw.aragog.common.utils.ExceptionUtils;
import com.gongshw.aragog.discover.service.ListPageParserDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

/**
 * @author gongshiwei
 */
@Component
@ConditionalOnProperty("aragog.discover.enable")
public class DiscoverWorker {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ListPageParserDispatcher parserDispatcher;

    @Autowired
    private MessageQueueService messageQueueService;

    @JmsListener(
            destination = AragogConstant.LIST_PAGE_URL_DESTINATION_NAME,
            containerFactory = "aragogQueueContainerFactory",
            concurrency = "${aragog.work.discover.concurrency:5}")
    @Transactional
    public void handlerListPageUrl(@Payload @Valid ListPageUrl listPageUrl) throws Exception {
        logger.debug("receive {} from LIST_PAGE_URL_DESTINATION", listPageUrl);
        ListPageParseResult parseResult;
        try {
            parseResult = parserDispatcher.parseListPage(listPageUrl);
        } catch (Exception e) {
            logger.error("parseListPage url {} failed! Send back to queue", listPageUrl, e);
            ExceptionUtils.throwRuntimeException(e);
            return;
        }
        logger.debug("send {} to LIST_PAGE_URL_DESTINATION", parseResult.getListPageUrls());
        messageQueueService.sendListPageUrls(parseResult.getListPageUrls());
        messageQueueService.sendDetailPageUrls(parseResult.getDetailPageUrls());
    }
}
