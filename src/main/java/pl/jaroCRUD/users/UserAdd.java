package pl.jaroCRUD.users;

import pl.jaroCRUD.utils.User;
import pl.jaroCRUD.utils.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/add")
public class UserAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if(!username.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                UserDAO userDAO = new UserDAO();
                User user = new User();
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                userDAO.create(user);
                response.sendRedirect(request.getContextPath()+"/user/list");
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/users/add.jsp")
                .forward(request,response);
    }
}
