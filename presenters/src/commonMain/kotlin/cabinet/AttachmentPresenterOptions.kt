package cabinet

import epsilon.FileManager
import klip.Clipboard

class AttachmentPresenterOptions(
    internal val headers: Map<String, String>,
    internal val clipboard: Clipboard?,
    internal val file: FileManager
)