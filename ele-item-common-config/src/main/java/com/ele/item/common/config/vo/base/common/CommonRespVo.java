package com.ele.item.common.config.vo.base.common;

import java.util.List;

import lombok.Data;

@Data
public class CommonRespVo<T> {

	private List<T> respVoList;
}
