package com.bpass.kmastudent.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.bpass.kmastudent.DTO.Note;
import com.bpass.kmastudent.Database.CreateDatabase;
import com.bpass.kmastudent.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNote extends AppCompatActivity {

    //nếu add thêm note, thì init bởi NEW_NOTE
    public static final long NEW_NOTE = -1;

    // Từ khóa để lấy id note từ activity khác đến display note
    public static final String ID = "ID";

    //để edit, add,... note từ database
    private CreateDatabase db;

    //note current
    private Note note;

    private EditText editTitle;
    private EditText editContent;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        context = this;

        db = CreateDatabase.getInstance(context);


        //db = new CreateDatabase(context);

        connectView();
        getInfo();

        final Toolbar toolbarCreateNote = (Toolbar) findViewById(R.id.toolbar_create_note);
        setSupportActionBar(toolbarCreateNote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //kết nối with xml view
    private void connectView() {
        editTitle = (EditText) findViewById(R.id.edit_title);
        editContent = (EditText) findViewById(R.id.edit_content);
    }


    // get info note to display
    private void getInfo() {
        long id = getIntent().getLongExtra(ID, NEW_NOTE);

        // nếu không phải new note thì tìm note từ database bởi id của note
        if (id != NEW_NOTE) {
            String sql = "SELECT * FROM " + CreateDatabase.TB_NOTE + " WHERE " + CreateDatabase.TB_NOTE_ID + " = " + id;
            note = db.getNote(sql);
        }

        if (note != null) {
            editTitle.setText(note.getTitle());
            editContent.setText(note.getContent());
        } else {
            editTitle.setText("");
            editContent.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                save();
                break;
            case R.id.menu_delete:
                delete();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * lấy title, content, thời gian last modified cho note, nếu nó đang trống thì dừng
     * nếu nó ko trống thì kiểm tra note có null ko?
     * nếu note null (tạo note mới), tạo rồi insert note mới vào database
     * nếu ko null thì update note
     * sau đó save và dừng
     */
    private void save() {

        String title = editTitle.getText().toString().trim();
        String content = editContent.getText().toString().trim();

        String notify = null;

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
            notify = " Note trống, không lưu!";
        } else {

            // lấy thời gian hiện tại cho last modified
            SimpleDateFormat formatTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String currentTime = formatTime.format(cal.getTime());

            // new note
            if (note == null) {
                Note note = new Note();
                note.setTitle(title).setContent(content).setLastModified(currentTime);
                if (db.insertNote(note) > 0) {
                    notify = " Thêm thành công!";
                } else {
                    notify = "Thêm lỗi!";
                }
            } else { // update note
                note.setTitle(title).setContent(content).setLastModified(currentTime);
                if (db.updateNote(note)) {
                    notify = " Cập nhật thành công!";
                } else {
                    notify = "Cập nhật thất bại!";
                }
            }
        }

        Toast.makeText(context, notify, Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * lấy title, content của note, nếu nó đang trống thì dừng
     * nếu ko trống thì show dialog hỏi có muốn xóa ko?
     * if chọn no thì đóng dialog
     * if chọn yes thì delete note
     */
    private void delete() {
        String title = editTitle.getText().toString().trim();
        String content = editContent.getText().toString().trim();

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
            finish();
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            builder.setTitle(R.string.delete).setIcon(R.drawable.ic_note_xacnhan)
                    .setMessage("Bạn có muốn xóa bản ghi này?");
            builder.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteNote();
                }
            });
            builder.setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            builder.show();
        }
    }

    /**
     * kiểm tra note có null ko?
     * nếu null thì finish
     * nếu khác null thì  xóa trong database và finish
     */
    private void deleteNote() {
        if (note != null) {
            String where = CreateDatabase.TB_NOTE_ID + " = " + note.getId();
            String notify = "Xóa thành công!";

            if (!db.deleteNote(where)) {
                notify = " Xóa lỗi!";
            }
            Toast.makeText(context, notify, Toast.LENGTH_SHORT).show();
        }
        finish();
    }


    // ấn phím back trên điện thoại
    @Override
    public void onBackPressed() {
        save();
    }

}
