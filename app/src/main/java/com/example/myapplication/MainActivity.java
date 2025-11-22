package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * A atividade principal da aplicação.
 */
public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView messageTextView;
    private Button changeMessageButton;

    private ArrayList<String> messages = new ArrayList<>();
    private int[] images = {
        R.drawable.oip,
        R.drawable.baixados_1,
        R.drawable.op_1112_696x394,
        R.drawable.portgas_d_ace_hat_raining_one_piece_anime_18200_1200x675,
        R.drawable.collage_maker_05_jul_2023_12_38
    };


    /**
     * Chamado quando a atividade é criada pela primeira vez.
     *
     * @param savedInstanceState Se a atividade estiver sendo reinicializada após
     *     ter sido desligada anteriormente, este Bundle contém os dados que ela forneceu mais
     *     recentemente em {@link #onSaveInstanceState}.  <b><i>Nota: Caso contrário, é nulo.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        messageTextView = findViewById(R.id.messageTextView);
        changeMessageButton = findViewById(R.id.changeMessageButton);

        messages.add("One Piece: uma obra de arte que transcende gerações. Simplesmente o melhor!");
        messages.add("A cada episódio, uma nova emoção. One Piece é incomparável nos dias de hoje.");
        messages.add("A jornada de Luffy e seus companheiros é a prova de que a amizade e a persistência conquistam tudo. Um dos melhores animes da atualidade!");
        messages.add("Oda é um gênio! A construção de mundo em One Piece é algo que poucos animes conseguem alcançar.");
        messages.add("Assistir One Piece é uma experiência única. Definitivamente, um dos melhores animes já feitos.");

        changeMessageAndImage(); // Exibe a primeira imagem e frase ao abrir

        changeMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMessageAndImage();
            }
        });
    }

    private void changeMessageAndImage() {
        Random random = new Random();
        int messageIndex = random.nextInt(messages.size());
        int imageIndex = random.nextInt(images.length);

        messageTextView.setText(messages.get(messageIndex));
        imageView.setImageResource(images[imageIndex]);
    }
}
