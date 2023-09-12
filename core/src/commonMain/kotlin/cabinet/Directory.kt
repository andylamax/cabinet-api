@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package cabinet

import kotlin.js.JsExport

interface Directory {
    fun rootDir(uid: String): RootDir
}