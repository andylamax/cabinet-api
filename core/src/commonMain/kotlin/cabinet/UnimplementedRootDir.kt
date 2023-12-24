package cabinet

import kollections.List
import kollections.Map
import kollections.mapOf
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import koncurrent.TODOLater

class UnimplementedRootDir : RootDir {
    override fun upload(param: FileUploadParam): Later<Attachment> = TODOLater()

    override fun upload(params: List<FileUploadParam>): Map<FileUploadParam, Later<Attachment>> = mapOf()

    override fun list(): Later<List<Attachment>> = TODOLater()

    override fun delete(attachment: Attachment): Later<Attachment> = TODOLater()
}