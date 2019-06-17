package a2itclient;

/**
 * Classe décrivant un contrat
 *
 * @author Thierry Baribaud
 * @version 1.26
 */
public class Contract2 {

    private String clientUuid;
    private String assetReference;
    private String callPurposeUuid;
    private String callPurpose;
    private String reference;

    /**
     * Construcuteur principal de la classe
     */
    public Contract2() {
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
     * @return the callPurposeUuid
     */
    public String getCallPurposeUuid() {
        return callPurposeUuid;
    }

    /**
     * @param callPurposeUuid the callPurposeUuid to set
     */
    public void setCallPurposeUuid(String callPurposeUuid) {
        this.callPurposeUuid = callPurposeUuid;
    }

    /**
     * @return the callPurpose
     */
    public String getCallPurpose() {
        return callPurpose;
    }

    /**
     * @param callPurpose the callPurpose to set
     */
    public void setCallPurpose(String callPurpose) {
        this.callPurpose = callPurpose;
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
     * Retourne le contenu du contrat
     *
     * @return retourne le contenu du contrat
     */
    @Override
    public String toString() {
        return "contract2:{"
                + "clientUuid:" + clientUuid
                + ", assetReference:" + assetReference
                + ", callPurposeUuid:" + callPurposeUuid
                + ", callPurpose:" + callPurpose
                + ", reference:" + reference
                + "}";
    }

}
