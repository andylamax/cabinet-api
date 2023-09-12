@file:JsExport

package cabinet

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Attachment(
    val uid: String,
    val name: String,
    val url: String,
    val sizeInBytes: Int,
    val description: String? = null,
    val contentType: String? = null,
)