package com.gameobjects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;

import com.engine.Game;
import com.enumerations.BlockType;
import com.enumerations.Direction;
import com.enumerations.SpriteType;

public class Block extends GameObject {
    
    private BlockType blockType;
    private Point gridPosition;
    
    protected BufferedImage defaultWallNW;
    protected BufferedImage defaultWallNE;
    
    private HashMap<Direction, Boolean> walls = new HashMap<Direction, Boolean>();
    
    private Unit unit;
    
    public Block(Point worldPos, Point gridPosition, BlockType blockType, SpriteType type) {
        super(worldPos, type);
        
        this.blockType = blockType;
        this.gridPosition = gridPosition;
        
        // load sprites
        this.defaultWallNW = Game.instance.getSpriteStorage().getSprite(SpriteType.WALL_NW);
        this.defaultWallNE = Game.instance.getSpriteStorage().getSprite(SpriteType.WALL_NE);
        
        // initialize walls
        walls.put(Direction.NORTH_WEST, false);
        walls.put(Direction.NORTH_EAST, false);
        walls.put(Direction.SOUTH_EAST, false);
        walls.put(Direction.SOUTH_WEST, false);
        
    }

    public void tick() {}
    
    public void render(Graphics g) {
        if(this.isVisible) {
            
            // draw floor/base
            g.drawImage(defaultStaticSprite, worldPosition.x, worldPosition.y, null);
            
            int size = (Game.SPRITEGRIDSIZE * Game.SPRITESIZEMULT) / 2;
            
            // draw walls
            for(Entry<Direction, Boolean> entry : walls.entrySet()) {
                
                // is this even needed here? doubt it.
                if(entry.getValue() == true) {
                    
                    switch(entry.getKey()) {
                    case NORTH_WEST:
                        g.drawImage(this.defaultWallNW, this.worldPosition.x, this.worldPosition.y - size, null);
                        break;
                    case NORTH_EAST:
                        g.drawImage(this.defaultWallNE, this.worldPosition.x, this.worldPosition.y - size, null);
                        break;
                    case SOUTH_WEST:
                        g.drawImage(this.defaultWallNE, this.worldPosition.x - size, this.worldPosition.y, null);
                        break;
                    case SOUTH_EAST:
                        g.drawImage(this.defaultWallNW, this.worldPosition.x + size, this.worldPosition.y, null);
                        break;
                    default:
                        break;
                    
                    }
                    
                }
                
            }
            
        }
    }
    
    public void changeBlock(BlockType bt, SpriteType st) {
        this.blockType = bt;
        this.defaultStaticSprite = Game.instance.getSpriteStorage().getSprite(st);
    }
    
    public void modifyWall(Direction dir, boolean b) {
        this.walls.put(dir, b);
    }
    
    // ------------- GETTERS & SETTERS ----------------
    
    public BlockType getBlocktype() { return blockType; }
    public void setBlocktype(BlockType blocktype) { this.blockType = blocktype; }
    
    public Point getGridPosition() { return gridPosition; }
    public void setGridPosition(Point gridPosition) { this.gridPosition = gridPosition; }
    
    public void setUnit(Unit u) { this.unit = u; }
    public Unit getUnit() { return this.unit; }
}

