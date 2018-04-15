package com.gameobjects;

import java.awt.Graphics;
import java.awt.Point;

import com.engine.Game;
import com.enumerations.BlockType;
import com.enumerations.SpriteType;

public class Block extends GameObject {
    
    private BlockType blockType;
    private Point gridPosition;
    
    private Unit unit;
    
    public Block(Point worldPos, Point gridPosition, BlockType blockType, SpriteType type) {
        super(worldPos, type);
        
        this.blockType = blockType;
        this.gridPosition = gridPosition;
    }

    public void tick() {}
    
    public void render(Graphics g) {
        if(this.isVisible) {
            g.drawImage(defaultStaticSprite, worldPosition.x, worldPosition.y, null);
        }
    }
    
    public void changeBlock(BlockType bt, SpriteType st) {
        this.blockType = bt;
        this.defaultStaticSprite = Game.instance.getSpriteStorage().getSprite(st);
    }
    
    // ------------- GETTERS & SETTERS ----------------
    
    public BlockType getBlocktype() { return blockType; }
    public void setBlocktype(BlockType blocktype) { this.blockType = blocktype; }
    
    public Point getGridPosition() { return gridPosition; }
    public void setGridPosition(Point gridPosition) { this.gridPosition = gridPosition; }
    
    public void setUnit(Unit u) { this.unit = u; }
    public Unit getUnit() { return this.unit; }
}

