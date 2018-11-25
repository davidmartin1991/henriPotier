package imt.front

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import imt.R
import src.front.ListBooksFragment.OnClickListBookListener
import imt.model.Book
import timber.log.Timber


class LibraryActivity : AppCompatActivity(), OnClickListBookListener {
    override fun onClick(book: Book) {
        val fragment = DetailsBookFragment.newInstance(book)

        if(Configuration.ORIENTATION_PORTRAIT == resources.configuration.orientation){
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerFrame, fragment, DetailsBookFragment::class.java.name)
                    .addToBackStack(ListBooksFragment::class.java.name)
                    .commit()
        }else{
            supportFragmentManager.beginTransaction()
                    .replace(R.id.detailsFrame, fragment, DetailsBookFragment::class.java.name)
                    .commit()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_library)
        supportFragmentManager.beginTransaction()
                .replace(R.id.containerFrame, ListBooksFragment(), ListBooksFragment::class.java.name)
                .commit()

    }


    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }




}
