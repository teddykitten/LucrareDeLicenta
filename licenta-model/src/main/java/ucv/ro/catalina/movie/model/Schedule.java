package ucv.ro.catalina.movie.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Schedule {
    private LocalDateTime date;
    private Hall hall;
    private Map<Schedule, Film> movieTime;

}
