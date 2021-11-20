package pl.jaroCRUD.users;

import pl.jaroCRUD.utils.User;
import pl.jaroCRUD.utils.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/list")
public class UserList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        request.setAttribute("users", userDAO.findAll());

        getServletContext().getRequestDispatcher("/WEB-INF/users/list.jsp")
                .forward(request, response);
    }
}
