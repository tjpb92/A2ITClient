package a2itclient;

import bkgpi2a.BasicAddress;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant une adresse
 *
 * @author Thierry Baribaud
 * @version 1.13
 */
public class Address {

    private String way;
    private String city;
    private String zip;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String country;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String complement;

    /**
     * Constructeur principal de la classe Address
     */
    public Address() {
    }

    /**
     * Constructeur secondaire de la casse Address
     *
     * @param basicAddress adresse basique
     */
    public Address(BasicAddress basicAddress) {
        StringBuffer thisWay;
        String quality;

        thisWay = new StringBuffer();
        if ((quality = basicAddress.getQuality()) != null) {
            thisWay.append(quality).append(" ");
        }
        thisWay.append(basicAddress.getStreet());
        this.way = thisWay.toString();
        this.zip = basicAddress.getZipCode();
        this.city = basicAddress.getCity();
        this.country = basicAddress.getCountry();
        this.complement = basicAddress.getComplement();
    }

    /**
     * @return the way
     */
    public String getWay() {
        return way;
    }

    /**
     * @param way the way to set
     */
    public void setWay(String way) {
        this.way = way;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the complement
     */
    public String getComplement() {
        return complement;
    }

    /**
     * @param complement the complement to set
     */
    public void setComplement(String complement) {
        this.complement = complement;
    }

    /**
     * @return Retourne l'adresse sous forme textuelle
     */
    @Override
    public String toString() {
        return "address:{"
                + "way:" + way
                + ", city:" + city
                + ", zip" + zip
                + ", country:" + country
                + ", complement:" + complement
                + "}";
    }
}
