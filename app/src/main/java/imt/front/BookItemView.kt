package imt.front

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import imt.R
import imt.model.Book

class BookItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defAttrStyle: Int = 0) :
        LinearLayout(context, attrs, defAttrStyle) {

    var titleView: TextView? = null
    var imageView: ImageView? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        titleView = findViewById(R.id.titleTextView)
        imageView = findViewById(R.id.imageView)
    }

    fun bindView(book: Book) {
        titleView?.text = book.titre

        imageView?.let {
            Glide.with(this)
                .load(book.cov)
                .into(it)
        }
    }
}
