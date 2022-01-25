package by.paul.springbootrestservice.monster.controller;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.entity.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.service.library.MonsterLibraryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/MonsterLibrary")
public class MonsterLibrary {

  private final MonsterLibraryService monsterLibraryService;

   @PostMapping("/postMonsters")
   public List<Monster> getMonsters(@RequestBody SearcherCriteria searcherCriteria) {
     return monsterLibraryService.getMonsterFromLibrary(searcherCriteria);
   }
  @GetMapping("/getMonster/{path}")
  public Monster getMonsterByName(@PathVariable String path) {
    return monsterLibraryService.getMonsterFromLibrary(path);
  }

  @GetMapping("/searchMonster")
  public List<GeneratedMonsterDTO> getAllAuthorMonster(@RequestParam(value = "author") String authorName){
    return monsterLibraryService.getAllAuthorMonster(authorName);
  }

}
