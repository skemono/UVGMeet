package com.example.uvgmeet;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;

public class SwipeableContainer extends Container {

    private Container content;
    private Container swipeButtons;

    public SwipeableContainer(Container content, Component[] swipeButtons) {
        this.content = content;
        this.swipeButtons = new Container(new BoxLayout(BoxLayout.X_AXIS));

        for (Component button : swipeButtons) {
            this.swipeButtons.add(button);
        }

        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, content);
        add(BorderLayout.EAST, swipeButtons);

        // Agregar un evento para manejar el deslizamiento
        addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Abre los botones deslizando a la izquierda
                animateSwipeButtons(true);
            }
        });

        // Agregar un evento para cerrar los botones al hacer clic en el contenido
        content.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Cierra los botones deslizando a la derecha
                animateSwipeButtons(false);
            }
        });
    }

    // Método para animar la apertura o cierre de los botones deslizando
    private void animateSwipeButtons(boolean open) {
        int newX = open ? -swipeButtons.getWidth() : 0;
        int duration = 200; // Duración de la animación en milisegundos

        Transition t = createSwipeTransition(newX, duration);
        swipeButtons.setScrollAnimationSpeed(duration);
        swipeButtons.scrollRectToVisible(newX, 0, t);
    }

    // Método para crear una transición de deslizamiento
    private Transition createSwipeTransition(int newX, int duration) {
        return Transition.create(Transition.TYPE_MOVE, duration, this, "x", newX);
    }
}
