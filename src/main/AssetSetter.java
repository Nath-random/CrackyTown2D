package main;

import entity.NPC_OldMan;
import object.OBJ_Key;
import object.OBJ_Door;
import object.OBJ_Chest;
import object.OBJ_SlowBoots;
import object.OBJ_FastBoots;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

//        gp.obj[0] = new OBJ_Key(gp);
//        gp.obj[0].worldX = 25 * gp.tileSize;
//        gp.obj[0].worldY = 24 * gp.tileSize;
//
//        gp.obj[1] = new OBJ_Key(gp);
//        gp.obj[1].worldX = 13 * gp.tileSize;
//        gp.obj[1].worldY = 42 * gp.tileSize;
//
//        gp.obj[2] = new OBJ_Key(gp);
//        gp.obj[2].worldX = 85 * gp.tileSize;
//        gp.obj[2].worldY = 22 * gp.tileSize;
//
//        gp.obj[3] = new OBJ_Chest(gp);
//        gp.obj[3].worldX = 42 * gp.tileSize;
//        gp.obj[3].worldY = 23 * gp.tileSize;
//
//        gp.obj[4] = new OBJ_Chest(gp);
//        gp.obj[4].worldX = 54 * gp.tileSize;
//        gp.obj[4].worldY = 12 * gp.tileSize;
//
//        gp.obj[5] = new OBJ_FastBoots(gp);
//        gp.obj[5].worldX = 59 * gp.tileSize;
//        gp.obj[5].worldY = 55 * gp.tileSize;
//
//        gp.obj[6] = new OBJ_Door(gp);
//        gp.obj[6].worldX = 75 * gp.tileSize;
//        gp.obj[6].worldY = 61 * gp.tileSize;
//
//        gp.obj[7] = new OBJ_Door(gp);
//        gp.obj[7].worldX = 75 * gp.tileSize;
//        gp.obj[7].worldY = 59 * gp.tileSize;
//
//        gp.obj[8] = new OBJ_Door(gp);
//        gp.obj[8].worldX = 75 * gp.tileSize;
//        gp.obj[8].worldY = 57 * gp.tileSize;
//
//        gp.obj[9] = new OBJ_SlowBoots(gp);
//        gp.obj[9].worldX = 76 * gp.tileSize;
//        gp.obj[9].worldY = 52 * gp.tileSize;

//        gp.obj[10] = new OBJ_SlowBoots(gp);
//        gp.obj[10].worldX = 60 * gp.tileSize;
//        gp.obj[10].worldY = 48 * gp.tileSize;

//        gp.obj[11] = new OBJ_FastBoots(gp);
//        gp.obj[11].worldX = 62 * gp.tileSize;
//        gp.obj[11].worldY = 48 * gp.tileSize;

    }

    public void setNPC() {

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 45;
        gp.npc[0].worldY = gp.tileSize * 45;


        for (int i = 1; i < 500; i++) {
            gp.npc[i] = new NPC_OldMan(gp);
            gp.npc[i].worldX = gp.tileSize * 45;
            gp.npc[i].worldY = gp.tileSize * 45;
        }


    }

}
