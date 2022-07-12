package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;

import dungeonmania.movingEntity.Moving;
import dungeonmania.player.Player;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.response.models.RoundResponse;

public class Battle {
    private Player player;
    private Moving enemy;
    public Battle(Player player, Moving enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public BattleResponse start() {
        RoundResponse round_response = null;
        double previous_player_health = player.getHealth();
        double initial_player_health = player.getHealth();
        double previous_enemy_health = enemy.getHealth();
        double initial_enemy_health = enemy.getHealth();
        ArrayList<RoundResponse> rounds = new ArrayList<>();
        while (player.getHealth() > 0 && enemy.getHealth() > 0){
            enemy.attack(player);
            round_response = new RoundResponse(previous_player_health - player.getHealth(), previous_enemy_health, new ArrayList<ItemResponse>());
            rounds.add(round_response);
            previous_enemy_health = enemy.getHealth();
            previous_player_health = player.getHealth();
        }
        return new BattleResponse(enemy.getClass().getSimpleName(), rounds, initial_player_health, initial_enemy_health);
    }
}
