package it.giangraziano.androiddeveloperchallengecar2go.adapters

import android.view.View
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import it.giangraziano.androiddeveloperchallengecar2go.R
import it.giangraziano.androiddeveloperchallengecar2go.model.Photo

//todo remove: put in assets
const val PLACEHOLDER_PICTURE = "http://www.pixedelic.com/themes/geode/demo/wp-content/uploads/sites/4/2014/04/placeholder4.png"

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

    inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById(R.id.item_image_item) as ImageView

        fun setImage(imageUrl: String?) {

            Picasso
                    .get()
                    .load(imageUrl)
//           todo         .placeholder()
                    .into(imageView)

        }
    }
}