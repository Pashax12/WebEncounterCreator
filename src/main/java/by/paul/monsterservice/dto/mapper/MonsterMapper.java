package by.paul.monsterservice.dto.mapper;

import by.paul.monsterservice.dto.GeneratedMonsterDTO;
import by.paul.monsterservice.entity.Monster;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MonsterMapper {

  MonsterMapper INSTANCE = Mappers.getMapper(MonsterMapper.class);

  GeneratedMonsterDTO monsterToMonsterDto(Monster monster);
}
