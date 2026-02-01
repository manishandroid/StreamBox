package com.imandroid.streambox.core.architecture

/**
 * Marker annotation to exclude UI-only code from coverage reports.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class IgnoreKover
