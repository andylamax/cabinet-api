package cabinet
import kotlin.js.JsExport

@JsExport
interface CabinetApi {
    val attachments: RootDir
}