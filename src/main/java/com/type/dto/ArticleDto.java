package com.type.dto;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
@AllArgsConstructor
public class ArticleDto {
    private String articleName;
    private String articleContent;
    private String articleLangFlg;
    private Date createDate;
    private Date updateDate;
    public  ArticleDto(String name,String content,String langFlg){
        this.articleName = name;
        this.articleContent = content;
        this.articleLangFlg = langFlg;
    }
}
