// package dungeonmania;

// import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import static dungeonmania.TestUtils.getPlayer;
// import static dungeonmania.TestUtils.getEntities;
// import static dungeonmania.TestUtils.getInventory;
// import static dungeonmania.TestUtils.getGoals;
// import static dungeonmania.TestUtils.countEntityOfType;
// import static dungeonmania.TestUtils.getValueFromConfigFile;

// import java.util.ArrayList;
// import java.util.List;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;

// import dungeonmania.movingEntity.Mercenary;
// //import dungeonmania.movingEntity.Player;
// import dungeonmania.movingEntity.Spider;
// import dungeonmania.movingEntity.ZombieToast;
// import dungeonmania.player.Player;
// import dungeonmania.response.models.BattleResponse;
// import dungeonmania.response.models.DungeonResponse;
// import dungeonmania.response.models.EntityResponse;
// import dungeonmania.response.models.RoundResponse;
// import dungeonmania.staticEntities.Wall;
// import dungeonmania.util.Direction;
// import dungeonmania.util.Position;



// public class MovingEntityTests {
//     @Test
//     @DisplayName("Test the zombie and other mob basic movement")
//     public void testBasicMovement() {
//         // Test movement
//         Position startPos = new Position(2, 2);
//         ZombieToast zombie = new ZombieToast(startPos, 10, 0, "1");
//         Position expectPos = new Position(3, 2);

//         // Test zombie cannot pass through wall
//         // x x x
//         // x - -
//         // x x x
//         Wall w = new Wall(new Position(1, 1), "1");
//         Wall w2 = new Wall(new Position(2, 1), "2");
//         Wall w3 = new Wall(new Position(3, 1), "3");
//         Wall w4 = new Wall(new Position(1, 2), "4");
//         Wall w5 = new Wall(new Position(3, 3), "5");
//         Wall w6 = new Wall(new Position(2, 3), "6");
//         Wall w7 = new Wall(new Position(1, 3), "7");
//         zombie.move();
//         assertEquals(expectPos, zombie.getPosition());

//     }

//     @Test
//     @DisplayName("Test the zombie attack player") 
//     public void testZombieAttackPlayer() {
//         Position startPlayerPos = new Position(1, 1);
//         Position startZombiePos = new Position(1,1);
//         Player player = new Player(startPlayerPos, 10, 10);
//         ZombieToast zombie = new ZombieToast(startZombiePos, 10, 0, "1");
//         double playerhealth = player.getHealth();
//         double enemieshealth = zombie.getHealth();
//         zombie.attack(player);
//         assertEquals(playerhealth-(zombie.getDamage()/10), player.getHealth());
//         assertEquals(enemieshealth-(player.getAttack()/5), zombie.getHealth());
//     }

//     @Test
//     @DisplayName("Test spider movement")
//     public void testSpiderMovement(){
//         // test basic spider movement, without blocking
//         Position startPos = new Position(2, 2);
//         Spider spider = new Spider(startPos, 10, 0, "1");
//         Position expectPos = new Position(2, 1);
//         spider.move();
//         assertEquals(expectPos, spider.getPosition());
//         Position newExpectPos = new Position(3, 1);
//         spider.move();
//         assertEquals(newExpectPos, spider.getPosition());
//         for (int i = 0; i < 8; i++) {
//             spider.move();
//         }
//         assertEquals(expectPos, spider.getPosition());

//     }

//     @Test
//     @DisplayName("Test Mercenary movement")
//     public void testMercenaryMovement(){
//         // Test Mercenary follow player
//         // x x x
//         // x M - - P
//         // x x x
//         Wall w = new Wall(new Position(1, 1), "1");
//         Wall w2 = new Wall(new Position(2, 1), "2");
//         Wall w3 = new Wall(new Position(3, 1), "2");
//         Wall w4 = new Wall(new Position(1, 2), "3");
//         Wall w5 = new Wall(new Position(3, 3), "4");
//         Wall w6 = new Wall(new Position(2, 3), "5");
//         Wall w7 = new Wall(new Position(1, 3), "6");

//         Position startMPos = new Position(2, 2);
//         Position startPPos = new Position(5, 2);
//         Mercenary mercenary = new Mercenary(startMPos, 10, 0, 1, "1");
//         Player player = new Player(startPPos, 10, 0);
//         mercenary.move(player);
//         Position expectMPos = new Position(3, 2);
//         assertEquals(expectMPos, mercenary.getPosition());
//         mercenary.move(player);
//         Position expectMPos2 = new Position(4, 2);
//         mercenary.move(player);
//         assertEquals(expectMPos2, mercenary.getPosition());
//     }

//     @Test
//     @DisplayName("Test bribe Mercenary")
//     public void testBribeMercenary(){

//     }
// }
