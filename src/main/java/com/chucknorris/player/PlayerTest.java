package com.chucknorris.player;

/**
 * Clase dummy para verificar que pueden correr los test y verificar que el proyecto lo pudieron importar bien
 */
public class PlayerTest {
    public PlayerTest(boolean value) {
        if (value) {
            System.out.println("pepe");
        } else
            try {
                System.out.println("sarlanga");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
