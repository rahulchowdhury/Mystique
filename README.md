[ ![Download](https://api.bintray.com/packages/rahulchowdhury/maven/mystique/images/download.svg) ](https://bintray.com/rahulchowdhury/maven/mystique/_latestVersion) [![Kotlin](https://img.shields.io/badge/Kotlin-1.1.2-green.svg)](http://kotlinlang.org/) [![API](https://img.shields.io/badge/API-19%2B-red.svg?style=flat)](https://android-arsenal.com/api?level=19) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

<img src="https://github.com/rahulchowdhury/Mystique/blob/master/docs/Mystique.png" width="300px" />

Mystique is a [Kotlin](http://kotlinlang.org/) library for Android’s `RecyclerView` which allows you to create homogeneous and heterogeneous lists effortlessly using an universal adapter. It’s `RecyclerView.Adapter` on steroids, written purely in Kotlin (_oh yeah, with extension functions too_).

# Download
You can download this library through **jCenter** by one of the following ways,

## Gradle
Add this line to your `build.gradle` file under the `dependencies` section along with any other library that you might use.

```groovy
dependencies {
	compile 'co.upcurve.mystique:mystique:1.0'
}
```

## Maven
If you prefer to use Maven, then add the following lines,

```xml
<dependency>
  <groupId>co.upcurve.mystique</groupId>
  <artifactId>mystique</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```

# Usage
Using this library is fairly simple. You need to create a data class which will be the model for your row data, and a presenter or item which extends `MystiqueItemPresenter`

## Creating the model class
The model class is a regular data class which holds all the data that needs to be displayed in a single row inside the list, with however one special addition. 

You need to annotate the presenter that will be used with this model class for Mystique to correctly recognize the presenter for this model class and bind both of them without any effort from your side.

### NewsModel.kt

```kotlin
//Specify your presenter here in this format
@Presenter(NewsItem::class)
data class NewsModel(var heading: String = "", 
		     var desc: String = "",
		     var content: String = "")
```

Please note, the model class needs to be `data` class for functions  like `removeItem()` to work.

That’s it, nothing more is required to add to your data class.

## Creating your presenter or item class
The presenter or the item class is the place where you specify which layout should be inflated for the model and how to load data into the inflated view from your model according to your business logic. You can also add your custom click listeners here to handle clicks on the row or individual items in the row.

Consider the following example class,

### NewsItem.kt

```kotlin
class NewsItem : MystiqueItemPresenter() {

    //Declare your NewsModel object
    lateinit private var newsModel: NewsModel

    //Binding all the views in the row using ButterKnife
    @BindView(R.id.heading_news)
    lateinit var newsHeading: TextView

    /**
     * This is where you need to populate your data from the model
     * to each element in the row according to your business logic
     */
    override fun displayView(itemView: View) {
        ButterKnife.bind(this, itemView)

        newsHeading.text = newsModel.heading
    }

    /**
     * Return your model here for Mystic to do proper mapping
     * while adding or deleting items from your list
     */
    override fun getModel() = newsModel

    /**
     * Return the layout associated with the model for Mystic
     * to correctly inflate the proper views for each supplied
     * model
     */
    override fun getLayout() = R.layout.view_item_news

    /**
     * This is where you initialize your model as supplied
     * by Mystique through this method call
     */
    override fun loadModel(model: Any) {
        newsModel = model as NewsModel
    }

    /**
     * If you need to handle clicks on your items or
     * items within your item, this is where you do it
     * by adding your own custom listener (see sample)
     */
    override fun handleClickEvents(itemView: View) {

    }

    /**
     * Initialize your custom listener in this function
     * call, which will be called my Mystique while loading
     * your data
     */
    override fun setListener(listener: Any?) {

    }
}
```

And, you’re all set!

## Loading the models to the adapter
The next step is to load your models to the universal adapter, which is a breeze thanks to Mystique’s ***auto-binding*** of models to their respective presenters.

Once you have your data initialized in their appropriate model classes, declare a reference to your adapter and load the data in this fashion,

```kotlin
/**
  * Initialize your adapter
  */
val adapter = MystiqueAdapter<MystiqueItemPresenter>()

/**
  * Set the models to the adapter for loading
  */
adapter.setItems(newsList.toMystifiedList())
```

Here, the extension function (_since it’s Kotlin_) converts a regular `List<T>` to a `MutableList<MystiqueItemPresenter>` by attaching all the appropriate models to their presenters behind the scenes for you.

Voila! That’s all. Did you expect more?

## Other functions
Mystique also defines other functions such as,

### Add an item

```kotlin
fun <T : MystiqueItemPresenter> MystiqueAdapter<T>.addItem(item: T?, index: Int = mystiqueItems.size)
```

### Remove an item

#### Using an index,

```kotlin
fun <T : MystiqueItemPresenter> MystiqueAdapter<T>.removeItem(index: Int = mystiqueItems.size - 1)
```

#### Using a model,

```kotlin
fun <T : MystiqueItemPresenter> MystiqueAdapter<T>.removeItem(model: Any)
```

### Add a list of items

```kotlin
fun <T : MystiqueItemPresenter> MystiqueAdapter<T>.addItems(items: List<T>, startPosition: Int = mystiqueItems.size)
```

### Remove a list of items

```kotlin
fun <T : MystiqueItemPresenter> MystiqueAdapter<T>.removeItems(items: List<Any>)
```

### Convert any object to a Mystified object

```kotlin
object.toMystifiedItem()
```

# Sample app
Refer to this section in this repo for a sample app using the library,

[MystiqueSample](https://github.com/rahulchowdhury/Mystique/tree/master/app)

# Contribution
I have made this library as easy as possible to use, but I might have missed out some cool stuff. If you have something that you would like to add, fork the library, make your changes and please send a pull request.

I would be happy to add your contribution to the library, if it improves the library.

# Who made Mystique?
Well the name of the library might be Mystique, but certainly the author is not a mystery.

### Rahul Chowdhury

[Blog](http://blog.rahulchowdhury.co/) | [Twitter](https://twitter.com/chowdhuryrahul) | [LinkedIn](https://www.linkedin.com/in/chowdhuryrahul/) | [Facebook](https://www.facebook.com/itsrcthegreat) | [Medium](https://medium.com/@rahulchowdhury)

# License
```
Copyright 2017 Rahul Chowdhury

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and

```
