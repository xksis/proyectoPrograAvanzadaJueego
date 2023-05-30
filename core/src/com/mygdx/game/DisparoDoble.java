package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class DisparoDoble implements Potenciador {
    private int estado;
    private Sprite spr;
    private int x;
    private int y;
    private int xSpeed;
    private int cantDisparos = 0;

    public DisparoDoble() {
    }

    public DisparoDoble(int x, int y, int size, int xSpeed, Texture tx) {
        spr = new Sprite(tx);
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;

        // Validar que el borde de la esfera no quede fuera
        if (x - size < 0)
            this.x = x + size;
        if (x + size > Gdx.graphics.getWidth())
            this.x = x - size;
        if (y - size < 0)
            this.y = y + size;
        if (y + size > Gdx.graphics.getHeight())
            this.y = y - size;

        spr.setPosition(x, y);
    }

    public void update() {
        x += getXSpeed();

        if (x + getXSpeed() < 0 || x + getXSpeed() + spr.getWidth() > Gdx.graphics.getWidth())
            setXSpeed(getXSpeed() * -1);
        spr.setPosition(x, y);
    }

    public Rectangle getArea() {
        return spr.getBoundingRectangle();
    }

    public void draw(SpriteBatch batch) {
        spr.draw(batch);
    }

    public int getEstado() {
        return this.estado;
    }

    public int getCantDisparos() {
        return this.cantDisparos;
    }

    public void disparo() {
        this.cantDisparos += 1;
        desactivarPotenciador();
    }

    public void desactivarPotenciador() {
        if (getCantDisparos() == 10) {
            desactiva();
            this.cantDisparos = 0;
        }
    }

    public int getXSpeed() {
        return this.xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void activa() {
        this.estado = ACTIVO;
    }

    public void desactiva() {
        this.estado = INACTIVO;
    }
}
