package controllers.favorites;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Favorite_logs;
import models.Report;
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
        String _token = (String)request.getParameter("_token");
        String report_id = (String)request.getParameter("id");//idを拾う

        if(_token != null && _token.equals(request.getSession().getId())) {
           EntityManager em =DBUtil.createEntityManager();
           Favorite_logs f =new Favorite_logs();

           Report r=em.find(Report.class,Integer.parseInt(request.getParameter("id"))); //文字列を変換 findメソッドで拾う

           f.setEmployee((Employee)request.getSession().getAttribute("login_employee"));//ログインしている人をsetEmployeeにセット
           f.setReport(r);//閲覧中のレポートのデータをsetReportにセット
           f.setFavorite_flag(1);//flagを1に設定

           em.getTransaction().begin();
           em.persist(f);
           em.getTransaction().commit();
           em.close();

           response.sendRedirect(request.getContextPath()+"/reports/show?id="+report_id);
        }
    }
}
