package src.front

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import imt.service.HenriPotierService
import imt.R
import imt.adapter.BookAdapter
import imt.model.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import kotlin.collections.ArrayList

class ListBooksFragment : Fragment() {

    companion object {
        const val BOOKS_KEY = "BOOKS_KEY"
    }
    private var listView: ListView? = null
    private var books = ArrayList<Book>()
    private var listener: OnClickListBookListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        when (context) {
            is OnClickListBookListener -> listener = context
            else -> throw Exception("...")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        listView = view.findViewById<ListView>(R.id.bookListView) as ListView
        if(savedInstanceState?.containsKey(BOOKS_KEY) == true){
            books = savedInstanceState.getParcelableArrayList(BOOKS_KEY)
            Timber.i("SaveInstance")

        }else{
            books = this.getBooks()
            Timber.i("PasSaveInstance")
        }

        listView?.setOnItemClickListener{parent, view, position, id ->
            listener?.onClick(books[position])
        }


        return view
    }

    fun getBooks(): ArrayList<Book> {
        books = ArrayList<Book>()
        val bookAdapter = BookAdapter(this.context!!, this.books)
        listView!!.adapter = bookAdapter

        val retrofit = Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(HenriPotierService::class.java)

        api.listBooks().enqueue(object : Callback<Array<Book>> {
            override fun onFailure(call: Call<Array<Book>>, t: Throwable) {
                Timber.i("Failure : " + t.message)
            }

            override fun onResponse(call: Call<Array<Book>>,
                                    response: Response<Array<Book>>)   {
                Timber.i("Success")
                response.body()?.forEach {
                    books.add(it)
                }
                bookAdapter.notifyDataSetChanged()
            }
        })
        return books
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(BOOKS_KEY, this.books)
    }

    interface OnClickListBookListener{
        fun onClick(book: Book)
    }

}