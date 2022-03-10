package by.paul.monsterservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import lombok.Data;

@Data
public class ArticleDTO {

  @JsonProperty("title")
  private String title;
  @JsonProperty("introduction")
  private String introduction;
  @JsonProperty("uploadDate")
  private Date uploadDate;
}
