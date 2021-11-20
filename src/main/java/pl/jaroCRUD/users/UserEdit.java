package pl.jaroCRUD.users;

import pl.jaroCRUD.utils.User;
import pl.jaroCRUD.utils.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        UserDAO userDAO = new UserDAO();
        userDAO.update(user);
        response.sendRedirect(request.getContextPath() + "/user/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id == null){
            id ="";
        }
        if(!id.isEmpty()){
            UserDAO userDAO = new UserDAO();

            try {

            User user = userDAO.read(Integer.parseInt(id));
            request.setAttribute("user",user);
            } catch (NumberFormatException e){
                request.setAttribute("message", "Nie poprawny ID");
            }
        } else {
            request.setAttribute("message", "ID wartość nie przekazana");

        }
        getServletContext().getRequestDispatcher("/WEB-INF/users/edit.jsp")
                .forward(request,response);
    }
}
