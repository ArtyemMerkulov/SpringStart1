package ru.geekbrains;

import ru.geekbrains.persist.Repository;
import ru.geekbrains.persist.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/users")
public class UsersHttpServlet extends HttpServlet {

    private static Repository<User> userRepository; // Следует ли делать вот так: Repository<User>?

    @Override
    public void init() throws ServletException {
        userRepository = (Repository<User>) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        List<User> userList = userRepository.findAll();

        pw.println("<table>");
        pw.println("<tr>");
        pw.println("<th>Id</th><th>Username</th>");
        pw.println("</tr>");

        userList.forEach(user -> pw.println("<tr><td>Id: " + user.getId() +
                "</td><td>Username: " + user.getUsername() + "</td></tr>"));

        pw.println("</table>");

        pw.close();
    }
}
