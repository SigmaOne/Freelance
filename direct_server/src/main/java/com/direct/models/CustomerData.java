package com.direct.models;

import javax.persistence.*;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "customer_data")
public class CustomerData {
    private @Id @GeneratedValue Long id;
    private Date creationDate;
    private String phrase;
    private String website;
    private String regions;
    private String averagePrice; // optional
    private String marginality;
    private String closeConversation;
    private String websiteConversation;
    private String plannedProfit;

    private CustomerData() {}

    public CustomerData(String phrase, String website, String regions, String marginality, String closeConversation, String websiteConversation, String plannedProfit, String averagePrice) {
        this.creationDate = new Date();

        this.phrase = phrase;
        this.website = website;
        this.regions = regions;
        this.marginality = marginality;
        this.closeConversation = closeConversation;
        this.websiteConversation = websiteConversation;
        this.plannedProfit = plannedProfit;
        this.averagePrice = averagePrice;
    }

    public boolean isValid() {
        // There's no averagePrice, coz it's optional
        return phrase != null && website != null && regions != null && marginality != null &&
                closeConversation != null && websiteConversation != null && plannedProfit != null;
    }
}
