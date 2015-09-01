package com.questtodo.questtodo.utils;

import com.questtodo.questtodo.R;

/**
 * Created by eunjooim on 15. 8. 30..
 */
public class ResourceId {

    public int getTypeResource(int type) {
        int[] typeArray = new int[] {R.drawable.type_green, R.drawable.type_blue, R.drawable.type_beige,
                R.drawable.type_orange, R.drawable.type_grey, R.drawable.type_green};
        return typeArray[type];
    }

    public int getTileResource(int type) {
        int[] typeArray = new int[] {R.drawable.tile_green, R.drawable.tile_blue, R.drawable.tile_beige,
                R.drawable.tile_orange, R.drawable.tile_grey, R.drawable.tile_green};
        return typeArray[type];
    }

    public int getMonsterResource(int monster) {
        int[] monsterArray = new int[]{R.drawable.monster_bat, R.drawable.monster_bunny, R.drawable.monster_cloud,
                R.drawable.monster_frog, R.drawable.monster_hedgehog, R.drawable.monster_lava,
                R.drawable.monster_lava_g, R.drawable.monster_slime, R.drawable.monster_slime_b,
                R.drawable.monster_slime_g, R.drawable.monster_spider, R.drawable.monster_sun,
                R.drawable.monster_zombie};

        return monsterArray[monster];
    }

}
