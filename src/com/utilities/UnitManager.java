package com.utilities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.enumerations.UnitType;
import com.data.Level;
import com.engine.Game;
import com.enumerations.SpriteType;
import com.gameobjects.Block;
import com.gameobjects.Unit;

public class UnitManager {

    private List<Unit> unitInstances;
    
    public UnitManager() {
        unitInstances = new ArrayList<Unit>();
    }

    public void createUnits(int enemyCount) {
        
        System.out.println("Creating units!");
        
        Level level = Game.instance.getLevel();
        
        // create enemies
        for(int i = 0; i < enemyCount; i++) {
            
            Block spawn = level.getRandomValidBlock();
            this.createUnitInstance("Enemy" + i + 1, spawn, UnitType.ENEMY_UNIT, 3, 1);
        }
        
        // create player units
        for(int i = 0; i < Game.MAXSQUADSIZE; i++) {
            
            Block spawn = level.getRandomValidBlock();
            this.createUnitInstance("Player" + i + 1, spawn, UnitType.PLAYER_UNIT, 3, 1);
            
        }
        
        System.out.println("Units created!");
        
    }
    
    private Unit createUnitInstance(String name, Block block, UnitType unitType, int health, int damage) {
        
        Unit unit = null;
        
        switch(unitType) {
        case ENEMY_UNIT:
            unit = new Unit(name, block.getWorldPosition(), SpriteType.ENEMY_UNIT, health, damage);
            break;
        case PLAYER_UNIT:
            unit = new Unit(name, block.getWorldPosition(), SpriteType.PLAYER_UNIT, health, damage);
            break;
        default:
            System.out.println("ActorManager::createEnemyInstance: unsupported enemy type: " + unitType);
            break;
        }
        
        if(unit != null) {
            this.unitInstances.add(unit);
            block.setUnit(unit);
        }
        
        return unit;
    }
    
    public void removeUnit(Unit go) {
        for(Unit actor : unitInstances) {
            if(actor.equals(go)) {
                unitInstances.remove(go);
                break;
            }
        }
    }
    
    // ---- GETTERS & SETTERS ----
    public List<Unit> getUnitInstances() { return unitInstances; }
}
