package a2itclient;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.StructuredName;

/**
 * Classe décrivant un contact
 *
 * @author Thierry Baribaud
 * @version 1.22
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
     * Retourne le contact au format vCard Intent Technologies reconnait la
     * version 3 et certains champs
     *
     * @see
     * https://developers.intent-technologies.eu/docs/actifs-immobiliers#section-contacts-vcard
     */
    public String toVCard() {
        VCard vcard;
//        StructuredName structuredName;
        String value;
        String aQuality;
        StringBuffer aRole;

        vcard = new VCard();

//        structuredName = new StructuredName();
//        structuredName.setFamily(name);
//        vcard.setStructuredName(structuredName);
        vcard.setFormattedName(name);
        if ((value = phone) != null) {
            vcard.addTelephoneNumber(value, TelephoneType.VOICE);
        }
        if ((value = mail) != null) {
            vcard.addEmail(value, EmailType.INTERNET);
        }
        aRole = new StringBuffer();
        aQuality = null;
        if ((aQuality = quality) != null) {
            aRole.append(aQuality);
        }
        if ((value = role) != null) {
            if (aQuality != null) {
//                aRole.append(", ");
                aRole.append(" - ");
            }
            aRole.append(value);
        }
        if (aRole.length()>0) {
            vcard.addRole(aRole.toString());
        }
        return (Ezvcard.write(vcard).version(VCardVersion.V3_0).prodId(false).go());

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
