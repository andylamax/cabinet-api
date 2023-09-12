@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package cabinet

import epsilon.Blob
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class FileUploadParam(
    val path: String,
    val filename: String,
    val blob: Blob
)