package controllers.favorites;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Favorite_logs;
import utils.DBUtil;

/**
 * Servlet implementation class FavoritesCreateServlet
 */
@WebServlet("/favorites/create")
public class FavoritesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoritesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

           EntityManager em =DBUtil.createEntityManager();
           Favorite_logs f =new Favorite_logs();

           // 全件数を取得
           long favoritelogs_count = (long)em.createNamedQuery("getFavorite_logsCount", Long.class)
                                         .getSingleResult();

           if(favoritelogs_count==1){
               //レコードが存在するためいいねカウント減少
           }
           else{
               //レコードが存在しないためいいねカウント増加
           }

           RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/views/employees/show.jsp");
           rd.forward(request,response);

           em.getTransaction().begin();
           em.persist(f);
           em.getTransaction().commit();
           em.close();

           response.sendRedirect(request.getContextPath()+"/reports/index");
        }
    }
