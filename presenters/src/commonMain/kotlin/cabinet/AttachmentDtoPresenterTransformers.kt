package cabinet

fun AttachmentDto.toPresenter(options: AttachmentPresenterOptions) = AttachmentPresenter(
    src = this,
    options = options
)