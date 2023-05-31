package vip.maosi.weddingserver.dto;

import lombok.Data;
import vip.maosi.weddingserver.domain.Activity;
import vip.maosi.weddingserver.domain.ActivityPrize;

import java.util.List;

@Data
public class ActivityInfoDto extends Activity {
    private List<ActivityPrize> prizeList;
}
