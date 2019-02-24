package com.type.dto;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 关联db_article表
 * @author qiguangjie
 *
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    /** 文章名称 */
    private String articleName;
    /** 文章 内容*/
    private String articleContent;
    /** 文章语言类型 */
    private String articleLangFlg;
    /** 生成日期 */
    private Date createDate;
    /** 更新日期 */
    private Date updateDate;

    public  ArticleDto(String articleName,String articleContent,String articleLangFlg){
        this.articleName = articleName;
        this.articleContent = articleContent;
        this.articleLangFlg = articleLangFlg;
    }
}
