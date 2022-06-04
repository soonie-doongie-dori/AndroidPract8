package ru.mirea.lukyanchuk.mireaproject.ui.stories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ru.mirea.lukyanchuk.mireaproject.R;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder>{
    private List<Story> items;

    public StoryAdapter(List<Story> stories){
        this.items = stories;
    }

    public void addStoryToList(Story story){
        items.add(story);
    }

    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new StoryViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = items.get(position);
        holder.numbers.setText(story.number);
        holder.personNameAdd.setText(story.personName);
        holder.petNameAdd.setText(story.petName);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder{
        public TextView numbers;
        public TextView personNameAdd;
        public TextView petNameAdd;

        public StoryViewHolder(View itemView) {
            super(itemView);
            numbers = (TextView) itemView.findViewById(R.id.textNumber);
            personNameAdd = (TextView) itemView.findViewById(R.id.textName);
            petNameAdd = (TextView) itemView.findViewById(R.id.textPet);

        }
    }
}
