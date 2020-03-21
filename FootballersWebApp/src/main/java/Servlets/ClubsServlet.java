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

public class ClubsServlet extends HttpServlet {

    private ClubsService service = new ClubsServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");


        if(service.addClub(request.getParameter("name"))) {
            try(PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head><title>Клуб добавлен</title></head>");
                out.println("<body>");
                out.println("Клуб добавлен");
                out.println("</body>");
                out.println("</html>");
            }

        } else {

            try(PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head><title>Ошибка при добавлении клуба</title></head>");
                out.println("<body>");
                out.println("Ошибка при добавлении клуба");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()) {

            out.println("<html>");
            out.println("<head><title>Список клубов</title></head>");
            out.println("<body>");
            out.println("<h1>Список клубов</h1>");
            out.println("<table border='1'>");
            out.println("<tr><td>Название клуба</td></tr>");

            for (Club it : service.getAllClubs()) {
                out.println("<tr><td>" + it.getName() + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
