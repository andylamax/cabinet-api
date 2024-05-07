@file:JsExport

package cabinet

import epsilon.MemorySize
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
data class AttachmentDto(
    val uid: String,
    val name: String,
    val url: String,
    val size: MemorySize,
    val description: String? = null,
    val contentType: String? = null,
)