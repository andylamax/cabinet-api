@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package cabinet

import kotlinx.JsExport

interface Directory {
    fun rootDir(uid: String): RootDir
}