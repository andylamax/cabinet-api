@file:JsExport

package cabinet

import cinematic.mutableLiveOf
import epsilon.FileManager
import epsilon.FileOutput
import epsilon.RawFileInfo
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import io.ktor.util.toByteArray
import kase.LazyState
import kase.Loading
import kase.Pending
import kase.toLazyState
import klip.Clipboard
import koncurrent.FailedLater
import koncurrent.Later
import koncurrent.later
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import kotlinx.coroutines.CoroutineScope

class AttachmentPresenter(
    val src: AttachmentDto,
    private val headers: Map<String, String>,
    private val scope: CoroutineScope,
    private val http: HttpClient,
    private val clipboard: Clipboard?,
    private val file: FileManager
) {
    val uid by lazy { src.uid }
    val name by lazy { src.name }
    val size by lazy { src.size }
    val description by lazy { src.description }
    val type by lazy { src.type }

    val state = mutableLiveOf<LazyState<FileOutput>>(Pending)

    fun view(): Later<String> = getUrl()

    fun copyUrlToClipboard(): Later<Unit> = getUrl().andThen {
        clipboard?.setText(it) ?: FailedLater("Clipboard has not been configured")
    }

    fun save(name: String? = null): Later<String> = getUrl().andThen {
        val n = name ?: state.value.data?.info?.nameWithExtension ?: src.name
        file.save(it, n)
    }

    fun open(): Later<String> = getUrl().andThen { file.open(it) }

    private fun getUrl(): Later<String> {
        val url = state.value.data?.url
        if (url == null) {
            state.value = Loading("Fetching attachment, please wait...")
            return download().finally {
                state.value = it.toLazyState()
            }.then { it.url as String }
        } else {
            return url.toLater()
        }
    }

    private fun download(): Later<FileOutput> = scope.later {
        if (headers.isEmpty()) {
            return@later FileOutput(src.url, updated = false, info = null, file = null)
        }
        val response = http.get(src.url) {
            this@AttachmentPresenter.headers.forEach { (key, value) -> headers.append(key, value) }
        }
        val content = response.bodyAsChannel().toByteArray()
        val info = RawFileInfo(file.create.binary(content, src.name, type ?: "application/octet-stream"))
        FileOutput(info.url, updated = false, info = info, file = info.file)
    }

    fun deInitialize() {
        state.value.data?.info?.dispose()
        state.value = Pending
    }
}