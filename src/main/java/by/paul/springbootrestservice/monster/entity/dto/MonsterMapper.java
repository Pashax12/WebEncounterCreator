package by.paul.springbootrestservice.monster.entity.dto;

import by.paul.springbootrestservice.monster.entity.Monster;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MonsterMapper {

  MonsterMapper INSTANCE = Mappers.getMapper(MonsterMapper.class);

  @Mapping(target = "monsterPath", source = "monsterPic")
  GeneratedMonsterDTO monsterToMonsterDto(Monster monster);

}
