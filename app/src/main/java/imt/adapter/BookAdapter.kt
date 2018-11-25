package imt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import imt.R
import imt.model.Book
import imt.front.BookItemView

class BookAdapter(context: Context, private val books: List<Book>) : BaseAdapter() {
    val inflater: LayoutInflater
    init {
        this.inflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.item_view_book, parent, false)
        }
        val book = getItem(position)
        val bookItemView = view as BookItemView
        book?.let { bookItemView.bindView(it as Book) }
        return bookItemView
    }

    override fun getItem(position: Int): Any {
        return books[position]
    }

    override fun getItemId(position: Int): Long {
        return books[position].hashCode().toLong()
    }

    override fun getCount(): Int {
        return books.count()
    }

}