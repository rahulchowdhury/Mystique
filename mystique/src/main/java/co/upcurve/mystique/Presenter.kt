package co.upcurve.mystique

import kotlin.reflect.KClass

/**
 * Created by rahulchowdhury on 5/29/17.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Presenter(val presenterClass: KClass<*>)