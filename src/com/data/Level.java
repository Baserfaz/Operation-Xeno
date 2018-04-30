package com.data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.engine.Game;
import com.enumerations.BlockType;
import com.enumerations.Direction;
import com.enumerations.SpriteType;
import com.gameobjects.Block;
import com.utilities.Util;

public class Level {

    private List<Block> blocks = new ArrayList<Block>();
    
    private int height = 100;
    private int width = 100;
    private int margin = 0;
    
    public Level() {
       this.createInitialLevel();
       this.createRooms();
    }
    
    private void createRooms() {
        
        System.out.println("Creating rooms.");
        
        int roomCount = 5;
        
        for(int i = 0; i < roomCount; i++) {
            
           System.out.println("Creating room: " + i); 
            
            // TODO: get random tile,
            // make it the start point and add walls to neighbors.
           
            
        }
        
    }
    
    private void createInitialLevel() {
        
        System.out.println("Creating initial level.");
        
        int size = Game.SPRITEGRIDSIZE * Game.SPRITESIZEMULT;
        
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                
                Point wPos = new Point(x * size + x * margin, y * (size / 2) + y * margin);
                Point tilePos = new Point(x, y);
                
                if(y % 2 == 0) { wPos.x += size / 2 + margin / 2; }
                
                Block block = new Block(wPos, tilePos, BlockType.OUTDOOR, SpriteType.CONCRETE_FLOOR);
                blocks.add(block);
                     
            }
        }   
    }
    
    private boolean isValidBlock(Block block) {
        return (block.getUnit() == null) ? true : false;
    }
    
    public void init() {
        
        System.out.println("Initializing level!");
        
        // create units
        //Game.instance.getUnitManager().createUnits(10);
        
    }
    
    public List<Block> getNeighbors(Block block) {
        List<Block> ns = new ArrayList<Block>();
        
        for(Direction dir : Direction.values()) {
            ns.add(this.getNeighbor(block, dir));
        }
        
        return ns;
    }
    
    public Block getNeighbor(Block block, Direction dir) {
        Block b = null;
        
        int x = block.getGridPosition().x;
        int y = block.getGridPosition().y;
        
        switch(dir) {
            case EAST:
                x += 1;
                break;
            case NORTH:
                y -= 1;
                break;
            case NORTH_EAST:
                y -= 1;
                x += 1;
                break;
            case NORTH_WEST:
                y -= 1;
                x -= 1;
                break;
            case SOUTH:
                y += 1;
                break;
            case SOUTH_EAST:
                y += 1;
                x += 1;
                break;
            case SOUTH_WEST:
                y += 1;
                x -= 1;
                break;
            case WEST:
                x -= 1;
                break;
        }
        
        b = this.getBlock(new Point(x, y));
        
        return b;
    }
    
    public Block getBlock(Point pos) {
        Block block = null;
        
        for(Block b : this.blocks) {
            if(b.getGridPosition().equals(pos)) {
                block = b;
                break;
            }
        }
        
        return block;
    }
    
    public Block getRandomValidBlock() {
        
        List<Block> bs = new ArrayList<Block>();
        
        for(Block b : this.blocks) {
            if(this.isValidBlock(b)) {
                bs.add(b);
            }
        }
        
        return this.getRandomBlock(bs);
    }
    
    public List<Block> getBlocksOfType(BlockType type) {
        List<Block> bs = new ArrayList<Block>();
        
        for(Block b : this.blocks) {
            if(b.getBlocktype() == type) {
                bs.add(b);
            }
        }
        
        return bs;
    }
    
    public Block getRandomBlockOfType(BlockType type) {
        List<Block> bs = new ArrayList<Block>();
        
        for(Block b : this.blocks) {
            if(b.getBlocktype() == type) {
                bs.add(b);
            }
        }
        
        return this.getRandomBlock(bs);
    }
    
    public Block getRandomBlock(List<Block> bs) {
        return bs.get(Util.GetRandomInteger(0, bs.size() - 1));
    }
    
    public Block getRandomBlock() {
        return this.blocks.get(Util.GetRandomInteger(0, blocks.size() - 1));
    }
    
    public List<Block> getBlocks() {
        return this.blocks;
    }
    
    
}
