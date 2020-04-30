package com.example.novindevelopers.database_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.novindevelopers.database_project.Model.Data;

import java.util.List;

/**
 * Created by NovinDevelopers on 2/6/2018.
 */

public class AdapterData extends RecyclerView.Adapter<AdapterData.viewHolder>
{
    List<Data> data;
    Context context;
    private DbSQL dbSQL;

    public AdapterData(List<Data> data, Context context)
    {
        this.data = data;
        this.context = context;
        dbSQL = new DbSQL(context);
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position)
    {
        final Data items = data.get(position);
        String name = items.getName();
        String family = items.getFamily();
        holder.textViewfamily.setText(name + " " + family);
        holder.textViewphone.setText(items.getPhone());
        holder.textViewdate.setText(items.getDate());

        holder.imageViewShare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String share = items.getPhone();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,share);
                context.startActivity(intent);

            }
        });
        holder.imageViewRemove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("حذف اطلاعات");
                builder.setMessage("آیا مطمئن هستید ؟");
                builder.setCancelable(false);
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dbSQL.Delete(items.getId());
                        data.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position,data.size());
                        Toast.makeText(context, "آیتم مورد نظر حذف شد", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("خیر", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(context, "لغو", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdapterData.this.context,Update_Activity.class);
                intent.putExtra("id",items.getId()+"");
                intent.putExtra("name",items.getName());
                intent.putExtra("family",items.getFamily());
                intent.putExtra("phone",items.getPhone());
                intent.putExtra("date",items.getDate());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView imageViewRemove, imageViewShare;
        TextView textViewdate, textViewfamily, textViewphone;

        public viewHolder(View itemView)
        {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardview);
            imageViewRemove = (ImageView) itemView.findViewById(R.id.imageRemove);
            imageViewShare = (ImageView) itemView.findViewById(R.id.imageShare);
            textViewfamily = (TextView) itemView.findViewById(R.id.textFamily);
            textViewphone = (TextView) itemView.findViewById(R.id.textPhone);
            textViewdate = (TextView) itemView.findViewById(R.id.textDate);
        }
    }

}
