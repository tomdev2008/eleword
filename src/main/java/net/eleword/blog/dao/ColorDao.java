package net.eleword.blog.dao;

import java.util.List;

import net.eleword.blog.dao.common.BaseDao;
import net.eleword.blog.entity.Color;
import net.eleword.blog.util.Pagination;

/**
 * TODO 此处填写 class 信息
 * @author krisjin (mailto:krisjin86@163.com)
 * @date  2014-3-13下午12:37:05
 */

public interface ColorDao extends BaseDao<Color>{
	
	public Pagination<Color> selectColorWithPage(Pagination<Color> page);
	
	public Color selectColorByCode(String code);
	
	public List<Color> getAllColors();

}

