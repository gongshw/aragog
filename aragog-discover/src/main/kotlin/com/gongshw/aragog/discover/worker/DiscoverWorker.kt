/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.discover.worker;

import com.gongshw.aragog.common.configuration.AragogConstant
import com.gongshw.aragog.common.model.ListPageParseResult
import com.gongshw.aragog.common.model.ListPageUrl
import com.gongshw.aragog.common.service.MessageQueueService
import com.gongshw.aragog.common.utils.ExceptionUtils
import com.gongshw.aragog.discover.service.ListPageParserDispatcher
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.validation.Valid

/**
 * @author gongshiwei
 */
@Component
open class DiscoverWorker {

    val logger = LoggerFactory.getLogger(this.javaClass);

    @Autowired
    lateinit private var parserDispatcher: ListPageParserDispatcher;

    @Autowired
    lateinit private var messageQueueService: MessageQueueService;

    @Transactional
    @JmsListener(
            destination = AragogConstant.LIST_PAGE_URL_DESTINATION_NAME,
            containerFactory = "aragogQueueContainerFactory",
            concurrency = "\${aragog.work.discover.concurrency:5}")
    fun handlerListPageUrl(@Payload @Valid listPageUrl: ListPageUrl) {
        logger.debug("receive {} from LIST_PAGE_URL_DESTINATION", listPageUrl);
        var parseResult: ListPageParseResult;
        try {
            parseResult = parserDispatcher.parseListPage(listPageUrl);
        } catch (e: Exception) {
            logger.error("parseListPage url {} failed! Send back to queue", listPageUrl, e);
            ExceptionUtils.throwRuntimeException(e);
            return;
        }
        logger.debug("send {} to LIST_PAGE_URL_DESTINATION", parseResult.listPageUrls);
        messageQueueService.sendListPageUrls(parseResult.listPageUrls);
        messageQueueService.sendDetailPageUrls(parseResult.detailPageUrls);
    }
}
