package client;

import uk.co.tpplc.http.SimpleHttpClient;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        GithubClient githubClient = new GithubClient(new SimpleHttpClient());
        List<String> languages = githubClient.getUserLanguages("hackeryou");
        languages.stream().forEach(e -> System.out.println(e));
    }
}
