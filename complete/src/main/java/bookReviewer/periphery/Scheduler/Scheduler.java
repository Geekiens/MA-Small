package bookReviewer.periphery.Scheduler;

import bookReviewer.adapter.in.scheduler.ScheduleAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    @Qualifier("ScheduleAdapterService")
    ScheduleAdapter scheduleAdapter;

    @Scheduled(fixedRate = 30000)
    public void acquireModerators() {
        System.out.println("Aquire new mods");
        scheduleAdapter.checkForUserPromotions();
    }
}
