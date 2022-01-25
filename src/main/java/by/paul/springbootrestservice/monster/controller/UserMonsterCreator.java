package by.paul.springbootrestservice.monster.controller;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.service.creator.UserMonsterCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
