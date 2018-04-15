package com.engine;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.enumerations.BlockType;
import com.gameobjects.Unit;
import com.gameobjects.Block;
import com.gameobjects.GameObject;

public class Handler {

    private List<GameObject> objects = new ArrayList<GameObject>();

    public void tickGameObjects() {
        for(int i = 0; i < objects.size(); i++) {
            GameObject current = objects.get(i);
            if(current != null) current.tick();
        }
    }
    
    public void renderGameObjects(Graphics g) {
        
        // references
        List<Block> solidBlocks = new ArrayList<Block>();
        List<Unit> actors = new ArrayList<Unit>();
        
        Camera cam = Game.instance.getCamera();
        if(cam == null) return;
        
        // --------------------- calculate objs that camera sees ------------------------
        
        Rectangle camView = (Rectangle) cam.getCameraBounds().clone();
        
        int size = Game.SPRITEGRIDSIZE * Game.SPRITESIZEMULT;
        
        camView.x -= size;
        camView.width += 2 * size;
        
        camView.y -= size;
        camView.height += 2 * size;
        
        List<GameObject> objInView = new ArrayList<GameObject>();
        for(int i = 0; i < this.objects.size(); i++) {
            GameObject go = this.objects.get(i);
            if(go == null) continue;
            if(camView.contains(go.getHitbox())) {
                objInView.add(go);
                if(go.getIsEnabled() == false) go.activate();
            } else {
                // go.deactivate();
            }
        }
        
        // ---------------------- RENDER ---------------------------------
        
        // render all game objects 
        for(int i = 0; i < objInView.size(); i++) {
            GameObject current = objInView.get(i);
            
            // get actors
            if(current instanceof Unit) {
                actors.add((Unit) current);
                continue;
            }
            
            // get blocks
            if(current instanceof Block) {
                Block block = (Block) current;
                BlockType type = block.getBlocktype();
                solidBlocks.add(block);
            }
        }
        
        // render queue: back to front
        for(Block block : solidBlocks) { block.render(g); }
        for(Unit actor : actors) { actor.render(g); }
    }

    // ---- GETTERS & SETTERS ----
    public void AddObject(GameObject go) { this.objects.add(go); }	
    public void RemoveObject(GameObject go) { this.objects.remove(go); }
    public List<GameObject> getObjects() { return objects; }
    public void setObjects(List<GameObject> objects) { this.objects = objects; }
}
