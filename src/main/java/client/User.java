package client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("repos_url")
    private String reposUrl;

    @JsonProperty("public_repos")
    private int publicRepos;

    private String owner;

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
