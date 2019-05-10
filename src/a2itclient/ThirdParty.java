package a2itclient;

/**
 * Classe définissant un tier
 *
 * @author Thierry Baribaud
 * @version 1.05
 */
public class ThirdParty {

    /**
     * Entité
     */
    private Owner entity;

    /**
     * Contacts du tier
     */
    private String contacts;

    /**
     * Référence
     */
    private String reference;

    /**
     * @return Retourne l'entité
     */
    public Owner getEntity() {
        return entity;
    }

    /**
     * @param entity Définit l'entité
     */
    public void setEntity(Owner entity) {
        this.entity = entity;
    }

    /**
     * @return Retourne la référence
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference Définit la référence
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return les contacts du tier
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * @param contacts définit les contacts du tier
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * Retourne le contenu du tier
     *
     * @return retourne le contenu du tier
     */
    @Override
    public String toString() {
        return "thirdParty:{"
                + "entity:" + entity
                + ", contacts:" + contacts
                + ", reference:" + reference
                + "}";
    }
}
