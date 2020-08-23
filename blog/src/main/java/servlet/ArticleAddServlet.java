package servlet;

import ZMQ.model.Article;

import ZMQ.utli.JSONUtil;
import dao.ArticleDAO;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;


@WebServlet("/articleAdd")
public class ArticleAddServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req) throws Exception {
        Article article = JSONUtil.deserialize(req.getInputStream(),Article.class);
        article.setUserId(1);
        System.out.println("请求数据"+article);
        if(!ArticleDAO.addArticle(article)){
            throw new RuntimeException("文章添加失败");
        }
        return article;
    }
}
