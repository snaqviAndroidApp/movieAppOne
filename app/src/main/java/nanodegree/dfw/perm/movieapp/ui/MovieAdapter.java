package nanodegree.dfw.perm.movieapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import nanodegree.dfw.perm.movieapp.R;
import nanodegree.dfw.perm.movieapp.data.POJO_MovieData;
import nanodegree.dfw.perm.movieapp.data.Showables;

import static android.app.PendingIntent.getActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Showables> mShowablesList;
    private ArrayList<POJO_MovieData> mMoviesList;

    final private MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler {              //Interface for OnCickHanlding
        void setDataClicked(String dataClicked);
    }


    public MovieAdapter(MovieAdapterOnClickHandler movieAdapterOnClickHandler){
        mClickHandler = movieAdapterOnClickHandler;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        public final ImageView mMovieImageView;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mMovieImageView = itemView.findViewById(R.id.poster_master);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int adapterPosition = getAdapterPosition();

            mClickHandler.setDataClicked(mShowablesList.get(adapterPosition).toString());   // Dummy Implementation
//            mClickHandler.setDataClicked(mMoviesList.get(adapterPosition).toString());

            Toast.makeText(v.getContext(),"OnClick() in MovieViewHolder at Adapter Position: " + adapterPosition,Toast.LENGTH_SHORT).show();
        }
    }



    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Log.d("Inside MovieViewHolder", "");

        Context vContext = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_poster_item;
        LayoutInflater mainPosterInflaltor = LayoutInflater.from(vContext);
        boolean shouldAttachToParentImmediately = false;

        View viewPoster = mainPosterInflaltor.inflate(layoutIdForListItem,viewGroup,shouldAttachToParentImmediately);
        return new MovieViewHolder(viewPoster);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        Showables mImagSelected = mShowablesList.get(position);
//        POJO_MovieData mImagSelected = mMoviesList.get(position);

        Log.d("onBinderVH","onBindViewHolder called");
        String imageUrl = mImagSelected.getmImageUrl();                   // worked with Dummy Data in 'Showables'
//        String imageUrl = mImagSelected.getPoster_path();

//        Picasso.get().load(imageUrl).fit().centerInside().into(movieViewHolder.mMovieImageView);


//        Picasso.get()
////                .load("https://upload.wikimedia.org/wikipedia/commons/c/c9/Moon.jpg")
//                .load(imageUrl)
//                .fit()
//                .centerCrop()
//                .rotate(0)
////                .centerInside()
////                .placeholder(R.drawable.ic_launcher_background)
////                .error(R.drawable.ic_launcher_foreground)
//                .into(movieViewHolder.mMovieImageView);


        Glide.with(movieViewHolder.mMovieImageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .into(movieViewHolder.mMovieImageView);
    }

    @Override
    public int getItemCount() {

        Log.d("adapterCnt", " " + Integer.toString(mShowablesList.size()));
//        Log.d("adapterCnt", " " + Integer.toString(mMoviesList.size()));

        if (null == mShowablesList) return 0;
//        return mMoviesList.size();
        return mShowablesList.size();
    }

    public void setMovieData(ArrayList<Showables> toSowData){
//    public void setMovieData(ArrayList<POJO_MovieData> toSowData){
        mShowablesList = toSowData;
        notifyDataSetChanged();
    }
}