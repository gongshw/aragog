import com.gongshw.aragog.common.model.DetailPageUrl
import com.gongshw.aragog.common.model.ListPageParseResult
import com.gongshw.aragog.common.model.ListPageUrl
import com.gongshw.aragog.common.model.PageDetail
import com.gongshw.aragog.common.rpc.AbstractPageParser
import com.gongshw.aragog.common.utils.ExceptionUtils
import org.springframework.util.concurrent.ListenableFuture

import java.util.concurrent.ExecutionException

class Bohaishibei2RssParser extends AbstractPageParser {

    @Override
    void init() throws Exception {

    }

    @Override
    ListPageParseResult parseListPage(ListPageUrl url) throws Exception {
        ListenableFuture<String> future = httpClient.get(url.getUrl());
        String response = null;
        try {
            response = future.get();
        } catch (InterruptedException e) {
            ExceptionUtils.throwRuntimeException(e);
        } catch (ExecutionException e) {
            ExceptionUtils.throwRuntimeException(e.getCause());
        }
        logger.debug(response);
        ListPageParseResult result = new ListPageParseResult();
        result.setListPageUrls(Arrays.asList(
                new ListPageUrl(url.getUrl()),
                new ListPageUrl(url.getUrl())
        ));
        result.setDetailPageUrls(Collections.emptyList());
        return result;
    }

    @Override
    PageDetail parseDetailPage(DetailPageUrl url) throws Exception {
        return null
    }
}
