package com.type.ItemProcessor;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.type.dto.ArticleDto;
import com.type.unit.CommonUnit;

/**
 * @author qiguangjie
 * 中间处理器创建
 *
 */
public class ArticleItemProcessor implements ItemProcessor<ArticleDto, ArticleDto> {
    private static final Logger log = (Logger) LogFactory.getLog(ArticleItemProcessor.class);

    @Override
    public ArticleDto process(final ArticleDto item) throws Exception {
        // 去除内容中的全角空格
        String name = item.getArticleName();
        String content = CommonUnit.getTrimFullWidthSpace(item.getArticleContent());
        String langFlg = item.getArticleLangFlg();
        final ArticleDto transFormData = new ArticleDto(name, content, langFlg);
        // log
        log.info("ArticleItemProcessor is OK! ");
        return transFormData;
    }

}
