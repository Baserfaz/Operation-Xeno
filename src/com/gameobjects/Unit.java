package com.gameobjects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.data.Health;
import com.enumerations.ActorState;
import com.enumerations.Direction;
import com.enumerations.SpriteType;
import com.utilities.RenderUtils;

public class Unit extends GameObject {
    
    protected String name;
    protected Health HP;
    
    protected BufferedImage frame;
    
    protected ActorState actorState = ActorState.IDLING;
    protected Direction facingDirection = Direction.EAST;
    protected Rectangle attackBox;
    
    protected int attackDamage = 1;
    
    public Unit(String name, Point worldPos, SpriteType spriteType, int hp, int damage) {
        super(worldPos, spriteType);
        
        this.name = name;
        this.attackDamage = damage;
        this.HP = new Health(hp, this);
    }
    
    public void tick() {}
    
    public void render(Graphics g) {
        if(this.isVisible) {
            
            BufferedImage img = this.defaultStaticSprite;
            
            if(this.facingDirection == Direction.EAST) {
                g.drawImage(img, this.worldPosition.x, this.worldPosition.y, null);
            } else if(this.facingDirection == Direction.WEST) {
                RenderUtils.renderSpriteFlippedHorizontally(img, this.worldPosition, g);
            } 
        }
    }
    
    public void onDeath() {
        this.deactivate();
    }
    
    // --------- GETTERS & SETTERS --------
    public Health getHP() { return this.HP; }
    public String getName() { return this.name; }
    public Direction getFacingDirection() { return facingDirection; }
    public void setFacingDirection(Direction facingDirection) { this.facingDirection = facingDirection; }
    public ActorState getActorState() { return this.actorState; }

}
