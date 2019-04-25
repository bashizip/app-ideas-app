package com.bashizip.badassview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.res.ColorStateList;
import android.os.Bundle;

import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskAdapter adapter;
    private List<Task> taskList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private CircleLayoutManager layoutManager;

    private static final int PEEK_DISTANCE = 10;
    private static final int RADIUS = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);

        initRecycleView();

        adapter = new TaskAdapter(this, taskList, new TaskAdapter.ListItemClickListener() {
            @Override
            public void clickPosition(int position, View v) {
                onClickItem(position, v);
            }
        });

        recyclerView.setAdapter(adapter);

        loadList();
    }

    private void initRecycleView() {


      layoutManager = new CircleLayoutManager(this,              // provide a context
                TurnLayoutManager.Gravity.START,        // from which direction should the list items orbit?
                TurnLayoutManager.Orientation.VERTICAL, // Is this a vertical or horizontal scroll?
                RADIUS,               // The radius of the item rotation
                PEEK_DISTANCE,                 // Extra offset distance
                true);        // should list items angle towards the center? true/false.

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                    View centerView = snapHelper.findSnapView(layoutManager);
                    int position = layoutManager.getPosition(centerView);
                    Task snapedTask = taskList.get(position);
                    switch (snapedTask.getState()) {
                        case assigned:
                            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                            break;
                        case pending:
                            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.deepOrange)));
                            break;
                        case done:
                            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                            break;
                    }

                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }


    void loadList() {

        Task task1 = new Task("Build a game engine", Task.State.pending);
        Task task2 = new Task("Develop a nive app", Task.State.assigned);
        Task task3 = new Task("Cook some rice", Task.State.pending);
        Task task4 = new Task("Flight booking", Task.State.pending);
        Task task5 = new Task("Uber food like app ", Task.State.done);
        Task task6 = new Task("Facebook for kids", Task.State.pending);
        Task task7 = new Task("Local music app", Task.State.pending);
        Task task8 = new Task("App for parking", Task.State.assigned);
        Task task9 = new Task("Flutter app", Task.State.assigned);
        Task task10 = new Task("Pills reminder", Task.State.pending);
        Task task11 = new Task("Build a game engine", Task.State.done);
        Task task12 = new Task("Restaurant bookin app", Task.State.pending);
        Task task13 = new Task("Build a game engine", Task.State.assigned);
        Task task14 = new Task("Lingala learning app", Task.State.done);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        taskList.add(task6);
        taskList.add(task1);
        taskList.add(task7);
        taskList.add(task8);
        taskList.add(task9);
        taskList.add(task10);
        taskList.add(task11);
        taskList.add(task12);
        taskList.add(task13);
        taskList.add(task14);


        adapter.notifyDataSetChanged();

    }


    private void onClickItem(int position, View v) {
        recyclerView.smoothScrollToPosition(position);
    }
}
