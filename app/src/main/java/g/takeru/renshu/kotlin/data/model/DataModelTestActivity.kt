package g.takeru.renshu.kotlin.data.model

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import g.takeru.renshu.R
import g.takeru.renshu.util.JsonUtil
import timber.log.Timber

class DataModelTestActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val docFolder =
                JsonUtil.loadFromRaw(this, R.raw.document_folder, DocFolder::class.java)
        Timber.d("docFolder: ${docFolder.version}")
        Timber.d("docFolder: ${docFolder.data.country["hkg"]!![0].label}")
    }
}