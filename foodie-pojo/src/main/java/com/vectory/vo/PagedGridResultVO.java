package com.vectory.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="分页信息出参", description="分页信息出参")
public class PagedGridResultVO implements Serializable {
	private static final long serialVersionUID = 2002389843098237034L;

	@ApiModelProperty(value="当前页数", name="page")
	private Integer page;

	@ApiModelProperty(value="总页数", name="total")
	private Integer total;

	@ApiModelProperty(value="总记录数", name="records")
	private Long records;

	@ApiModelProperty(value="每行显示的内容", name="rows")
	private List<?> rows;
}