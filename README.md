# Android Developer Challenge - Car2Go

<img src="https://i.imgur.com/iMnmyzX.png" width=250>



___
## Why Kotlin?
I chose __Kotlin__ because it is more concise and pragmatic than Java, in fact I wrote this app in a very smooth way, besides, extensions functions allowed to me to encapsulate recycler Views setup encouraging code reuse in other projects. 

### Extensions Function

Into directory [extensions](https://github.com/GianniGM/AndroidDeveloperChallengeCar2Go/blob/master/app/src/main/java/it/giangraziano/androiddeveloperchallengecar2go/extensions/RecyclerViewExtensions.kt) is located all my kotlin extension functions. In this case i decided to use two functions witch extends StaggeredGridLayout class:

* __setColumnsLayout__ : calculate number of columns in base of device dimensions and apply a StaggeredGridLayoutManager witch those settings.

* __onScrollToEnd__ : for this project i want to launch a lambda every time I am close to the end of the list, i chose to launch the lambda with less than 10 items.

Future implementation: generalize __onScrollToEnd__ for all the other LayoutManagers (maybe using the Generics).

### Concise Adapters using lambdas
If I wanted to use Android + Java and, in the future, i wanted to decide to make a recyclerview item clickable I should have add an interface onClick inside my adapter ed bind this into my activity but using kotlin this is possible
simply adding a lambda in [this adapter](https://github.com/GianniGM/AndroidDeveloperChallengeCar2Go/blob/master/app/src/main/java/giangraziano/it/androiddeveloperchallengecar2go/adapters/PhotoListAdapter.kt) without interfaces or other boilerplates
___
## Libraries
I used several libraries both for network and async stuff:

### Network and Async
For the network I could choose to use __Loaders__ or __jobScheduler__, at the end I chose technology I wanted to go deep more: [RxJava](https://github.com/ReactiveX/RxJava).

I already had experience with __Retrofit__ so first requirements was to bind these two libraries and to do this I used retrofit's [RxJava Adapters](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava).

As we know __RxJava__ isn't asynchronous by default so I wanted to observe my task on a main thread as I would have done using a Loader, so to use RxJava on android on its best I used [RxAndroid](https://github.com/ReactiveX/RxAndroid) so I could observe on the main thread

### Picasso
For image loading I used latest version [Picasso](http://square.github.io/picasso/) Provides a simple loading images and relative caching.

Other good alternative was Glide but for now I'm more confident using Picasso.
___
## Tests
I'm not very able to implementing tests (for now), I've never tested rxJava and Retrofit (always for now), however this project was a great starting point for me to learn a couple of things about Tests on Android.

### Unit Tests
In this app there's a little business logic, I used __JUnit__ to test a pair functionalities.
[Here](https://github.com/GianniGM/AndroidDeveloperChallengeCar2Go/blob/master/app/src/test/java/it/giangraziano/androiddeveloperchallengecar2go/EndlessScrollTest.kt) I wrote simply two tests that check if my [pageNumber](https://github.com/GianniGM/AndroidDeveloperChallengeCar2Go/blob/master/app/src/main/java/it/giangraziano/androiddeveloperchallengecar2go/network/NetworkLogic.kt) will be incremented after an api request. I mocked my request using [Mockito](http://site.mockito.org/) to simulate requests to server and __TestObserver__ to simulate observers.

### UI Tests
Unfortunately in this app I was not able to write a concise and useful UI test, but if I had  decided to implement tests i would have use these libraries:

* __Mockito__ to use mocked data, through a stub that simulates the http call
* __Espresso__ for UI Tests,
* [Roboelectric](http://robolectric.org/) to allow me to test the app without start my device app without starting my device whether it's an emulator or a physical device
___
## Misc
### Bugs
* When I scroll my app to end very fast, images loading become a little slow
* Image taken for placeholder is not scaled very well with loaded images.

### Improvements
Open a second Activity with details when I tap on selected Image

### Note
Writing this app has stimulated me a lot, in particular pushed to me to investigate more about all the test stuff.
I realize that there's really a lot of things to learn about Android tests.