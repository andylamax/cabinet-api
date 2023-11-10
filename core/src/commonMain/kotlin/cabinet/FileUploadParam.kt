@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package cabinet

import kotlin.js.JsExport
import epsilon.RawFile

data class FileUploadParam(
    val path: String,
    val filename: String,
    val file: RawFile
)