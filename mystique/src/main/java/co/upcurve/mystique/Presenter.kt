package co.upcurve.mystique

import kotlin.reflect.KClass

/**
 * This annotation marks a model class with it's respective Presenter or Item
 * class which will be presenting the data from the model class to the user
 * using the RecyclerView adapter which in here is the [MystiqueAdapter]
 *
 * Every model class has to be annotated with this annotation for Mystique
 * to be able to find it's corresponding presenter class and attach the
 * model to the presenter easily and seamlessly while loading data to the
 * adapter
 *
 * Use [mystify] function to automatically bind annotated presenters to each
 * supplied model classes while loading the model classes to the [MystiqueAdapter]
 *
 * Note: If this annotation is skipped for a model class then it's the developer's
 * responsibility to manually initialize each presenter with it's corresponding
 * model class and send it to the adapter.
 *
 * Save yourself some time, let Mystique cast it's charm and do it for you
 * by using proper annotations instead of you doing it manually
 *
 * @param presenterClass A presenter or item class which extends [MystiqueItemPresenter]
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Presenter(val presenterClass: KClass<*>)