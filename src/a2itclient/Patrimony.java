package a2itclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe décrivant un patrimoine
 * @author Thierry Baribaud
 * @version 1.12
 */
@JsonIgnoreProperties({"_id"})
public class Patrimony {
    
    private String clientUuid;
    private String name;
    private String useApi;
    private String reference;
    
    /**
     * Constructeur principal de la classe Patrimony
     */
    public Patrimony() {
    }

    /**
     * @return the clientUuid
     */
    public String getClientUuid() {
        return clientUuid;
    }

    /**
     * @param clientUuid the clientUuid to set
     */
    public void setClientUuid(String clientUuid) {
        this.clientUuid = clientUuid;
    }

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
     * @return the useApi
     */
    public String getUseApi() {
        return useApi;
    }

    /**
     * @param useApi the useApi to set
     */
    public void setUseApi(String useApi) {
        this.useApi = useApi;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    /**
     * @return Retourne le patrimoine sous forme textuelle
     */
    @Override
    public String toString() {
        return "patrimony:{"
                + "clientUuid:" + clientUuid
                + ", name" + name
                + ", useApi:" + useApi
                + ", reference:" + reference
                + "}";
    }
    
}
