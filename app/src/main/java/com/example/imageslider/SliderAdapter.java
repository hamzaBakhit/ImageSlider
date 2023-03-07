package com.example.imageslider;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderItem> images;
    private ViewPager2 viewPager2;

    public SliderAdapter(List<SliderItem> images, ViewPager2 viewPager2) {
        this.images = images;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_container, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(images.get(position), holder.context);
        if (position == images.size() -2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ImageView imageView;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = itemView.findViewById(R.id.image_slide);
        }

        void setImage(SliderItem sliderItem,Context context) {
            System.out.println(sliderItem.getImage());
            Glide.with(context).load(sliderItem.getImage()).centerCrop().into(imageView);
//            imageView.setImageResource(sliderItem.getImage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            images.addAll(images);
            notifyDataSetChanged();
        }
    };
}
