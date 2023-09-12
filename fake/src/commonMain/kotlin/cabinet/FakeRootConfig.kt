package cabinet

import koncurrent.CoroutineExecutor
import koncurrent.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class FakeRootConfig(
    val path: String,
    val max: Int = 10,
    val delay: Int = 100,
    val executor: CoroutineExecutor = CoroutineExecutor(CoroutineScope(Dispatchers.Default + SupervisorJob())),
) {
    val scope = executor.scope
}