package uk.co.mruoc.github.client;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        GithubClient githubClient = new GithubClient.GithubClientBuilder().build();
        List<String> languages = githubClient.getUserLanguages("michaelruocco");
        languages.stream().forEach(e -> System.out.println(e));
    }

}
