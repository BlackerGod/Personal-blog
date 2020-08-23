package servlet;

import ZMQ.model.Article;
import dao.ArticleDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/articleDetail")
public class AricleDetailServlet extends BaseServlet{

    @Override
    public Object process(HttpServletRequest req) throws Exception {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Article article = ArticleDAO.queryArticleById(id);
        return article;
    }
}
