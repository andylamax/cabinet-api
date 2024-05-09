@file:JsExport

package cabinet

import cinematic.mutableLiveOf
import epsilon.FileOutput
import epsilon.RawFileInfo
import kase.LazyState
import kase.Loading
import kase.Pending
import kase.toLazyState
import koncurrent.FailedLater
import koncurrent.Later
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport

class AttachmentPresenter(
    val src: AttachmentDto,
    private val options: AttachmentPresenterOptions
) {
    val uid by lazy { src.uid }
    val name by lazy { src.name }
    val size by lazy { src.size }
    val description by lazy { src.description }
    val type by lazy { src.type }

    val state = mutableLiveOf<LazyState<FileOutput>>(Pending)

    fun view(): Later<String> = getUrl()

    fun copyUrlToClipboard(): Later<Unit> = getUrl().andThen {
        options.clipboard?.setText(it) ?: FailedLater("Clipboard has not been configured")
    }

    fun save(name: String? = null): Later<String> = getUrl().andThen {
        val n = name ?: state.value.data?.info?.nameWithExtension ?: src.name
        options.file.save(it, n)
    }

    fun open(): Later<String> = getUrl().andThen { options.file.open(it) }

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

    private fun download(): Later<FileOutput> {
        if (options.headers.isEmpty()) {
            return FileOutput(src.url, updated = false, info = null, file = null).toLater()
        }
        return options.file.download(src.url, src.name, options.headers).then { file ->
            RawFileInfo(file)
        }.then { info ->
            FileOutput(info.url, updated = false, info = info, file = info.file)
        }
    }

    fun deInitialize() {
        state.value.data?.info?.dispose()
        state.value = Pending
    }
}