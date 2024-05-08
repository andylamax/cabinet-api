package cabinet

import epsilon.FileManager
import io.ktor.client.HttpClient
import klip.Clipboard
import kotlinx.coroutines.CoroutineScope

class AttachmentPresenterOptions(
    internal val headers: Map<String, String>,
    internal val scope: CoroutineScope,
    internal val http: HttpClient,
    internal val clipboard: Clipboard?,
    internal val file: FileManager
)