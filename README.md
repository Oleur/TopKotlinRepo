# TopKotlinRepo

This sample application allows to display the top GitHub Kotlin repository (public, no forked).
Using the latest GitHub API v4 with GraphQL and Apollo on the Android side to communicate with the API. 
For dependency injection, Koin is used as the application is still small enough.
For the architecture, high use of the Jetpack libraries (ViewModel, LiveData, Fragment, KTX...) to
ensure a good implementation of Clean Architecture principles.

**UI > ViewModels > UseCases > Repositories**

JodaTime is used from date operations.
OkHttp for making HTTP requests (no need to add Retrofit for what you are doing here)

### Application UI/UX improvements
- Setting up a real design system based on a Material Theme
- Designing reusable components (styles, themes and shape)
- Error handling: displaying relevant message when an error occurred
- Improve UI/UX with meaningful animations (MotionLayout, Animator...)
- Using pagination for lists (lazy loading the data when reaching the end of the page)
- Support for large screens and tablets

### Application architecture improvements
- Use OpenID AppAuth library for handling authentication instead of a hacky WebView
- Find the right navigation system for the application
- Challenging the Clean archi + MVVM once the app is bigger
- Maybe go multi modules

### Known issues
- Scrolling is disabled for pull request RecyclerView (only one RecyclerView should be used with a merge adapter to display the data)

### Other improvements 
- Add pagination with GraphQL using `cursor` and `edge`
- Migration to coroutines `Flow`
- Adding instrumental tests
- Adding more unit tests

### Challenges while developing the application
- Finding the documentation and retrieving exactly what is needed (especially for the Github API)
- Managing OAuth is not trivial as a classic user/password authentication and Android does not offer a set of tools to easily deal with
- Understanding the base principles of GraphQL

### Testing strategy
- Testing first domain and data layers then testing higher layers with instrumental tests to ensure that the app flow is great
- Testing object manipulation and transformation is quite important to ensure that all the required information is available when needed

### Completion
- 90%: more testing is need, oauth needs improvements  
 
