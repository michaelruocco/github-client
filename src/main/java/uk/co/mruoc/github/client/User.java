package uk.co.mruoc.github.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("repos_url")
    private String reposUrl;

    @JsonProperty("public_repos")
    private int publicRepos;

    private String login;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String owner) {
        this.login = login;
    }

}
