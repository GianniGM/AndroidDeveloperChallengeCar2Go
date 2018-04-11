package giangraziano.it.androiddeveloperchallengecar2go.adapters

import android.view.View
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import giangraziano.it.androiddeveloperchallengecar2go.R
import giangraziano.it.androiddeveloperchallengecar2go.model.Photo

/**
 * Created by giannig on 09/04/18.
 */
const val PLACEHOLDER_PICTURE = "http://www.pixedelic.com/themes/geode/demo/wp-content/uploads/sites/4/2014/04/placeholder4.png";

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    private var photos: MutableList<Photo> = mutableListOf()

    fun setData(list: MutableList<Photo>){
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
        val url: String = item.urls.thumb ?: PLACEHOLDER_PICTURE

        holder.setImage(url)
    }

    inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById(R.id.item_image_item) as ImageView

        fun setImage(imageUrl: String) {

            Picasso
                    .get()
                    .load(imageUrl)
                    .into(imageView)

        }
    }
}