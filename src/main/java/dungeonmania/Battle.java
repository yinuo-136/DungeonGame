package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;

import dungeonmania.buildableEntity.Bow;
import dungeonmania.buildableEntity.Shield;
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
        double bow_multiplication = 1;
        double shield_defense = 0;
        double sword_attack = 0;
        List <ItemResponse> itemsUsed = new ArrayList<>();
        // invincible player immediately wins battle
        // waiting for invincible potion implementation
        if (player.isInvincible()) {
            itemsUsed.add(new ItemResponse("needs to be changed", "invincibility_potion"));
            rounds.add(new RoundResponse( 0, -1 * enemy.getHealth(), itemsUsed));
            this.info.getEntityMap().remove(enemy.getId());
            return new BattleResponse(enemy.getClass().getSimpleName(), rounds, initial_player_health, initial_enemy_health);
        }
        // Check for the existence of battle items and store some info
        List<InvItem> itemList = info.getItemList();
        for (InvItem item : itemList) {
            if (item instanceof Sword) {
                Sword sword = (Sword) item;
                sword_attack = sword.getAttackBonus();
                itemsUsed.add(new ItemResponse(sword.getId(), "Sword"));  
                sword.use();
            }
            if (item instanceof Shield) {
                Shield shield = (Shield) item;
                shield_defense = shield.getDefenseBonus();
                itemsUsed.add(new ItemResponse(shield.getId(), "Shield"));  
                shield.use();
            }
            if (item instanceof Bow) {
                Bow bow = (Bow) item;
                bow_multiplication = 2;
                itemsUsed.add(new ItemResponse(bow.getId(), "Bow"));  
                bow.use();    
            }
        }

        // rounds continue until player or enemy dies
        while (current_player_health > 0 && current_enemy_health > 0){
            current_enemy_health = enemy.getHealth() - ((bow_multiplication * (player.getAttack() + sword_attack)) / 5);
            current_player_health = player.getHealth() - ((enemy.getDamage() - shield_defense) / 10);
            player.setHealth(current_player_health);
            enemy.setHealth(current_enemy_health);
            round_response = new RoundResponse(player.getHealth() - previous_player_health, enemy.getHealth() - previous_enemy_health , itemsUsed);
            rounds.add(round_response);
            previous_enemy_health = enemy.getHealth();
            previous_player_health = player.getHealth();
        }
        // remove any dead entities
        if (enemy.getHealth() <= 0)
            this.info.getEntityMap().remove(enemy.getId());
            
        info.getPlayer().setEnemiesMet(info.getPlayer().getEnemiesMet() + 1);
        
        if (player.getHealth() <= 0)
        this.info.getEntityMap().remove(player.getId());
        // return battle response
        return new BattleResponse(enemy.getClass().getSimpleName(), rounds, initial_player_health, initial_enemy_health);
    }
}
