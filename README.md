# GitHub Client

This project is a simple demonstration of how to use the [fake-github](https://github.com/michaelruocco/fake-github)
to test client code. This is by no means a complete example but demonstrates the use
of a separate hand coded fake. This can be preferrable to using wiremock to spin up
a mock in test code as it is reusable across multiple projects, it also simplifies the
amount of test and setup code required.

The client provides the ability to read user details, user repos, repo
languages and user languages via the use of the GitHub API. When the main
method of the Main class is run it will print out the list of languages for
the username that is passed into the client as a hardcoded parameter.

The tests test each of the separate calls individually, but make use of the
[fake-github](https://github.com/michaelruocco/fake-github) mock project to
keep test code simple.

## Running the Tests

You can run the tests for this project by running the following command:

```
gradlew clean build
```