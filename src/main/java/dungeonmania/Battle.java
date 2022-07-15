package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;

import dungeonmania.inventoryItem.InvItem;
import dungeonmania.inventoryItem.Sword;
import dungeonmania.movingEntity.Moving;
import dungeonmania.player.Player;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.response.models.RoundResponse;

public class Battle {
    private Player player;
    private Moving enemy;
    private DungeonInfo info;
    public Battle(Player player, Moving enemy, DungeonInfo info) {
        this.player = player;
        this.enemy = enemy;
        this.info = info;
    }

    public BattleResponse start() {
        RoundResponse round_response = null;
        double previous_player_health = player.getHealth();
        double initial_player_health = player.getHealth();
        double current_player_health = player.getHealth();
        double previous_enemy_health = enemy.getHealth();
        double current_enemy_health = enemy.getHealth();
        double initial_enemy_health = enemy.getHealth();
        ArrayList<RoundResponse> rounds = new ArrayList<>();
        int bow_multiplication = 1;
        int shield_defense = 0;
        int sword_attack = 0;
        String bow_id = null;
        String shield_id = null;
        String sword_id = null;
        // Check for the existence of battle items and store some info
        List<InvItem> itemList = info.getItemList();
        for (InvItem item : itemList) {
            if (item instanceof Sword) {
                Sword sword = (Sword) item;
                sword_attack = sword.getAttackBonus();
                sword.setDurability(sword.getDurability() - 1);
                sword_id = sword.getId();
            }
            // if (item instanceof Shield) {
            //     Shield shield = (Shield) item;
            //     shield_defense = shield.getDefense();
            //     shield.setDurability(shield.getDurability() - 1);
            //     shield_id = shield.getId;
            // }
            // if (item instanceof Bow) {
            //     Bow bow = (Bow) item;
            //     bow_multiplication = 2;
            //     bow_id = bow.getId;
            //     bow.setDurability(bow.getDurability() - 1);
            // }
        }
        // Items that will be used in battle
        ItemResponse bow_response = null;
        ItemResponse sword_response = null;
        ItemResponse shield_response = null;
        List <ItemResponse> itemsUsed = new ArrayList<>();
        // if (bow_id != null) {
        //     bow_response = new ItemResponse(bow_id, "Bow");
        //     itemsUsed.add(bow_response);
        // }
        // if (shield_id != null) {
        //     shield_response = new ItemResponse(shield_id, "Shield");
        //     itemsUsed.add(shield_response);
        // }
        if (sword_id != null) {
            sword_response = new ItemResponse(sword_id, "Sword");
            itemsUsed.add(sword_response);
        }
        
        // invincible player immediately wins battle
        // waiting for invincible potion implementation
        /*
        if (player.isInvincible()) {
            for ()
            rounds.add(new RoundResponse(previous_player_health - player.getHealth(), previous_enemy_health, new ArrayList<ItemResponse>()))
        }
        */
        // rounds continue until player or enemy dies
        while (current_player_health > 0 && current_enemy_health > 0){
            current_enemy_health = enemy.getHealth() - ((bow_multiplication * (player.getAttack() + sword_attack)) / 5);
            current_player_health = player.getHealth() - ((enemy.getDamage() - shield_defense) / 10);
            player.setHealth(current_player_health);
            enemy.setHealth(current_enemy_health);
            round_response = new RoundResponse(previous_player_health - player.getHealth(), previous_enemy_health, itemsUsed);
            rounds.add(round_response);
            previous_enemy_health = enemy.getHealth();
            previous_player_health = player.getHealth();
        }
        // remove any dead entities
        if (player.getHealth() <= 0)
            this.info.getEntityMap().remove(player.getId());
        if (enemy.getHealth() <= 0)
            this.info.getEntityMap().remove(enemy.getId());
        // return battle response
        return new BattleResponse(enemy.getClass().getSimpleName(), rounds, initial_player_health, initial_enemy_health);
    }
}
