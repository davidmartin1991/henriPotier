package imt.front

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import imt.R
import imt.model.Book

class DetailsBookFragment : Fragment() {
    companion object {
        const val BOOK = "BOOK"
        fun newInstance(book: Book): DetailsBookFragment {
            val args = Bundle()
            args.putParcelable(BOOK, book)
            val fragment = DetailsBookFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var titleBook: TextView? = null
    private var coverBook: ImageView? = null
    private var descriptionBook: TextView? = null
    private var priceBook: TextView? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val currentBook: Book? = arguments?.getParcelable(BOOK)

        titleBook = view.findViewById(R.id.titleBook) as TextView
        coverBook = view.findViewById(R.id.coverImage) as ImageView
        descriptionBook = view.findViewById(R.id.descriptionBook) as TextView
        priceBook = view.findViewById(R.id.priceBook) as TextView

        titleBook?.text = currentBook?.titre
        coverBook?.let {
            Glide.with(this)
                    .load(currentBook?.cov)
                    .into(it)
        }
        var synopsis = ""
        currentBook?.synopsis?.forEach {
            synopsis += it
        }
        descriptionBook?.text = synopsis
        priceBook?.text = "${currentBook?.prix} €"


        return view
    }

}