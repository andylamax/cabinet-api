import cabinet.FakeRootConfig
import cabinet.FakeRootDir
import cabinet.FileUploadParam
import cabinet.RootDir
import epsilon.FakeBlob
import kase.Executing
import kase.Failure
import kase.Pending
import kase.Success
import kommander.expect
import koncurrent.later.await
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class CabinetApiTest {

    @Test
    fun should_have_an_easy_to_use_upload_api() = runTest {
        val c = Customer("1234")
        val root = c.getRootDir()

        val params = FileUploadParam(
            path = "testament.pdf",
            blob = FakeBlob(4545),
            filename = "banana.int"
        )

        root.upload(params).onUpdate { state ->
            when (state) {
                is Executing -> println("${state.progress.donePercentage}% completed")
                is Failure -> println("Failure")
                is Pending -> println("Pending")
                is Success -> println("Success")
            }
        }.then {
            println("Yeeeiiiit")
            println("Thanks anyway")
        }.catch {
            println("OOOppppsssssss")
        }.await()
    }

    @Test
    fun should_list_files() = runTest {
        val c = Customer("10042")
        val root = c.getRootDir()

        val attachments = root.list().await()
        expect(attachments.size).toBe(0)
    }

    class Customer(val uid: String)

    fun Customer.getRootDir(): RootDir = FakeRootDir(FakeRootConfig(path = "customers/$uid"))
}
