package pl.jaroCRUD.users;

import pl.jaroCRUD.utils.User;
import pl.jaroCRUD.utils.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/delete")
public class UserDelete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(!id.isEmpty()){

            try {
                UserDAO userDAO = new UserDAO();
                userDAO.delete(Integer.parseInt(id));
                response.sendRedirect(request.getContextPath()+"/user/list");
            } catch (NumberFormatException e){
                request.setAttribute("message", "Nie poprawny ID");
            }

        } else {
            id ="";
            response.sendRedirect(request.getContextPath()+"/user/list");

        }
    }

}
