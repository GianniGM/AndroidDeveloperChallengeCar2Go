# Android Developer Challenge - Car2Go

<img src="https://raw.githubusercontent.com/GianniGM/AndroidDeveloperChallengeCar2Go/master/sample_image.jpg" width=250>

___
## Why Kotlin?
I chose __Kotlin__ because I think it is more concise and pragmatic than Java. 

I wrote this app in a very smooth way thanks to Kotlin, besides, extensions functions allowed to me to encapsulate recycler Views function encouraging code reuse in other projects (we always use RecylerViews) 

### Extensions Function

I created a directory called [extensions](https://github.com/GianniGM/AndroidDeveloperChallengeCar2Go/blob/master/app/src/main/java/giangraziano/it/androiddeveloperchallengecar2go/extensions/RecyclerViewExtensions.kt) I use always GridLayoutManager so I used two functions for it: 
* __setColumnsLayout__ : calculate number of columns and apply a GridLayoutManager.

* __onScrollToEnd__ : for this project i want to launch a lambda every time I am close to the end of the list, i chose to launch lambda with less than 10 items.

Maybe for the future I can extends __onScrollToEnd__ not only for GridLayoutManager (maybe using Generics).

### Concise Adapters using lambdas
Using Android + Java if i want to make a recyclerview's item clickable i want to add an interface onClick, binding this etc..

In this [adapter](https://github.com/GianniGM/AndroidDeveloperChallengeCar2Go/blob/master/app/src/main/java/giangraziano/it/androiddeveloperchallengecar2go/adapters/PhotoListAdapter.kt) i could add a lambdas that could trigger on an onClickListener's item. this means less boilerplates if i want to extends my app functionality.
___
## Libraries
I used several libraries both for network and async stuff

### Network and Async
For the network I could choose to use __Loaders__ or __jobScheduler__, at the end I chose technology I wanted to go deep more: __RxJava__.

I already had experience with __Retrofit__ so first requirements was to bind these two libraries, to do this I use retrofit's __RxJava Adapters__.

As we now __RxJava__ isn't asynchronous by default so I wanted to observe my task on a main thread as I would have done using a Loader, so to do use RxJava on android on its best i use __RxAndroid__ so I could observe on the main thread

### Picasso
Provides to me simple loading images and relative caching

## Tests
___
## Bugs
No bugs founded (yet)
___
## Improvements
Adding a second screen opening on tappig in selected image showed in my recyclerview
___
## Note