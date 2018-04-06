package com.vitaliyhtc.dagger2investigation

import android.os.AsyncTask
import io.reactivex.Scheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class AsyncTaskSchedulerRule: TestRule {

    val asyncTaskScheduler: Scheduler = Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)

    override fun apply(base: Statement?, description: Description?): Statement {
        return object: Statement() {
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { _: Scheduler -> asyncTaskScheduler }
                RxJavaPlugins.setComputationSchedulerHandler { _: Scheduler -> asyncTaskScheduler }
                RxJavaPlugins.setNewThreadSchedulerHandler { _: Scheduler -> asyncTaskScheduler }

                try {
                    base?.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                }
            }
        }
    }
}