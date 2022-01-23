package by.paul.springbootrestservice.monster.controller;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.service.creator.UserMonsterCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/UserMonsterCreator")
@RequiredArgsConstructor
public class UserMonsterCreator {

  private final UserMonsterCreatorService userMonsterCreatorService;

  @PostMapping("/addUserMonster")
  public void createEncounter(@RequestBody Monster monster) {
    userMonsterCreatorService.addMonster(monster);
    //обмазать статусами и проверками
  }

  @GetMapping("/searchMonster")
  public List<GeneratedMonsterDTO> getAllAuthorMonster(@RequestParam(value = "author") String authorName){
    return userMonsterCreatorService.getAllAuthorMonster(authorName);
  }


}
