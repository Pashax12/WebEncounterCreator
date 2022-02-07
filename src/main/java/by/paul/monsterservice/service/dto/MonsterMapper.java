package by.paul.monsterservice.service.dto;

import by.paul.monsterservice.entity.Monster;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MonsterMapper {

  MonsterMapper INSTANCE = Mappers.getMapper(MonsterMapper.class);

  GeneratedMonsterDTO monsterToMonsterDto(Monster monster);

  @AfterMapping
  default void setMonsterPath(@MappingTarget GeneratedMonsterDTO generatedMonsterDTO,
      Monster monster) {

    if (generatedMonsterDTO.monsterName.contains(" ")) {
      generatedMonsterDTO.setMonsterPath(
          "http://localhost:8080/MonsterLibrary/getMonster/" + monster.getMonsterName()
              .replace(" ", "_"));
    } else {
      generatedMonsterDTO.setMonsterPath(
          "http://localhost:8080/MonsterLibrary/getMonster/" + monster.getMonsterName());
    }

  }

}
