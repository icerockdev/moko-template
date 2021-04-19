package org.example.library

import dev.icerock.moko.test.runBlocking
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

fun CoroutineScope.waitChildrenCompletion() = runBlocking {
    val job = this@waitChildrenCompletion.coroutineContext[Job]
    val children = job?.children.orEmpty().toList()
    children.forEach { it.join() }
}
