# cvs-auto-svc
This is the the project for creating and managing the backend services automated tests

## Getting Started

These instructions will get you up and running with the automation framework.

### Prerequisites

- Browserstack credentials
- Jenkins access
- Java 1.8 and above
- Maven
- Git
- NodeJs
- IntelliJ

### Setting Up

#### Git Hooks

Please set up the following prepush git hook in .git/hooks/pre-push

```
#!/bin/sh
npm run prepush && git log -p | scanrepo

```

##### Security

Please install and run the following securiy programs as part of your testing process:

https://github.com/awslabs/git-secrets

- After installing, do a one-time set up with `git secrets --register-aws`. Run with `git secrets --scan`.

https://github.com/UKHomeOffice/repo-security-scanner

- After installing, run with `git log -p | scanrepo`.

These will be run as part of prepush so please make sure you set up the git hook above so you don't accidentally introduce any new security vulnerabilities.

#### Config File

In the project structure create the file: `cvs-auto-svc/src/main/resources/conf/environment.properties` 

(Do NOT add it to Git versioning !)
The file should contain:

```properties
base.path.url=<test_environment_url>
no.data.base.path.url=<test_environment_url>
s3.bucket="cvs-cert-develop"
branch.name=<test_environment>
microsoftonline.url=https://login.microsoftonline.com/<azure_app_id>/oauth2/authorize?client_id=<azure_client_id>&response_type=id_token&redirect_uri=http://localhost:3000&scope=openid&response_mode=fragment&nonce=678910
microsoftonline.username=<automation_username>
microsoftonline.pass=<automation_password>
microsoftonline.url.v2=https://login.microsoftonline.com/<azure_app_id>/oauth2/v2.0/authorize?client_id=<azure_client_id>&response_type=id_token&scope=openid &redirect_uri=http://localhost:8100&nonce=12345
microsoftonline.username.v2=<automation_username>
microsoftonline.pass.v2=<automation_password>
browserstack.username=<browserstack_username>
browserstack.password=<browserstack_password>
data.location=develop
api.keys=<api_key>
app.token.url=https://login.microsoftonline.com/<azure_app_id>/oauth2/v2.0/token
app.client.id=<app_client_id>
app.scope=<app_scope>
app.client.secret=<app_client_secret>
```

## Running locally

Running can be triggered from IntelliJ or via mvn command in the terminal

## Running in CI

In Jenkins tests may be executed against either a branch or develop:

- [UPDATE__BRANCH](https://jenkins.cvs.dvsacloud.uk/job/UPDATE__BRANCH/job/job_feature_test_backend/)
- [UPDATE__DEVELOP](https://jenkins.cvs.dvsacloud.uk/job/UPDATE__DEVELOP/job/job_develop_test_backend/)

Execution is triggered via "Build with Parameters" job with the options to select the test group from the 'MVN_TAG' dropdown.

A full data reseed is also optional via a checkbox (selected by default)

## Contributors

 - Bogdan Catalin Florea - @bflorea
 - Dragos Panzaru - @dpanzaru
 - Deepika Singh - @deepikasingh