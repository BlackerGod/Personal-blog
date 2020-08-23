package servlet;

import ZMQ.model.Article;
import dao.ArticleDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet("/articleList")
public class ArticleListServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req) throws Exception {
        Integer id = Integer.parseInt(req.getParameter("id"));
        List<Article> articles = new ArticleDAO().listArticle(id);
        return articles;
    }
}
