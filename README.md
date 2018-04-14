# Android Developer Challenge - Car2Go

<img src="https://i.imgur.com/iMnmyzX.png" width=250>



___
## Why Kotlin?
I chose __Kotlin__ because I think it is more concise and pragmatic than Java. 

I wrote this app in a very smooth way thanks to Kotlin, besides, extensions functions allowed to me to encapsulate recycler Views setup encouraging code reuse in other projects. 

### Extensions Function

I created a directory called [extensions](https://github.com/GianniGM/AndroidDeveloperChallengeCar2Go/blob/master/app/src/main/java/it/giangraziano/androiddeveloperchallengecar2go/extensions/RecyclerViewExtensions.kt) In this case i decided to use StaggeredGridLayout and two extension functions:

* __setColumnsLayout__ : calculate number of columns and apply a StaggeredGridLayoutManager.

* __onScrollToEnd__ : for this project i want to launch a lambda every time I am close to the end of the list, i chose to launch lambda with less than 10 items.

Maybe for the future I can extend __onScrollToEnd__ not just for StaggeredGridLayoutManager but for other LayoutManagers (maybe using Generics).

### Concise Adapters using lambdas
if I wanted to use Android + Java and, in the future, i wanted to decide to make a recyclerview's item clickable I should add an interface onClick, bind views etc..

In this [adapter](https://github.com/GianniGM/AndroidDeveloperChallengeCar2Go/blob/master/app/src/main/java/giangraziano/it/androiddeveloperchallengecar2go/adapters/PhotoListAdapter.kt) i could add a lambdas that could trigger on an onClickListener's item. this means less boilerplates if i want to extends my app functionality.
___
## Libraries
I used several libraries both for network and async stuff

### Network and Async
For the network I could choose to use __Loaders__ or __jobScheduler__, at the end I chose technology I wanted to go deep more: [RxJava](https://github.com/ReactiveX/RxJava).

I already had experience with __Retrofit__ so first requirements was to bind these two libraries, to do this I used retrofit's [RxJava Adapters](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava).

As we now __RxJava__ isn't asynchronous by default so I wanted to observe my task on a main thread as I would have done using a Loader, so to use RxJava on android on its best I used [RxAndroid](https://github.com/ReactiveX/RxAndroid) so I could observe on the main thread

### Picasso
Provides a simple loading images and relative caching If I decided to reload the same image. 

## Tests
I'm not very able to implementing tests, I've never tested rxJava and Retrofit, however this project was a great starting point for me to learn a couple of things about the Test on Android.

Unfortunately in this app I was not able to write a concise test and useful test, During these days I had decided to implement tests using these libraries:

* [Mockito](http://site.mockito.org/) to use mocked data, through a stub that simulates the http call
* __Espresso__ for UI Test,
* __JUnit__ for Business Logic 
* [Roboelectric](http://robolectric.org/) to allow me to test the app without starting a device
___
## Bugs
When I scrollEnd very fast, images loading become a little slow
___
## Improvements
Open a second Activity with details when I tap on selected Image
___
## Note
to implement this app in particular because it stimulated me to investigate more about all the test stuff.