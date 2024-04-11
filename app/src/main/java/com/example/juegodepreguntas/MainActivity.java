package com.example.juegodepreguntas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mijuegodepalabras.R;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button answer1Button;
    private Button answer2Button;
    private Button answer3Button;
    private TextView resultTextView;

    private String[] questions = {
            "¿Qué es Android Studio?",
            "¿Cuál es el lenguaje de programación principal utilizado en Android Studio?",
            "¿Qué función realiza el emulador de Android en Android Studio?",
            "¿Qué es un proyecto en Android Studio?",
            "¿Cuál es el propósito del archivo AndroidManifest.xml en un proyecto de Android?",
            "¿Qué es Gradle en Android Studio?",
            "¿Qué es un fragmento en Android Studio?",
            "¿Qué es la depuración en Android Studio?",
            "¿Qué es un widget en Android Studio?",
            "¿Qué es ADB en Android Studio?"
    };

    private String[] answers = {
            "Es un entorno de desarrollo integrado (IDE) utilizado para el desarrollo de aplicaciones Android.",
            "Java y Kotlin son los principales lenguajes de programación utilizados en Android Studio.",
            "El emulador de Android simula un dispositivo Android en el que puedes probar y depurar tus aplicaciones sin necesidad de un dispositivo físico.",
            "Es un conjunto de archivos y recursos que forman una aplicación específica.",
            "El archivo AndroidManifest.xml describe la estructura de la aplicación y define sus componentes, como actividades, servicios y permisos necesarios.",
            "Es una herramienta de automatización de compilación que se utiliza para compilar, construir y empaquetar aplicaciones Android en Android Studio.",
            "Es una parte modular y reutilizable de la interfaz de usuario de una aplicación Android.",
            "Es el proceso de identificar y corregir errores en una aplicación Android utilizando herramientas proporcionadas por Android Studio.",
            "Es un componente de la interfaz de usuario que se puede colocar en la pantalla de inicio o en otras pantallas de inicio de una aplicación Android.",
            "Es una herramienta de línea de comandos que se utiliza para comunicarse con un dispositivo Android conectado, para instalar y depurar aplicaciones, entre otras funciones."
    };

    private String[][] incorrectAnswers = {
            {"Android Studio es un sistema operativo para teléfonos inteligentes", "Android Studio es una red social para desarrolladores de aplicaciones."},
            {"HTML", "C++"},
            {"Reproduce música en dispositivos Android.", "Controla el brillo de la pantalla en dispositivos Android."},
            {"Un proyecto es una colección de videos tutoriales en Android Studio.", "Un proyecto es una lista de reproducción de música en Android Studio."},
            {"El archivo AndroidManifest.xml almacena fotos de perfil de usuarios.", "El archivo AndroidManifest.xml es un registro de errores de la aplicación."},
            {"Gradle es un editor de texto en Android Studio.", "Gradle es un reproductor de música en Android Studio."},
            {"Un fragmento es un tipo de fuente en Android Studio.", "Un fragmento es un archivo de audio en Android Studio."},
            {"La depuración es el proceso de edición de videos en Android Studio.", "La depuración es el proceso de navegación web en Android Studio."},
            {"Un widget es un tipo de fuente en Android Studio.", "Un widget es un tipo de archivo de audio en Android Studio."},
            {"ADB es un navegador web en Android Studio.", "ADB es un editor de código en Android Studio."}
    };

    private int currentIndex = 0;
    private int correctAnswersCount = 0;
    private int incorrectAnswersCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answer1Button = findViewById(R.id.answer1Button);
        answer2Button = findViewById(R.id.answer2Button);
        answer3Button = findViewById(R.id.answer3Button);
        resultTextView = findViewById(R.id.resultTextView);

        showQuestion(currentIndex);

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0);
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });
    }

    private void showQuestion(int index) {
        questionTextView.setText(questions[index]);
        answer1Button.setText(answers[index]);
        answer2Button.setText(incorrectAnswers[index][0]);
        answer3Button.setText(incorrectAnswers[index][1]);
    }

    private void checkAnswer(int selectedAnswerIndex) {
        if (selectedAnswerIndex == 0 && answer1Button.getText().equals(answers[currentIndex])) {
            correctAnswersCount++;
            Toast.makeText(this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show();
        } else {
            incorrectAnswersCount++;
            Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
        }

        if (currentIndex < questions.length - 1) {
            currentIndex++;
            showQuestion(currentIndex);
        } else {
            showScoreDialog();
        }
    }

    private void showScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Puntuación");
        double score = (double) correctAnswersCount / questions.length * 100;
        String message = String.format("Tu puntuación es: %.2f%%", score);
        builder.setMessage(message);
        builder.setPositiveButton("Intentar de nuevo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
            }
        });
        builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void resetGame() {
        currentIndex = 0;
        correctAnswersCount = 0;
        incorrectAnswersCount = 0;
        showQuestion(currentIndex);
        resultTextView.setText("");
    }
}
