/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.zhangxd.trip.service.api.entity;

import cn.zhangxd.trip.service.api.entity.base.DataEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 文章Entity
 * @author ThinkGem
 * @version 2013-01-15
 */
public class CmsArticleData extends DataEntity<CmsArticleData> {

	private static final long serialVersionUID = 1L;
	private String id;		// 编号
	private String content;	// 内容
	private String copyfrom;// 来源
	private String relation;// 相关文章

	private CmsArticle article;
	
	public CmsArticleData() {
		super();
	}
	
	public CmsArticleData(String id){
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotBlank
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Length(min=0, max=255)
	public String getCopyfrom() {
		return copyfrom;
	}

	public void setCopyfrom(String copyfrom) {
		this.copyfrom = copyfrom;
	}

	@Length(min=0, max=255)
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public CmsArticle getArticle() {
		return article;
	}

	public void setArticle(CmsArticle article) {
		this.article = article;
	}

}