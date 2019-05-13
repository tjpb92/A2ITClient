package a2itclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe définissant un Asset
 * @author Thierry Baribaud
 * @version 1.06
 */
public class Asset {
    
    /**
     * Catégorie de l'asset
     */
    private String category;
    
    /**
     * Code de l'asset
     */
    private String code;
    
    /**
     * Chemin complet de l'asset
     */
    private String fullPath;
    
    /**
     * Label de l'asset
     */
    private String label;
    
    /**
     * Liste des tags de l'asset
     */
    private Map<String, String> tags;
    
    /**
     * Type de l'asset
     */
    private String type;
    
    /**
     * Contrat(s) de l'asset
     */
    private List<String> contracts;
    
    /**
     * Liens de l'asset
     */
    @JsonProperty("_links")
    private Map<String, HALLink> links;

    /**
     * Dernière date de mise à jour
     */
    private String lastUpdateDate;
    
    /**
     * Date de création 
     */
    private String creationDate;
    
    /**
     * Chemin du parent
     */
    private String parentPath;
    
    /**
     * Entité propriétaire
     */
    private Entity owner;

    /**
     * Constructeur principal de la classe Asset
     */
    public Asset (){
        tags = new HashMap<>();
        contracts = new ArrayList();
        links = new HashMap<>();
    }
    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return Retourne les liens de l'asset
     */
    public Map<String, HALLink> getLinks() {
        return links;
    }

    /**
     * @param links Définit les liens de l'asset
     */
    public void setLinks(Map<String, HALLink> links) {
        this.links = links;
    }

    /**
     * @return retourne l'entité propriétaire
     */
    public Entity getOwner() {
        return owner;
    }

    /**
     * @param owner définit l'entité propriétaire
     */
    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the fullPath
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * @param fullPath the fullPath to set
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the tags
     */
    public Map<String, String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the contracts
     */
    public List<String> getContracts() {
        return contracts;
    }

    /**
     * @param contracts the contracts to set
     */
    public void setContracts(List<String> contracts) {
        this.contracts = contracts;
    }

    /**
     * @return Retourne la date de dernière mise à jour
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param lastUpdateDate Définit la date de dernière mise à jour
     */
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * @return Retourne la date de création
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate Définit la date de création
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the parentPath
     */
    public String getParentPath() {
        return parentPath;
    }

    /**
     * @param parentPath the parentPath to set
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
    
}
