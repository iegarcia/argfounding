# NO-COUNTRY CROWDFOUNDING PROJECT CODE GUIDE 

### PROJECT SETUP & TOOLS
1. JDK 17
2. [MySQL](https://dev.mysql.com/doc/refman/8.0/en/installing.html).
3. [Postman](https://www.postman.com/downloads/) for testing endpoints.

### CODE STANDARDS
- Code must be in English.
- The controllers should finish with suffix "Controller". Example: UserController.
- The services should finish with suffix "Service". Example: UserService.
- The repositories should finish with suffix "Repository". Example: UserRepository.
- The interfaces should start with prefix "I". Example: IUserRepository.
- The implementations should finish with suffix "Impl". Example: UserServiceImpl.
- The DTOs should finish with suffix "Dto". Example: UserDto, UserRequestDto.
- Usage of DTOs is a must. Can have DTOs for request and response.
- Package names are in singular.
- The names of attributes/fields from Java classes must be written using camel case. Example: firstName.
- The name of columns in the entities must be written using underscore and uppercase. Example: FIRST_NAME. The name of the tables is always in plural, but the entity name should be in singular. 
- If you add a new endpoint, make sure to set the role access for it in the SecurityConfig class.

### GIT STANDARDS

#### FORMAT
- Always create the branch from develop
- The branch name format is: `feature/{trelloTask#}`.
- The pull request title format is: `{trelloTask#}: {taskTitle}`.
- The commits format is: `{trelloTask#}: {commitDescription}`. Small commits are a nice to have.
- The pull request has to contain only the changes related to the scope defined in the ticket.
- Pull request should always be from your current branch to develop.

#### BRANCHES

- `master` -> this branch is only for productive versions, it has official release history.
- `develop` -> this branch serves as an integration branch for features. All features must start from this branch and after it's finished it gets merged back into develop.

For understanding more about git and how to work with different branches, I recommend to read about Gitflow workflow. [Here](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow) you have a little explanation that can serve as introduction.

