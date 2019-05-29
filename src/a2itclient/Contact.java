package a2itclient;

/**
 * Classe décrivant un contact
 *
 * @author Thierry Baribaud
 * @version 1.21
 */
public class Contact {

    /**
     * Nom du contact
     */
    private String name;

    /**
     * Qualité du contact
     */
    private String quality;

    /**
     * Rôle du contact
     */
    private String role;

    /**
     * Téléphone du contact
     */
    private String phone;

    /**
     * Email du contact
     */
    private String mail;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quality
     */
    public String getQuality() {
        return quality;
    }

    /**
     * @param quality the quality to set
     */
    public void setQuality(String quality) {
        this.quality = quality;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Retourne le contenu du contact
     *
     * @return retourne le contenu du contact
     */
    @Override
    public String toString() {
        return "contact:{"
                + "name:" + name
                + ", quality:" + quality
                + ", role:" + role
                + ", phone:" + phone
                + ", mail:" + mail
                + "}";
    }

}
