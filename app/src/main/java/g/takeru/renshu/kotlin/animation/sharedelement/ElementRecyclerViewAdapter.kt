package g.takeru.renshu.kotlin.animation.sharedelement

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import g.takeru.renshu.R
import g.takeru.renshu.RenShuApplication
import g.takeru.renshu.databinding.ListitemElementBinding
import g.takeru.renshu.kotlin.animation.sharedelement.ElementRecyclerViewAdapter.ImageViewHolder


/**
 * Created by takeru on 2018/4/17.
 */
class ElementRecyclerViewAdapter : RecyclerView.Adapter<ImageViewHolder> {

    private lateinit var colorList: MutableList<Int>
    private lateinit var itemClickListener: (ImageView) -> Unit

    constructor()

    constructor(colorList: MutableList<Int>) {
        this.colorList = colorList
    }

    constructor(colorList: MutableList<Int>,
                itemClickListener: (ImageView) -> Unit) {
        this.colorList = colorList
        this.itemClickListener = itemClickListener
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(ListitemElementBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.binding.apply {
            elementImg.setImageResource(R.drawable.ic_vector_android_24dp)
            // set tint for imageview
            val colorInt = ContextCompat.getColor(
                RenShuApplication.getInstance()!!.applicationContext, colorList[position])
            ImageViewCompat.setImageTintList(elementImg, ColorStateList.valueOf(colorInt))

            //
            val bgColorInt = ContextCompat.getColor(
                RenShuApplication.getInstance()!!.applicationContext, R.color.waveLine_100)
            elementBackground.setBackgroundColor(bgColorInt)

            elementBackground.setOnClickListener{itemClickListener(elementImg)}
        }
    }


    class ImageViewHolder(val binding: ListitemElementBinding) :
        RecyclerView.ViewHolder(binding.root)
}