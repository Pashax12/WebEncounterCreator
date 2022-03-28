package by.paul.monsterservice.service.monster;

import by.paul.monsterservice.entity.DifficultyEnum;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ExpCounter {

  public int getHoleFightExp(List<String> playersLevel, String difficulty) {
    return parseInteger(playersLevel).stream()
        .mapToInt(integer -> DifficultyEnum.valueOf(difficulty).getExpLevel(integer)).sum();
  }

  private List<Integer> parseInteger(List<String> playersLevel) {
    return playersLevel.stream().map(s -> Integer.parseInt(s.split(" ")[0]))
        .collect(Collectors.toList());
  }
}
