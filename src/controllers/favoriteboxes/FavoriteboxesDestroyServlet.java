package controllers.favoriteboxes;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Favoriteboxes;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FavoriteboxesCreateServlet
 */
@WebServlet("/favoriteboxes/destroy")
public class FavoriteboxesDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteboxesDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");//ログイン中の社員をセット
        String report_id = (String)request.getParameter("id");//idを拾う

        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em =DBUtil.createEntityManager();
            Report r=em.find(Report.class,Integer.parseInt(request.getParameter("id"))); //文字列を変換 findメソッドで拾う

            Favoriteboxes favoriteboxes = em.createNamedQuery("getsomeFavoriteboxes", Favoriteboxes.class)
                    .setParameter("employee",login_employee)    //クエリにセット
                    .setParameter("report",r)                   //クエリにセット
                    .getSingleResult();

            em.getTransaction().begin();
            em.remove(favoriteboxes);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath()+"/reports/show?id="+report_id);
         }
     }
}