package by.paul.springbootrestservice.monster.controller;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.service.MonsterLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/MonsterLibrary")
public class MonsterLibrary {

  private final MonsterLibraryService monsterLibraryService;

  @PostMapping("/postMonsters")
  public List<Monster> getMonsters(@RequestBody SearcherCriteria searcherCriteria) {
    return monsterLibraryService.getMonsterFromLibrary(searcherCriteria);
  }
}
