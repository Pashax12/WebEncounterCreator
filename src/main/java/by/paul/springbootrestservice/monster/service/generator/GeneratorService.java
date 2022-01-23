package by.paul.springbootrestservice.monster.service.generator;


import by.paul.springbootrestservice.monster.dao.MonsterRepository;
import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.dto.MonsterMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GeneratorService {

  private final MonsterRepository monsterRepository;
  private final MonsterEncounterGenerator monsterEncounterGenerator;

  @Value("${url.path.monsterLibrary}")
  String path;

  public List<Monster> generateEncounter(EncounterBuilder encounterBuilder) {
    return monsterEncounterGenerator.monstersListGenerator(monsterRepository.
            findAllByMonsterChallengeBetweenAndMonsterOwnerOrderByMonsterId(
                encounterBuilder.getHoleFightExp() / 5,
                encounterBuilder.getHoleFightExp() + 100, encounterBuilder.getMonsterOwner()),
        encounterBuilder.isMixedTypes(), encounterBuilder.getHoleFightExp() + 100);
  }

  public List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterBuilder encounterBuilder){
    List<GeneratedMonsterDTO> generatedMonsterDTOS = new ArrayList<>();
    generateEncounter(encounterBuilder).forEach(monster -> {
      GeneratedMonsterDTO generatedMonsterDTO = MonsterMapper.INSTANCE.monsterToMonsterDto(monster);
      generatedMonsterDTO.setMonsterPath(path.concat(monster.getMonsterName()));
          generatedMonsterDTOS.add(generatedMonsterDTO);
    });

    return generatedMonsterDTOS;
  }
}
