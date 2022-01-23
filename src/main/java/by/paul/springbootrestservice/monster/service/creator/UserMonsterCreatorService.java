package by.paul.springbootrestservice.monster.service.creator;

import by.paul.springbootrestservice.monster.dao.MonsterRepository;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.entity.dto.MonsterMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMonsterCreatorService {

  @Value("${url.path.monsterLibrary}")
  private String path;
  private final MonsterRepository monsterRepository;

  public void addMonster(Monster monster) {
    monster.setMonsterName("Homebrew: ".concat(monster.getMonsterName()));
    monsterRepository.save(monster);
  }

  public List<GeneratedMonsterDTO> getAllAuthorMonster(String authorName) {
    List<GeneratedMonsterDTO> generatedMonsterDTOS = new ArrayList<>();
    monsterRepository.findAllByMonsterOwnerIsContainingOrderByMonsterId(authorName).forEach(monster -> {
      GeneratedMonsterDTO generatedMonsterDTO = MonsterMapper.INSTANCE.monsterToMonsterDto(monster);
      generatedMonsterDTO.setMonsterPath(path.concat(monster.getMonsterName()));
      generatedMonsterDTOS.add(generatedMonsterDTO);
    });

    return generatedMonsterDTOS;

  }
}
