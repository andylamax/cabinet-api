@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package cabinet

import kotlinx.JsExport
import epsilon.RawFile

data class FileUploadParam(
    val path: String,
    val filename: String,
    val file: RawFile
)