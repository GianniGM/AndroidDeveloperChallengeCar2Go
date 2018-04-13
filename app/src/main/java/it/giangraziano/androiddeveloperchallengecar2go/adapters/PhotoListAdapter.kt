package it.giangraziano.androiddeveloperchallengecar2go.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import it.giangraziano.androiddeveloperchallengecar2go.R
import it.giangraziano.androiddeveloperchallengecar2go.model.Photo

//todo remove: put in assets
const val PLACEHOLDER_FILE_PATH = "placeholder.png"

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    private var photos: MutableList<Photo> = mutableListOf()

    fun addData(list: MutableList<Photo>){
        this.photos.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        //build my itemView
        val ctx = parent.context
        val inflate = LayoutInflater.from(ctx)
        val v = inflate.inflate(R.layout.image_list_item, parent, false)
        return PhotoViewHolder(v)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = photos[position]
        holder.setImage(item.urls.small)
    }

    inner class PhotoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById(R.id.item_image_item) as ImageView

        private fun getDrawablePlaceHolder(): Drawable {
            val ctx = view.context
            val stream = ctx.assets.open(PLACEHOLDER_FILE_PATH)
            return Drawable.createFromStream(stream, null)
        }

        fun setImage(imageUrl: String?) {

            Picasso
                    .get()
                    .load(imageUrl)
                    .placeholder(getDrawablePlaceHolder())
                    .into(imageView)

        }
    }
}