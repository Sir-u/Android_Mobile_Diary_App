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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_tmi_diaryapp.DTO.PhoneBookItemDTO;

import java.util.ArrayList;

public class PhoneBookRVAdapter extends RecyclerView.Adapter<PhoneBookRVAdapter.ViewHolder> {
    private ArrayList<PhoneBookItemDTO> mPhoneBookItemDTO;
    private Context mPhonebookContext;
    private PhoneBookDBActivity phoneBookDBActivity;
    private ItemClickListener clickListener;

    public PhoneBookRVAdapter(ArrayList<PhoneBookItemDTO> phoneBookItemDTO, Context phonebookContext, ItemClickListener clickListener){
        this.mPhoneBookItemDTO = phoneBookItemDTO;
        this.mPhonebookContext = phonebookContext;
        this.clickListener = clickListener;
        phoneBookDBActivity = new PhoneBookDBActivity(phonebookContext);
    }

    public PhoneBookRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_phonebook, parent, false);
        return new PhoneBookRVAdapter.ViewHolder(holder);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.nameView.setText(mPhoneBookItemDTO.get(position).getName());
//        holder.numberView.setText(mPhoneBookItemDTO.get(position).getNumber());
//    }

    public void onBindViewHolder(PhoneBookRVAdapter.ViewHolder ViewHolder, @SuppressLint("RecyclerView") int position) {
        ViewHolder.onBind(mPhoneBookItemDTO.get(position));

        ViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(mPhoneBookItemDTO.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPhoneBookItemDTO.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameView;
        //TextView numberView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameView = (TextView) itemView.findViewById(R.id.phonebook_name);
            //numberView = (TextView) itemView.findViewById(R.id.phonebook_number);

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view) {
                    int curPos = getAdapterPosition();
                    PhoneBookItemDTO phoneBookItemDTO = mPhoneBookItemDTO.get(curPos);

                    String[] strChoiceItem = {"삭제하기"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mPhonebookContext);
                    builder.setTitle("삭제 하시겠습니까?");
                    builder.setItems(strChoiceItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int position) {
                            if(position == 0){
                                int id = phoneBookItemDTO.getId();
                                phoneBookDBActivity.DeletePhoneBook(id);
                                mPhoneBookItemDTO.remove(curPos);
                                notifyItemRemoved(curPos);
                                Toast.makeText(mPhonebookContext, "목록이 제거되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.show();

                    return true;
                }
            });
        }

        void onBind(PhoneBookItemDTO item){
            nameView.setText(item.getName());
        }
    }

    public interface ItemClickListener {
        public void onItemClick(PhoneBookItemDTO phoneBookItemDTO);
    }
}
