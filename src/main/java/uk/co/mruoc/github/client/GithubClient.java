package uk.co.mruoc.github.client;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import java.util.*;

public class GithubClient {

    private final JsonConverter jsonConverter = new JsonConverter();
    private final HttpClient http = new SimpleHttpClient();

    private final String hostUrl;

    private GithubClient(GithubClientBuilder builder) {
        this.hostUrl = builder.hostUrl;
    }

    public List<String> getUserLanguages(String username) {
        User user = getUser(username);
        List<Repo> repos = getRepos(user.getReposUrl());
        List<String> languages = toUniqueLanguages(repos);
        Collections.sort(languages);
        return languages;
    }

    public User getUser(String username) {
        String url = hostUrl + "/users/" + username;
        Response response = http.get(url);
        return jsonConverter.toUser(response.getBody());
    }

    public List<Repo> getRepos(String reposUrl) {
        Response response = http.get(reposUrl);
        return jsonConverter.toRepos(response.getBody());
    }

    public List<String> getLanguages(String languagesUrl) {
        Response response = http.get(languagesUrl);
        Map<String, String> languageMap = jsonConverter.toMap(response.getBody());
        return new ArrayList<>(languageMap.keySet());
    }

    private List<String> toUniqueLanguages(List<Repo> repos) {
        Set<String> languages = new HashSet<>();
        repos.forEach(r -> languages.addAll(getLanguages(r.getLanguagesUrl())));
        return toList(languages);
    }

    private List<String> toList(Set<String> set) {
        return new ArrayList<>(set);
    }

    public static class GithubClientBuilder {

        private static final String DEFAULT_HOST_URL = "https://api.github.com/";
        private String hostUrl = DEFAULT_HOST_URL;

        public GithubClientBuilder setHostUrl(String hostUrl) {
            this.hostUrl = hostUrl;
            return this;
        }

        public GithubClient build() {
            return new GithubClient(this);
        }

    }

}
