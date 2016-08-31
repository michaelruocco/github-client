package uk.co.mruoc.github.client;

import uk.co.mruoc.github.client.GithubClient;
import uk.co.mruoc.github.client.GithubClient.GithubClientBuilder;
import org.junit.Rule;
import org.junit.Test;
import uk.co.mruoc.fake.github.FakeGithubRule;
import uk.co.mruoc.fake.github.FakeGithubRule.FakeGithubRuleBuilder;
import uk.co.mruoc.github.client.Repo;
import uk.co.mruoc.github.client.User;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GithubClientTest {

    private static final int PORT = 8099;
    private static final String URL = "http://localhost:" + PORT;

    private static final String USER_NAME = "hackeryou";
    private static final String USER_REPOS_URL = URL + "/users/HackerYou/repos";
    private static final String USER_AMAZON_REPO_LANGUAGES_URL = URL + "/repos/HackerYou/amazon/languages";

    @Rule
    public final FakeGithubRule githubRule = new FakeGithubRuleBuilder()
            .setPort(PORT)
            .setResponseHostUrl(URL)
            .build();

    private final GithubClient client = new GithubClientBuilder()
            .setHostUrl(URL)
            .build();

    @Test
    public void shouldReturnUser() {
        User user = client.getUser(USER_NAME);

        assertThat(user).isEqualToComparingFieldByField(new TestUser());
    }

    @Test
    public void shouldReturnUserRepos() {
        List<Repo> repos = client.getRepos(USER_REPOS_URL);

        assertThat(repos.size()).isEqualTo(30);
        assertThat(repos.get(0)).isEqualToComparingFieldByFieldRecursively(new TestRepo1());
        assertThat(repos.get(1)).isEqualToComparingFieldByFieldRecursively(new TestRepo2());
    }

    @Test
    public void shouldReturnRepoLanguages() {
        List<String> languages = client.getLanguages(USER_AMAZON_REPO_LANGUAGES_URL);

        assertThat(languages.size()).isEqualTo(4);
        assertThat(languages.get(0)).isEqualTo("Ruby");
        assertThat(languages.get(1)).isEqualTo("CSS");
        assertThat(languages.get(2)).isEqualTo("JavaScript");
        assertThat(languages.get(3)).isEqualTo("CoffeeScript");
    }

    @Test
    public void shouldReturnUserLanguages() {
        List<String> languages = client.getUserLanguages(USER_NAME);

        assertThat(languages.size()).isEqualTo(5);
        assertThat(languages.get(0)).isEqualTo("CSS");
        assertThat(languages.get(1)).isEqualTo("CoffeeScript");
        assertThat(languages.get(2)).isEqualTo("HTML");
        assertThat(languages.get(3)).isEqualTo("JavaScript");
        assertThat(languages.get(4)).isEqualTo("Ruby");
    }

    private static class TestUser extends User {

        private TestUser() {
            setLogin(USER_NAME);
            setPublicRepos(35);
            setReposUrl(USER_REPOS_URL);
        }

    }

    private static class TestRepo1 extends Repo {

        private TestRepo1() {
            User user = new User();
            user.setLogin(USER_NAME);
            user.setReposUrl(USER_REPOS_URL);
            setOwner(user);
            setName("amazon");
            setLanguagesUrl(USER_AMAZON_REPO_LANGUAGES_URL);
        }

    }

    private static class TestRepo2 extends Repo {

        private TestRepo2() {
            User user = new User();
            user.setLogin(USER_NAME);
            user.setReposUrl(USER_REPOS_URL);
            setOwner(user);
            setName("assigner");
            setLanguagesUrl(URL + "/repos/HackerYou/assigner/languages");
        }

    }

}
