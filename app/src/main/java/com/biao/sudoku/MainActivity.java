package com.biao.sudoku;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridLayout;


public class MainActivity extends AppCompatActivity {

    private EditText et_input;
    private RecyclerView rv_sudoku;
    private RVadapter rVadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        et_input = findViewById(R.id.et_input);
        rv_sudoku = findViewById(R.id.rv_sudoku);
    }

    @SuppressLint("NewApi")
    private void initData() {
        //禁止系统输入法拉起
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        et_input.setShowSoftInputOnFocus(false);
        et_input.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());//禁止复制/剪切

        rVadapter = new RVadapter(this);
        rVadapter.setOnClickItem(new RVadapter.OnClickItem() {
            @Override
            public void clickItem(int position) {
                setEt_input(position);
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayout.VERTICAL, false);
        rv_sudoku.setLayoutManager(layoutManager);
        rv_sudoku.setAdapter(rVadapter);
    }

    private void setEt_input(int position) {
        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                addChar(et_input, String.valueOf(position + 1));
                break;
            case 9:
                deleteAll(et_input);
                break;
            case 10:
                addChar(et_input, String.valueOf(0));
                break;
            case 11:
                deleteChar(et_input);
                break;
        }
    }

    /**
     * 添加字符
     *
     * @param editText
     * @param data
     */
    private void addChar(EditText editText, String data) {
        int index = editText.getSelectionStart();
        Editable editable = editText.getText();
        editable.insert(index, data);
    }

    /**
     * 删除字符
     *
     * @param editText
     */
    private void deleteChar(EditText editText) {
        int index = editText.getSelectionStart();
        Editable editable = editText.getText();
        if (index > 0) {
            editable.delete(index - 1, index);
        }
    }

    /**
     * 清除
     *
     * @param editText
     */
    private void deleteAll(EditText editText) {
        editText.setText("");
    }

    /**
     * 禁止复制和粘贴
     */
    private class ActionModeCallbackInterceptor implements ActionMode.Callback {

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }


        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }


        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }


        public void onDestroyActionMode(ActionMode mode) {
        }
    }
}
