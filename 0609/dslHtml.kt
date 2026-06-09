data class Head(var title: String = "")
data class Body(var h1: String = "", var p: String = "")

class Html {
    var head = Head()
    var body = Body()
    override fun toString() =
        "<html>" +
                "<head><meta charset=\"UTF-8\"><title>${head.title}</title></head>" +
                "<body><h1>${body.h1}</h1><p>${body.p}</p></body>" +
                "</html>"
}

fun html(block: Html.() -> Unit): Html = Html().apply(block)

fun Html.head(block: Head.() -> Unit) { head = Head().apply(block) }
fun Html.body(block: Body.() -> Unit) { body = Body().apply(block) }