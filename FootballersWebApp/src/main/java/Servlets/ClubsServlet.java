package Servlets;


import Model.Club;
import Services.ClubsService;
import Services.ClubsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClubsServlet extends HttpServlet {

    private ClubsService service = new ClubsServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        ResourceBundle res = ResourceBundle.getBundle("Clubs", "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");


        if(service.addClub(request.getParameter("name"))) {

            showResponsePage(response, res, "success_response");

        } else {

            showResponsePage(response, res, "error_response");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        ResourceBundle res = ResourceBundle.getBundle("Clubs", "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()) {

            out.println("<html>");
            out.println("<head><title>" + res.getString("title") +"</title></head>");
            out.println("<body>");
            out.println("<h1>" + res.getString("title") + "</h1>");
            out.println("<table border='1'>");
            out.println("<tr><td>" + res.getString("name") + "</td></tr>");

            for (Club it : service.getAllClubs()) {
                out.println("<tr><td>" + it.getName() + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");


        if(service.deleteClub(request.getParameter("name"))) {
            try(PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head><title>Клуб удален</title></head>");
                out.println("<body>");
                out.println("Клуб удален");
                out.println("</body>");
                out.println("</html>");
            }

        } else {

            try(PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head><title>Ошибка при удалении клуба</title></head>");
                out.println("<body>");
                out.println("Ошибка при удалении клуба");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    private static void showResponsePage(HttpServletResponse response, ResourceBundle res, String key) throws IOException {
        try(PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>" + res.getString(key) + "</title></head>");
            out.println("<body>");
            out.println(res.getString(key));
            out.println("</body>");
            out.println("</html>");
        }
    }



}
