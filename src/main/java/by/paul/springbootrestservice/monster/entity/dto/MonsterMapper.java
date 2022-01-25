package by.paul.springbootrestservice.monster.entity.dto;

import by.paul.springbootrestservice.monster.entity.Monster;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MonsterMapper {

  MonsterMapper INSTANCE = Mappers.getMapper(MonsterMapper.class);

  @Mapping(target = "monsterPath", expression = "java(\"http://localhost:8080/MonsterLibrary/getMonster/\" + monster.getMonsterName())")
  GeneratedMonsterDTO monsterToMonsterDto(Monster monster);

}
