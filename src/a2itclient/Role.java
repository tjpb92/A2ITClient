package a2itclient;

/**
 * Classe définissant un rôle pour un utilisateur
 *
 * @author Thierry Baribaud
 * @version 1.02
 */
public class Role {

    /**
     * Nom du rôle
     */
    private String name;

    /**
     * @return retourne le nom du rôle
     */
    public String getName() {
        return name;
    }

    /**
     * @param name définit le rôle
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne le contenu du rôle
     *
     * @return retourne le contenu du rôle
     */
    @Override
    public String toString() {
        return "role:{"
                + "name:" + name
                + "}";
    }
}
