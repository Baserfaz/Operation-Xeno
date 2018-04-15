package com.data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.engine.Game;
import com.enumerations.BlockType;
import com.enumerations.SpriteType;
import com.gameobjects.Block;
import com.utilities.Util;

public class Level {

    private List<Block> blocks = new ArrayList<Block>();
    
    private int height = 10;
    private int width = 10;
    private int margin = 5;
    
    public Level() {
        
        int size = Game.SPRITEGRIDSIZE * Game.SPRITESIZEMULT;
        
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                
                Point wPos = new Point(x * size + x * margin, y * size + y * margin);
                Point tilePos = new Point(x, y);
                
                Block block = new Block(wPos, tilePos, BlockType.FLOOR, SpriteType.CONCRETE_FLOOR);
                blocks.add(block);
                
            }
        }
        
    }
    
    public void init() {
        
        System.out.println("Initializing level!");
        
        // create units
        Game.instance.getUnitManager().createUnits(10);
        
    }
    
    private boolean isValidBlock(Block block) {
        if(block.getBlocktype() == BlockType.FLOOR && block.getUnit() == null) {
            return true;
        } else {
            return false;
        }
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
