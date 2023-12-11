@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package cabinet

import kollections.Map
import koncurrent.Later
import kollections.List
import kotlinx.JsExport
import kotlin.js.JsName

interface RootDir {
    fun upload(param: FileUploadParam): Later<Attachment>

    @JsName("uploadMany")
    fun upload(params: Array<FileUploadParam>): Map<FileUploadParam, Later<Attachment>>

    fun list(): Later<List<Attachment>>

    @JsName("deleteAttachment")
    fun delete(attachment: Attachment): Later<Attachment>
}