package Servlets;

import Model.Player;
import Services.PlayersService;
import Services.PlayersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

public class PlayersServlet extends javax.servlet.http.HttpServlet {

    private PlayersService service = new PlayersServiceImpl();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String lang = request.getParameter("lang");
        ResourceBundle res = ResourceBundle.getBundle("Players", "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");


        if(service.addPlayer(request.getParameter("name"),
                          Integer.parseInt(request.getParameter("age")),
                          request.getParameter("country"),
                          request.getParameter("position"),
                          request.getParameter("club")))
        {
            try(PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head><title>" + res.getString("success_responce") + "</title></head>");
                out.println("<body>");
                out.println(res.getString("success_responce"));
                out.println("</body>");
                out.println("</html>");
            }

        } else {

            try(PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head><title>" + res.getString("error_responce") + "</title></head>");
                out.println("<body>");
                out.println(res.getString("error_responce"));
                out.println("</body>");
                out.println("</html>");
            }
        }

    }


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String lang = request.getParameter("lang");
        ResourceBundle res = ResourceBundle.getBundle("Players", "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");


        try(PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>" + res.getString("title") + "</title></head>");
            out.println("<body>");
            out.println("<h1>" + res.getString("title") + " " + request.getParameter("club") + " </h1>");
            out.println("<table border='1'>");
            out.println("<tr><td>" + res.getString("name") + "</td><td>" +
                                     res.getString("age") + "</td><td>" +
                                     res.getString("country") + "</td><td>" +
                                     res.getString("position") + "</td></tr>");

            for (Player it : service.getClubPlayersList(request.getParameter("club"))) {
                out.println("<tr><td>" + it.getName() +
                            "</td><td>" + it.getAge() +
                            "</td><td>" + it.getCountry() +
                            "</td><td>" + it.getPosition() + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        ResourceBundle res = ResourceBundle.getBundle("Players", "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");


        if(service.deletePlayer(request.getParameter("name")))
        {
            try(PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head><title>" + "Игрок удален" + "</title></head>");
                out.println("<body>");
                out.println("Игрок удален");
                out.println("</body>");
                out.println("</html>");
            }

        } else {

            try(PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head><title>" + "Ощибка при удалении игрока" + "</title></head>");
                out.println("<body>");
                out.println("Ощибка при удалении игрока");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

}
