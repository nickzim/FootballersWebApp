package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "UsernameServlet")
public class UsernameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("username", URLEncoder.encode(request.getParameter("name"), "UTF-8"));
        response.addCookie(cookie);
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
    }
}
