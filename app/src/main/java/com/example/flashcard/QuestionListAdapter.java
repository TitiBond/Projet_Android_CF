package com.example.flashcard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {

    private ArrayList<Question> questions;

    public QuestionListAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questions.get(position);
        if (question.getPictureId() == 0){
            holder.image.setImageResource(R.drawable.volume);
        }else{
            holder.image.setImageResource(question.getPictureId());
        }

        holder.title.setText("Question : " + (position+1));
        holder.theme.setText(question.getTheme());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Question> questions = new ArrayList<>();
                questions.add(question);
                Quiz quiz = new Quiz(questions,0,0);
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("quiz", quiz);
                intent.putExtra("selectedLevel", "Difficile");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        final ImageView image;
        final TextView title;
        final TextView theme;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //récupération du composant graphique
            //qui se trouve dans itemView. itemView = item_question.xml
            image = itemView.findViewById(R.id.ListImageView);
            title = itemView.findViewById(R.id.ListTitleTextView);
            theme = itemView.findViewById(R.id.ThemeListTextView);

        }
    }
}
