package vn.impl.projectbase.base.ultils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class AppReactivexSchedulers : ReactivexSchedulers {

    companion object {
        private val writerExecutor = Executors.newSingleThreadExecutor()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun androidMainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun writerScheduler(): Scheduler {
        return Schedulers.from(writerExecutor)
    }
}
