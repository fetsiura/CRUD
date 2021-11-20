package pl.jaroCRUD.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDAO {


    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    private static final String READ_USER_QUERY =
            "select * from users where id = ?";

    private static final String UPDATE_USER_QUERY =
            "update users set username =?, email=?, password=? where id=?";

    private static final String DELETE_USER_QUERY =
            "delete from users where id = ?";

    private static final String FindAll_USER_QUERY =
            "select * from users";


    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public User create(User user){

        try(Connection connection = DbUtil.getConnection()){

            PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                user.setId(resultSet.getInt(1));
            }
            System.out.println("user o id "+user.getId()+" created");
            return user;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public User read (int id){

        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user){

        try ( Connection  connection = DbUtil.getConnection()){

            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getEmail());
            statement.setString(3,this.hashPassword(user.getPassword()));
            statement.setInt(4,user.getId());
            statement.executeUpdate();
            System.out.println("User o id "+user.getId()+ " updated");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete (int id){

        try(Connection connection = DbUtil.getConnection()){

            PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1,id);
            statement.executeUpdate();
            System.out.println("user o id "+id+" deleted");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User[] findAll (){

        try ( Connection connection = DbUtil.getConnection()){
            User [] users = new User[0];

            PreparedStatement statement = connection.prepareStatement(FindAll_USER_QUERY);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                User user= new User();
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getInt("id"));
                users = addToArray(user,users);
            }
            return users;

        }catch (SQLException e){
            e.printStackTrace();

        }
        return null;
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }
}
