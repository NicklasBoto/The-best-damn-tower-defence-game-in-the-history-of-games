package edu.chl.hajo.td.model;

import edu.chl.hajo.td.model.creeps.AbstractCreep;
import lombok.Getter;

import java.util.ArrayList;

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

    private final AbstractCreep prototype;

    @Getter
    private ArrayList<AbstractCreep> creeps;

    public Wave(int size, long spawnDelay, long startDelay, AbstractCreep prototype) {
        this.size = size;
        this.startDelay = startDelay;
        this.spawnDelay = spawnDelay;
        this.prototype = prototype;
        this.creeps = new ArrayList<AbstractCreep>();
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

    public AbstractCreep clonePrototype () {
        return prototype.copy();
    }

    public void equalsmove(){
        for (int i = creeps.size()-1; i >= 0; i--){
            if (creeps.get(i).isFinished() || creeps.get(i).isDead()){
                creeps.remove(creeps.get(i));
            } else {
                creeps.get(i).move();
            }
        }
    }
}
