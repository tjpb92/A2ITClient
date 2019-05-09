package a2itclient;

import java.util.List;

/**
 * Classe définissant un utilisateur
 *
 * @author Thierry Baribaud
 * @version 1.02
 */
public class User {

    private String id;
    private String domain;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String creationDate;
    private String phone;
    private String mobile;
    private List<Role> entityRoles;
    private List<Role> userRoles;
    private String jid;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
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
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the entityRoles
     */
    public List<Role> getEntityRoles() {
        return entityRoles;
    }

    /**
     * @param entityRoles the entityRoles to set
     */
    public void setEntityRoles(List<Role> entityRoles) {
        this.entityRoles = entityRoles;
    }

    /**
     * @return the userRoles
     */
    public List<Role> getUserRoles() {
        return userRoles;
    }

    /**
     * @param userRoles the userRoles to set
     */
    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    /**
     * @return the jid
     */
    public String getJid() {
        return jid;
    }

    /**
     * @param jid the jid to set
     */
    public void setJid(String jid) {
        this.jid = jid;
    }

    /**
     * Retourne le contenu de l'utilsateur
     *
     * @return retourne le contenu de l'utilisateur
     */
    @Override
    public String toString() {
        return "user:{"
                + "id:" + id
                + "domain:" + domain
                + "email:" + email
                + "username:" + username
                + "firstname:" + firstname
                + "lastname:" + lastname
                + "creationDate:" + creationDate
                + "phone:" + phone
                + "mobile:" + mobile
                + "entityRoles:" + entityRoles
                + "userRoles:" + userRoles
                + "jid:" + jid
                + "}";
    }
}
