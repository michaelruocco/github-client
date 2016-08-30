package client;

import uk.co.tpplc.http.HttpClient;
import uk.co.tpplc.http.Response;
import uk.co.tpplc.http.SimpleHttpClient;

import java.util.*;

public class GithubClient {

    private static final String DEFAULT_HOST_URL = "https://api.github.com/";
    private final JsonConverter jsonConverter = new JsonConverter();

    private final String hostUrl;
    private final HttpClient http = new SimpleHttpClient();

    public GithubClient() {
        this(DEFAULT_HOST_URL);
    }

    public GithubClient(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public List<String> getUserLanguages(String username) {
        User user = getUser(username);
        List<Repo> repos = getRepos(user.getReposUrl());
        Set<String> languages = new HashSet<>();
        for (Repo repo : repos)
            languages.addAll(getLanguages(repo.getLanguagesUrl()));
        return new ArrayList<>(languages);
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

}
