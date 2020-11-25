package vn.impl.projectbase.base.ultils.rx

import io.reactivex.Scheduler

interface ReactivexSchedulers {
    fun io(): Scheduler

    fun androidMainThread(): Scheduler

    fun writerScheduler(): Scheduler
}
