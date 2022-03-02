package by.paul.monsterservice.service.dto;

import by.paul.monsterservice.entity.GeneratedMonsterDTO;
import by.paul.monsterservice.entity.Monster;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MonsterMapper {

  MonsterMapper INSTANCE = Mappers.getMapper(MonsterMapper.class);

  GeneratedMonsterDTO monsterToMonsterDto(Monster monster);

}
