package com.gongshw.aragog.common.configuration;

import com.gongshw.aragog.common.rpc.RuleSelector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author       : gongshw
 * Created At   : 16/1/31.
 */
@Configuration
@ConditionalOnProperty(value = "aragog.master", havingValue = "false")
open class RpcClientConfiguration {
    @Bean
    open fun ruleSelector(): RuleSelector? = null;

}
