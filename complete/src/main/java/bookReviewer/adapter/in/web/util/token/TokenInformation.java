package bookReviewer.adapter.in.web.util.token;

import javafx.util.Pair;

import java.util.Date;
import java.util.HashMap;

public class TokenInformation {
    private Date issuedAt;
    private HashMap customFields;
    private Date expirationDate;

    public TokenInformation() {
        this.customFields = new HashMap();
    }

    public HashMap getCustomFields() {
        return customFields;
    }

    public Object getCustomFieldValue(String key) {
        return customFields.get(key);
    }

    public void addCustomField(String key, Object value){
        this.customFields.put(key, value);
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
