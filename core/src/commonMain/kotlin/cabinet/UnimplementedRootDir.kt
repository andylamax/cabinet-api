package cabinet

import kollections.List
import kollections.Map
import kollections.iMapOf
import koncurrent.Later
import koncurrent.TODOLater

class UnimplementedRootDir : RootDir {
    override fun upload(param: FileUploadParam): Later<Attachment> = TODOLater()

    override fun upload(params: Array<FileUploadParam>): Map<FileUploadParam, Later<Attachment>> = iMapOf()

    override fun list(): Later<List<Attachment>> = TODOLater()

    override fun delete(attachment: Attachment): Later<Attachment> = TODOLater()
}