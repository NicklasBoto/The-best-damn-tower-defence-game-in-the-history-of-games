package edu.chl.hajo.td.model;

import edu.chl.hajo.td.model.creeps.Creep;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 *    A Wave of creeps
 *    - All creeps of same kind
 *    - All follow same path.
 *
 */

public class Wave {
    private final int size;
    private final long startDelay;
    private final long spawnDelay;

    private long timer;
    private boolean readyToSpawn = true; 
    private boolean startDelayed = false;
    private boolean gottenStartTime = false;
    private int spawnedCreepers;

    private final Creep prototype;

    @Getter
    private ArrayList<Creep> creeps;

    public Wave(int size, long spawnDelay, long startDelay, Creep prototype) {
        this.size = size;
        this.startDelay = startDelay;
        this.spawnDelay = spawnDelay;
        this.prototype = prototype;
        this.creeps = new ArrayList<Creep>();
        this.spawnedCreepers = 0;
    }

    public void spawn(long now) {
        if (!gottenStartTime) {
            timer = now;
            gottenStartTime = true;
        }
        if (now - timer > startDelay) {
            startDelayed = true;
        }
        
        if (startDelayed && spawnedCreepers < size) {
            if(readyToSpawn) {
                creeps.add(clonePrototype());
                spawnedCreepers++;

                readyToSpawn = false;
                timer = now;
            }
            if(now - timer > spawnDelay) {
                readyToSpawn = true;
            }
        }
    }
    
    public Creep clonePrototype () {
        return new Creep(prototype.getPath());
    }

    public void equalsmove(){
        for (int i = creeps.size()-1; i >= 0; i--){
            if (creeps.get(i).getFinished() || creeps.get(i).getDead()){
                creeps.remove(creeps.get(i));
            } else {
                creeps.get(i).move();
            }
        }
    }



}
