package by.paul.monsterservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MonsterControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @SneakyThrows
  void createEncounterIfAllParamsAllowed() {
    String encounter = "{\n"
        + "  \"monsterOwner\":\"admin\",\n"
        + "  \"difficulty\": \"HARD\",\n"
        + "  \"playersLevel\": [\"5 level priest\",\n"
        + "  \"5 level priest\",\n"
        + "  \"5 level priest\",\n"
        + "  \"5 level priest\"],\n"
        + "  \"mixedTypes\": false\n"
        + "}";

    this.mockMvc.perform(post("/encounter-generator")
        .contentType(MediaType.APPLICATION_JSON)
        .content(encounter))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @Test
  @SneakyThrows
  void createEncounterIfWithoutPlayersCharacters() {
    String encounter = "{\n"
        + "  \"monsterOwner\":\"admin\",\n"
        + "  \"difficulty\": \"HARD\",\n"
        + "  \"playersLevel\": [],\n"
        + "  \"mixedTypes\": false\n"
        + "}";

    this.mockMvc.perform(post("/encounter-generator")
        .contentType(MediaType.APPLICATION_JSON)
        .content(encounter))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @Test
  @SneakyThrows
  void forbiddenAddMonster() {
    String monster = "{\n"
        + "    \"monsterOwner\": \"Paul\",\n"
        + "    \"name\": \"not Large elemental\",\n"
        + "    \"meta\": \"Tiny elemental, neutral\",\n"
        + "    \"Armor_Class\": \"14 (Natural Armor)\",\n"
        + "    \"Hit_Points\": \"114 (12d10 + 48)\",\n"
        + "    \"Speed\": \"30 ft., swim 90 ft. \",\n"
        + "    \"STR\": \"18\",\n"
        + "    \"STR_mod\": \"(+4)\",\n"
        + "    \"DEX\": \"14\",\n"
        + "    \"DEX_mod\": \"(+2)\",\n"
        + "    \"CON\": \"18\",\n"
        + "    \"CON_mod\": \"(+4)\",\n"
        + "    \"INT\": \"5\",\n"
        + "    \"INT_mod\": \"(-3)\",\n"
        + "    \"WIS\": \"10\",\n"
        + "    \"WIS_mod\": \"(+0)\",\n"
        + "    \"CHA\": \"8\",\n"
        + "    \"CHA_mod\": \"(-1)\",\n"
        + "    \"Saving_Throws\": null,\n"
        + "    \"Skills\": null,\n"
        + "    \"Senses\": \"Darkvision 60 ft.,  Passive Perception 10\",\n"
        + "    \"Languages\": \"Aquan\",\n"
        + "    \"Challenge\": 1800,\n"
        + "    \"Traits\": \"<p><em><strong>Water Form.</strong></em> The elemental can enter a hostile creature's space and stop there. It can move through a space as narrow as 1 inch wide without squeezing. </p><p><em><strong>Freeze.</strong></em> If the elemental takes cold damage, it partially freezes; its speed is reduced by 20 feet until the end of its next turn.</p>\",\n"
        + "    \"Actions\": \"<p><em><strong>Multiattack.</strong></em> The elemental makes two slam attacks. </p><p><em><strong>Slam.</strong></em> <em>Melee Weapon Attack:</em> +7 to hit, reach 5 ft., one target. <em>Hit:</em> 13 (2d8 + 4) bludgeoning damage. </p><p><em><strong>Whelm (Recharge 4–6).</strong></em> Each creature in the elemental's space must make a DC 15 Strength saving throw. On a failure, a target takes 13 (2d8 + 4) bludgeoning damage. If it is Large or smaller, it is also grappled (escape DC 14). Until this grapple ends, the target is restrained and unable to breathe unless it can breathe water. If the saving throw is successful, the target is pushed out of the elemental's space.</p><p>The elemental can grapple one Large creature or up to two Medium or smaller creatures at one time. At the start of each of the elemental's turns, each target grappled by it takes 13 (2d8 + 4) bludgeoning damage. A creature within 5 feet of the elemental can pull a creature or object out of it by taking an action to make a DC 14 Strength and succeeding.</p>\",\n"
        + "    \"Legendary_Actions\": null,\n"
        + "    \"img_url\": \"https://media-waterdeep.cursecdn.com/avatars/thumbnails/0/84/315/315/636252736680781387.jpeg\"\n"
        + "}";

    this.mockMvc.perform(post("/monster")
        .contentType(MediaType.APPLICATION_JSON)
        .content(monster))
        .andExpect(status().isForbidden());
  }

  @Test
  @SneakyThrows
  void getMonstersBySearchCriteria() {
    String searchCriteria = "{\n"
        + "    \"monsterType\":\"\",\n"
        + "    \"monsterSize\":\"\",\n"
        + "    \"minMonsterChallenge\":\"0\",\n"
        + "    \"maxMonsterChallenge\":\"155000\",\n"
        + "    \"monsterOutlook\":\"\",\n"
        + "    \"source\":false,\n"
        + "    \"legendaryAction\":false,\n"
        + "    \"specialSkills\":true\n"
        + "}\n";

    this.mockMvc.perform(post("/monsterlibrary")
        .contentType(MediaType.APPLICATION_JSON)
        .content(searchCriteria))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andDo(print());
  }

  @Test
  @SneakyThrows
  void getMonsterByName() {
    this.mockMvc.perform(get("/monsterlibrary/Ape")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("Ape"))
        .andDo(print());
  }

  @Test
  @SneakyThrows
  void getMonsterByAuthor() {
    this.mockMvc.perform(get("/monsterlibrary")
        .param("author", "admin")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.[0].monsterName").value("Aboleth"))
        .andDo(print());
  }
}
