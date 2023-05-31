package vip.maosi.weddingServer.dto;

import lombok.Data;
import vip.maosi.weddingServer.domain.Activity;
import vip.maosi.weddingServer.domain.ActivityPrize;

import java.util.List;

@Data
public class ActivityInfoDto extends Activity {
    private List<ActivityPrize> prizeList;
}
