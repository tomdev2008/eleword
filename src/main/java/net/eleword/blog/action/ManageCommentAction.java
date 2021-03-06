package net.eleword.blog.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.eleword.blog.entity.Article;
import net.eleword.blog.entity.Comment;
import net.eleword.blog.service.ArticleService;
import net.eleword.blog.service.CommentService;
import net.eleword.blog.util.ConstantEnum;
import net.eleword.blog.util.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.StringUtils;

/**
 * TODO 此处填写 class 信息
 * 
 * @author krisjin (mailto:krisjin86@163.com)
 * @date 2014-2-14上午7:18:22
 */
@Controller
public class ManageCommentAction{

	@Autowired
	private CommentService commentService;

	@Autowired
	private ArticleService artcileService;
	
	@RequestMapping(value="/admin/comments/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Long id) {
		commentService.deleteById(Long.valueOf(id));
		return "redirect:/admin/comments";
	}
	@RequestMapping(value="/admin/comments",method=RequestMethod.GET)
	public String listComments(HttpServletRequest request) {
		
		Pagination<Comment> commentPage = new Pagination<Comment>();
		commentPage.setPageSize(15);
		String pageCount = request.getParameter("page");

		if (StringUtils.isNullOrEmpty(pageCount)) {
			commentPage.setCurrentPage(1);
		} else {
			commentPage.setCurrentPage(Integer.valueOf(pageCount));
		}
		commentPage.getStartPage();
		commentPage = commentService.selectCommentWithPage(commentPage);

		List<Comment> commentList = commentPage.getResultSet();

		for (Comment comment : commentList) {
			Article art = artcileService.queryById(comment.getArticleId());
			comment.setArticleTitle(art.getTitle());
		}
		commentPage.setResultSet(commentList);
		
		request.setAttribute("pa", commentPage);
		request.setAttribute(ConstantEnum.pageTitle.toString(), "Eleword博客后台——评论管理");

		return "admin/listComment.htm";
	}

}
