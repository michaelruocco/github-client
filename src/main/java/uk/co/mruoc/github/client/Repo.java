package uk.co.mruoc.github.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo {

    @JsonProperty("languages_url")
    private String languagesUrl;
    private String name;
    private User owner;

    public String getLanguagesUrl() {
        return languagesUrl;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public void setLanguagesUrl(String languagesUrl) {
        this.languagesUrl = languagesUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
