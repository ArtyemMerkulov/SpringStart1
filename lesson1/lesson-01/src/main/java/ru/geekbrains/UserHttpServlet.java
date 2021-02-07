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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/user/*")
public class UserHttpServlet extends HttpServlet {

    private static final Pattern PARAM_PATTERN = Pattern.compile("/(\\d+)");

    private static Repository<User> userRepository; // Следует ли делать вот так: Repository<User>?

    @Override
    public void init() throws ServletException {
        userRepository = (Repository<User>) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());

        if (matcher.find()) {
            Long index = Long.parseLong(matcher.group(1));
            User user = userRepository.findById(index);

            if (user != null) {
                PrintWriter pw = resp.getWriter();

                pw.println("<table>");
                pw.println("<tr>");
                pw.println("<th>Id</th><th>Username</th>");
                pw.println("</tr>");
                pw.println("<tr><td>Id: " + user.getId() + "</td><td>Username: " + user.getUsername() + "</td></tr>");
                pw.println("</table>");

                pw.close();
            } else {
                resp.setStatus(400);
                resp.getWriter().println("<h1>Bad Request</h1>");
            }
        }
    }
}
