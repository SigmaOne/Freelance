package com.direct.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class CustomerData {
    private @Id @GeneratedValue Long id;
    private Date created_at;
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
        this.created_at = new Date();

        this.phrase = phrase;
        this.website = website;
        this.regions = regions;
        this.marginality = marginality;
        this.closeConversation = closeConversation;
        this.websiteConversation = websiteConversation;
        this.plannedProfit = plannedProfit;
        this.averagePrice = averagePrice;
    }

    public void setCreationDate(Date date) {
        this.created_at = date;
    }
}
