<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Locale" %>
<%@ page import="javax.servlet.http.* "%>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.net.URLDecoder" %>
<%
  ResourceBundle res = ResourceBundle.getBundle("index", "en".equalsIgnoreCase(request.getParameter("lang")) ? Locale.ENGLISH : Locale.getDefault());
%>

<html>
<head>
  <title><%= res.getString("title") %></title>
</head>
<body>

<%
    Cookie [] c = request.getCookies();
    String username = "незнакомец";
    if(c != null) {
        for (int i = 0; i < c.length; i++) {
            if ("username".equals(c[i].getName())) {
                username = URLDecoder.decode(c[i].getValue(), "UTF-8");
            }
        }
    }
    out.println("<h1><b>" + res.getString("hello") + username + "</b></h1>");
%>

<h1><b> <%= res.getString("players") %></b></h1>

<p><b> <%= res.getString("getplayers") %> </b><br>
  <form>
      <p><input placeholder= <%= res.getString("clubname") %> name="club"></p>
      <p><input type="submit" formaction="/players" value=<%= res.getString("sendrequest") %>></p>
  </form>
</p>

<p><b><%= res.getString("addplayer") %> </b><br>
  <form>
<p><input placeholder=<%= res.getString("playername") %> name="name"></p>
<p><input type = "number" placeholder=<%= res.getString("age") %> name="age"></p>
<p><input placeholder=<%= res.getString("country") %> name="country"></p>
<p><input placeholder=<%= res.getString("position") %> name="position"></p>
<p><input placeholder=<%= res.getString("clubname") %> name="club"></p>
<p><input type="submit" formaction="/players" value=<%= res.getString("sendrequest") %> formmethod="post"></p>
</form>
</p>


<hr>
<h1><b><%= res.getString("clubs") %></b></h1>

<p><b><%= res.getString("getclubs") %></b><br>
  <form>
    <% if (request.getParameter("lang") != null) { %>
      <input type="button" value=<%= res.getString("sendrequest") %> onClick='location.href="http://localhost/clubs?lang=en"'>
    <% } else { %>
      <input type="button" value=<%= res.getString("sendrequest") %> onClick='location.href="http://localhost/clubs"'>
    <% } %>
</form>
</p>

<p><b><%= res.getString("addclub") %></b><br>
  <form>
    <p><input placeholder= <%= res.getString("clubname") %> name="name"></p>
    <p><input type="submit"  formaction="/clubs" value=<%= res.getString("sendrequest") %> formmethod="post"></p>
  </form>
</p>


<hr>
<h1><b><%= res.getString("language") %></b></h1>

<form>
      <% if (request.getParameter("lang") != null) { %>
          <input type="button" value=<%= res.getString("russian") %> onClick='location.href="http://localhost/"'>
      <% } else { %>
         <input type="button" value=<%= res.getString("english") %> onClick='location.href="http://localhost?lang=en"'>
      <% } %>
</form>

<hr>
<h1><b><%= res.getString("changename") %></b></h1>

<form>
    <p><input placeholder= <%= res.getString("playername") %> name="name"></p>
    <p><input type="submit"  formaction="/username" value=<%= res.getString("sendrequest") %>></p>
</form>


</body>
</html>

