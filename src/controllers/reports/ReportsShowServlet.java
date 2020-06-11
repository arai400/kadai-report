package controllers.reports;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em= DBUtil.createEntityManager();
        Report r=em.find(Report.class,Integer.parseInt(request.getParameter("id"))); //文字列を変換 findメソッドで拾う

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");//ログイン中の社員をセット

        List<Favoriteboxes> favoriteboxes = em.createNamedQuery("getsomeFavoriteboxes", Favoriteboxes.class)
                .setParameter("employee",login_employee)    //クエリにセット
                .setParameter("report",r)                   //クエリにセット
                .getResultList();

        long reportcnt = (long)em.createNamedQuery("getFavoriteboxesCount", Long.class)
                .setParameter("report",r)
                .getSingleResult();
         em.close();

         request.setAttribute("report",r);
         request.setAttribute("favoriteboxes",favoriteboxes);   //show.jspに情報を渡す
         request.setAttribute("reportcnt",reportcnt);
         request.setAttribute("_token",request.getSession().getId());

         RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
         rd.forward(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
