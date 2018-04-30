package com.engine;

import com.utilities.Mathf;
import com.utilities.Util;

import java.awt.Point;
import java.awt.Rectangle;

public class Camera {

    private float cameraPanSpeed = 0.2f;
    
    private Rectangle cameraBounds;
    private Point target = new Point();
    
    public Camera() {
        this.cameraBounds = new Rectangle(0, 0, Game.CAMERA_WIDTH, Game.CAMERA_HEIGHT);
    }

    public void tick() {
        
        // current position
        int camX = (this.cameraBounds.x + this.cameraBounds.width) / 2;
        int camY = (this.cameraBounds.y + this.cameraBounds.height) / 2;
      
        Point mPos = Util.calculateCameraPos(Game.instance.getMousePos());
        
        // new position
        target.x += mPos.x * this.cameraPanSpeed;
        target.y += mPos.y * this.cameraPanSpeed;
        
        // apply camera smoothing
        camX -= (target.x + camX) * Game.CAMERA_SMOOTH_MULT;
        camY -= (target.y + camY) * Game.CAMERA_SMOOTH_MULT * 2;
        
        this.Update((int)camX, (int)camY);
    }
    
    public void nudge(int xmax, int ymax) {
        int x = (int) (this.cameraBounds.x + (Mathf.randomRange(-1, 1) * xmax));
        int y = (int) (this.cameraBounds.y + (Mathf.randomRange(-1, 1) * ymax));
        this.Update(x, y);
    }
   
    public void Update(Point pos, int width, int height) { 
        cameraBounds.setBounds(pos.x, pos.y, width, height); 
    }
    public void Update(int x, int y) {
        cameraBounds.setBounds(x, y, Game.CAMERA_WIDTH, Game.CAMERA_HEIGHT);
    }
    
    // ------ GETTERS & SETTERS -------
    
    public Rectangle getCameraBounds() { return cameraBounds; }
    public Point getCameraCenterPosition() { return new Point(this.cameraBounds.x + this.cameraBounds.width / 2,
            this.cameraBounds.y + this.cameraBounds.height / 2); }
}
