package cabinet.internal

import cabinet.Attachment
import cabinet.FileUploadParam
import cabinet.RootDir
import koncurrent.Later

abstract class AbstractRootDir : RootDir {
    override fun upload(param: FileUploadParam): Later<Attachment> = upload(arrayOf(param)).first().value
}