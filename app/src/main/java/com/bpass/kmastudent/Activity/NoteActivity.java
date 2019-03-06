package com.bpass.kmastudent.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.bpass.kmastudent.Adapter.ItemNoteAdapter;
import com.bpass.kmastudent.DTO.Note;
import com.bpass.kmastudent.Database.CreateDatabase;
import com.bpass.kmastudent.R;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener {

    private ItemNoteAdapter adapter;
    private List<Note> listNote = new ArrayList<>();

    private Context context;

    private CreateDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_note_main);

        context = this;

        db = CreateDatabase.getInstance(context);
        connectView();

        final Toolbar toolbarNoteActivity = (Toolbar) findViewById(R.id.toolbar_note_main);
        setSupportActionBar(toolbarNoteActivity);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // kết nối java với xml view
    private void connectView() {

        // tìm Float Action Button
        findViewById(R.id.fab).setOnClickListener(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_note);

        // kích thước của view ko thay đổi khi dữ liệu thay đổi
        recyclerView.setHasFixedSize(true);

        // Cài đặt LayoutManager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Cài đặt adapter.
        adapter = new ItemNoteAdapter(context, listNote);
        recyclerView.setAdapter(adapter);
    }


    //update danh sách note khi resume ( mở app or tắt Create Note)
    public void onResume() {
        super.onResume();
        updateListNote();
    }

    /**
     * chọn tất cả note từ database và set vào ls(ArrayList)
     * dùng vòng lặp để thêm vào listNote
     * phải thêm tất cả item trong ls(ArrayList) vào listNote sau đó adapter có thể update
     * ta thêm ngược danh sách ls vào nơi hiển thị note để note nào được tạo sau sẽ ở trên
     */
    private void updateListNote() {
        // clear list cũ
        listNote.clear();
        // thêm tất cả note từ database, đảo ngược list
        ArrayList<Note> ls = db.getListNote("SELECT * FROM " + CreateDatabase.TB_NOTE);

        // đảo ngược list
        for (int i = ls.size() - 1; i >= 0; i--) {
            listNote.add(ls.get(i));
        }

        adapter.notifyDataSetChanged();
    }


    //hiển thị note có id =
    public static void showNote(Context context, long id) {
        Intent intent = new Intent(context, CreateNote.class);

        // gửi id đến CreateNote
        intent.putExtra(CreateNote.ID, id);

        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showNote(context, CreateNote.NEW_NOTE);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_main, menu);
        return true;

    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_back:
//                back();
//                break;
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onBackPressed() {
//        back();
//    }
//
//    public void back(){
////        Intent iTrangchu = new Intent(this, class);
////        startActivity(iTrangchu);
//    }

}