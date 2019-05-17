package a2itclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe décrivant une raison d'appel modélisée dans la base MongoDb
 *
 * @author Thierry Baribaud
 * @version 1.12
 */
@JsonIgnoreProperties({"_id"})
public class CallPurpose {

    private String clientUuid;
    private String uuid;
    private int extId;
    private int code;
    private String name;
    private String useApi;
    private String reference;

    /**
     * Constructeur principal de la classe CallPurpose
     */
    public CallPurpose() {
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
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the extId
     */
    public int getExtId() {
        return extId;
    }

    /**
     * @param extId the extId to set
     */
    public void setExtId(int extId) {
        this.extId = extId;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
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
     * @return Retourne le client sous forme textuelle
     */
    @Override
    public String toString() {
        return "client:{"
                + "clientUuid:" + clientUuid
                + ", uuid:" + uuid
                + ", extId:" + extId
                + ", code:" + getCode()
                + ", name" + name
                + ", useApi:" + useApi
                + ", reference:" + reference
                + "}";
    }

}
