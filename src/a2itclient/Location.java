package a2itclient;

/**
 * Classe définissant une position (location in english)
 * @author Thierry Baribaud
 * @version 1.11
 */
public class Location {
    
    private String assetReference;
    private Address address;
//    private Geo geo;
    
    /**
     * Constructeur principal de la classe Location
     */
    public Location() {
        
    }

    /**
     * @return the assetReference
     */
    public String getAssetReference() {
        return assetReference;
    }

    /**
     * @param assetReference the assetReference to set
     */
    public void setAssetReference(String assetReference) {
        this.assetReference = assetReference;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    
    /**
     * @return Retourne la position sous forme textuelle
     */
    @Override
    public String toString() {
        return "location:{"
                + "assetRefence:" + assetReference
                + ", address:" + address
//                + ", geo" + geo
                + "}";
    }
    
}
