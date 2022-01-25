package by.paul.springbootrestservice.monster.entity.dto;

import by.paul.springbootrestservice.monster.entity.Monster;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter {

  public List<GeneratedMonsterDTO> convertMonsterDtoToMonster(List<Monster> monsters) {

    return monsters.stream().map(MonsterMapper.INSTANCE::monsterToMonsterDto)
        .collect(Collectors.toList());
  }
}
