package com.example.android_tmi_diaryapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android_tmi_diaryapp.DTO.MemoItemDTO;

import java.util.ArrayList;

public class MemoRVAdapter extends RecyclerView.Adapter<MemoRVAdapter.ViewHolder> {
    private ArrayList<MemoItemDTO> mMemoItemDTO;
    private Context memoContext;
    private MemoDBActivity memoDBActivity;
    private ItemClickListener clickListener;

    public MemoRVAdapter(ArrayList<MemoItemDTO> mMemoItemDTO, Context memoContext, ItemClickListener clickListener){
        this.mMemoItemDTO = mMemoItemDTO;
        this.memoContext = memoContext;
        this.clickListener = clickListener;
        memoDBActivity = new MemoDBActivity(memoContext);

    }

    public MemoRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_memo, parent, false);
        return new ViewHolder(holder);
    }


    public void onBindViewHolder(MemoRVAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        viewHolder.onBind(mMemoItemDTO.get(position));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(mMemoItemDTO.get(position));
            }
        });
    }

    public int getItemCount() {
        return mMemoItemDTO.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleView = (TextView) itemView.findViewById(R.id.rv_memo_title);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int curPos = getAdapterPosition();
                    MemoItemDTO memoItemDTO = mMemoItemDTO.get(curPos);

                    String[] strChoiceItem = {"삭제하기"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(memoContext);
                    builder.setTitle("삭제 하시겠습니까?");
                    builder.setItems(strChoiceItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int position) {
                            if(position == 0){
                                int id = memoItemDTO.getId();
                                memoDBActivity.DeleteMemo(id);
                                mMemoItemDTO.remove(curPos);
                                notifyItemRemoved(curPos);
                                Toast.makeText(memoContext, "메모가 제거되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.show();

                    return true;
                }
            });
        }

        public void setItem(MemoItemDTO item){
            titleView.setText(item.getTitle());
        }

        void onBind(MemoItemDTO item){
            titleView.setText(item.getTitle());
        }
    }

    public interface ItemClickListener {
        public void onItemClick(MemoItemDTO memoItemDTO);
    }
}
