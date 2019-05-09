package a2itclient;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe décrivant une liste de d'utilisateurs
 *
 * @author Thierry Baribaud
 * @version 1.02
 */
public class Users {

    /**
     * Liste d'utilisateurs
     */
    private List<User> users;

    /**
     * @return la liste des utilisateurs
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Constructeur principal de la classe
     */
    public Users() {
        users = new ArrayList();
    }
    
    /**
     * Constructeur secondaire de la classe
     * @param user définit un utilisateur
     */
    public Users(User user) {
        this();
        this.addUser(user);
    }

    /**
     * @param users définit une liste d'utilisateurs
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /** 
     * @param user ajoute un utilisateur à la liste
     */
    public void addUser(User user) {
        users.add(user);
    }
    
    /**
     * @return Retourne l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "users:{" 
                + users
                +"}";
    }
}
