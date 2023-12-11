package com.example.safecallv2;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorP extends RecyclerView.Adapter<SPaciente> {
    private Context context;
    private List<Paciente> dataList;
    public AdaptadorP(Context context, List<Paciente> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public SPaciente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new SPaciente(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SPaciente holder, int position) {
        holder.recNpaciente.setText(dataList.get(position).getNombreP());
        holder.recApaciente.setText(dataList.get(position).getApellidoP());
        holder.recTpaciente.setText(dataList.get(position).getTemperatura());
        holder.recPpaciente.setText(dataList.get(position).getRitmoCardiaco());
        holder.recOpaciente.setText(dataList.get(position).getSaturacionOxigeno());
        String tempT = dataList.get(position).getTemperatura()+"°";
        holder.recTpaciente.setText(tempT);
        String pulP = dataList.get(position).getRitmoCardiaco()+"LPM";
        holder.recPpaciente.setText(pulP);
        String satP = dataList.get(position).getSaturacionOxigeno()+"°";
        holder.recOpaciente.setText(satP);
        temperatura(holder.recTpaciente, position);
        pulso(holder.recPpaciente, position);
        saturacion(holder.recOpaciente, position);
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetPersona.class);
                intent.putExtra("Nombre", dataList.get(holder.getAdapterPosition()).getNombreP());
                intent.putExtra("Apellido", dataList.get(holder.getAdapterPosition()).getApellidoP());
                intent.putExtra("Edad", dataList.get(holder.getAdapterPosition()).getEdad());
                intent.putExtra("Temperatura",dataList.get(holder.getAdapterPosition()).getTemperatura());
                intent.putExtra("Pulso", dataList.get(holder.getAdapterPosition()).getRitmoCardiaco());
                intent.putExtra("Saturacion", dataList.get(holder.getAdapterPosition()).getSaturacionOxigeno());
                intent.putExtra("Genero", dataList.get(holder.getAdapterPosition()).getGenero());
                intent.putExtra("Medicamentos", dataList.get(holder.getAdapterPosition()).getMedicamento());
                intent.putExtra("Enfermedad", dataList.get(holder.getAdapterPosition()).getEnfermedad());
                intent.putExtra("Rut", dataList.get(holder.getAdapterPosition()).getRut());
                intent.putExtra("Id", dataList.get(holder.getAdapterPosition()).getIdPaciente());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList(ArrayList<Paciente> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }


    private void temperatura(TextView recTpaciente, int position) {
        DatabaseReference pacReference = FirebaseDatabase.getInstance().getReference("Paciente");
        pacReference.orderByChild("rut").equalTo(dataList.get(position).getRut()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean adv = true;

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    int tempArduino = Integer.parseInt(dataList.get(position).getTemperatura());
                    if (!(tempArduino >= 35 && tempArduino <= 38)) {
                        adv = false;
                        break;
                    }
                }

                int color = adv ? R.color.verde : R.color.rojo;
                recTpaciente.setTextColor(ContextCompat.getColor(context, color));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void pulso(TextView recPpaciente, int position) {
        DatabaseReference pacReference = FirebaseDatabase.getInstance().getReference("Paciente");

        pacReference.orderByChild("rut").equalTo(dataList.get(position).getRut()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean adv = true;

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    int pulsoArduino = Integer.parseInt(dataList.get(position).getRitmoCardiaco());

                    if (!(pulsoArduino >= 73 && pulsoArduino <= 104)) {
                        adv = false;
                        break;
                    }
                }

                int color = adv ? R.color.verde : R.color.rojo;
                recPpaciente.setTextColor(ContextCompat.getColor(context, color));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void saturacion(TextView recOpaciente, int position) {
        DatabaseReference pacReference = FirebaseDatabase.getInstance().getReference("Paciente");

        pacReference.orderByChild("rut").equalTo(dataList.get(position).getRut()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean adv = true;

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    int satArduino = Integer.parseInt(dataList.get(position).getSaturacionOxigeno());;

                    if (!(satArduino >= 89 && satArduino <= 120)) {
                        adv = false;
                        break;
                    }
                }

                int color = adv ? R.color.verde : R.color.rojo;
                recOpaciente.setTextColor(ContextCompat.getColor(context, color));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}
class SPaciente extends RecyclerView.ViewHolder{

    TextView recNpaciente, recApaciente, recTpaciente, recPpaciente, recOpaciente;
    CardView recCard;
    public SPaciente(@NonNull View itemView) {
        super(itemView);
        recCard = itemView.findViewById(R.id.recCard);
        recNpaciente = itemView.findViewById(R.id.recNpaciente);
        recApaciente = itemView.findViewById(R.id.recApaciente);
        recTpaciente = itemView.findViewById(R.id.recTpaciente);
        recPpaciente = itemView.findViewById(R.id.recPpaciente);
        recOpaciente = itemView.findViewById(R.id.recOpaciente);
    }
}