package com.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.enumerations.GameState;
import com.gameobjects.Unit;

public class KeyInput extends KeyAdapter {

    private Map<Integer, String> buttons = new HashMap<Integer, String>();
    private Map<Integer, String> keyBinds = new HashMap<Integer, String>();
    
    public KeyInput() {
        
        // bind keys
        this.keyBinds.put(KeyEvent.VK_A, "LEFT");
        this.keyBinds.put(KeyEvent.VK_LEFT, "LEFT");
        
        this.keyBinds.put(KeyEvent.VK_D, "RIGHT");
        this.keyBinds.put(KeyEvent.VK_RIGHT, "RIGHT");
        
        this.keyBinds.put(KeyEvent.VK_W, "JUMP");
        this.keyBinds.put(KeyEvent.VK_UP, "JUMP");
        
        this.keyBinds.put(KeyEvent.VK_S, "DOWN");
        this.keyBinds.put(KeyEvent.VK_DOWN, "DOWN");
        
        this.keyBinds.put(KeyEvent.VK_SPACE, "ATTACK");
        
        this.keyBinds.put(KeyEvent.VK_E, "ACTION");
        this.keyBinds.put(KeyEvent.VK_ENTER, "ACTION");
        
        this.keyBinds.put(KeyEvent.VK_F, "CAST");
        this.keyBinds.put(KeyEvent.VK_CONTROL, "CAST");
    }

    public void keyPressed(KeyEvent e) {

        // get the pressed key 
        int key = e.getKeyCode();
        
        if(buttons.containsKey(key)) return;
        buttons.put(key, this.keyBinds.get(key));

        // -------------- HANDLE INPUTS ------------------

        if(Game.instance.getGamestate() == GameState.MAINMENU) this.handleKeysInMenu(e);
        else if(Game.instance.getGamestate() == GameState.INGAME) this.handleKeysInGame(e);
        else if(Game.instance.getGamestate() == GameState.PAUSEMENU) this.handleKeysInPauseMenu(e);
        
    }

    public void keyReleased(KeyEvent e) {
        
        // get the pressed key 
        int key = e.getKeyCode();

        String cmd = this.keyBinds.get(key);
        
        // TODO: handle keys here
        
        buttons.remove(key);
    }

    private void handleKeysInGame(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(keyBinds.containsKey(key)) {
            String cmd = this.keyBinds.get(key);

            // TODO: handle keys
            
        }
        
        // debug keys ingame.
        if(key == KeyEvent.VK_F1) {
            Game.drawDebugInfo = !Game.drawDebugInfo;
        } else if(key == KeyEvent.VK_F2) {
            Game.drawCameraRect = !Game.drawCameraRect;
        } else if(key == KeyEvent.VK_F3) {
            Game.isPaused = !Game.isPaused;
        } else if(key == KeyEvent.VK_ESCAPE) {
            Game.instance.setGamestate(GameState.PAUSEMENU);
            Game.isPaused = true;
        }
    }

    private void handleKeysInPauseMenu(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_ESCAPE) {
            Game.instance.setGamestate(GameState.INGAME);
            Game.isPaused = false;
        }
    }
    
    private void handleKeysInMenu(KeyEvent e) {
        int key = e.getKeyCode();   
        
        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }
}
